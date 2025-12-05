
// demonstrates bubble sort
// to run this program: C>java BubbleSortApp
////////////////////////////////////////////////////////////////
class ArrayBub {
   private long[] a; // ref to array a
   private int nElems; // number of data items
   private int nSwaps; // number of swaps
   private int nComparison;
   // --------------------------------------------------------------

   public ArrayBub(int max) // constructor
   {
      a = new long[max]; // create the array
      nElems = 0; // no items yet
      nSwaps = 0; // no swaps yet
      nComparison = 0;
   }

   // --------------------------------------------------------------
   public void insert(long value) // put element into array
   {
      a[nElems] = value; // insert it
      nElems++; // increment size
   }

   // --------------------------------------------------------------
   public void display() // displays array contents
   {
      for (int j = 0; j < nElems; j++) // for each element,
         System.out.print(a[j] + " "); // display it
      System.out.println("");
   }

   // --------------------------------------------------------------
   public void bubbleSort() {
      int out, in;

      for (out = nElems - 1; out > 1; out--) // outer loop (backward)
         for (in = 0; in < out; in++) {// inner loop (forward)
            nComparison++;
            if (a[in] > a[in + 1]) // out of order?
               swap(in, in + 1); // swap them
            }
   } // end bubbleSort()
     // --------------------------------------------------------------

   private void swap(int one, int two) {
      long temp = a[one];
      a[one] = a[two];
      a[two] = temp;

      nSwaps++; // increase number of swap by 1
   }

   public int getSwapNumber() {
      return nSwaps;
   }

   public int getnComparison() {
       return nComparison;
   }
   // --------------------------------------------------------------
} // end class ArrayBub
  ////////////////////////////////////////////////////////////////

class BubbleSortApp {
   public static void main(String[] args) {
      int maxSize = 100; // array size
      ArrayBub arr; // reference to array
      arr = new ArrayBub(maxSize); // create the array

      arr.insert(77); // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);
      System.out.print("Items before sorting: ");      
      arr.display(); // display items
      System.out.print("Items after sorting: ");
      arr.bubbleSort(); // bubble sort them

      arr.display(); // display them again

      // display the number of swaps
      System.out.println("The number of swaps = " + arr.getSwapNumber());
      System.out.println("The number of comparison = " + arr.getnComparison());
      System.out.println("The number of comparison after each loop in this sort algorithm is (n-1)--.\nBy taking the sum of them, we have a theoretical formula for complexity n(n-1)/2.\nHowever, in the output, the number of comparison is n(n-1)/2 - 1 since we skip the step 'out = 0'.");
      System.out.println("If we modified the sort method to have the check sorted array condition, we can achieve the best case, where the array is already sorted in a desired order. The time complexity is n for running only the outer loop");
      
   } // end main()
} // end class BubbleSortApp
  ////////////////////////////////////////////////////////////////
