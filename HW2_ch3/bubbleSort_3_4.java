/********************************************************************************
*******************************Program project 3.4********************************
Another simple sort is the odd-even sort. The idea is to repeatedly make two
passes through the array. On the first pass you look at all the pairs of items,
a[j] and a[j+1], where j is odd (j = 1, 3, 5, …). If their key values are out of
order, you swap them. On the second pass you do the same for all the even
values (j = 2, 4, 6, …). You do these two passes repeatedly until the array is
sorted. Replace the bubbleSort() method in bubbleSort.java (Listing 3.1) with
an oddEvenSort() method. Make sure it works for varying amounts of data.
You’ll need to figure out how many times to do the two passes.
The odd-even sort is actually useful in a multiprocessing environment, where a
separate processor can operate on each odd pair simultaneously and then on
each even pair. Because the odd pairs are independent of each other, each pair
can be checked—and swapped, if necessary—by a different processor. This
makes for a very fast sort.
*********************************************************************************/
// bubbleSort.java
// demonstrates bubble sort
// to run this program: C>java BubbleSortApp
////////////////////////////////////////////////////////////////
class ArrayBub
   {
   private long[] a;                 // ref to array a
   private int nElems;               // number of data items
//--------------------------------------------------------------
   public ArrayBub(int max)          // constructor
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
   public void bubbleSort()
      {
      int out, in;

      for(out=nElems-1; out>1; out--)   // outer loop (backward)
         for(in=0; in<out; in++)        // inner loop (forward)
            if( a[in] > a[in+1] )       // out of order?
               swap(in, in+1);          // swap them
      }  // end bubbleSort()
//--------------------------------------------------------------
   //added by xuechun for oddEvenSort
   public void oddEvenSort() {
	   int j;
	   boolean isSorted=false;
	   
	   /*
	   for(out=0;out<nElems-1;out++) {
		   for(in=out;in>=0;in--) {
			   if(in%2==0&&a[in]>a[in+1])
				   swap(in,in+1);
			   else if(in%2!=0&&a[in]>a[in+1])
				   swap(in,in+1);
		   }
	   }*/
	   while(!isSorted) {
		   isSorted=true;
		   //when j is even
		   for(j=0;j<nElems-1;j=j+2) {
			   if(a[j]>a[j+1]) {                 //when j=0,2,4,6...compare a[j]&a[j+1]
				   isSorted=false;
				   swap(j,j+1);                 //if disorder, swap them
			   }				   			   
		   }
		   
		   //when j is odd
		   for(j=1;j<nElems-1;j=j+2) {
			   if(a[j]>a[j+1]) {              //when j=1,3,5,7...compare a[j]&a[j+1]
				   isSorted=false;	
				   swap(j,j+1);				   			   
			   }

		   }		   
	   }	                                                                 
   }
   
 //--------------------------------------------------------------   
   private void swap(int one, int two)
      {
      long temp = a[one];
      a[one] = a[two];
      a[two] = temp;
      }
//--------------------------------------------------------------
   }  // end class ArrayBub
////////////////////////////////////////////////////////////////
class BubbleSortApp
   {
   public static void main(String[] args)
      {
      int maxSize = 100;            // array size
      ArrayBub arr;                 // reference to array
      arr = new ArrayBub(maxSize);  // create the array

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

      arr.oddEvenSort();             // bubble sort them

      arr.display();                // display them again
      }  // end main()
   }  // end class BubbleSortApp
////////////////////////////////////////////////////////////////
