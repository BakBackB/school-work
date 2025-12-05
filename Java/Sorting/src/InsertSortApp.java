// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   private int nPass = 0, totalPass = 0;
//--------------------------------------------------------------
   public ArrayIns(int max)          // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
//--------------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
//--------------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
//--------------------------------------------------------------
   public void insertionSort()
      {
      int in, out;

      for(out=1; out<nElems; out++)     // out is dividing line
         {
         long temp = a[out];            // remove marked item
         in = out;
         totalPass++;                   // start shifts at out
         while(in>0 && a[in-1] >= temp) // until one is smaller,
            {
            totalPass++;
            nPass++;
            a[in] = a[in-1];            // shift item to right
            --in;                       // go left one position
            }
         a[in] = temp;                  // insert marked item
         
         }  // end for
      }  // end insertionSort()
   public int getnPass() {
       return nPass;
   }
   public int getTotalPass() {
       return totalPass;
   }
//--------------------------------------------------------------
   }  // end class ArrayIns
////////////////////////////////////////////////////////////////
class InsertSortApp
   {
   public static void main(String[] args)
      {
      int maxSize = 100;            // array size
      ArrayIns arr;                 // reference to array
      arr = new ArrayIns(maxSize);  // create the array

      arr.insert(77);               // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);

      arr.display();                // display items

      arr.insertionSort();          // insertion-sort them
      System.out.println("Number of pass in the inner loops (Number of copies) = " + arr.getnPass());
      System.out.println("Total number of pass (Both inner and outer loops) = " + arr.getTotalPass());
      System.out.println("The number of pass in the inner loop is 1 + 2 + 3 + ... + n - 1 = n(n-1)/2 in the worst case, where the array is already sorted in reversed order. However, in the average case, where the array is half sorted, the time complexity is n(n-1)/4");
      }  // end main()
   }  // end class InsertSortApp
