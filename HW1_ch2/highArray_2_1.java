/********************************************************************************
**********************Program Projects 2.1***************************************
To the HighArray class in the highArray.java program (Listing 2.3), add a
method called getMedian() that returns the value of the highest key in the array,
or –1 if the array is empty. Add some code in main() to exercise this method.
You can assume all the keys are positive numbers.
**********************************************************************************/


// highArray.java
// demonstrates array class with high-level interface
// to run this program: C>java HighArrayApp
////////////////////////////////////////////////////////////////
class HighArray
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
   //-----------------------------------------------------------
   public HighArray(int max)         // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
   //-----------------------------------------------------------
   public boolean find(long searchKey)
      {                              // find specified value
      int j;
      for(j=0; j<nElems; j++)            // for each element,
         if(a[j] == searchKey)           // found item?
            break;                       // exit loop before end
      if(j == nElems)                    // gone to end?
         return false;                   // yes, can't find it
      else
         return true;                    // no, found it
      }  // end find()
   //-----------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
   //-----------------------------------------------------------
   public boolean delete(long value)
      {
      int j;
      for(j=0; j<nElems; j++)        // look for it
         if( value == a[j] )
            break;
      if(j==nElems)                  // can't find it
         return false;
      else                           // found it
         {
         for(int k=j; k<nElems; k++) // move higher ones down
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
   //assignment 2.1 getMedian()
   public long getMedian() {
	   long medianVal;
	   int pos;
	   if((nElems%2)==0) {                   //if the total elements are even
		   pos=nElems/2;
		medianVal=(a[pos]+a[pos-1])/2;
	   }
	   else {                                //if the total elements are odd
		   pos=(nElems+1)/2;
		   medianVal=a[pos-1];		   
	   }
	   System.out.println("The median value of the array is "+medianVal);
	   return medianVal;	   
   }
   //-----------------------------------------------------------
   public void sorting() {
	   int min;
	   for(int i=0;i<nElems;i++) {
		   min=i;
		   for(int j=i+1;j<nElems;j++) {
			   if(a[j]<a[min])
				   min=j;			   			   
		   }
		   long temp = a[i];
		   a[i] = a[min];
		   a[min] = temp;		   		   
	   }
   }
   }  // end class HighArray
////////////////////////////////////////////////////////////////
class HighArrayApp
   {
   public static void main(String[] args)
      {
      int maxSize = 100;            // array size
      HighArray arr;                // reference to array
      arr = new HighArray(maxSize); // create the array

      arr.insert(77);               // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      //arr.insert(33);

      arr.display();                // display items

      int searchKey = 35;           // search for item
      if( arr.find(searchKey) )
         System.out.println("Found " + searchKey);
      else
         System.out.println("Can't find " + searchKey);
      
      arr.sorting();
      arr.display(); 
      arr.getMedian();

      arr.delete(00);               // delete 3 items
      arr.delete(55);
      arr.delete(99);

      arr.display();                // display items again
      }  // end main()
   }  // end class HighArrayApp
