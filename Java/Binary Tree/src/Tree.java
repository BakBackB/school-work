// -------------------------------------------------------------
// Representing arithmetic expressions by binary tree
// CS 501 
// Zdravko Markov
// -------------------------------------------------------------

class Tree
{
  public static void main(String[] args)
  {
      Node a = node(2);
      Node b = node(3);
      Node c = node('+',a,b);
      Node d = node(5);
      Node e = node(1);
      Node f = node('-',d,e);
      Node g = node('*',c,f);
      Node h = node(8);
      Node i = node('/',g,h);   

//    Node i = node('/',node('*',node('+',node(2),node(3)),node('-',node(5),node(1))),node(8));
      
      System.out.println("Tree:");
      showTree(0,i);
      System.out.print("Prefix: ");
      prefix(i);
      System.out.print("\nPostfix: ");
      postfix(i);
      System.out.print("\nInfix: ");
      infix(i);
      System.out.println("\nValue: "+eval(i));
      System.out.println("Number of leaves: " + countNode (i));
  }
// -------------------------------------------------------------
   public static Node node (char op, Node l, Node r)
   {
      Node a = new Node();
      a.operation=op;
      a.leftChild=l;
      a.rightChild=r;
      return a;
   }
// -------------------------------------------------------------
   public static Node node (int val)
   {
      Node a = new Node();
      a.value=val;
      return a;
   }

// -------------------------------------------------------------      
   public static void prefix (Node t)
   {
      if (t.leftChild==null && t.rightChild==null) 
            System.out.print(t.value+" ");
      else
      {
          System.out.print(t.operation+" ");
          prefix(t.leftChild);
          prefix(t.rightChild);
      }            
   }
// -------------------------------------------------------------      
   public static void postfix (Node t)
   {
      if (t.leftChild==null && t.rightChild==null) 
            System.out.print(t.value+" ");
      else
      {
          postfix(t.leftChild);
          postfix(t.rightChild);
          System.out.print(t.operation+" ");
      }            
   }
// -------------------------------------------------------------      
   public static void infix (Node t)
   {
      if (t.leftChild==null && t.rightChild==null) 
            System.out.print(t.value);
      else
      {
          System.out.print("(");
          infix(t.leftChild);
          System.out.print(t.operation);
          infix(t.rightChild);
          System.out.print(")");
      }            
   }
// -------------------------------------------------------------      
   public static double eval (Node t)
   {
      double val=0;
      if (t.leftChild==null && t.rightChild==null) 
          val = t.value;
      else
          switch(t.operation)
          {
            case '+':
                val = eval(t.leftChild) + eval(t.rightChild);
                break;
            case '-':
                val = eval(t.leftChild) - eval(t.rightChild);
                break;
            case '*':
                val = eval(t.leftChild) * eval(t.rightChild);
                break;
            case '/':
                val = eval(t.leftChild) / eval(t.rightChild);
          }      
          return val;
   }
// -------------------------------------------------------------      
   public static void showTree (int n, Node t)
   {
       tab(n);
       if (t.leftChild==null && t.rightChild==null) 
            System.out.println(t.value);
       else
       {
           System.out.println(t.operation);
           showTree(n+2,t.leftChild);
           showTree(n+2,t.rightChild);
       }
   }
// -------------------------------------------------------------      
   public static void tab(int n)
   {
       for (int i=0;i<n;i++) System.out.print(" ");
   }

   public static int countNode(Node root) {
      if(root == null) return 0;
      return 1 + countNode(root.leftChild) + countNode (root.rightChild);
   }

   public static int countLeaves(Node root) {
      if(root == null) return 0;
      if(root.leftChild == null && root.rightChild == null) return 1;
      return countLeaves(root.leftChild) + countLeaves (root.rightChild);
   }

   public static int treeHeight(Node root) {
      if(root == null) return 0;
      int leftHeight = treeHeight (root.leftChild);
      int rightHeight = treeHeight (root.rightChild);
      return 1 + Math.max(leftHeight, rightHeight);
   }

   public static boolean isBalanced(Node root) {
      if(root == null) return true;
      int leftHeight = treeHeight (root.leftChild);
      int rightHeight = treeHeight (root.rightChild);
      if(Math.abs(leftHeight - rightHeight) > 1) return false;
      return isBalanced(root.leftChild) && isBalanced(root.rightChild);
   }
   public static boolean areIdentical (Node t1, Node t2){
    if (t1 == null && t2 == null)
        return true;

    if (t1 == null || t2 == null)
        return false;
    boolean t1_is_leaf = (t1.leftChild == null && t1.rightChild == null);
    boolean t2_is_leaf = (t2.leftChild == null && t2.rightChild == null);
    if (t1_is_leaf && t2_is_leaf) {
        return (t1.value == t2.value);
    } 
    else if (!t1_is_leaf && !t2_is_leaf) {
        // So sánh phép toán và đệ quy kiểm tra các cây con
        return (t1.operation == t2.operation) && 
               areIdentical(t1.leftChild, t2.leftChild) && 
               areIdentical(t1.rightChild, t2.rightChild);
    } 
    else {
        return false;
    }
}
}
// -------------------------------------------------------------

class Node
{
   char operation;
   int value;
   Node leftChild;
   Node rightChild;
}
