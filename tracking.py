import Tracker
from datetime import datetime
def main():
    '''
    Docstring for main
    Description: Main function to run the expense tracker program.
    '''
    continue_program = True
    tracking = Tracker.Tracker()
    Options = """ Options: 
        1 - Adding an Expense to the tracker
        2 - Get Total Expense 
        3 - Get Expense by Category (optional -specify a category) 
        4 - Expense Trend 
        5 - Highest and Lowest Spend Category
        Write exit or 0 to exit"""
    
    print("Welcome to the expense tracker!" + Options)
    
    while continue_program:
        instructions = input("What would you like to do? Press h for help: ")
        if instructions.lower() in ["exit", "0", "o", "quit"]:
            continue_program = False
        else:
            if instructions.lower() in ["h","help","options"]:
                print(Options)
            
            elif instructions == "1":
                category = input("Enter the category of the expense: ")
                amount = input("Enter the amount of the expense: ")
                while not is_integer(amount):
                    print("Please enter a valid number for amount.")
                    amount = input("Enter the amount of the expense: ")
                amount = float(amount)
                date = input("Enter the date of the expense (YYYY-MM-DD): ")
                while not is_date(date):
                    print("Please enter a valid date in the format YYYY-MM-DD.")
                    date = input("Enter the date of the expense (YYYY-MM-DD): ")
                date = datetime.strptime(date, '%Y-%m-%d').date()
                tracking.Add_Expense(category, amount, date)
                print("Expense added successfully.\n")
            
            elif instructions == "2":
                total = tracking.total_expense()
                print(f"Total Expense: {total}\n")
            
            elif instructions == "3":
                category = input("Enter the category to filter by (or leave blank for all): ")
                if category:
                    total_by_cat = tracking.total_expense_by_category(category)
                else:
                    total_by_cat = tracking.total_expense_by_category()
                print(f"Total Expense by Category:\n{total_by_cat}\n")
            
            elif instructions == "4":
                to_see = input("Would you also like to see the expense trend plotted out? (Press \"Y\" to see otherwise press enter to continue): ")
                print(tracking.expense_trend(to_see.lower() == "y"))
            
            elif instructions == "5":
                high_low = tracking.Highest_and_lowest_spend_category()
                print(f"Highest and Lowest Spend Category:\n * Highest: {high_low.get("Highest Spend Category")} \n * Lowest {high_low.get("Lowest Spend Category")}\n")
            
            else :
                print("Invalid option. Please try again. Press h for help.\n")

    print("Thank you for using this Program! Please come again.")


def is_integer(s):
    '''
    Docstring for is_integer
    Description: Checks if a string can be converted to a float.

    :param s: string to check if it can be converted to float
    :return: True if s can be converted to float, False otherwise
    '''
    try:
        float(s)
        return True
    except ValueError:
        return False

def is_date(s):
    '''
    Docstring for is_date
    Description: Checks if a string is in the format YYYY-MM-DD.

    :param s: String to check if it is in date format
    :return: True if s is in date format, False otherwise
    '''
    try:
        datetime.strptime(s, '%Y-%m-%d')
        return True
    except ValueError:
        return False


main()




