import java.util.NoSuchElementException;
public class LinkQueue<E>
   {
   private final FirstLastList<E> theList;
   private int callCounter = 0;
   private int processingTime;
   private final int maxSize;
   private int nElems = 0;
//--------------------------------------------------------------
   public LinkQueue(int maxSize)                // constructor
      { theList = new FirstLastList<>(); this.maxSize = maxSize;}  // make a 2-ended list
//--------------------------------------------------------------
   public boolean isEmpty()          // true if queue is empty
      { return theList.isEmpty(); }
//--------------------------------------------------------------
   public void insert(E j)        // insert, rear of queue
      { theList.insertLast(j); nElems++;}
//--------------------------------------------------------------
   public E remove()              // remove, front of queue
      {  nElems--; return theList.deleteFirst();}
   public int size() {
        return nElems;
   }
   public E customRemove() {
      callCounter++;
      if(isEmpty()) {
         throw new NoSuchElementException("Queue is empty");
      }
      return removeTheNthElement(callCounter);
   }

   public E removeTheNthElement(int n) {
      int i = 0;
      LinkQueue<E> tempQueue = new LinkQueue<>(maxSize);
      while(!isEmpty() && i < n - 1) {
         tempQueue.insert(remove());
         i++;
      }
      for(int j = 0; j < i; j++) {
         insert(tempQueue.remove());
      }
      return remove();
   }
//--------------------------------------------------------------
   public void displayQueue()
      {
      System.out.print("Queue (front-->rear): ");
      theList.displayList();
      }
//--------------------------------------------------------------
    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
}