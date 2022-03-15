// filename:    PersonalLoan.java
// Developer:   David LaRocco
// Date:        March 14, 2022
// Description: Child class used for personal loans in the Sanchez Construction Loan Application.  It adds 2% to the entered 
//              prime interest rate and appends the letter "P" to the end of the loan number.

public class PersonalLoan extends Loan 
{
    public PersonalLoan(String lastName, double amount, int term, double interest)
    {
        super(lastName, amount, term);
        // set interest rate to 2% more than current prime interest rate
        this.setInterestRate(interest + .02);
        this.loanNumber += "P";
    }
}
