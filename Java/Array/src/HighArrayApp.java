// highArray.java
// demonstrates array class with high-level interface
// to run this program: C>java HighArrayApp
////////////////////////////////////////////////////////////////
import java.util.Random;
class HighArray
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   private int counter_find = 0;
   //-----------------------------------------------------------
   public HighArray(int max)         // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
   //-----------------------------------------------------------
   public boolean find(long searchKey)
      {                              // find specified value
      int j;
      for(j=0; j<nElems; j++) {            // for each element,
         counter_find++;
         if(a[j] == searchKey)           // found item?
            break;                       // exit loop before end
      }    
      if(j == nElems)                    // gone to end?
         return false;                   // yes, can't find it
      else
         return true;                    // no, found it
      }  // end find()
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
   //-----------------------------------------------------------
   public boolean delete(long value)
      {
      int j;
      for(j=0; j<nElems; j++)        // look for it
         if( value == a[j] )
            break;
      if(j==nElems)                  // can't find it
         return false;
      else                           // found it
         {
         for(int k=j; k<nElems; k++) // move higher ones down
            a[k] = a[k+1];
         nElems--;                   // decrement size
         return true;
         }
      }  // end delete()
   //-----------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
   //-----------------------------------------------------------
   public long getMax() {
      if ( nElems == 0) {
         return -1;
      }
      int maxIndex = 0;
      for (int i = 1; i < nElems; i++) {
         if (a[i] > a[maxIndex]) {
            maxIndex = i;
         }
      }
      return a[maxIndex];
   }
   //-----------------------------------------------------------
   public void noDups() {
      for (int i = 0; i < nElems; i++) {
         long current = a[i];
         for (int j = i + 1; j < nElems; ) {
            if (a[j] == current) {
               delete(a[j]);
            } else {
               j++;
            }
         }
      }
   }
   //-----------------------------------------------------------
   public int getSize() {
      return nElems;
   }
   //-----------------------------------------------------------
   public int getCounterFind() {
      return counter_find;
   }
   //-----------------------------------------------------------
   public void resetCounterFind() {
      counter_find = 0;
   }
}
   
   // end class HighArray
////////////////////////////////////////////////////////////////
class HighArrayApp
   {
   public static void main(String[] args)
      {
      Random random = new Random();
      int maxSize;           // array size
      int searchKey = 53;           // search for item

      for(maxSize = 100; maxSize <= 1000; maxSize += 100) {
         HighArray arr = new HighArray(maxSize); 
         for (int i = 1; i <= maxSize; i++) {
            arr.insert(random.nextInt(1, maxSize + 1));   
         }
         System.out.println("Number of items in the array: " + maxSize);
         System.out.print("Searching for " + searchKey + ". ");
         if( arr.find(searchKey) ) {
            System.out.println("Found " + searchKey);
            System.out.println("Number of comparisons: " + arr.getCounterFind() + " in an array of size " + maxSize);
            System.out.println("Average number of comparisons for " + maxSize + " trials: " + (double)arr.getCounterFind() / maxSize);

         }
         else
            System.out.println("Can't find " + searchKey);
         
         
         arr.display();                // display items again
      }  // end main()

      // arr_200 = new HighArray(200);
      // for (int i = 0; i < arr.getSize(); i++) {
      //    arr_200.insert(random.nextInt(1, 201));
      // }  // end main()
      // if(arr_200.find(searchKey)) {
      //    System.out.println("Found " + searchKey + " in the array of size 200");
      //    System.out.println("Number of comparisons: " + arr_200.getCounterFind());
      //    System.out.println("Average number of comparisons for 200 trials: " + (double)arr_200.getCounterFind() / 200 );
      // } else {
      //    System.out.println("Can't find " + searchKey + " in the array of size 200");
      // }

      // for (; maxSize <= 1000; i++) {
      //    arr.insert(random.nextInt(1, maxSize + 1));
      // }  // end main()
      // if(arr_200.find(searchKey)) {
      //    System.out.println("Found " + searchKey + " in the array of size 200");
      //    System.out.println("Number of comparisons: " + arr_200.getCounterFind());
      //    System.out.println("Average number of comparisons for 200 trials: " + (double)arr_200.getCounterFind() / 200 );
      // } else {
      //    System.out.println("Can't find " + searchKey + " in the array of size 200");
      // }
   }  
}   // end class HighArrayApp
