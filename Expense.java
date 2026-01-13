/** 
 *  Program Name : Expense.java
 *  Purpose : PUT SOMETHING USEFUL HERE, DUMMY!
 *  Coder : Joseph Servellita 1344828 Section 3
 *  Date : Dec 28, 2025
 */

public class Expense
{
	private double amount;
	private String category;
	private String description;
	
	public Expense (double amount, String category, String description) {
		this.amount = amount;
		this.category = category;
		this.description = description;
	}//end of constructor
	
	//getters
	public double getAmount() {
		return amount;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getDescription() {
		return description;
	}//end of getters
	
	//setters
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setDescription(String description) {
		this.description = description;
	}//end of setters
	
	public String toString() {
		return amount + "     :     " + category + "     :     " + description;
	}
	
}
//end of class