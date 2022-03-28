// filename:    CreateLoans.java
// Developer:   David LaRocco
// Date:        March 14, 2022
// Description: This application displays loan information for the Sanchez Construction Company. It prompts the user
//              to enter the current prime interest rate and other information about new loans until the user quits
//              the program.  It then generates a loan number and calculates the total amount the customer owes at 
//              the end of each loan term. The results can be either written to a CSV file or displayed on screen.

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class CreateLoans 
{
    public static void main(String[] args)
    {
        // Variables for loan array, user input, and prime interest rate
        ArrayList<Loan> loans = new ArrayList<Loan>();
        Scanner input = new Scanner(System.in);
        DecimalFormat dfDollar = new DecimalFormat("###,##0.00"); // to display dollar values with 2 decimal places
        
        // Variables for while loop 
        double primeInterestRate = 0;
        int userChoice = 0;
        final int QUIT = 999;
        String userLastName = "";
        double userLoanAmount = 0;
        int userLoanTerm = 0;
        
        System.out.print("Enter the current prime interest rate XX.XXX >> ");
        primeInterestRate = input.nextDouble();
        System.out.print("Loan Type:\n\t1 - Business\n\t2 - Personal\n Choose type of loan or " + QUIT + " to quit >> ");
        userChoice = input.nextInt();
        input.nextLine();

        // while loop to get information on each loan
        while(userChoice != QUIT)
        {
            System.out.print("Enter customer's last name >> ");
            userLastName = input.nextLine();
            System.out.print("Enter loan amount >> ");
            userLoanAmount = input.nextDouble();
            System.out.print("Loan Terms:\n\t1 - Short-Term (1 year)\n\t3 - Medium-Term (3 year)" +
                                "\n\t5 - Long-term (5 year)\nChoose loan term >> ");
            userLoanTerm = input.nextInt();

            if(userChoice == 1)
                loans.add(new BusinessLoan(userLastName, userLoanAmount, userLoanTerm, primeInterestRate));
            else if(userChoice == 2)
                loans.add(new PersonalLoan(userLastName, userLoanAmount, userLoanTerm, primeInterestRate));

            System.out.println("Loan information entered successfully.");
            System.out.println();
            System.out.print("Loan Type:\n\t1 - Business\n\t2 - Personal\n Choose type of loan or " + QUIT + " to quit >> ");
            userChoice = input.nextInt();
            input.nextLine();
        } // end while loop
        
        // Retrieve information on all loans
        System.out.println("\nNumber of loans entered: " + loans.size());
        System.out.println();

        // Ask user if they want output to CSV or screen
        System.out.print("\t1 - CSV file\n\t2 - On-screen report" +
                        "\nChoose output type >> ");
        userChoice = input.nextInt();
        input.nextLine();

        if(userChoice == 1) // CSV file
        {
            System.out.print("Enter file name (.csv extension will be added automatically) >> ");
            String fileName = input.nextLine();

            writeToCSVFile(fileName, loans);
        }
        else // On-screen report
        {
            // Default to on-screen report if user selects invalid option
            if(userChoice != 2)
                System.out.println("Invalid entry - results will be displayed on screen.");
            
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
        totalDue = Math.round(totalDue * 100) / 100;
        return totalDue;
    }

    // method to create CSV file from loan application data
    public static void writeToCSVFile(String file, ArrayList<Loan> arr) 
    {
        String fileName = file + ".csv";

        try (PrintWriter pw = new PrintWriter(fileName)) 
        {
            // Column Headers
            pw.write("LoanNumber,LastName,LoanAmount,LoanTerm,InterestRate,AmountDue,");
            pw.println();
            for(Loan application : arr) 
            {
                pw.write(application.getLoanNumber() + ",");
                pw.write(application.getLastName() + ",");
                pw.write(application.getLoanAmount() + ",");
                pw.write(application.getLoanTerm() + ",");
                pw.write(application.getInterestRate() + ",");

                // get loan amount, interest rate, and term from current application 
                double loanAmount = application.getLoanAmount();
                double interestRate = application.getInterestRate();
                int loanTerm = application.getLoanTerm();
                pw.write(calculateAmountDue(loanAmount, loanTerm, interestRate) + ",");
                pw.println();
            }

            System.out.println(fileName + " was created successfully");
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Error creating/ writing to file.");
            e.printStackTrace();
        }
    }
}
