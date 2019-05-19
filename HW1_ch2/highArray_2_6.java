/*************************************************************************************
***********************************program project 2.6******************************
Write a noDups() method for the HighArray class of the highArray.java
program (Listing 2.3). This method should remove all duplicates from the
array. That is, if three items with the key 17 appear in the array, noDups()
should remove two of them. Don’t worry about maintaining the order of the
items. One approach is to first compare every item with all the other items and
overwrite any duplicates with a null (or a distinctive value that isn’t used for
real keys). Then remove all the nulls. Of course, the array size will be reduced.
***************************************************************************************/
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
   public void noDups() {
	   int noDups=0;
	   for(int i=0;i<nElems;i++) {
		   int count=0;
		   for(int j=0;j<nElems;j++) {
			   if(a[j]==a[i]) {
				   count++;                       
				   if(count>1) {                   //mark the duplicate one from the second duplicate one
					   a[j]=-1;
					   noDups++;
				   }
			   }			   
		   }
	   }
	   
	   for(int k=0;k<noDups;k++) {
		   delete(-1);                                     //delete all the duplications which has already marked as -1
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

      arr.insert(55);               // insert 10 items
      arr.insert(66);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(55);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(55);

      arr.display();                // display items

      int searchKey = 35;           // search for item
      if( arr.find(searchKey) )
         System.out.println("Found " + searchKey);
      else
         System.out.println("Can't find " + searchKey);
      
      arr.noDups();
      //arr.delete(-1); 
      arr.display();


      //arr.delete(00);               // delete 3 items
      //arr.delete(55);
     // arr.delete(99);

      arr.display();                // display items again
      }  // end main()
   }  // end class HighArrayApp
