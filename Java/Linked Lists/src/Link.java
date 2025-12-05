public class Link<E>
   {
   public E dData;                // data item
   public Link<E> next;                 // next link in list
// -------------------------------------------------------------
   public Link(E d)               // constructor
      { dData = d; }
// -------------------------------------------------------------
   public void displayLink()         // display this link
      { System.out.print(dData + " "); }
// -------------------------------------------------------------
   }  // end class LinkList2App.LinkStackApp.Link
////////////////////////////////////////////////////////////////