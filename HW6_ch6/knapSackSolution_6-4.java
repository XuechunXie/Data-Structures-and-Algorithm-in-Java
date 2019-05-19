/**************************************************************************************************************
******************************************Program Project 6.4*************************************************
Write a program that solves the knapsack problem for an arbitrary knapsack
capacity and series of weights. Assume the weights are stored in an array. Hint:
The arguments to the recursive knapsack() function are the target weight and
the array index where the remaining items start.
***************************************************************************************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class knapSackSolution {
	public int[] arrWeigth;
	//private int size;
	
	public knapSackSolution(int[] arr) {
		arrWeigth=arr;
	}
	

	public Boolean knapsack(int target, int index)
	{
		Boolean isFound = false;
		
		if(index == arrWeigth.length) 
			return false;                                         //if the traversal has go to the end
		if(arrWeigth[index] == target)                            //If solution is found
		{
			System.out.print("The solution is: " + arrWeigth[index] + " ");
			isFound = true;
		}  
		if(arrWeigth[index] < target)                          //if the item is too small
		{
			System.out.println("Target " +target+","+arrWeigth[index]+" is too small");
			isFound = knapsack(target-arrWeigth[index], index+1);      //go on the check the next element
			
			if(isFound) 
				System.out.print(arrWeigth[index] + " ");
				
			for(int i = index+1; i < arrWeigth.length; i++)        //if no solution is found above start the whole process from the next item
			{
				if(!isFound) 
					isFound = knapsack(target, i);
			}
		}
		if(arrWeigth[index] > target) {                        //if the item is too big discard it and from the next one
			System.out.println("Target " +target+","+arrWeigth[index]+" is too big");
			isFound = knapsack(target, index+1);
		
		}
				
		return isFound;
	}
}

class knapSackApp{
	public static void main(String[] args) throws IOException
	{
		int[] arr={11, 8, 7, 6, 5};
		boolean isFound=false;
		
		knapSackSolution kss=new knapSackSolution(arr);
		
		System.out.println("Please enter a target weigth:");
		int target=getInt();
		isFound = kss.knapsack(target, 0);
		
		if(!isFound) 
			System.out.println("No solution is found!");
		
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
	
}
