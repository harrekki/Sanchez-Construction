// filename:    LoanConstants.java
// Developer:   David LaRocco
// Date:        March 14, 2022
// Description: Interface holding constants for the Loan abstract class in the Sanchez Construction Loan application.

public interface LoanConstants 
{
    // constants for loan terms
    public static final int SHORT_TERM = 1;
    public static final int MEDIUM_TERM = 3;
    public static final int LONG_TERM = 5;
    // constants for company name and maximum loan amount
    public static final String COMPANY_NAME = "Sanchez Construction Loan Co.";
    public static final double MAX_LOAN_AMT = 100_000;
}
