/**************************************************************************************
********************************Program Projects 3.5**********************************
Here’s an interesting way to remove duplicates from an array. The insertion sort
uses a loop-within-a-loop algorithm that compares every item in the array with
every other item. If you want to remove duplicates, this is one way to start.
(See also Exercise 2.6 in Chapter 2.) Modify the insertionSort() method in the
insertSort.java program so that it removes duplicates as it sorts. Here’s one
approach: When a duplicate is found, write over one of the duplicated items
with a key value less than any normally used (such as –1, if all the normal keys
are positive). Then the normal insertion sort algorithm, treating this new key
like any other item, will put it at index 0. From now on the algorithm can
ignore this item. The next duplicate will go at index 1, and so on. When the
sort is finished, all the removed dups (now represented by –1 values) will be
found at the beginning of the array. The array can then be resized and shifted
down so it starts at 0.
****************************************************************************************/
// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
//--------------------------------------------------------------
   public ArrayIns(int max)          // constructor
      {
      a = new long[max];                 // create the array
      nElems = 0;                        // no items yet
      }
//--------------------------------------------------------------
   public void insert(long value)    // put element into array
      {
      a[nElems] = value;             // insert it
      nElems++;                      // increment size
      }
//--------------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         System.out.print(a[j] + " ");  // display it
      System.out.println("");
      }
//--------------------------------------------------------------
   public void insertionSort()
      {
      int in, out;
      int dupCount=0;
      //modified by xuechun to maked duplicates
      for(out=1; out<nElems; out++)     // out is dividing line
         {
         long temp = a[out];      // remove marked item         
         int moveStep;
         int keyPos=0;
         in = out;                      // start shifts at out
         while(in>0 && a[in-1] >= temp) // until one is smaller,
            {
        	 if(a[in-1]==temp) {
        		 dupCount++;
        		 moveStep=in-dupCount+1;
        		 for(int j=0;j<moveStep;j++) {
        			 a[in-j]=a[in-j-1];        	//shift the elements to right,according moveStep		 
        		 }
        		 keyPos=dupCount-1;  //keyPos=in-moveStep=dupCount-1,the pos which value should be set to -1
        		 break;
        	 }else {
                 a[in] = a[in-1];            // shift item to right 
                 --in;
        	 }                        // go left one position
            }
         if(dupCount>0) {
        	 a[keyPos] = -1;  
         }else {
        	 a[in] = temp;                  // insert marked item 
         }
         
         }  // end for
      //System.out.println("the no. of dup"+dupCount);
      deleteDup(dupCount);
      }  // end insertionSort()
//--------------------------------------------------------------  
//add by xuechun to delete shift the elements
public void deleteDup(int dupNo) {
	   nElems=nElems-dupNo;
	   for(int i=0;i<nElems;i++) {
		   a[i]=a[i+dupNo];		   
	   }
   }
//--------------------------------------------------------------
   }  // end class ArrayIns
////////////////////////////////////////////////////////////////
class InsertSortApp
   {
   public static void main(String[] args)
      {
      int maxSize = 100;            // array size
      ArrayIns arr;                 // reference to array
      arr = new ArrayIns(maxSize);  // create the array

      arr.insert(77);               // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);

      //added by xuechun xie to test no dupliate
      arr.insert(77);               // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);   

      arr.display();                // display items

      arr.insertionSort();          // insertion-sort them

      arr.display();                // display them again
      }  // end main()
   }  // end class InsertSortApp
