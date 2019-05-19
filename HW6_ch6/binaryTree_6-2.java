/******************************************************************************************************
**********************************Program Project 6.2*************************************************
In Chapter 8, “Binary Trees,” we’ll look at binary trees, where every branch has
(potentially) exactly two sub-branches. If we draw a binary tree on the screen
using characters, we might have 1 branch on the top row, 2 on the next row,
then 4, 8, 16, and so on. Here’s what that looks like for a tree 16 characters
wide:
--------X-------
----X-------X---
--X---X---X---X-
-X-X-X-X-X-X-X-X
XXXXXXXXXXXXXXXX
312 CHAPTER 6 Recursion
(Note that the bottom line should be shifted a half character-width right, but
there’s nothing we can do about that with character-mode graphics.) You can
draw this tree using a recursive makeBranches() method with arguments left
and right, which are the endpoints of a horizontal range. When you first enter
the routine, left is 0 and right is the number of characters (including dashes)
in all the lines, minus 1. You draw an X in the center of this range. Then the
method calls itself twice: once for the left half of the range and once for the
right half. Return when the range gets too small. You will probably want to put
all the dashes and Xs into an array and display the array all at once, perhaps
with a display() method. Write a main() program to draw the tree by calling
makeBranches() and display(). Allow main() to determine the line length of the
display (32, 64, or whatever). Ensure that the array that holds the characters for
display is no larger than it needs to be. What is the relationship of the number
of lines (five in the picture here) to the line width?
******************************************************************************************************/
package binaryTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class binaryTree {
	public static String[] arrPrint;          //define arrPrint as static, so that it can be used between functions
	
	public static void main(String args[]) throws IOException {
		System.out.println("Enter lineLength to continue:");
		int lineLength=getInt();
		int rowsNo=(int)(Math.log(lineLength)/Math.log(2)+1);   //No. of lines=log(lineLength)
		
		arrPrint=new String[rowsNo];
		for(int i=0;i<rowsNo;i++) {            //initialize the arrPrint to be blank for each row
			arrPrint[i]="";
		}
		
		makeBranches(0,lineLength-1,0);
		print();
		}
	
	public static String getString() throws IOException{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		String s=br.readLine();
		return s;
	}
	
	public static int getInt() throws IOException{
		String s=getString();
		return Integer.parseInt(s);	
	}
	
	public static void makeBranches(int left,int right, int row) {
		int mid=(left+right)/2;
		if(left==right) {
			arrPrint[row]+="X";       //left=right is the base case, they are small enought 
			return ;			
		}

		else {
			for(int j=left;j<(right+1);j++) {
				if(j==mid+1) 
					arrPrint[row]+="X";
				else
					arrPrint[row]+="-";
			}
		
			makeBranches(left,mid,row+1);              //go through the left branch
			makeBranches(mid+1,right,row+1);			//go through the right branch
		}
		
	}
	
	public static void print() {  
		for(int i=0;i<arrPrint.length;i++) {                 //print the output to Console
			System.out.println(arrPrint[i]);
		}
	}

}
