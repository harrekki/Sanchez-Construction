# Sanchez-Construction
A Java application that calculates and displays loan information. This was my final project for Java Programming II, with additions. My main goal was to increase my working knowledge of inheritance, as well as play around a bit with file writing and other data structures.

## Description
This application displays loan information for the Sanchez Construction Company. It prompts the user
to enter the current prime interest rate and other information about new loans until the user quits
the program.  It then generates a loan number and calculates the total amount the customer owes at 
the end of each loan term. The results can be either written to a CSV file or displayed on screen.

## Classes
### Loan
Abstract class that implements the LoanConstants interface. It holds the class fields loanNumber, 
interestRate, LastName, loanAmt, and loanTerm. The constructor is passed the customer's last name, the loan amount, and the loan term as arguments; the prime interest
rate is initialized via a set method, while the loan number
is generated via a method in the class.  Get methods are 
provided for all class fields, and a toString() method
displays all the information about the loan to the terminal.

### LoanConstants
Interface used to hold the loan terms constants, as well
as the maximum loan amount accepted and the name of the
company.

### BusinessLoan
Extends the Loan class and instantiates BusinessLoan objects.  It adds 1% to the interest rate, and appends 
"B" to the end of the loan number.

### PersonalLoan
Extends the Loan class and instantiates PersonalLoan objects.  It adds 2% to the interest rate, and appends 
"P" to the end of the loan number.

### CreateLoans
Class that contains the Main method. The user is prompted
via terminal to choose the type of loan (business or 
personal) and then is prompted to enter other information.
Depending on the choice, either a PersonalLoan or BusinessLoan
object is instantiated or, if 999 is entered, the user is 
asked how the output should be provided.  If CSV is chosen,
the user is prompted to chose a name for the file, the writeToCSVFile() method is called, and a new file is created; otherwise, the results are displayed on screen.  