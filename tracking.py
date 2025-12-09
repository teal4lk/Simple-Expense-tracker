import Tracker
from datetime import datetime
def main():
    continue_program = True
    tracking = Tracker.Tracker()
    Options = """ Options: 
        1 - Adding an Expense to the tracker
        2 - Get Total Expense 
        3 - Get Expense by Category (optional -specify a category) 
        4 - Expense Trend 
        5 - Highest and Lowest Spend Category
        Write exit to exit"""
    print("Welcome to the expense tracker!" + Options)
    while continue_program:
        instructions = input("What would you like to do? Press O to see options again: ")
        if instructions.lower() == "exit":
            continue_program = False
        else:
            if instructions.lower() in ["o", "0"]:
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
                print("Expense added successfully.")
            elif instructions == "2":
                total = tracking.total_expense()
                print(f"Total Expense: {total}")
            elif instructions == "3":
                category = input("Enter the category to filter by (or leave blank for all): ")
                if category:
                    total_by_cat = tracking.total_expense_by_category(category)
                else:
                    total_by_cat = tracking.total_expense_by_category()
                print(f"Total Expense by Category:\n{total_by_cat}")
            elif instructions == "4":
                tracking.expense_trend()
                print("Expense Trend:")
            elif instructions == "5":
                high_low = tracking.Highest_and_lowest_spend_category()
                print(f"Highest and Lowest Spend Category:\n{high_low.keys()[1]}: {high_low.values()[1]}/n{high_low.keys()[0]}: {high_low.values()[0]}")
            else :
                print("Invalid option. Please try again. Press O to see options again.")

    print("Thank you for using this Program! Please come")


def is_integer(s):
    try:
        float(s)
        return True
    except ValueError:
        return False
def is_date(s):
    try:
        datetime.strptime(s, '%Y-%m-%d')
        return True
    except ValueError:
        return False


main()




