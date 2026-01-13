import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/** 
 *  Program Name : PersonalExpensesTrackerV1.java
 *  Purpose : PUT SOMETHING USEFUL HERE, DUMMY!
 *  Coder : https://github.com/CrppyPnts
 *  Date : Dec 25, 2025
 */

public class PersonalExpensesTrackerV2
{

	public static void main(String[] args) throws IOException 
	{
		// Variables
		   // Objects
		Scanner input = new Scanner(System.in);
		ArrayList<Expense> expenseDataList = new ArrayList<Expense>();
		File userFile;
		   //for user-input
		int numChosen = 0;
		double moneySpent = 0;
		String category = "";
		String description = "";
		String userName = "";
		String infoToEdit = "";
		boolean invalidInput = false;
		   //for data processing
		double totalSpent = 0;
		
		// Title & Description
		
		do {
			invalidInput = false;
			System.out.print("Please enter your last name, user... ");
			userName = input.nextLine();
			
			for (int i = 0; i < userName.length(); i++) {
				if (Character.isDigit(userName.charAt(i)) || userName.contains(" ")) {
					System.out.println("Invalid Username!");
					invalidInput = true;
				}
			}
		}while(invalidInput);
		
		userFile = new File(userName + "ExpenseFile.txt");
		createUserFile(userFile);
		writerHeader(userFile);
		
		System.out.println("\nHello, " + userName
				+ "!\nThis program is an expense tracker that is used for you personally.");
		System.out.println("the program will fulfill the user's wants according to what they choose from the UI");
		System.out.println("and will store the data on a .txt file to save information easy information access and data editting.");
		
		// Input & Validation
		while(true) {
			
			do {
				
				System.out.println("\n-----MENU-----");
				System.out.println("(1) Add expense");
				System.out.println("(2) View expenses");
				System.out.println("(3) View total spent");
				System.out.println("(4) Edit Expenses");
				System.out.println("(5) Exit");
				System.out.print("Your Choice: ");
				numChosen = input.nextInt();
				
				if (!(numChosen >= 1 && numChosen <= 5)) {
					System.out.println("Invalid Choice! Please input numbers from 1-5");
				}//end if
				
			}while(!(numChosen >= 1 && numChosen <= 5));//end do-while
			
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
				Expense expenseData = new Expense(moneySpent,category,description);
				expenseDataList.add(expenseData);
				appendExpense(userFile, expenseData);
;				
			} else if (numChosen == 2) {
				
				System.out.println("Amount      :     Category     :     Description\n");
				for ( int i = 0; i < expenseDataList.size(); i++) {
					System.out.println("" + (i+1) + ". " + expenseDataList.get(i).getAmount() + "     :     " + expenseDataList.get(i).getCategory() + "     :     " + expenseDataList.get(i).getDescription());
				}//end for
				
			} else if (numChosen == 3) {
				
				System.out.printf("Total Spent : %.2f\n", totalSpent);
				
			} else if (numChosen == 4) {
				
				System.out.println("\nAmount      :     Category     :     Description");
				for ( int i = 0; i < expenseDataList.size(); i++) {
					System.out.println((i+1) + ". " + expenseDataList.get(i).getAmount() + "     :     " + 
				                   expenseDataList.get(i).getCategory() + "     :     " 
							              + expenseDataList.get(i).getDescription());
				}//end for
				
				do {
					
					System.out.print("Select Expense # To Edit: ");
					numChosen = input.nextInt();
					
					if (!(numChosen >= 1 && numChosen <= expenseDataList.size())) {
						System.out.println("Invalid Choice!");
					}//end if
					
				}while(!(numChosen >= 1 && numChosen <= expenseDataList.size()));//end do-while
				
				System.out.print("\nSelect Which Detail to Edit... ");
				System.out.println("Expense | Category | Description\n");
				
				do {
					invalidInput = false;
					System.out.print("\nYour Choice: ");
					infoToEdit = input.next();
					
					for (int i = 0; i < infoToEdit.length(); i++) {
						if (Character.isDigit(infoToEdit.charAt(i)) || infoToEdit.contains(" ")) {
							System.out.println("Invalid Choice!");
							invalidInput = true;
						}
					}
				}while(invalidInput);
				
				if (infoToEdit.equalsIgnoreCase("Expense")) {
					
				  do {
          	
          	System.out.println("\nCurrent Amount: " + expenseDataList.get(numChosen-1).getAmount() + "$");
					System.out.print("Enter Replacement: ");
					moneySpent = input.nextDouble();
					
					if (!(moneySpent > 0)) {
						System.out.println("Invalid Amount!");
					} else {
						System.out.println("Expense Replaced Successfully!");
					}//end if-else
					
				  }while(!(moneySpent > 0));//end do-while
				  
				  totalSpent = totalSpent - expenseDataList.get(numChosen-1).getAmount() + moneySpent;
	        expenseDataList.get(numChosen-1).setAmount(moneySpent);
	        rewriteFile(userFile, expenseDataList);
				
				} else if (infoToEdit.equalsIgnoreCase("Category")) {
					
					do {
						invalidInput = false;
						System.out.println("\nCurrent Inputted Category: \"" + expenseDataList.get(numChosen-1).getCategory() + "\"");
						System.out.print("Enter Replacement: ");
						category = input.next();
						
						for (int i = 0; i < category.length(); i++) {
							if (Character.isDigit(category.charAt(i)) || category.contains(" ")) {
								System.out.println("Invalid Choice!");
								invalidInput = true;
							} else {
								System.out.println("Category Replaced Successfully!");
							}
						}
					}while(invalidInput);//end do-while
					
	        expenseDataList.get(numChosen-1).setCategory(category);
	        rewriteFile(userFile, expenseDataList);
	        
				} else {
					
					//Buffer-flush
					input.nextLine();
					
					do {
						invalidInput = false;
						System.out.println("\nCurrent Inputted Description: \"" + expenseDataList.get(numChosen-1).getDescription() + "\"");
						System.out.print("Enter Replacement: ");
						description = input.nextLine();
						
						for (int i = 0; i < description.length(); i++) {
							if (Character.isDigit(description.charAt(i))) {
								System.out.println("Invalid Choice!");
								invalidInput = true;
							} else {
								System.out.println("Description Replaced Successfully!");
							}
						}
					}while(invalidInput);//end do-while
					
	        expenseDataList.get(numChosen-1).setDescription(description);
	        rewriteFile(userFile, expenseDataList);
	        
				}//end if-elseif-else
				
         
			} else {
				
				System.out.println("\nGoodbye, User!");
				break;
				
			}//end if-elseif-else
		}//end while

