--Finds the title, editionNumber, and copyright of all titles with copyright after 2000. 
SELECT
title,
editionNumber,
copyright
FROM titles
WHERE copyright >= 2000;  GO 
--Finds authorID, firstName, and lastName from the authors whose last name contains I as the second letter.  SELECT
authorID,
firstName,
lastName
FROM authors
WHERE lastName LIKE '_i%'; 
GO
--Finds isbn, title, editionNumber, copyright, and price of titles whose titles end with “how to program” in ascending order by title.  SELECT
isbn,
title,
editionNumber,
copyright,
price
FROM titles
WHERE title LIKE '% How to Program'
ORDER BY title; 
GO
--Finds firstName, lastName, and isbn for the authors and the books they have written in ascending order by lastName and firstName  SELECT
firstName,
lastName,
t.isbn,
title
FROM authors AS a INNER JOIN authorISBN AS aISBN ON a.authorID = aISBN.authorID
INNER JOIN titles AS t ON aISBN.isbn = t.isbn
ORDER BY lastName, firstName; 
GO
--How many books were copyright in 2001.  SELECT COUNT(copyright) AS num_of_copyright
FROM titles
WHERE copyright = 2001; 
GO
--Finds the name of books which have. published by Prentice Hall PTG.  SELECT title
FROM titles
WHERE publisherID IN (
SELECT publisherID
FROM publishers
WHERE publisherName = 'Prentice Hall PTG'
);
GO
--Finds the author names who've the books with the highest price.  SELECT
firstName,
lastName,
t.isbn,
title
FROM authors AS a INNER JOIN authorISBN AS aISBN ON a.authorID = aISBN.authorID
INNER JOIN titles AS t ON aISBN.isbn = t.isbn
WHERE price IN (
SELECT MAX(price)
FROM titles
);
GO
--Finds name of authors who've published more than 2 books.  SELECT
aISBN.authorID,
firstName,
lastName,
COUNT(aISBN.authorID) AS num_published_book
FROM authors AS a INNER JOIN authorISBN AS aISBN ON a.authorID = aISBN.authorID
GROUP BY aISBN.authorID, firstName, lastName
HAVING COUNT(aISBN.authorID) > 2;
GO