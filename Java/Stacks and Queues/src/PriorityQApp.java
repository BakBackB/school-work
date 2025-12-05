// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class PriorityQ<E extends Comparable<E>>
   {
   // array in sorted order, from max at 0 to min at size-1
   private int maxSize;
   private int nItems;
   private int processingTime;
   private Object[] e;
//-------------------------------------------------------------
   public PriorityQ(int s)          // constructor
      {
      maxSize = s;
      this.e = new Object[maxSize];
      nItems = 0;
      }
//-------------------------------------------------------------
   public void insert(E item)    // insert item
      {
      int j;

      if(nItems==0)                         // if no items,
         e[nItems++] = item;         // insert at 0
      else                                // if items,
         {
         for(j=nItems-1; j>=0; j--)         // start at end,
            {
            if( item.compareTo((E)e[j]) > 0)      // if new item larger,
               e[j+1] = e[j]; // shift upward
            else                          // if smaller,
               break;                     // done shifting
            }  // end for
         e[j+1] = item;            // insert it
         nItems++;
         }  // end else (nItems > 0)
      }  // end insert()
   public void display() {
      System.out.println("The array priority queue: ");
      for(int i = 0; i < nItems; i++){
         System.out.print(e[i] + " ");
      }
      System.out.println();
   }
//-------------------------------------------------------------
   public E remove()             // remove minimum item
      { return (E)e[--nItems]; }
//-------------------------------------------------------------
   public E peekMin()            // peek at minimum item
      { return (E)e[nItems-1]; }
//-------------------------------------------------------------
   public boolean isEmpty()         // true if queue is empty
      { return (nItems==0); }
//-------------------------------------------------------------
   public boolean isFull()          // true if queue is full
      { return (nItems == maxSize); }

   public int getProcessingTime() {
      return processingTime;
   }

   public void setProcessingTime(int processingTime) {
      this.processingTime = processingTime;
   }
      //-------------------------------------------------------------
   }  // end class PriorityQ
////////////////////////////////////////////////////////////////
class PriorityQApp
   {
   public static void main(String[] args)
      {
      PriorityQ<Integer> thePQ = new PriorityQ<>(5);
      Customer customer = new Customer(0);
      thePQ.insert(30);
      thePQ.insert(50);
      thePQ.insert(10);
      thePQ.insert(40);
      thePQ.insert(20);

      while( !thePQ.isEmpty() )
         {
         int item = thePQ.remove();
         System.out.print(item + " ");  // 10, 20, 30, 40, 50
         }  // end while
      System.out.println("");
      System.out.println("For inserting at the rear using Priority Queue, it is less efficient than basic Queue since its insertion also sorts the queue.");
         PriorityQ<Customer> customerQueue = new PriorityQ<>(5);
         final int NUM_CUSTOMERS = 5;
         long cashierTime = 0, start = 0, depart = 0, totalTime = 0, count = NUM_CUSTOMERS - 1;
         double rateOfCustomer = 0;
         customerQueue.setProcessingTime(50);
         for(int i = 0; i < NUM_CUSTOMERS; i++) {
            customerQueue.insert(new Customer(15*i));
         }

         while(!customerQueue.isEmpty()) {
            customer = customerQueue.remove();
            System.out.println("Arrival time of the customer " + (NUM_CUSTOMERS - count) + ": " + customer.getArrivalTime());
            count--;
            if(customer.getArrivalTime() > cashierTime) {
               start = customer.getArrivalTime();
            } else {
               start = cashierTime;
            }
            depart = start + customerQueue.getProcessingTime();
            customer.setDepartureTime(depart);
            cashierTime = depart;
            totalTime += customer.getTotalTime();
            System.out.println("Time from waiting to done being served: " + customer.getTotalTime() + " seconds");

         }
         rateOfCustomer = (double)60*NUM_CUSTOMERS/totalTime;
         System.out.println("The total time for processing all the customers is " + totalTime + " seconds");
         System.out.println("If the range of time processing is more narrowed than the arrival time, the waiting time + processing time only takes processing time into account, meaning the customers do not have to wait for the queue");
         System.out.println("The rate at which customers arrive at the queue is " + rateOfCustomer + " customer/minute");
         System.out.println("For larger processing time, the rate mentioned rate decreases, which mean the time at which the customers arrive at the queue is prolonged");
      }  // end main()
//-------------------------------------------------------------
   }  // end class PriorityQApp
////////////////////////////////////////////////////////////////
