// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
   {
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   public boolean red;			//Xuechun:This node's color

   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(iData);
      System.out.print(", ");
      System.out.print(dData);
    //Xuechun: Print node's color
      System.out.print(", ");
      System.out.print(red);     
      System.out.print("} ");
      }
   }  // end class Node
////////////////////////////////////////////////////////////////
class Tree
   {
   private Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
      {                           // (assumes non-empty tree)
      Node current = root;               // start at root
      while(current.iData != key)        // while no match,
         {
         if(key < current.iData)         // go left?
            current = current.leftChild;
         else                            // or go right?
            current = current.rightChild;
         if(current == null)             // if no child,
            return null;                 // didn't find it
         }
      return current;                    // found it
      }  // end find()
// -------------------------------------------------------------
   
   /*public void insert(int id, double dd)
      {
      Node newNode = new Node();    // make new node
      newNode.iData = id;           // insert data
      newNode.dData = dd;
      if(root==null)                // no node in root
         root = newNode;
      else                          // root occupied
         {
         Node current = root;       // start at root
         Node parent;
         while(true)                // (exits internally)
            {
            parent = current;
            if(id < current.iData)  // go left?
               {
               current = current.leftChild;
               if(current == null)  // if end of the line,
                  {                 // insert on left
                  parent.leftChild = newNode;
                  return;
                  }
               }  // end if go left
            else                    // or go right?
               {
               current = current.rightChild;
               if(current == null)  // if end of the line
                  {                 // insert on right
                  parent.rightChild = newNode;
                  return;
                  }
               }  // end else go right
            }  // end while
         }  // end else not root
      }  // end insert()*/
   
 //---------------------------------------------------------------- 
 //---------Xuechun:Start insertion and RBTree rule checking---------
 //-------------------------------------------------------------------
   public void insert(int id, double dd) {
	   boolean isLeftChild=false;
	   boolean isPLeftChild=false;
	   boolean isGLeftChild=false;
	   Node n=new Node();
	   n.iData=id;
	   n.dData=dd;
	   n.red=true;
	  
	   if(root==null) {
		   root=n;
		   n.red=false;
	   }else {
		   Node current=root;
		   Node parent=root;
		   Node grandParent=root;
		   Node grandGrand=root;
		   while(true) {
			   //adjust the tree while on the way down
			   adjustAfterInsertion(current,parent,grandParent,grandGrand,isLeftChild,isPLeftChild,isGLeftChild);						
			   
			   grandGrand=grandParent;
			   grandParent=parent;
			   parent=current;
			   if(isPLeftChild)				//mark if grandparent a leftchild, used to decide after rotation the node should be connected to left or right
				   isGLeftChild=true;
			   else
				   isGLeftChild=false;
			   
			   if(isLeftChild)				//mark if grandparent a leftchild, used for outside/inside grandchild
				   isPLeftChild=true;
			   else
				   isPLeftChild=false;	 
			   if(n.iData<current.iData) {
				   current=current.leftChild;
				   isLeftChild=true;
				   if(current==null) {				//if it is the end, insert node
					   parent.leftChild=n;
					   current=n;
					   break ;
				   }				   
			   }else {
				   current=current.rightChild;	//if it is the end, insert node
				   isLeftChild=false;
				   if(current==null) {
					   parent.rightChild=n;
					   current=n;		   
					   break ;
				   }
			   }
		   
		   }
		   //adjust after insertion
		   if(parent.red)
			   adjustAfterInsertion(current,parent,grandParent,grandGrand,isLeftChild,isPLeftChild,isGLeftChild);
		   return ;

	   }
   }
 //---------------------------------------------------------------------------------------
   public void colorFlips(Node current) {
	   if(current.leftChild!=null&&current.rightChild!=null) {
		   if(current.leftChild.red==true&&current.rightChild.red==true) { //if both children exist and red
			   if(current!=root)			//switch parent to red if not root
				   current.red=true;
			   current.leftChild.red=false;		//switch children to black
			   current.rightChild.red=false;
		   }		   
	   }
   }
 //--------------------------------------------------------------------------------------------
//------------------check if violate rule 3-----------------------------------------------
   public boolean isRedRed(Node n1,Node n2) {   
	   if(n1.red==true&&n2.red==true)		//if both parent and child are red
		   return true;
	   else 
		   return false;
   }
 //----------------------------------------------------------------------------------------------  
   public void rotateLeft(Node rotate,Node parent,boolean isPLeftChild) {
	   if(rotate==root) {
		   root=rotate.rightChild;							//new root=the leftchild of the rotate node
		   if(rotate.rightChild.leftChild!=null)			//if crossover node exist
			   rotate.rightChild=rotate.rightChild.leftChild;  //connect crossover node to rotate.rightchild
		   else
			   rotate.rightChild=null;				// if crossover node null
		   root.leftChild=rotate;					//new root's leftchild is rotate
		   return ;
	   }else {
		   if(isPLeftChild) {
			   parent.leftChild=rotate.rightChild;
			   if(rotate.rightChild.leftChild!=null)
				   rotate.rightChild=rotate.rightChild.leftChild;       //crossover child will connect to roate note 
			   else
				   rotate.rightChild=null;
			   parent.leftChild.leftChild=rotate;	
			   return ;
		   }else {
			   parent.rightChild=rotate.rightChild; 
			   if(rotate.rightChild.leftChild!=null)
				   rotate.rightChild=rotate.rightChild.leftChild;       //crossover child will connect to roate note   
			   else
				   rotate.rightChild=null;
			   parent.rightChild.leftChild=rotate;
			   return ;
		   }
			   
	   }
   }
//---------------------------------------------------------------------------  
   public void rotateRight(Node rotate,Node parent,boolean isPLeftChild) {
	   if(rotate==root) {
		   root=rotate.leftChild;
		   if(rotate.leftChild.rightChild!=null)
			   rotate.leftChild=rotate.leftChild.rightChild;
		   else
			   rotate.leftChild=null;
		   root.rightChild=rotate;
		   return ;
	   }else {
		   if(isPLeftChild) {
			   parent.leftChild=rotate.leftChild;
			   if(rotate.leftChild.rightChild!=null)
				   rotate.leftChild=rotate.leftChild.rightChild;       //crossover child will connect to roate note  
			   else
				   rotate.leftChild=null;
			   parent.leftChild.rightChild=rotate;
			   return ;
		   }
		   else {
			   parent.rightChild=rotate.leftChild;
			   if(rotate.leftChild.rightChild!=null)
				   rotate.leftChild=rotate.leftChild.rightChild;       //crossover child will connect to roate note   
			   else
				   rotate.leftChild=null;
			   parent.rightChild.rightChild=rotate;
			   return ;
		   }
		  
			   
	   }
	   
   }
//-----------------------------------------------------------------------------------------------------
   public void adjustAfterInsertion(Node current,Node parent,Node grandParent,Node grandGrand,boolean isLeftChild,boolean isPLeftChild,boolean isGLeftChild) {
	   boolean violateR3=false;
	   if(!current.red) 
		   colorFlips(current);
	   violateR3=isRedRed(current,parent);
	   if(violateR3) {
		   if((isLeftChild&&isPLeftChild)||(!isLeftChild&&!isPLeftChild)) {					//if it is outside grandchild
			   if(grandParent.red)			//switch the color of grandParent
				   grandParent.red=false;
			   else
				   grandParent.red=true;
			   if(parent.red)				//switch the color of parent
				   parent.red=false;
			   else
				   parent.red=true;	
			   if(isLeftChild&&isPLeftChild)   //if leftchild, rotate right
				   rotateRight(grandParent,grandGrand,isGLeftChild);
			   else if(!isLeftChild&&!isPLeftChild)  //if right child, rotate left
				   rotateLeft(grandParent,grandGrand,isGLeftChild);
		   }else {
			   if(grandParent.red)			//switch the color of grandParent
				   grandParent.red=false;
			   else
				   grandParent.red=true;
			   if(current.red)			//switch the color of current,that is X
				   current.red=false;
			   else
				   current.red=true;
			   if(isLeftChild)
				   rotateRight(parent,grandParent,isPLeftChild);
			   else
				   rotateLeft(parent,grandParent,isPLeftChild);
			   if(isPLeftChild) 
				   rotateRight(grandParent,grandGrand,isGLeftChild);
			   else
				   rotateLeft(grandParent,grandGrand,isGLeftChild);
		   }				   
	   }				   					   
	   return ;
   }
//---------------------------------------------------------------------
//---------Xuechun:Start insertion and RBTree rule checking------------  
// --------------------------------------------------------------------
   public boolean delete(int key) // delete node with given key
      {                           // (assumes non-empty list)
      Node current = root;
      Node parent = root;
      boolean isLeftChild = true;

      while(current.iData != key)        // search for node
         {
         parent = current;
         if(key < current.iData)         // go left?
            {
            isLeftChild = true;
            current = current.leftChild;
            }
         else                            // or go right?
            {
            isLeftChild = false;
            current = current.rightChild;
            }
         if(current == null)             // end of the line,
            return false;                // didn't find it
         }  // end while
      // found node to delete

      // if no children, simply delete it
      if(current.leftChild==null &&
                                   current.rightChild==null)
         {
         if(current == root)             // if root,
            root = null;                 // tree is empty
         else if(isLeftChild)
            parent.leftChild = null;     // disconnect
         else                            // from parent
            parent.rightChild = null;
         }

      // if no right child, replace with left subtree
      else if(current.rightChild==null)
         if(current == root)
            root = current.leftChild;
         else if(isLeftChild)
            parent.leftChild = current.leftChild;
         else
            parent.rightChild = current.leftChild;

      // if no left child, replace with right subtree
      else if(current.leftChild==null)
         if(current == root)
            root = current.rightChild;
         else if(isLeftChild)
            parent.leftChild = current.rightChild;
         else
            parent.rightChild = current.rightChild;

      else  // two children, so replace with inorder successor
         {
         // get successor of node to delete (current)
         Node successor = getSuccessor(current);

         // connect parent of current to successor instead
         if(current == root)
            root = successor;
         else if(isLeftChild)
            parent.leftChild = successor;
         else
            parent.rightChild = successor;

         // connect successor to current's left child
         successor.leftChild = current.leftChild;
         }  // end else two children
      // (successor cannot have a left child)
      return true;                                // success
      }  // end delete()
// -------------------------------------------------------------
   // returns node with next-highest value after delNode
   // goes to right child, then right child's left descendents
   private Node getSuccessor(Node delNode)
      {
      Node successorParent = delNode;
      Node successor = delNode;
      Node current = delNode.rightChild;   // go to right child
      while(current != null)               // until no more
         {                                 // left children,
         successorParent = successor;
         successor = current;
         current = current.leftChild;      // go to left child
         }
                                           // if successor not
      if(successor != delNode.rightChild)  // right child,
         {                                 // make connections
         successorParent.leftChild = successor.rightChild;
         successor.rightChild = delNode.rightChild;
         }
      return successor;
      }
// -------------------------------------------------------------
   public void traverse(int traverseType)
      {
      switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         System.out.print(localRoot.iData + " ");
         preOrder(localRoot.leftChild);
         preOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         inOrder(localRoot.leftChild);
         System.out.print(localRoot.iData + " ");
         inOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         postOrder(localRoot.leftChild);
         postOrder(localRoot.rightChild);
         System.out.print(localRoot.iData + " ");
         }
      }
