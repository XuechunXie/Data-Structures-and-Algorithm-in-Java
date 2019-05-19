/***************************************************************************
**********************Program Project 6.5***********************************
Implement a recursive approach to showing all the teams that can be created
from a group (n things taken k at a time). Write the recursive showTeams()
method and a main() method to prompt the user for the group size and the
team size to provide arguments for showTeam(), which then displays all the
possible combinations.
***************************************************************************/
package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class combination {
	//public static boolean flag=false;
	
	public static void main(String args[]) throws IOException {
		System.out.println("please enter a string as the group.");
		String group=getString();
		int groupSize=group.length();
		System.out.println("please enter the team size: ");
		int teamSize=getInt();
		int n=0;
		String team="";
		
		showTeams(groupSize,teamSize,group,n,team,teamSize);
		
		
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
	
	public static void showTeams(int groupSize,int teamSize,String group,int index,String result,int length) {
		//boolean flag=false;
		if(teamSize>groupSize||groupSize==0||teamSize==0)
			return ;
		else {
			//if(flag==true) {
				char ch=group.charAt(index);
				result+=Character.toString(ch);
				index++;							
			//}
			//flag=true;	
			showTeams(groupSize-1,teamSize-1,group,index,result,length);

			//System.out.println(group.charAt(index));
			if(result.length()==length) {
				System.out.println(result);				
			}
			result=result.substring(0, result.length()-1);
				 
			showTeams(groupSize-1,teamSize,group,index,result,length);
			//result=result.substring(0, result.length()-2);
			index--;
			//System.out.println(group.charAt(index));
		}
			
		
	}

}
