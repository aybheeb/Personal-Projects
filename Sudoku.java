package project1;
/*Name: Habeeb Sowemimo
 * Date: March 12, 2023
 * Project 1 (Sudoku): This program reads a 9x9 sudoku board from the user (which includes 1, 2 or 3 zeroes- missing values)
 * finds and solves the missing values in the board. User can enter a Type 1 board which consist of just one zero and 
 * this program solves the missing value. User can also enter Type 2 board which has two zeroes, either stacked
 * together or side by side and the program solves the missing value. Finally, program also solves a Type 3 board with three
 * zeroes, two side by side and one stacked on either of the two zeroes making a L shape. Program ends when a 9x9 board with all 
 * zeroes is input.
 */
import java.util.Scanner;
public class Sudoku
{
	static int[][] board = new int[9][9];
	static int zeroes = 0;					//use to count zeroes in the board
	static int row = 0, column = 0;			//variables to help scan the board
	static Scanner myScan = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		do 							//input will be  sequential boards until
		{							//we have a 9x9 zeroes.
			readBoard();
			if(zeroes <= 3) 
			{
				solveBoard();
			}
		}
		while(zeroes < 81);
		System.out.println("END");
	}
	static void readBoard()			//this method reads the boards and to count the zeroes in the board
	{
		zeroes = 0;
		for(int row = 0; row < 9; row++) 					//for each row
		{					
			for( int column = 0; column < 9; column++)		//for each column
			{
				board[row][column] = myScan.nextInt();
				if(board[row][column] == 0) 
				{  
					//counts the number of zeroes in the board
					zeroes++;
				}
			}
		}
	}
	public static void solveBoard()		//we have read in a board, and we know we have 1, 2, or 3 zeroes
	{
		if(zeroes == 0) 
		{
			System.out.println("No missing values.");
		}
		else if(zeroes == 1) //for type 1 with one zero,
							//if there is only one missing value, then we need to figure out
							//what row it's in then we can look at everything else in the row
							// and determine the value that is missing
		{
				for(int row = 0; row < 9; row++) 
				{	
					for(int column = 0; column < 9; column++)
						if(board[row][column] == 0)
						{
							//find missing value using findMissing method
							board[row][column] = findMissing(board[row]);
							System.out.println("\n" + "(" + row + "," + column + "," + board[row][column] + ")");
						}
				}
		}
		else if(zeroes == 2) 		//for type two with two zeroes
		{
			for(int row = 0; row < 9; row++) 
			{				
				for(int column = 0; column < 9; column++) 
				{
					if(board[row][column] == 0)
					{
						if(column != 8 && board[row][column + 1] == 0) //zeroes are next to eachother
						{
							//pass each column into different arrays and send to findMissing method
							int[] col1 = new int[] {board[0][column], board[1][column], board[2][column], board[3][column], 
													board[4][column], board[5][column], board[6][column], board[7][column], 
													board[8][column]};
							board[row][column] = findMissing(col1);
							System.out.print("(" + row + "," + column + "," + board[row][column]+ ")");
							column ++;
							int[] col2 = new int[] {board[0][column], board[1][column], board[2][column], board[3][column], 
													board[4][column], board[5][column], board[6][column], board[7][column], 														board[8][column]};
													board[row][column]  = findMissing(col2); 
							System.out.println("(" + row + "," + column + "," + board[row][column]+ ")");
						}		
						else	 	//zeroes are stacked
						{
							//pass each rows with the zeroes and pass each to findMissing method
							board[row][column] = findMissing(board[row]);
							System.out.print("(" + row + "," + column + "," + board[row][column] + ")");
							row ++;
							board[row][column] = findMissing(board[row]);
							System.out.println("(" + row + "," + column + "," + board[row][column] + ")");
						}
					}
				}
			}
		}
		else if (zeroes == 3)			//for type 3 with three zeroes
        {
			int[][] threeByCounts = new int[9][3]; 			//a 2D array to keep track of the number of empty cells in each 3x3 box
			int whichBox = 0;
			for (int row = 0; row < 9; row++)				//iterate through each cells in the 9x9 board
			{
				for (int column = 0; column < 9; column++)
				{	
					//if the cell is empty, increment the count of empty cells in the corresponding 3x3 box
					if (board[row][column] == 0)
					{
						whichBox = ((row / 3) * 3) + (column / 3);
						threeByCounts[whichBox][0]++;
						threeByCounts[whichBox][1] = row;
						threeByCounts[whichBox][2] = column;
					}
				}
			}
					//iterate through each 3x3 box for a row in threeByCounts with one zero in [0]
			for (int i = 0; i < 9; i++)
			{
				if (threeByCounts[i][0] == 1)
				{
					int row = threeByCounts[i][1];
					int column = threeByCounts[i][2];
					int startRow = (row / 3) * 3;
					int startCol = (column / 3) * 3;
					//pass 3x3 box into an array 'value'
					int[] value = new int [] {board[startRow][startCol], board[startRow][startCol + 1],board[startRow][startCol + 2],
											board[startRow + 1][startCol], board[startRow + 1][startCol + 1], board[startRow + 1][startCol + 2],
											board[startRow + 2][startCol], board[startRow + 2][startCol + 1], board[startRow + 2][startCol + 2]};
					//find the missing value in the 3x3 box using findMissing method
					board[row][column]= findMissing(value);
					System.out.print("("+row + ","+ column +","+board[row][column] +")");
					zeroes--; 
                }
			}
			for(int row = 0; row < 9; row++) 
			{				
				for(int column = 0; column < 9; column++) 
				{
					if(board[row][column] == 0)
					{
						if(column != 8 && board[row][column + 1] == 0) //zeroes are next to eachother
						{
							//pass each column into different arrays and send to findMissing method
							int[] col1 = new int[] {board[0][column], board[1][column], board[2][column], board[3][column], 
													board[4][column], board[5][column], board[6][column], board[7][column], 
													board[8][column]};
							board[row][column] = findMissing(col1);
							System.out.print("(" + row + "," + column + "," + board[row][column]+ ")");
							column ++;
							int[] col2 = new int[] {board[0][column], board[1][column], board[2][column], board[3][column], 
													board[4][column], board[5][column], board[6][column], board[7][column], 														board[8][column]};
													board[row][column]  = findMissing(col2); 
							System.out.println("(" + row + "," + column + "," + board[row][column]+ ")");
						}		
						else	 	//zeroes are stacked
						{
							//pass each rows with the zeroes and pass each to findMissing method
							board[row][column] = findMissing(board[row]);
							System.out.print("(" + row + "," + column + "," + board[row][column] + ")");
							row ++;
							board[row][column] = findMissing(board[row]);
							System.out.println("(" + row + "," + column + "," + board[row][column] + ")");
						}
					}
				}
			}
        }
	}	
	static int findMissing(int[] A)			//input A is a 9 elements array of ints, with each value being between 0 and 9
	{										//this method returns a two-int array that tells where and what the missing value is
		int location = 0;
		//use a boolean array 'found'
		boolean[] found = {false, false, false, false, false, false, false, false, false, false};
		for(int i = 0; i < 9; i++)
		{
			//assign true to all number's found
			found[A[i]] = true;
			if(A[i] == 0)
				location = i;
		}
		for(int i = 0; i < 10; i++)
		{
			if(!found[i]) 
				return i;
		}
	return 0;
	}
}
	