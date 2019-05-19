/********************************************************************************
*************************Project Promgram 2.4************************************
Modify the orderedArray.java program (Listing 2.4) so that the insert() and
delete() routines, as well as find(), use a binary search, as suggested in the
text.
*******************************************************************************/
// orderedArray.java
// demonstrates ordered array class
// to run this program: C>java OrderedApp
////////////////////////////////////////////////////////////////
class OrdArray
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   //-----------------------------------------------------------
   public OrdArray(int max)          // constructor
      {
      a = new long[max];             // create array
      nElems = 0;
      }
   //-----------------------------------------------------------
   public int size()
      { return nElems; }
   //-----------------------------------------------------------
   public int find(long searchKey)
      {
      int lowerBound = 0;
      int upperBound = nElems-1;
      int curIn;

      while(true)
         {
         curIn = (lowerBound + upperBound ) / 2;
         if(a[curIn]==searchKey)
            return curIn;              // found it
         else if(lowerBound > upperBound)
            return nElems;             // can't find it
         else                          // divide range
            {
            if(a[curIn] < searchKey)
               lowerBound = curIn + 1; // it's in upper half
            else
               upperBound = curIn - 1; // it's in lower half
            }  // end else divide range
         }  // end while
      }  // end find()
   //-----------------------------------------------------------
   //added by xuechun xie to use Binary search to find the insert position
   public int findInsertPos(long searchKey)
      {
      int lowerBound = 0;
      int upperBound = nElems-1;
      int curIn;

      while(true)
         {
         curIn = (lowerBound + upperBound ) / 2;
         if(a[curIn]<searchKey&&a[curIn+1]>searchKey)
            return curIn+1;
         else if(curIn==lowerBound) {         //when nElems=0,1,2, curIn=0
        	 if(lowerBound>upperBound)             //when nElems=0,upperBound=-1
        		 return lowerBound;                  
        	 else if(lowerBound==upperBound) {   //when nElems=1
            	 if(a[curIn]<=searchKey)
            		 return curIn+1;              //new element should be inserted behind the first element if searchkey is greater 
            	 else 
            		 return curIn;       		//new element should be inserted in front of the first element if searchkey is smaller                     		 
        	 }else {                              //when nElems=2
        		 if(a[lowerBound]>searchKey)
        			 return lowerBound;
        		 else if(a[upperBound]<searchKey)
        			 return upperBound+1;
        		 else
        			 return upperBound;
        	 }
        }else {                         // divide range
            if(a[curIn] < searchKey)
               lowerBound = curIn + 1; // it's in upper half
            else
               upperBound = curIn - 1; // it's in lower half
            }  // end else divide range
         }  // end while
      }  // end find()
   //-----------------------------------------------------------   
   public void insert(long value)    // put element into array
      {
	   /*
	   int j;
      for(j=0; j<nElems; j++)        // find where it goes
         if(a[j] > value)            // (linear search)
            break;
            */
      int j=findInsertPos(value);     //added by xuechun to use Binary search to find the insert position
      for(int k=nElems; k>j; k--)    // move bigger ones up
         a[k] = a[k-1];
      a[j] = value;                  // insert it
      nElems++;                      // increment size
      }  // end insert()
   //-----------------------------------------------------------
   public boolean delete(long value)
      {
      int j = find(value);
      if(j==nElems)                  // can't find it
         return false;
      else                           // found it
         {
         for(int k=j; k<nElems; k++) // move bigger ones down
            a[k] = a[k+1];
         nElems--;                   // decrement size
         return true;
         }
      }  // end delete()
   //-----------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
   //-----------------------------------------------------------
   }  // end class OrdArray
////////////////////////////////////////////////////////////////
class OrderedApp
   {
   public static void main(String[] args)
      {
      int maxSize = 100;             // array size
      OrdArray arr;                  // reference to array
      arr = new OrdArray(maxSize);   // create the array

      arr.insert(77);                // insert 10 items      
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      //arr.display();
      arr.insert(88);
      arr.insert(11);
      arr.insert(11);
      //arr.display();
      arr.insert(00);
      arr.insert(66);
      arr.display();
      arr.insert(33);
 
    

      int searchKey = 88;            // search for item
      if( arr.find(searchKey) != arr.size() )
         System.out.println("Found " + searchKey);
      else
         System.out.println("Can't find " + searchKey);

      arr.display();                 // display items

      arr.delete(00);                // delete 3 items
      arr.delete(55);
      arr.delete(99);

      arr.display();                 // display items again
      }  // end main()
   }  // end class OrderedApp
