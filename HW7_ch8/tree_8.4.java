/*********************************************************************************
***************************Program Project 8.4*************************************
Write a program that transforms a postfix expression into a tree such as that
shown in Figure 8.11 in this chapter. You’ll need to modify the Tree class from
the tree.java program (Listing 8.1) and the ParsePost class from the
postfix.java program (Listing 4.8) in Chapter 4. There are more details in the
discussion of Figure 8.11.
After the tree is generated, traversals of the tree will yield the prefix, infix, and
postfix equivalents of the algebraic expression. The infix version will need
parentheses to avoid generating ambiguous expressions. In the inOrder()
method, display an opening parenthesis before the first recursive call and a
closing parenthesis after the second recursive call.
*********************************************************************************/
// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
   {
	//xuechun:change iData from int to char to adjust postfix
   public char iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(iData);
      System.out.print(", ");
      System.out.print(dData);
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
   public Node find(char key)      // find node with given key
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
   public void insert(char id, double dd)
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
      }  // end insert()
// -------------------------------------------------------------
   public boolean delete(char key) // delete node with given key
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
                 inOrder(root,false);
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
   private void inOrder(Node localRoot,boolean isLeft)
      {
	   
      if(localRoot != null) {
    	  //xuechun:add for prefix expression tree
    	 System.out.print("("+ " ");
         inOrder(localRoot.leftChild,true);
         System.out.print(localRoot.iData + " ");
         inOrder(localRoot.rightChild,false);
         //xuechun:add for prefix expression tree
         System.out.print(")"+ " ");     
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
//----------------------------------------------------------------
//---------xuechun: start to transform the postfix into a tree----
   public void doParse(String s)
	{
		Stack theStack = new Stack();
		char ch;
		int j;
		Node operand1, operand2;
	
		for(j=0; j<s.length(); j++)       // for each char,
		{
			ch = s.charAt(j);              // read from input
			if(ch >= '0' && ch <= '9') {         // if it's a number
				 Node operand = new Node();    // make new node
				 operand.iData=ch;
				 operand.dData=(double)ch;
				 theStack.push(operand);       // push it into stack				
			}
			else                               // it's an operator
			{
				operand1 = (Node)theStack.pop();          // pop operands
				operand2 = (Node)theStack.pop();
				switch(ch)                      // build the tree if it is operator
				{
				case '+':
				case '-':
				case '*':
				case '/':
					Node operator=new Node();		//new node for operator
					operator.iData=ch;
					operator.dData=(double)ch;
					operator.leftChild=operand2;    //add the operand to be the child of operator
					operator.rightChild=operand1;
					theStack.push(operator);		//push the subtree into stact
					break;
				default:
					break;
				}  // end switch
			}  // end else
		}  // end for
		root =(Node) theStack.pop();            // the last pop would be a the tree required
		return  ;
	}  // end doParse()
//---------xuechun: end to transform the postfix into a tree----   
// -------------------------------------------------------------
   }  // end class Tree
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
class TreeApp
   {
   /**
 * @param args
 * @throws IOException
 */
public static void main(String[] args) throws IOException
      {
      char value;
      int t;
      String input;
      Tree theTree = new Tree();

//      theTree.insert(50, 1.5);
//      theTree.insert(25, 1.2);
//      theTree.insert(75, 1.7);
//      theTree.insert(12, 1.5);
//      theTree.insert(37, 1.2);
//      theTree.insert(43, 1.7);
//      theTree.insert(30, 1.5);
//      theTree.insert(33, 1.2);
//      theTree.insert(87, 1.7);
//      theTree.insert(93, 1.5);
//      theTree.insert(97, 1.5);
      
      while(true)
         {
    	  
    	  //xuechun: added to transform the postfix into a tree
          System.out.print("Enter postfix: ");
          System.out.flush();
          input = getString();         // read a string from kbd
          if( input.equals("") )       // quit if [Enter]
             break;                                       	  
         theTree.doParse(input);	// make a parser 
         
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
               value = getChar();
               theTree.insert(value, value + 0.9);
               break;
            case 'f':
               System.out.print("Enter value to find: ");
               value = getChar();
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
               value = getChar();
               boolean didDelete = theTree.delete(value);
               if(didDelete)
                  System.out.print("Deleted " + value + '\n');
               else
                  System.out.print("Could not delete ");
                  System.out.print(value + '\n');
               break;
            case 't':
               System.out.print("Enter type 1, 2 or 3: ");
               t = getInt();
               theTree.traverse(t);
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
