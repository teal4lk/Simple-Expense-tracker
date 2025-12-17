# Simple-Expense-tracker
Creates a simple expense tracker using python

## Requirements to use the code
### Python
This code uses the libraries Pandas and Matplotlib

To install Pandas: 

```pip install pandas```

To install Matplotlib:

```pip install matplotlib```
### Java
All classes imported and used should already be contained within java.

## Code Design

This code uses Object Oriented Programming to run.

### Tracker.py
This is the object, tracker. It stores the data in the csv and has various functions to track and analyze the data.

### Tracking.py
This is the main function and the file to use to run the code.

Code should run in any code editor. Simply copy the github repo and press run. The code will run on the terminal.

### Data.csv
This is the file that the data is stored in. If it doesn't exist it will be created. The current numbers are sample numbers for one to try and test out.

## Running/Using the code
Capitalization does not matter, however spelling does.

Running the code should give the user the following prompt: 


<img width="538" height="119" alt="image" src="https://github.com/user-attachments/assets/0a20204c-e4d9-4519-bc4c-503138cdebba" />

 **Options:**

> 0 - Exit the program
>   * Can also be achieved by typing "exit" or "quit"
> 
> 1 - Add an expense to the tracker
> 
> 2 - Get total Expense
> 
> 3 - Get expense by category
>   * OPTIONAL (python only): Offers the ability to select a specific singular category to look at the data for
>
> 4 - Expense Trend
>
>   * OPTIONAL (python only): Offers the ability to see the expense trend as a table using matplotlib
> 
> 5 -  Get the Highest and Lowest Spend Category
> 
> h - offers the options again

Optional cases are where the user is asked to supply input. The user can also simply press enter and move on.

User can give their input and will be prompted further depending on their selection. The program runs until an error is found or the user's input requests to leave the program.


## Room for Improvement

As this is a basic expense tracker, the goal was to keep things simple and easy to run. Potential area for improvement would be adding capability to handle more types of input such as date formats. Potential to cancel adding a new expense mid expense. As well as making the code more intuitive.

Thank you.
