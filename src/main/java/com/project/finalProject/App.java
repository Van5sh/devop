package com.project.finalProject;

import java.util.*;

public class App {

    static class Expense {
        String description;
        double amount;

        Expense(String description, double amount) {
            this.description = description;
            this.amount = amount;
        }

        public String toString() {
            return String.format("%s - ₹%.2f", description, amount);
        }
    }

    // === For testability ===
    private final List<Expense> expenses = new ArrayList<>();

    public void addExpense(String description, double amount) {
        expenses.add(new Expense(description, amount));
    }

    public boolean deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            return true;
        }
        return false;
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public double getTotal() {
        return expenses.stream().mapToDouble(e -> e.amount).sum();
    }

    public int getCount() {
        return expenses.size();
    }

    // === CLI logic remains unchanged ===
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        App app = new App(); // Using instance

        System.out.println("Expense Tracker");

        while (true) {
            System.out.println("\nCommands: add, list, total, delete <id>, exit");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equals("add")) {
                System.out.print("Description: ");
                String desc = scanner.nextLine();
                System.out.print("Amount: ");
                try {
                    double amount = Double.parseDouble(scanner.nextLine());
                    app.addExpense(desc, amount);
                    System.out.println("Expense added.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount.");
                }

            } else if (input.equals("list")) {
                List<Expense> expenses = app.getExpenses();
                if (expenses.isEmpty()) {
                    System.out.println("No expenses yet.");
                } else {
                    for (int i = 0; i < expenses.size(); i++) {
                        System.out.println((i + 1) + ". " + expenses.get(i));
                    }
                }

            } else if (input.equals("total")) {
                System.out.printf("Total: ₹%.2f\n", app.getTotal());

            } else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (app.deleteExpense(index)) {
                        System.out.println("Expense deleted.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid index.");
                }

            } else if (input.equals("exit")) {
                System.out.println("Exiting. Bye!");
                break;

            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }
}
