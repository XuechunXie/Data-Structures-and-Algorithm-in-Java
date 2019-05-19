/*********************************************************************************
*****************************Program Projects 4.2*********************************
Create a Deque class based on the discussion of deques (double-ended queues) in
this chapter. It should include insertLeft(), insertRight(), removeLeft(),
removeRight(), isEmpty(), and isFull() methods. It will need to support wraparound
at the end of the array, as queues do.
*********************************************************************************/
class Deque {
	
	private int maxSize;
	private int left,right;
	private long[] DequeArray;
	private int nItems;
	
	public Deque(int s) {
		maxSize=s;
		DequeArray=new long[maxSize];
		nItems=0;
		right=0;
		left=-1;
	}
	
	public void insertRight(long j) {
		if(nItems==maxSize) {
			System.out.println("Reach maxsize.");
			return ;	
		}		
		if(right==0)
			right=maxSize;                                                                                  
		DequeArray[--right]=j;
		nItems++;		
	}
	
	public void insertLeft(long j) {
		if(nItems==maxSize) {
			System.out.println("Reach maxsize.");
			return ;	
		}
		if(left==maxSize-1)
			left=-1;
		DequeArray[++left]=j;
		nItems++;
	}
	
	public long removeRight() {
		if(nItems==0)
			return -1;
		long temp=DequeArray[right++];		
		if(right==maxSize)
			right=0;
		nItems--;
		return temp;
	}
	
	public long removeLeft() {
		if(nItems==0)
			return -1;
		long temp=DequeArray[left--];
		if(left==-1)
			left=maxSize-1;
		nItems--;
		return temp;
	}
	
	public boolean isFull() {
		if(nItems==maxSize)
			return true;
		return false;
	}
	
	public boolean isEmpty() {
		//System.out.println("nItems="+nItems);
		if(nItems==0)
			return true;
		return false;
	}
	
}

class DequeApp {
	public static void main(String[] args) {
		
		Deque theQeque=new Deque(5);
		
			theQeque.insertLeft(10);
			theQeque.insertLeft(20);
			theQeque.insertLeft(30);
			theQeque.insertLeft(40);
			theQeque.insertLeft(50);
			theQeque.insertRight(70);
			theQeque.insertRight(80);
			theQeque.insertRight(90);		
		
		
		while(!theQeque.isEmpty()) {
			long nLeft=theQeque.removeLeft();
			if(nLeft==-1)
				break;
			System.out.println(nLeft);
			long nRight=theQeque.removeRight();
			if(nRight==-1)
				break;
			System.out.println(nRight);			
		}
		theQeque.insertRight(70);
		theQeque.insertRight(80);
		theQeque.insertRight(90);
		theQeque.insertLeft(30);
		theQeque.insertLeft(40);
		theQeque.insertLeft(50);

	}
	
}