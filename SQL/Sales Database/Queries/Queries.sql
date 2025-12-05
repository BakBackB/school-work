USE SalesOrdersModify;
GO

--Show me all the orders shipped on October 3, 2017, and each order’s related customer last name.

SELECT
	OrderNumber,
	CustomerID,
	(SELECT CustLastName
	FROM Customers
	WHERE CustomerID = o.CustomerID
	) AS CustomerLastName
FROM Orders AS o
WHERE ShipDate = '2017-10-3';
GO

--List all the customer names and a count of the orders they placed.

SELECT
	CustomerID,
	CustFirstName,
	CustLastName,
	(SELECT
		COUNT(OrderNumber)
		FROM Orders
		WHERE c.CustomerID = CustomerID
		GROUP BY CustomerID
	) AS OrdersPlaced
FROM Customers AS c;
GO

--List customers and all the details from their last order.

SELECT
    c.CustomerID,
    c.CustFirstName,
    c.CustLastName,
    lo.OrderNumber,
    lo.OrderDate AS LastOrderDate,
    d.ProductNumber,
    d.QuotedPrice,
    d.QuantityOrdered
FROM Customers AS c
LEFT JOIN (
    SELECT o.CustomerID, o.OrderNumber, o.OrderDate
    FROM Orders o
    WHERE o.OrderDate = (
        SELECT MAX(OrderDate)
        FROM Orders o2
        WHERE o2.CustomerID = o.CustomerID
    )
) AS lo
    ON lo.CustomerID = c.CustomerID
LEFT JOIN Order_Details AS d
    ON d.OrderNumber = lo.OrderNumber;
GO

--Find all accessories that are priced greater than any clothing item. (hint use ALL)

SELECT
	ProductNumber,
	ProductName,
	RetailPrice
FROM Products
WHERE CategoryID IN (
	SELECT CategoryID
	FROM Categories
	WHERE CategoryDescription = 'Accessories'
)
AND RetailPrice >  ALL(
	SELECT
		RetailPrice
		FROM Products
	WHERE CategoryID IN (
		SELECT CategoryID
		FROM Categories
		WHERE CategoryDescription = 'Clothing'
	)
);
GO

--Find all the customers who ordered a bicycle. (Use EXISTS) 

SELECT
	c.CustomerID AS Bicycle_CustomerID,
	c.CustFirstName,
	c.CustLastName
FROM Customers AS c
WHERE EXISTS (
	SELECT 1
	FROM Orders AS o
	WHERE c.CustomerID = o.CustomerID
	AND EXISTS (
		SELECT 1
		FROM Order_Details AS od
		WHERE o.OrderNumber = od.OrderNumber
		AND EXISTS (
			SELECT 1
			FROM Products AS p
			WHERE od.ProductNumber = p.ProductNumber
			AND EXISTS (
				SELECT 1
				FROM Categories AS cat
				WHERE p.CategoryID = cat.CategoryID
 				AND CategoryDescription = 'Bikes'
			)
		)
	)
);
GO

--List customers who ordered bikes.(use IN) 

SELECT
	CustomerID AS Bicycle_CustomerID,
	CustFirstName,
	CustLastName
FROM Customers
WHERE  CustomerID IN (
	SELECT CustomerID
	FROM Orders
	WHERE OrderNumber IN (
		SELECT OrderNumber
		FROM Order_Details
		WHERE ProductNumber IN (
			SELECT ProductNumber
			FROM Products
			WHERE CategoryID IN (
				SELECT CategoryID
				FROM Categories AS cat
				WHERE CategoryDescription = 'Bikes'
			)
		)
	)
);
GO

--Display customers who ordered clothing or accessories

SELECT
	CustomerID AS Cloth_Acces_CusID,
	CustFirstName,
	CustLastName
FROM Customers
WHERE CustomerID = SOME (
	SELECT CustomerID
	FROM Orders
	WHERE OrderNumber = SOME (
		SELECT OrderNumber
		FROM Order_Details
		WHERE ProductNumber = SOME (
			SELECT ProductNumber
			FROM Products
			WHERE CategoryID = SOME (
				SELECT CategoryID
				FROM Categories
				WHERE CategoryDescription = 'Clothing' OR CategoryDescription = 'Accessories'
			)
		)
	)
);
GO

--Find all the customers who ordered a bicycle helmet.

SELECT
	CustomerID AS Bicycle_Helmet_CusID,
	CustFirstName,
	CustLastName