	//Housekeeping
		input.close();
	}
	//end main
	
	/*
	* Method Name: createUserFile()  <br>
	* Purpose: a public class method that will create a new file using .createNewFile method from the imported IO class<br>
	* Accepts: an object of type File. <br>
	* Returns: VOID. <br>
	* Date: Sun. Dec. 28, 2025. <br>
	*/
	
	public static void createUserFile(File userFile) throws IOException {
		userFile.createNewFile();
	}//end method
	
	/*
	* Method Name: writerHeader()  <br>
	* Purpose: a public class method that will print-out a formatted header onto the user's text file simply for organization purposes.  <br>
	* Accepts: an object of type File. <br>
	* Returns: VOID. <br>
	* Date: Tues. Dec. 30, 2025. <br>
	*/
	
	public static void writerHeader(File userFile) throws IOException {
		
    PrintWriter writer = new PrintWriter(userFile);
    writer.println("Amount      :     Category     :     Description");
    
    //Housekeeping
    writer.close();
	}//end method
	
	/*
	* Method Name: appendExpense()  <br>
	* Purpose: a public class method that will print-out the expense details to the user's text file.  <br>
	* Accepts: an object of type File & a user-defined Expense object. ( needs Expense.java ) <br>
	* Returns: VOID. <br>
	* Date: Tues. Dec. 30, 2025. <br>
	*/
	
	public static void appendExpense(File userFile, Expense expenseData) throws IOException {
		
    PrintWriter writer = new PrintWriter(new FileWriter(userFile, true));
    writer.println(expenseData.getAmount() + "     :     " +
    		expenseData.getCategory() + "     :     " +
    		expenseData.getDescription());
    
    //Housekeeping
    writer.close();
  }//end method
	
	/*
	* Method Name: rewriteFile()  <br>
	* Purpose: a public class method that updates a user's .txt file based on the info that they want to edit by overwritting.  <br>
	* Accepts: an object of type File & ArrayList of type Expense object ( needs Expense.java ). <br>
	* Returns: VOID. <br>
	* Date: Tues. Dec. 30, 2025. <br>
	*/

  public static void rewriteFile(File userFile, ArrayList<Expense> expenseDataList) throws IOException {
    PrintWriter writer = new PrintWriter(userFile);
    writer.println("Amount      :     Category     :     Description");

    for (Expense e : expenseDataList) {
        writer.println(e.getAmount() + "     :     " +
                       e.getCategory() + "     :     " +
                       e.getDescription());
    }//end for
    
    //Housekeeping
    writer.close();
  }//end method
  
}
//end of class