// -------------------------------------------------------------
   public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.iData);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println(
      "......................................................");
      }  // end displayTree()
// -------------------------------------------------------------
   }  // end class Tree
////////////////////////////////////////////////////////////////
class TreeApp
   {
   public static void main(String[] args) throws IOException
      {
      int value;
      Tree theTree = new Tree();

      theTree.insert(50, 1.5);
      theTree.insert(25, 1.2);
      theTree.insert(75, 1.7);
      theTree.insert(12, 1.5);
      theTree.insert(37, 1.2);
      theTree.insert(43, 1.7);
      theTree.insert(30, 1.5);
      theTree.insert(33, 1.2);
      theTree.insert(87, 1.7);
      theTree.insert(93, 1.5);
      theTree.insert(97, 1.5);

      while(true)
         {
         System.out.print("Enter first letter of show, ");
         System.out.print("insert, find, delete, or traverse: ");
         int choice = getChar();
         switch(choice)
            {
            case 's':
               theTree.displayTree();
               break;
            case 'i':
               System.out.print("Enter value to insert: ");
               value = getInt();
               theTree.insert(value, value + 0.9);
               break;
            case 'f':
               System.out.print("Enter value to find: ");
               value = getInt();
               Node found = theTree.find(value);
               if(found != null)
                  {
                  System.out.print("Found: ");
                  found.displayNode();
                  System.out.print("\n");
                  }
               else
                  System.out.print("Could not find ");
                  System.out.print(value + '\n');
               break;
            case 'd':
               System.out.print("Enter value to delete: ");
               value = getInt();
               boolean didDelete = theTree.delete(value);
               if(didDelete)
                  System.out.print("Deleted " + value + '\n');
               else
                  System.out.print("Could not delete ");
                  System.out.print(value + '\n');
               break;
            case 't':
               System.out.print("Enter type 1, 2 or 3: ");
               value = getInt();
               theTree.traverse(value);
               break;
            default:
               System.out.print("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
// -------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
// -------------------------------------------------------------
   public static char getChar() throws IOException
      {
      String s = getString();
      return s.charAt(0);
      }
//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
// -------------------------------------------------------------
   }  // end class TreeApp
////////////////////////////////////////////////////////////////
