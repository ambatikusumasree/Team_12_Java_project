import java.util.Scanner;
public class ExpenseAnalyzer {
    // Method to calculate total
    static double totalExpense(double[] expenses) {
        double total = 0;
        for (int i = 0; i < expenses.length; i++)
            total += expenses[i];
        return total;
    }
    // Method to display category
    static void showCategory(int choice) {
        switch (choice) {
            case 1:
                 System.out.println("Food");
                 break;
            case 2:
                 System.out.println("Transport");
                 break;
            case 3:
                 System.out.println("Education");
                 break;
            case 4:
                 System.out.println("Shopping");
                 break;
            default:
                 System.out.println("Entertainment");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double income, budget;
        double[] expenses = new double[5];
        int count = 0;
        int choice;
        boolean more = true;
        System.out.print("Enter Income: ");
        income = sc.nextDouble();
        System.out.print("Enter Budget: ");
        budget = sc.nextDouble();
        while (more) {
            System.out.println("1.Food");
            System.out.println("2.Transport");
            System.out.println("3.Education");
            System.out.println("4.Shopping");
            System.out.println("5.Entertainment");
            System.out.print("Choose category: ");
            choice = sc.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid category!");
                continue;                 //continue
            }
            System.out.print("Enter expense: ");
            double amount = sc.nextDouble();
            if (amount <= 0) {
                System.out.println("Invalid amount!");
                continue;
            }
            expenses[count] = amount;
            count++;                       //increment
            System.out.print("Category: ");
            showCategory(choice);          //method
            if (count == 5)
                break;                     //break
            System.out.print("Add another? (1-Yes / 0-No): ");
            int option = sc.nextInt();
            if (option == 0)
                more = false;
        }
        double total = totalExpense(expenses);
        double remaining = budget - total;
        double savings = income - total;
        //Type casting
        int average = (int)(total / count);
        System.out.println("----- MONTHLY REPORT -----");
        System.out.println("Income: " + income);
        System.out.println("Budget: " + budget);
        System.out.println("Total Expense: " + total);
        System.out.println("Remaining Budget: " + remaining);
        System.out.println("Savings: " + savings);
        System.out.println("Average Expense: " + average);
        //Logical and Relational operators
        if (total > budget && income > 0) {
            System.out.println("Budget Exceeded!");
        } else if (total <= budget && savings > 0) {
            System.out.println("You are within budget.");
        }
        //Ternary operator
        String status = (total > budget) ? "Over Budget" : "Within Budget";
        System.out.println("Status: " + status);
        count--;       // decrement example
        sc.close();
    }
}

