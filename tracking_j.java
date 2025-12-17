// import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class tracking_j{
    /**
     * Main method to run the expense tracker program.
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        boolean continue_program = true;
        SimpleTracker tracking = new SimpleTracker();
        String Options = "Options: \n"
            + "1 - Adding an Expense to the tracker \n"
            +"2 - Get Total Expense \n" 
            +"3 - Get Expense by Category (optional -specify a category)\n" 
            + "4 - Expense Trend \n" 
            +"5 - Highest and Lowest Spend Category \n" 
            + "Write exit or 0 to exit the program. \n";
        System.out.println(Options);
        while (continue_program) {
            String user_input = System.console().readLine("What would you like to do? \n");
            if (isNum(user_input)){
                if (Integer.parseInt(user_input) == 1) {
                    System.out.println("Adding an Expense to the tracker");
                    String category = System.console().readLine("Enter category: ");
                    String amount = System.console().readLine("Enter amount: ");
                    while (!isNum(amount)){
                        System.out.println("Invalid amount. Please enter a numeric value.");
                        amount = System.console().readLine("Enter amount: ");
                    }
                    String date = System.console().readLine("Enter date (YYYY-MM-DD): ");
                    boolean valid_date = false;
                    Date date_expense = null;
                    while(!valid_date || date.length() != 10) {
                        if (date.length() != 10) {
                            date = System.console().readLine("Incorect date format. Please enter date (YYYY-MM-DD): ");
                        } else {
                            if (isNum(date.substring(0,4))) {
                                if (isNum(date.substring(5,7))) {
                                    if (isNum(date.substring(8,10))) {
                                        valid_date = true;
                                        try {
                                            date_expense = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                                        } catch (Exception e) {
                                            System.out.println("An error occurred while parsing the date. Please try again.");
                                            valid_date = false;
                                        } 
                                    
                                    } else {
                                        System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                                        date = System.console().readLine("Enter date (YYYY-MM-DD): ");
                                        valid_date = false;
                                    } 
                                } else {
                                    System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                                    date = System.console().readLine("Enter date (YYYY-MM-DD): ");
                                    valid_date = false;
                                }
                            } else {
                                System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                                date = System.console().readLine("Enter date (YYYY-MM-DD): ");
                                valid_date = false;
                            }
                        }
                    }

                    tracking.addExpense(date_expense, category, Double.parseDouble(amount));
                
                } else if (Integer.parseInt(user_input) == 2) {
                    System.out.println("Getting Total Expense");
                    tracking.getTotalExpense();
                } else if (Integer.parseInt(user_input) == 3) {
                    System.out.println("Getting Expense by Category");
                    tracking.getExpenseByCategory();
                } else if (Integer.parseInt(user_input) == 4) {
                    System.out.println("Getting Expense Trend");
                    tracking.getExpenseTrend();
                } else if (Integer.parseInt(user_input) == 5) {
                    System.out.println("Getting Highest and Lowest Spend Category");
                    tracking.getHighestAndLowestSpendCategory();
                } else if (Integer.parseInt(user_input) == 0 ) {
                    continue_program = false;
                    System.out.println("Thank you for using the Expense Tracker. Goodbye!");
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } else {
                if (user_input.equalsIgnoreCase("exit")) {
                    continue_program = false;
                    System.out.println("Thank you for using the Expense Tracker. Goodbye!");
                } else { 
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
    /**
     * Checks if a string is numeric.
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}


