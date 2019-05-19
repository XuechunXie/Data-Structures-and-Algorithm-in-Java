/*******************************************************************************************
/**********************************Program Projects 4.4**************************************
The priority queue shown in Listing 4.6 features fast removal of the high-priority
item but slow insertion of new items. Write a program with a revised
PriorityQ class that has fast O(1) insertion time but slower removal of the highpriority
item. Include a method that displays the contents of the priority
queue, as suggested in Programming Project 4.1.
********************************************************************************************/
// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class PriorityQ
   {
   // array in sorted order, from max at 0 to min at size-1
   private int maxSize;
   private long[] queArray;
   private int nItems;
//-------------------------------------------------------------
   public PriorityQ(int s)          // constructor
      {
      maxSize = s;
      queArray = new long[maxSize];
      nItems = 0;
      }
//-------------------------------------------------------------
   public long remove()    // insert item
      {
      int min;
      long temp;

      if(nItems==0)                         // if no items,
         return -1;                        // no remove
      else                                // if items,
         {
    	  min= nItems-1;
         for(int j=nItems-2; j>=0; j--)         // start at end-1
            {
            if( queArray[min] > queArray[j])      // if new value large
            	min = j; 							//get index of the min value
            }  
         
         temp= queArray[min];                //store the min value to temp
         for(int i=min;i<nItems-1;i++) {
        	 queArray[i]=queArray[i+1];    //move the elements below 
         }
         nItems--;
         return temp;
         }  // end else (nItems > 0)
      }  // end insert()

//-------------------------------------------------------------
   /*
   public long remove()             // remove minimum item
      { return queArray[--nItems]; }
   */
//-------------------------------------------------------------
//-------------------------------------------------------------
   public void insert(long item)             
      {  
	   queArray[nItems]=item;     // Insert item to the top
	   nItems++;
	   }
 //-------------------------------------------------------------
 //-------------------------------------------------------------
   public void display()             
      {  
	   if(nItems==0) {
		   System.out.println("The queue is empty");
	   }
	   else {
		   System.out.println("The items in queue are:"); 
		   for(int i=0;i<nItems;i++) {
			  System.out.println(queArray[i]); 
		   }			   
	   }	   	   
	   }
 //-------------------------------------------------------------
   public long peekMin()            // peek at minimum item
      { return queArray[nItems-1]; }
//-------------------------------------------------------------
   public boolean isEmpty()         // true if queue is empty
      { return (nItems==0); }
//-------------------------------------------------------------
   public boolean isFull()          // true if queue is full
      { return (nItems == maxSize); }
//-------------------------------------------------------------
   }  // end class PriorityQ
////////////////////////////////////////////////////////////////
class PriorityQApp
   {
   public static void main(String[] args)
      {
      PriorityQ thePQ = new PriorityQ(5);
      thePQ.insert(30);
      thePQ.insert(50);
      thePQ.insert(10);
      thePQ.insert(40);
      thePQ.insert(20);
      thePQ.display();
      
      System.out.println("The item removed is showed as below:"); 
      while( !thePQ.isEmpty() )
         {
         long item = thePQ.remove();
         System.out.print(item + " ");  // 10, 20, 30, 40, 50
         }  // end while
      System.out.println("");
      thePQ.display();
      }  // end main()
   	  
//-------------------------------------------------------------
   }  // end class PriorityQApp
////////////////////////////////////////////////////////////////
