import pandas as pd
import matplotlib.pyplot as plt
from datetime import datetime

class Tracker:
    def __init__(self):
        '''
        Declaration method
        
        :param self: creates the class instance
        '''
        self.__data_path = "data.csv"
        self.__df = pd.read_csv(self.__data_path)
        self.__added = False
    
    def Add_Expense(self, category, amount, date):
        '''
        Allows the user to add an expense to the tracker.
        
        :param category: category of the expense
        :param amount: amount of the expense
        :param date: date of the expense
        '''
        df_to_append = pd.DataFrame({
            'Category': [category],
            'Amount': [amount],
            'Date': [date]
        })

        df_to_append.to_csv(self.__data_path, mode='a', index=False, header=False)
        self.__df = pd.read_csv(self.__data_path)
        self.added = True
    def total_expense(self):
        if self.__added:
            self.__df = pd.read_csv(self.__data_path)
            self.added = False
        return self.__df['Amount'].sum()

    def total_expense_by_category(self, category=None):
        if self.__added:
            self.__df = pd.read_csv(self.__data_path)
            self.__added = False
        if category is None:
            return pd.pivot_table(self.__df, values='Amount', index=['Category'], aggfunc='sum')
        else: 
            if category not in self.__df['Category'].values:
                return f"No expenses found for category: {category}"
            return pd.pivot_table(self.__df, values='Amount', index=['Category'], aggfunc='sum').loc[category]
    
    def expense_trend(self, Plot=False):
        if self.__added:
            self.__df = pd.read_csv(self.__data_path)
            self.__added = False
        if Plot:
            pd.pivot_table(self.__df, values='Amount', index=['Date'], aggfunc='sum').plot.line()
            plt.title('Line Plot of Expense Trends Over Time')
            plt.xlabel('Date')
            plt.ylabel('Amount Spent')
            plt.show()
        return pd.pivot_table(self.__df, values='Amount', index=['Date'], aggfunc='sum')


    def Highest_and_lowest_spend_category(self):
        lowest = None
        highest = None
        if self.__added:
            self.__df = pd.read_csv(self.__data_path)
            self.__added = False
        expense = self.total_expense_by_category()
        for cat in expense.index:
            if expense.loc[cat]["Amount"]  == min(expense["Amount"]):
                lowest = cat
            elif expense.loc[cat]["Amount"]  == max(expense["Amount"]):
                highest = cat
        return {"Lowest Spend Category": lowest, "Highest Spend Category": highest}