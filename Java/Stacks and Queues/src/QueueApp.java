// Queue.java
// demonstrates queue
// to run this program: C>java QueueApp

import java.util.Arrays;
import java.util.EmptyStackException;

////////////////////////////////////////////////////////////////
class Queue<E>
   {
   private final int maxSize;
   private int front;
   private int rear;
   private int nItems;
   private int countRemove = 0;
   private int processingTime;
   private E[] e;
//--------------------------------------------------------------
   public Queue(int s)          // constructor
      {
      maxSize = s;
      this.e = (E[]) new Object[maxSize];
      front = 0;
      rear = -1;
      nItems = 0;
      }

//--------------------------------------------------------------
   public void insert(E j)   // put item at rear of queue
      {
      if(nItems == maxSize) {
         throw new StackOverflowError();
      } else {
         if (rear == maxSize - 1)         // deal with wraparound
            rear = -1;
         e[++rear] = j;         // increment rear and insert
         nItems++;                     // one more item
         }
      }
//--------------------------------------------------------------
   public E remove()         // take item from front of queue
      {
      if(nItems == 0) {
         throw new EmptyStackException();
      }
      E temp = e[front++]; // get value and incr front
      if(front == maxSize)           // deal with wraparound
         front = 0;
      nItems--;                      // one less item
      countRemove++;
      return temp;
      }
//--------------------------------------------------------------
   public E peekFront()      // peek at front of queue
      {
      return e[front];
      }
//--------------------------------------------------------------
   public boolean isEmpty()    // true if queue is empty
      {
      return (nItems==0);
      }
//--------------------------------------------------------------
   public boolean isFull()     // true if queue is full
      {
      return (nItems==maxSize);
      }
//--------------------------------------------------------------
   public int size()           // number of items in queue
      {
      return nItems;
      }
//--------------------------------------------------------------
   public String arrayPrint() {
      System.out.println("Front = " + front + ", rear = " + rear + ", number of items = " + nItems);
      return Arrays.toString(e);
   }
//--------------------------------------------------------------
   public String displayQueue() {
      StringBuilder s = new StringBuilder();
      if(front == rear) {
         System.out.println("Front = " + front + ", rear = " + rear +
                 ", number of items = " + nItems);
         s.append(peekFront());
      } else {
         int temp = front;
         System.out.println("Front = " + front + ", rear = " + rear +
                 ", number of items = " + nItems);
         for (int i = 0; i < nItems; i++) {
            s.append(peekFront());
            s.append(' ');
            front++;
            if (front == maxSize) {
               front = 0;
            }
         }
         front = temp;
      }
      return s.toString();
   }
//--------------------------------------------------------------
   public E removeByCounting() {
      E temp = null;
      for(int i = 0; i < countRemove; i++) {
         temp = remove();
      }
      return temp;
   }
//--------------------------------------------------------------

   public int getProcessingTime() {
      return processingTime;
   }

      public void setProcessingTime(int processingTime) {
         this.processingTime = processingTime;
      }

      //--------------------------------------------------------------
   }  // end class Queue
////////////////////////////////////////////////////////////////
class QueueApp
   {
   public static void main(String[] args)
      {
      Queue<Integer> theQueue = new Queue<>(5);  // queue holds 5 items
      Customer customer = new Customer(0);
      theQueue.insert(10);            // insert 4 items
      theQueue.insert(20);
      theQueue.insert(30);
      theQueue.insert(40);
      System.out.println("The initialized queue is derived from the basic array:\n " + theQueue.arrayPrint());
      theQueue.remove();              // remove 3 items
      theQueue.remove();              //    (10, 20, 30)
      theQueue.remove();
      System.out.println("After removing some elements, the array becomes: " + theQueue.arrayPrint());
      theQueue.insert(50);            // insert 4 more items
      theQueue.insert(60);            //    (wraps around)
      theQueue.insert(70);
      theQueue.insert(80);
      System.out.println("After insertion, the array becomes: " + theQueue.arrayPrint());
      System.out.println("We have the final queue: " + theQueue.displayQueue());
      theQueue.insert(100);
      Queue<Customer> customerQueue = new Queue<>(5);
      final int NUM_CUSTOMERS = 5;
      long cashierTime = 0, start = 0, depart = 0, totalTime = 0, count = NUM_CUSTOMERS - 1;
      double rateOfCustomer = 0;
      customerQueue.setProcessingTime(50);
      for(int i = 0; i < NUM_CUSTOMERS; i++) {
         customerQueue.insert(new Customer(15*i));
      }
      
      while(!customerQueue.isEmpty()) {
         customer = customerQueue.remove();
         System.out.println("Arrival time of the customer "
                 + (NUM_CUSTOMERS - count) + ": " + customer.getArrivalTime());
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
         System.out.println("Time from waiting to done being served: "
                 + customer.getTotalTime() + " seconds");

      }
      rateOfCustomer = (double)60*NUM_CUSTOMERS/totalTime;
      System.out.println("The total time for processing all the customers is "
              + totalTime + " seconds");
      System.out.println("If the range of time processing is more narrowed than" +
              " the arrival time, the waiting time + " +
              "processing time only takes processing time into account, " +
              "meaning the customers do not have to wait for the queue");
      System.out.println("The rate at which customers arrive at the queue is "
              + rateOfCustomer + " customer/minute");
      System.out.println("For larger processing time, the rate mentioned decreases" +
              ", which mean the time at which the customers arrive at the queue is prolonged");
      }  // end main()
   }  // end class QueueApp
////////////////////////////////////////////////////////////////
