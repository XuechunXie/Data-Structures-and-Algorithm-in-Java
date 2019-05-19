/*******************************************************************************
************************Program Project 12.5**************************************
Write a program that implements the tree heap (the tree-based implementation
of the heap) discussed in the text. Make sure you can remove the largest item,
insert items, and change an item’s key.
********************************************************************************/
// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp
import java.io.*;
import java.util.Stack;
////////////////////////////////////////////////////////////////
class Node
   {
   private int iData;             // data item (key)
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   public Node parent;				//this node's parent
// -------------------------------------------------------------
   public Node(int key)           // constructor
      { iData = key; }
// -------------------------------------------------------------
   public int getKey()
      { return iData; }
// -------------------------------------------------------------
   public void setKey(int id)
      { iData = id; }
// -------------------------------------------------------------
   }  // end class Node
////////////////////////////////////////////////////////////////
class Heap
   {
   private int currentSize;       // number of nodes in array
   public Node root;
   private int[] path;
// -------------------------------------------------------------
   public Heap(int maxSize)            // constructor
      {
	  path=new int[maxSize];			//path[] to store the path for the node
      currentSize = 0;
      root=null;
      }
// -------------------------------------------------------------
   public boolean isEmpty()
      { return currentSize==0; }
// -------------------------------------------------------------
   public boolean insert(int key)
      {
	   int nthNode;
	   int j=0;
	   Node current=root,previous=root;
	   Node newNode = new Node(key);
	   if(root==null) {			//if heap is empty
		   root=newNode;			
		   currentSize++;  
		   return true;
	   }
	   currentSize++;
	   nthNode=currentSize;
	   while(nthNode>=1) {				//if nthNode>=1
		   path[j++]=nthNode%2;			//calculate the path and store it
		   nthNode=nthNode/2;
	   }
	   for(int i=j-2;i>0;i--) {
		   if(path[i]==0)				//if path=0
			   current=current.leftChild;		//goes to leftchild
		   else
			   current=current.rightChild;		//goes to rightchild
	   }
	   previous=current;
	   if(path[0]==0) {			   
		   current.leftChild=newNode;
		   current=current.leftChild;	   
	   }else { 
		   current.rightChild=newNode;
		   current =current.rightChild;
	   }
	   current.parent=previous;
      trickleUp(current);
      return true;
      }  // end insert()
// -------------------------------------------------------------
   public void trickleUp(Node current)
      {
	   int temp=current.getKey();
	   while(current.parent!=null && temp>current.parent.getKey()) {		   
		   current.setKey(current.parent.getKey());
		   current=current.parent;
	   }
	   current.setKey(temp);	   
      }  // end trickleUp()
// -------------------------------------------------------------
   public Node remove()           // delete item with max key
      {                           // (assumes non-empty list)
	   Node top=root;
	  int nthNode=currentSize;
	  int j=0;
	  Node current=root;
	   while(nthNode>=1) {
		   path[j++]=nthNode%2;
		   nthNode=nthNode/2;
	   }
	   for(int i=j-2;i>-1;i--) {
		   if(path[i]==0) 
			   current=current.leftChild;	   
		   else
			   current=current.rightChild;
	   }	   
	   root.setKey(current.getKey());
	   if(path[0]==1)
		   current.parent.rightChild=null;
	   else
		   current.parent.leftChild=null;
	   if(currentSize>1)
		   trickleDown(root);
	   currentSize--;
      return top;
      }  // end remove()
// -------------------------------------------------------------
   public void trickleDown(Node node)
      {
	   int temp=node.getKey();
	   Node largerChild;
	   Node current=node;
	   
	   while(current.leftChild!=null || current.rightChild!=null) {
		   if(current.rightChild!=null&&
				   current.rightChild.getKey()>current.leftChild.getKey()) {
			   largerChild=current.rightChild;			   
		   }else
			   largerChild=current.leftChild;		
		   if(temp>largerChild.getKey())
			   break;
		   current.setKey(largerChild.getKey());
		   current=largerChild;
	   }
	   current.setKey(temp);
      }  // end trickleDown()
// -------------------------------------------------------------
   
   public boolean change(int index, int newValue)
      {  
	  int j=0,temp;
	  Node current=root; 
      if(index<0 || index>currentSize)
         return false;

	   while(index>=1) {
		   path[j++]=index%2;
		   index=index/2;
	   }
	   for(int i=j-2;i>-1;i--) {
		   if(path[i]==0) 
			   current=current.leftChild;	   
		   else
			   current=current.rightChild;
	   }
	   temp=current.getKey();
	   current.setKey(newValue);
	   if(temp>newValue)
		   trickleDown(current);
	   else
		   trickleUp(current);
	   return true;
      }  // end change()
      
   public void inOrder(Node localRoot)
   {
   if(localRoot != null)
      {
      inOrder(localRoot.leftChild);
      System.out.print(localRoot.getKey() + " ");
      inOrder(localRoot.rightChild);
      }
   }
   // -------------------------------------------------------------
   public void displayHeap()
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
               System.out.print(temp.getKey());
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
   }  // end class Heap
////////////////////////////////////////////////////////////////
class HeapApp
   {
   public static void main(String[] args) throws IOException
      {
      int value, value2;
      Heap theHeap = new Heap(31);  // make a Heap; max size 31
      boolean success;

      theHeap.insert(70);           // insert 10 items
      theHeap.insert(40);
      theHeap.insert(50);
      theHeap.insert(20);
      theHeap.insert(60);
      theHeap.insert(100);
      theHeap.insert(80);
      theHeap.insert(30);
      theHeap.insert(10);
      theHeap.insert(90);

      while(true)                   // until [Ctrl]-[C]
         {
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, remove, change: ");
         int choice = getChar();
         switch(choice)
            {
            case 's':                        // show
               theHeap.displayHeap();
               break;
            case 'i':                        // insert
               System.out.print("Enter value to insert: ");
               value = getInt();
               success = theHeap.insert(value);
               if( !success )
                  System.out.println("Can't insert; heap full");
               break;
            case 'r':                        // remove
               if( !theHeap.isEmpty() )
                  theHeap.remove();
               else
                  System.out.println("Can't remove; heap empty");
               break;
            case 'c':                        // change
               System.out.print("Enter current index of item: ");
               value = getInt();
               System.out.print("Enter new key: ");
               value2 = getInt();
               success = theHeap.change(value, value2);
               if( !success )
                  System.out.println("Invalid index");
               break;
            default:
               System.out.println("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
//-------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
//-------------------------------------------------------------
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
//-------------------------------------------------------------
  }  // end class HeapApp
////////////////////////////////////////////////////////////////
