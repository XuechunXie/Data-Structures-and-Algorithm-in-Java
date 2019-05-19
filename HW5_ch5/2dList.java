/***************************************************************************************
*************************************Programe Project 5.6*********************************
Let’s try something a little different: a two-dimensional linked list, which we’ll
call a matrix. This is the list analogue of a two-dimensional array. It might be
useful in applications such as spreadsheet programs. If a spreadsheet is based
on an array, and you insert a new row near the top, you must move every cell
in the lower rows N*M cells, which is potentially a slow process. If the spreadsheet
is implemented by a matrix, you need only change N pointers.
For simplicity, we’ll assume a singly linked approach (although a double-linked
approach would probably be more appropriate for a spreadsheet). Each link
(except those on the top row and left side) is pointed to by the link directly
above it and by the link on its left. You can start at the upper-left link and
navigate to, say, the link on the third row and fifth column by following the
pointers down two rows and right four columns. Assume your matrix is created
with specified dimensions (7 by 10, for example). You should be able to insert
values in specified links and display the contents of the matrix.
1.
need to support
insert/delete of specific cells, and navigation around
the spreadsheet
2.
Add an iterator for the 2d linked list, support going
up/down/left/right, get/insert/remove of the value in
the current cell, display of the whole spreadsheet
****************************************************************************************/
class link {
	public long dData;
	public int row,column;
	public link up;
	public link down;
	public link next;
	public link previous;
	
	public link(long d) {
		dData=d;
	}
	
	public void display() {
		System.out.println(dData+" ");
	}
}

class linkList2D{
	private int row,column;
	public link first;
	public link last;
		
	public linkList2D(int r,int col,long arr[][]) {
		row=r;
		column=col;
		first=null;
		last=null;
		link[][] matrixLink=new link[r][col];
		link current=first;
		for(int i=0;i<r;i++) {
			for(int j=0;j<col;j++) {
				matrixLink[i][j]=new link(arr[i][j]);
				if(i==0) {
					if(first==null) {
						first=matrixLink[i][j];
						current=first;
					}else if(j!=col-1){
						current.next=matrixLink[i][j];
						matrixLink[i][j].previous=current;
						current=current.next;
					}else {
						current.next=matrixLink[i][j];
						matrixLink[i][j].previous=current;	
						current=matrixLink[i][0];
					}										
				}else if(j==col-1){
					current.next=matrixLink[i][j];
					matrixLink[i][j].previous=current;	
					matrixLink[i][j].up=matrixLink[i-1][j];
					matrixLink[i-1][j].down=matrixLink[i][j];					
					current=matrixLink[i][0];
				}else {
					if(j==0) {
						current.down=matrixLink[i][j];
						matrixLink[i][j].up=current;
						current=matrixLink[i][j];
					}else{
						current.next=matrixLink[i][j];
						matrixLink[i][j].previous=current;
						matrixLink[i][j].up=matrixLink[i-1][j];
						matrixLink[i-1][j].down=matrixLink[i][j];
						current=current.next;					
					}					
				}
				
				if(i==(r-1)&&j==(col-1)) {
					last=matrixLink[i][j];
				}
								
			}
		}
	}
	
	public void insertValue(int pos[],int val) {		
		link current=navigateCell(pos);
		current.dData=val;		
	}
	
	public long removeValue(int pos[]) {
		link current=navigateCell(pos);
		long data=current.dData;
		current.dData=-1;
		return data;
	}
	
	public link navigateCell(int pos[]) {
		int rowPos=pos[0];
		int colPos=pos[1];
		link current=first;
		for(int i=1;i<rowPos;i++) {
			current=current.down;
		}
		
		for(int j=1;j<colPos;j++) {
			current=current.next;
		}
		
		return current;
	}
	
	
	public boolean isEmpty() {
		return first==null;
	}
	
	/*
	public void insertCellInRow(int pos[],long val) {
		int rowPos=pos[0];
		link current=navigateCell(pos);
		for(int i=rowPos+1;i<row;i++) {
			current=current.next;
		}

	}
	
	public void insertCellInCol(long key) {
		
	}
	
	public void delete(long key) {
		
	}
	
	public void displayBackward() {
		
	}*/
	
	public void display(){
		link current =first;
		for(int i=0;i<row;i++) {
			for(int j=0;j<column;j++) {
				System.out.println(current.dData);
					if(i%2==0) {						
						if((j==column-1)&&(i!=(row-1)))
							current=current.down;
						else
							current=current.next;
					}else {
						if(j==column-1&&(i!=(row-1)))
							current=current.down;
						else
							current=current.previous;
				}								
			}
		}
		
	}
	
	// -------------------------------------------------------------
	public listIterator getIterator() // return iterator
	{
		return new listIterator(this); // initialized with
	} // this list
	// -------------------------------------------------------------
}


class listIterator{
	private link current;
	//private link pre, down, up;
	private linkList2D our2DList;
	
	public listIterator(linkList2D list) {
		our2DList=list;
		reset();
	}
	
	public void reset() {
		current=our2DList.first;
		//pre=null;
		//up=null;
	}
	
	public link goDown() {
		if(current.down!=null) {
			current=current.down;
		}
		
		return current;
	}
	
	public link goUp() {
		if(current.up!=null) {
			current=current.up;
		}
		
		return current;		
	}
	
	public link goNext() {
		if(current.next!=null) {
			current=current.next;
		}
		
		return current;
	}
	
	public link goPrevious() {
		if(current.previous!=null) {
			current=current.previous;
		}
		
		return current;		
	}
	
	public long getCurrentVal() {
		return current.dData;
	}
	
	public void insertCurrentVal(long val) {
		current.dData=val;
	}
	
	public void removeCurrentVal() {
		current.dData=-1;
	}
}

class List2DApp {
	
	public static void main(String args[]) {
		long[][] array=new long[3][4];
		int key=1;
		for(int i=0;i<3;i++) {
			for(int j=0;j<4;j++) {
				array[i][j]=key;
				key++;
			}
		}
		
		linkList2D list2d=new linkList2D(3,4,array);
		listIterator iter1=list2d.getIterator();
		iter1.goNext();
		iter1.goDown();
		iter1.goPrevious();
		iter1.goUp();
		System.out.println("The value of the current link is:"+iter1.getCurrentVal());
		
		iter1.insertCurrentVal(99);
		System.out.println("The value of the current link after insertion is:"+iter1.getCurrentVal());
		
		iter1.removeCurrentVal();
		System.out.println("The value of the current link after remove is:"+iter1.getCurrentVal());
		//list2d.display();
		int pos[]= {2,2};
		list2d.insertValue(pos,56);
		list2d.display();
		list2d.removeValue(pos);
		list2d.display();
	}
	
}