FROM Customers
WHERE CustomerID IN (
	SELECT CustomerID
	FROM Orders
	WHERE OrderNumber IN (
		SELECT OrderNumber
		FROM Order_Details
		WHERE ProductNumber IN (	
			SELECT ProductNumber
			FROM Products
			WHERE ProductName LIKE '%Helmet%'
		)
	)
);
GO

--What products have never been ordered?

SELECT
	ProductNumber,
	ProductName AS Not_Ordered_Product
FROM Products
WHERE ProductNumber NOT IN (
	SELECT ProductNumber
	FROM Order_Details
);
GO

--List vendors and a count of the products they sell to us.

SELECT 
	VendorID,
	(SELECT VendName
	FROM Vendors
	WHERE Vendors.VendorID = Product_Vendors.VendorID
	) AS VendName,
	COUNT(ProductNumber) AS Sold_To_Us
FROM Product_Vendors
GROUP BY VendorID;
GO

--Display customers who ordered clothing or accessories

SELECT
	CustomerID AS Cloth_Acces_CusID,
	CustFirstName,
	CustLastName
FROM Customers
WHERE CustomerID = ANY (
	SELECT CustomerID
	FROM Orders
	WHERE OrderNumber = ANY (
		SELECT OrderNumber
		FROM Order_Details
		WHERE ProductNumber = ANY (
			SELECT ProductNumber
			FROM Products
			WHERE CategoryID = ANY (
				SELECT CategoryID
				FROM Categories
				WHERE CategoryDescription = 'Clothing' OR CategoryDescription = 'Accessories'
			)
		)
	)
);
GO

--Display products and the latest date each product was ordered. (Hint: Use the MAX aggregate function.) (40 rows).

SELECT TOP 40
	ProductName,
	(SELECT MAX(OrderDate)
	FROM Orders AS o
	WHERE o.OrderNumber IN (
		SELECT OrderNumber
		FROM Order_Details AS od
		WHERE od.ProductNumber = p.ProductNumber
		)
	) AS Latest_Date
FROM Products AS p;
GO

--Calculate a total of all unique wholesale costs for the products we sell. (use SUM)

SELECT SUM(DISTINCT WholesalePrice) AS Total_Cost
FROM Product_Vendors;
GO

--What is the average item total for order 64?

SELECT AVG(QuotedPrice * QuantityOrdered) AS Average_Total
FROM Order_Details
WHERE OrderNumber = 64;
GO

--Calculate an average of all unique product prices.

SELECT AVG(DISTINCT RetailPrice) AS Average_Product_Price
FROM Products;
GO

--What is the lowest price we charge for a product?

SELECT MIN(RetailPrice) AS Lowest_Product_Price
FROM Products;
GO

--How many different products were ordered on order number 553, and what was the total
--cost of that order? (use SUM and COUNT)

SELECT
	OrderNumber,
	COUNT(Ordernumber) AS Number_Ordered,
	SUM(QuotedPrice * QuantityOrdered) AS Total_Price
FROM Order_Details
GROUP BY OrderNumber
HAVING OrderNumber = 553;
GO

--List the product names and numbers that have a quoted price greater than or equal to the
--overall average retail price in the products table.

SELECT
	p.ProductNumber,
	p.ProductName,
	od.QuotedPrice,
	(SELECT AVG(RetailPrice)
	FROM Products) AS Average_Retail
FROM Products AS p INNER JOIN Order_Details AS od
ON p.ProductNumber = od.ProductNumber
WHERE QuotedPrice >= (
	SELECT AVG(RetailPrice)
	FROM Products
);
GO

--What is the average retail price of a mountain bike?

SELECT
	ProductNumber,
	ProductName,
	AVG(RetailPrice) AS Average_Retail_Price
FROM Products
WHERE CategoryID IN (
	SELECT CategoryID
	FROM Categories
	WHERE CategoryDescription = 'Bikes'
)
GROUP BY ProductNumber, ProductName
ORDER BY Average_Retail_Price;
GO

--What was the date of our most recent order?

SELECT 
	OrderNumber,
	MAX(OrderDate) AS Latest_Order_Date
FROM Orders
GROUP BY OrderNumber;
GO

--What was the total amount for order number 8?

SELECT COUNT(OrderNumber) AS Total_Order
FROM Order_Details
WHERE OrderNumber = 8;
GO

--Show me each vendor and the average by vendor of the number of days to deliver
--products. (Hint: Use the AVG aggregate function and group on vendor.)

SELECT 
	v.VendorID,
	v.VendName,
	AVG(pv.DaysToDeliver) AS Average_Deliver
