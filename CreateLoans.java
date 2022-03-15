// filename:    CreateLoans.java
// Developer:   David LaRocco
// Date:        March 14, 2022
// Description: This applicaton that displays loan information for the Sanchez Construction Co. It prompts the user
//              to enter the current prime interest rate and other information about 5 new loans.  It then generates 
//              a loan number and calculates the total amount the customer owes at the end of each loan term.  The 
//              details of each loan are then displayed.

import java.text.DecimalFormat;
import java.util.Scanner;

public class CreateLoans 
{
    public static void main(String[] args)
    {
        // Variables for loan array, user input, and prime interest rate
        Loan[] loans = new Loan[5];
        Scanner input = new Scanner(System.in);
        DecimalFormat dfDollar = new DecimalFormat("###,##0.00"); // to display dollar values with 2 decimal places
        double primeInterestRate = 0.0325;

        /* test values - should be commented out in production
        loans[0] = new PersonalLoan("Smith", 25000, 3, primeInterestRate);
        loans[1] = new BusinessLoan("Jones", 50000, 5, primeInterestRate);
        loans[2] = new BusinessLoan("Adams", 75000, 5, primeInterestRate);
        loans[3] = new BusinessLoan("Turner", 100000, 5, primeInterestRate);
        loans[4] = new PersonalLoan("Ramos", 10000, 1, primeInterestRate);
        */

        // Variables for loop
        int userChoice = 0;
        String userLastName = "";
        double userLoanAmount = 0;
        int userLoanTerm = 0;
        
        System.out.print("Enter the current prime interest rate XX.XXX >> ");
        primeInterestRate = input.nextDouble();
        for(int i = 0; i < loans.length; i++) 
        {
            System.out.println("Loan #" + (i + 1));
            System.out.print("Loan Type:\n\t1 - Business\n\t2 - Personal\n Choose type of loan >> ");
            userChoice = input.nextInt();
            input.nextLine();

            // get user inputs
            System.out.print("Enter customer's last name >> ");
            userLastName = input.nextLine();
            System.out.print("Enter loan amount >> ");
            userLoanAmount = input.nextDouble();
            System.out.print("Loan Terms:\n\tShort-Term (1 year) - 1\n\tMedium-Term (3 year) - 3" +
                                "\n\tLong-term (5 year) - 5\n Choose loan term >> ");
            userLoanTerm = input.nextInt();

            if(userChoice == 1)
                loans[i] = new BusinessLoan(userLastName, userLoanAmount, userLoanTerm, primeInterestRate);
            else if(userChoice == 2)
                loans[i] = new PersonalLoan(userLastName, userLoanAmount, userLoanTerm, primeInterestRate);

            System.out.println("Loan information entered successfully.");
            System.out.println();
        } // end for loop
        
        // Display information on all loans
        for(Loan application : loans)
        {
            System.out.println(application.toString()); // Displays loan information
            
            // get loan amount, interest rate, and term from current application 
            double loanAmount = application.getLoanAmount();
            double interestRate = application.getInterestRate();
            int loanTerm = application.getLoanTerm();

            if(application.getLoanAmount() > 0)
                // Calculate and display total amount due at end of the loan
                System.out.println("Total amount due at end of loan term: $" + 
                    dfDollar.format(calculateAmountDue(loanAmount, loanTerm, interestRate)));
            else 
                // Print error message
                System.out.println("Loan denied - Maximum loan amount exceeded.");    
            System.out.println("--------------------");
        }

        input.close();
    } // end of main method
   
    // method to calculate and return total amount due at the end of the loan term
    public static double calculateAmountDue(double amount, int term, double interest)
    {
        double totalDue = amount;
        for(int i = 0; i < term; i++)
        {
            totalDue += totalDue * (interest / 100);
        }
        return totalDue;
    }
}
