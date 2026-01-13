import java.util.ArrayList;
import java.util.Scanner;
/** 
 *  Program Name : PersonalExpensesTrackerV1.java
 *  Purpose : PUT SOMETHING USEFUL HERE, DUMMY!
 *  Coder : https://github.com/CrppyPnts
 *  Date : Dec 25, 2025
 */

public class PersonalExpensesTrackerV1
{

	public static void main(String[] args)
	{
		// Variables
		Scanner input = new Scanner(System.in);
		ArrayList<Double> expensesList = new ArrayList<Double>();
		ArrayList<String> categoryList = new ArrayList<String>();
		ArrayList<String> descriptionList = new ArrayList<String>();
		int numChosen = 0;
		double moneySpent = 0;
		double totalSpent = 0;
		String category = "";
		String description = "";
		
		// Title & Description
		
		System.out.println("Hello, user! "
				+ "\nThis program is an expense tracker that is used for you personally.");
		System.out.println("the program will fulfill the user's wants according to what they choose from the UI");
		System.out.println("and will automatically update the data stored for the user.");
		
		// Input & Validation
		while(true) {
			
			do {
				
				System.out.println("\n-----MENU-----");
				System.out.println("(1) Add expense");
				System.out.println("(2) View expenses");
				System.out.println("(3) View total spent");
				System.out.println("(4) Exit");
				System.out.print("Your Choice: ");
				numChosen = input.nextInt();
				
				if (!(numChosen >= 1 && numChosen <= 4)) {
					System.out.println("Invalid Choice! Please input numbers from 1-4");
				}//end if
				
			}while(!(numChosen >= 1 && numChosen <= 4));//end do-while
			
			if (numChosen == 1) {
				do {
					
					System.out.print("\nEnter Amount: ");
					moneySpent = input.nextDouble();
					System.out.print("Enter Category: ");
					category = input.next();
					
					// Buffer Flush 
					input.nextLine();
					
					System.out.print("Enter Description: ");
					description = input.nextLine();
					
					if (!(moneySpent > 0)) {
						System.out.println("Invalid Amount!");
					} else {
						System.out.println("Expense Added Successfully!");
					}//end if-else
					
				}while(!(moneySpent > 0));//end do-while
				
				totalSpent += moneySpent;
				expensesList.add(moneySpent);
				categoryList.add(category);
			  descriptionList.add(description);
				
			} else if (numChosen == 2) {
				
				System.out.println("Amount         Category         Description");
				for ( int i = 0; i < expensesList.size(); i++) {
					System.out.println(expensesList.get(i) + "            " + categoryList.get(i) + "            " + descriptionList.get(i));
				}//end for
				
			} else if (numChosen == 3) {
				System.out.println("Total Spent : " + totalSpent);
			} else {
				System.out.println("Goodbye, User!");
				break;
			}//end if-elseif-else
		}//end while

		//Housekeeping
		input.close();
	}
	//end main
}
//end of class