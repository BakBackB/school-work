import java.util.PriorityQueue;

public class HuffmanTree {
    public static void main(String[] args) {
        Tree huffmanTree = new Tree();
        String s = "I am a student at International University. My name is TRAN MINH PHUC. "
        + "I am working on a DSA lab.";
        int[] freqArray = new int[256];
        for (char c : s.toCharArray()) {
            freqArray[c]++;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        for (int i = 0; i < 256; i++) {
            if (freqArray[i] > 0) {
                Node newNode = new Node();
                newNode.cData = (char) i;
                newNode.freq = freqArray[i];
                pq.add(newNode);
            }
        }
        for (int i = 0; i < 256; i++) {
            if (freqArray[i] > 0) {
                System.out.println("'" + (char) i + "': " + freqArray[i]);
            }
        }
        for (Node item : pq) {
            System.out.print(item.cData + " ");
        }
        System.out.println();
        for (Node item : pq) {
            System.out.print(item.freq + " ");
        }
        System.out.println();
        while (pq.size() > 1) {
            Node left = pq.poll();  
            Node right = pq.poll();
            Node parent = new Node();
            parent.cData = '*';
            parent.freq = left.freq + right.freq;
            parent.leftChild = left;
            parent.rightChild = right;
            pq.add(parent);
        }

        huffmanTree.root = pq.poll(); 
        huffmanTree.showTree(0, huffmanTree.root);
    }
}
class NodeComparator implements java.util.Comparator<Node> {
    @Override
    public int compare(Node x, Node y) {
        return x.freq - y.freq;
    }
}
class Node
   {
   public char cData;              // data item (key)
   public int freq;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(cData);
      System.out.print(", ");
      System.out.print(freq);
      System.out.print("} ");
      }
   }  // end class Node
////////////////////////////////////////////////////////////////
class Tree
   {
   Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
       public void showTree (int n, Node t)
   {
       tab(n);
       if (t==null)
           System.out.println("*");
       else 
       {
           n=n+3;
           System.out.println(t.cData);
           if (t.leftChild==null && t.rightChild==null) return;
           showTree(n,t.leftChild);
           showTree(n,t.rightChild);
       }
   }
   public static void tab(int n)
   {
       for (int i=0;i<n;i++) System.out.print(" ");
   }
    }