FROM Vendors AS v INNER JOIN Product_Vendors AS pv
ON v.VendorID = pv.VendorID
GROUP BY v.VendorID, v.VendName;
GO

--My clothing supplier just announced a price increase of 4 percent. Update the price of the clothing products and add 4 percent. (use UPDATE)

UPDATE Product_Vendors
SET WholesalePrice = 1.04*WholesalePrice
WHERE ProductNumber IN (
	SELECT ProductNumber
	FROM Products
	WHERE CategoryID IN (
		SELECT CategoryID
		FROM Categories
		WHERE CategoryDescription = 'Clothing'
	)
);
GO

--Modify products by increasing the retail price by 4 percent for products that are
--clothing. (use UPDATE)

UPDATE Products
SET RetailPrice = RetailPrice + 0.04*RetailPrice
WHERE CategoryID IN (
	SELECT CategoryID
	FROM Categories
	WHERE CategoryDescription = 'Clothing'
);
GO

--Change the orders table by setting the order total to the sum of quantity ordered times
--quoted price for all related order detail rows. (use UPDATE with subquery)

UPDATE Orders
SET OrderTotal = sub.OrderTotal
FROM Orders AS o INNER JOIN (
	SELECT OrderNumber, SUM(QuotedPrice*QuantityOrdered) AS OrderTotal
	FROM Order_Details AS od
	GROUP BY OrderNumber
) AS sub ON o.OrderNumber = sub.OrderNumber;
GO

--Reduce the quoted price by 2 percent for orders shipped more than 30 days after the
--order date. (use UPDATE with subquery)

UPDATE Order_Details
SET QuotedPrice = 0.98*QuotedPrice
WHERE OrderNumber IN (
	SELECT OrderNumber
	FROM Orders
	WHERE DATEDIFF(DAY, OrderDate, ShipDate) > 30
);
GO

--* Make sure the retail price for all bikes is at least a 45 percent markup over the
--wholesale price of the vendor with the lowest cost. (update and subsequent) - 1 row

UPDATE Products
SET RetailPrice = 1.45 * WholesalePrice
FROM Products AS p INNER JOIN Product_Vendors AS pv ON p.ProductNumber = pv.ProductNumber
WHERE WholesalePrice = (SELECT MIN(WholesalePrice) FROM Product_Vendors)
AND RetailPrice < (
	SELECT MIN(WholesalePrice) * 1.45
	FROM Product_Vendors
)
AND CategoryID IN (
	SELECT CategoryID
	FROM Categories
	WHERE CategoryDescription = 'Bikes'
);
GO

--Apply a 5 percent discount to all orders for customers who purchased more than
--$50,000 in the month of October 2017. (hint You need a subquery within a subquery to
--fetch the order numbers for all orders where the customer ID of the order is in the set of
--customers who ordered more than $50,000 in the month of October.) (639 rows changed)

UPDATE Orders
SET OrderTotal = 0.95 * OrderTotal
WHERE OrderNumber IN (
	SELECT OrderNumber
	FROM Orders
	WHERE CustomerID IN (
		SELECT CustomerID
		FROM Orders
		WHERE MONTH(OrderDate) = 10 AND YEAR(OrderDate) = 2017
		GROUP BY CustomerID
		HAVING SUM(OrderTotal) > 50000
	)
);
GO

--Set the retail price of accessories (category = 1) to the wholesale price of the highestpriced vendor plus 35 percent. (11 rows changed).

UPDATE Products
SET RetailPrice = (
	SELECT MAX(WholesalePrice) * 1.35
	FROM Product_Vendors
)
WHERE CategoryID = 1;
GO

--Copy to the Employees table the relevant columns in the Customers table for customer David Smith. (INSERT INTO)
INSERT INTO Employees(EmpFirstName, EmpLastName, EmpStreetAddress, EmpCity, EmpState, EmpZipCode, EmpAreaCode, EmpPhoneNumber)
SELECT CustFirstName, CustLastName, CustStreetAddress, CustCity, CustState, CustZipCode, CustAreaCode, CustPhoneNumber
FROM Customers
WHERE CustFirstName = 'David' AND CustLastName = 'Smith';
GO

--Add a new product named ‘Hot Dog Spinner’ with a retail price of $895 in the Bikes category. (INSERT INTO)

INSERT INTO Products(ProductName, RetailPrice, CategoryID)
VALUES('Hot Dog Spinner', 895, (SELECT CategoryID FROM Categories WHERE CategoryDescription = 'Bikes'));
GO