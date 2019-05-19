/***********************************************************************************
***********************************program projects 3.3*****************************
To the insertSort.java program (Listing 3.3), add a method called noDups() that
removes duplicates from a previously sorted array without disrupting the order.
(You can use the insertionSort() method to sort the data, or you can simply
use main() to insert the data in sorted order.) One can imagine schemes in
which all the items from the place where a duplicate was discovered to the end
of the array would be shifted down one space every time a duplicate was
discovered, but this would lead to slow O(N2) time, at least when there were a
lot of duplicates. In your algorithm, make sure no item is moved more than
once, no matter how many duplicates there are. This will give you an algorithm
with O(N) time.
************************************************************************************/
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

      for(out=1; out<nElems; out++)     // out is dividing line
         {
         long temp = a[out];            // remove marked item
         in = out;                      // start shifts at out
         while(in>0 && a[in-1] >= temp) // until one is smaller,
            {
            a[in] = a[in-1];            // shift item to right
            --in;                       // go left one position
            }
         a[in] = temp;                  // insert marked item
         }  // end for
      }  // end insertionSort()
//--------------------------------------------------------------
//added by xuechun to remove the duplicates.
   public void noDups() {
	   int insertPos=1;
	   for(int loop=0;loop<nElems;loop++) {
		   if(a[loop+1]>a[loop]) {
			   a[insertPos]=a[loop+1];
			   insertPos++;
		   }
	   }	   
        nElems=insertPos; 
   }
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
      arr.insert(3);
      
      arr.insert(77);               // insert 10 items
      arr.insert(99);
      arr.insert(44);
      arr.insert(77);               // insert 10 items
      arr.insert(99);
      arr.insert(44);      

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

      arr.display();   // display them again
      
      arr.noDups();
      arr.display();
      }  // end main()
   }  // end class InsertSortApp
