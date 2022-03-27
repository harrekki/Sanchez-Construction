// filename:    Loan.java
// Developer:   David LaRocco
// Date:        March 14, 2022
// Description: Abstract class to extend BusinessLoan and PersonalLoan classes in the Sanchez Construction Loan application.

import java.time.LocalDate;
import java.text.DecimalFormat;

public abstract class Loan implements LoanConstants
{
    // class attributes
    protected String loanNumber;
    private double interestRate;
    private String lastName;
    private double loanAmt;
    private int loanTerm;

    DecimalFormat dfDollar = new DecimalFormat("###,##0.00"); // to display dollar values with 2 decimal places
    DecimalFormat dfPercent = new DecimalFormat("#0.00##"); // to display interest percentates

    // constructor that accepts arguments for all attributes except interestRate and does not 
    // allow loans greater than maximum loan amount
    public Loan(String lastName, double amount, int term)
    {
        this.loanNumber = generateLoanNumber();
        this.lastName = lastName;
        // if loan amount is greater than maximum limit, set loan amount to negative 1
        if(amount < MAX_LOAN_AMT)
            this.loanAmt = amount;
        else
        {
            this.loanAmt = 0;
        }
        // if term is not equal to pre-defined terms, force to 1-year loan
        if(term == SHORT_TERM || term == MEDIUM_TERM || term == LONG_TERM)
            this.loanTerm = term;
        else
            this.loanTerm = SHORT_TERM;
    }

    // set method for current prime interest rate
    protected void setInterestRate(double rate)
    {
        this.interestRate = rate;
    }

    // get methods
    public String getLoanNumber()
    {
        return loanNumber;
    }

    public String getLastName() 
    {
        return lastName;
    }
    
    public double getLoanAmount()
    {
        return loanAmt;
    }

    public int getLoanTerm()
    {
        return loanTerm;
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    // method to display loan information
    @Override
    public String toString()
    {
        String info = COMPANY_NAME + "\nLoan Number: " + loanNumber + "\nCustomer Last Name: " + lastName +
                        "\nLoan Amount: $" + dfDollar.format(loanAmt) +"\nInterest Rate: " + dfPercent.format(interestRate) + 
                        "%\nLoan Term: " + loanTerm + " year(s)\n";
        return info;
    }

    // method to generate loan number
    private String generateLoanNumber()
    {
        // loan number consists of 4 digits for the month and year of the loan, followed by a random 5 digit number
        String strNumber;

        // get today's date and store year and month vaulues
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        // get random 5 digit number
        String strRandom = "";
        final int MAX = 5;
        int random = 0;
        for(int i = 0; i < MAX; i++)
        {
            random = (int)(Math.random() * 9);
            strRandom += random;
        }

        // convert values to strings
        String strYear = Integer.toString(year);
        String strTwoDigitYear = strYear.substring(2); // saves last 2 digits of year 
        String strMonth = Integer.toString(month);

        // Concatenate strings into loan number
        strNumber = strMonth + strTwoDigitYear + strRandom;

        return strNumber;
    }
}
