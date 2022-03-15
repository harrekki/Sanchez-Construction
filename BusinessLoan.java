// filename:    BusinessLoan.java
// Developer:   David LaRocco
// Date:        March 14, 2022
// Description: Child class used for business loans in Sanchez Construction Loan application.  It adds 1% to the entered  
//              prime interest rate and appends the letter "B" to the end of the loan number.

public class BusinessLoan extends Loan 
{
    public BusinessLoan(String lastName, double amount, int term, double interest)
    {
        super(lastName, amount, term);
        // set interest rate to 1% more than current prime interest rate
        this.setInterestRate(interest + .01);
        this.loanNumber += "B";
    }
}
