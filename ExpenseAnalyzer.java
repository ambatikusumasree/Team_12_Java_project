import java.util.*;
import java.nio.file.*;
import java.io.IOException;
// CO4: Class and Encapsulation
class Expense {
    private String category;
    private double amount;
    // Parameterized Constructor
    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public double getAmount() {
        return amount;
    }
    public String getType() {
        return "Regular Expense";
    }
    public String toString() {
        return category + " : Rs. " + amount;
    }
}
// CO4: Inheritance
class BudgetExpense extends Expense {
    public BudgetExpense(String category, double amount) {
        super(category, amount);
    }
    // Method Overriding
    @Override
    public String getType() {
        return "Budget Expense";
    }
}
public class ExpenseAnalyzer {
    // CO3: Method and CO6: Stream API
    static double totalExpense(ArrayList<Expense> expenses) {
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
    // CO3: Method to display expenses
    static void displayExpenses(ArrayList<Expense> expenses) {
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }
    // CO5: Searching expenses
    static void searchExpense(ArrayList<Expense> expenses,
                              String category) {
        boolean found = false;
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Expense not found.");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // CO1: Variables and Data Types
        double income, budget;
        System.out.print("Enter monthly income: ");
        income = sc.nextDouble();
        System.out.print("Enter monthly budget: ");
        budget = sc.nextDouble();
        if (income < 0 || budget < 0) {
            System.out.println("Invalid income or budget.");
            sc.close();
            return;
        }
        // CO3: Array
        String[] categories = {
            "Food", "Transport", "Education",
            "Shopping", "Entertainment"
        };
        // CO6: ArrayList
        ArrayList<Expense> expenses = new ArrayList<>();
        int more = 1;
        int count = 0;
        // CO2: While Loop
        while (more == 1) {
            System.out.println("\nChoose Category:");
            for (int i = 0; i < categories.length; i++) {
                System.out.println((i + 1) + ". " + categories[i]);
            }
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            // CO2: If statement and continue
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid category!");
                continue;
            }
            System.out.print("Enter expense amount: ");
            double amount = sc.nextDouble();
            if (amount <= 0) {
                System.out.println("Invalid amount!");
                continue;
            }
            String category = categories[choice - 1];
            // CO4: Object and Inheritance
            Expense e = new BudgetExpense(category, amount);
            expenses.add(e);
            count++; // CO1: Increment operator
            System.out.println("Added: " + e);
            System.out.println("Type: " + e.getType());
            System.out.print("Add another? (1-Yes / 0-No): ");
            more = sc.nextInt();
            // CO2: Break
            if (more == 0) {
                break;
            }
        }
        // CO3 and CO6: Calculate total
        double total = totalExpense(expenses);
        double remaining = budget - total;
        double savings = income - total;
        // CO1: Type Casting and Ternary Operator
        int average = count > 0 ? (int) (total / count) : 0;
        System.out.println("\n--- MONTHLY REPORT ---");
        System.out.println("Income: Rs. " + income);
        System.out.println("Budget: Rs. " + budget);
        System.out.println("Total Expense: Rs. " + total);
        System.out.println("Remaining Budget: Rs. " + remaining);
        System.out.println("Savings: Rs. " + savings);
        System.out.println("Average Expense: Rs. " + average);
        // CO1: Relational and Logical Operators
        if (total > budget && income > 0) {
            System.out.println("Budget Exceeded!");
        } else {
            System.out.println("You are within budget.");
        }
        String status = total > budget
                ? "Over Budget" : "Within Budget";
        System.out.println("Status: " + status);
        // CO5: StringBuilder
        StringBuilder report = new StringBuilder();
        report.append("MONTHLY EXPENSE REPORT\n");
        report.append("Income: Rs. ").append(income).append("\n");
        report.append("Budget: Rs. ").append(budget).append("\n");
        report.append("Total Expense: Rs. ").append(total).append("\n");
        report.append("Remaining Budget: Rs. ")
              .append(remaining).append("\n");
        report.append("Savings: Rs. ").append(savings).append("\n");
        report.append("Status: ").append(status).append("\n");
        // CO6: Lambda Expression and Sorting
        expenses.sort((a, b) ->
                Double.compare(a.getAmount(), b.getAmount()));
        System.out.println("\nExpenses from Lowest to Highest:");
        displayExpenses(expenses);
        // CO6: Stream API and Filtering
        System.out.println("\nExpenses of Rs. 1000 or more:");
        expenses.stream()
                .filter(e -> e.getAmount() >= 1000)
                .forEach(System.out::println);
        // CO5: Searching
        sc.nextLine();
        System.out.print("\nEnter category to search: ");
        String search = sc.nextLine();
        searchExpense(expenses, search);
        // Add expense details to the report
        report.append("\nExpense Details:\n");
        for (Expense e : expenses) {
            report.append(e).append("\n");
        }
        // CO5: File Handling and Exception Handling
        try {
            Path file = Paths.get("expense_report.txt");
            Files.writeString(file, report.toString());
            System.out.println("\nReport saved successfully!");
            List<String> lines = Files.readAllLines(file);
            System.out.println("\nSaved Report:");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("Error handling the file.");
        }
        // CO1: Decrement Operator
        count--;
        System.out.println("\nProgram completed.");
        sc.close();
    }
}
