package com.cesarhanna;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank("THE BANKING SYSTEM");

    public static void main(String[] args) {
        System.out.print("WELCOME TO " + bank.getMainBankName() + "!");
        int x = 1;
        do {
            try {
                chooseOptions();
                x = 2;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (x == 1);
    }

    private static void chooseOptions() throws InputMismatchException {
                boolean option = true;
                int choice;
                optionsList();
                while (option) {
                    System.out.print("Enter an option between 0 and 14: ");
                    Scanner scanner = new Scanner(System.in); // this will create a new object each time you need to re-enter the values.
                                                                // it will solve the issue of passing always the same value that creates an infinite loop.
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 0:
                            option = false;
                            break;
                        case 1:
                            addBranch();
                            break;
                        case 2:
                            addCustomerToBranch();
                            break;
                        case 3:
                            depositTransactionToCustomer();
                            break;
                        case 4:
                            withdrawalTransactionToCustomer();
                            break;
                        case 5:
                            printCustomers();
                            break;
                        case 6:
                            printTransactionsList();
                            break;
                        case 7:
                            transactionsTotal();
                            break;
                        case 8:
                            editBranch();
                            break;
                        case 9:
                            editCustomer();
                            break;
                        case 10:
                            editTransaction();
                            break;
                        case 11:
                            removeBranch();
                            break;
                        case 12:
                            removeCustomer();
                            break;
                        case 13:
                            printCustomersInBranch();
                            break;
                        case 14:
                            optionsList();
                            break;
                    }
                }
            }

    private static void addBranch() {
        scanner.nextLine();
        System.out.println("Add branch: ");
        String branchName = scanner.nextLine();
        bank.addBranch(branchName);
    }

    private static void addCustomerToBranch() throws InputMismatchException {
        int x = 1;
        scanner.nextLine();
        System.out.println("Branch: ");
        String branchName = scanner.nextLine();
        System.out.println("Add customer: ");
        do {
            try {
                String customerName = scanner.nextLine();
                System.out.println("Initial transaction: ");
                Double initialTransaction = scanner.nextDouble();
                if (initialTransaction > 0) {
                    bank.addCustomerToBranch(branchName, customerName, initialTransaction);
                } else {
                    System.out.println("Enter a valid initial amount");
                    addCustomerToBranch();
                }
                x = 2;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (x == 1);
    }

    private static void depositTransactionToCustomer() {
        scanner.nextLine();
        System.out.println("Branch: ");
        String branchName = scanner.nextLine();
        bank.addTransactionToCustomer(branchName);
    }

    private static void withdrawalTransactionToCustomer() {
        scanner.nextLine();
        System.out.println("Branch: ");
        String branchName = scanner.nextLine();
        bank.withdrawTransaction(branchName);
    }

    private static void printCustomers() {
        bank.printCustomers();
    }

    private static void printTransactionsList() {
        bank.printTransactionsList();
    }

    private static void transactionsTotal() {
        scanner.nextLine();
        System.out.println("Enter branch name");
        String branchName = scanner.nextLine();
        bank.customerTransTotal(branchName);
    }

    private static void editBranch() {
        scanner.nextLine();
        System.out.println("Enter branch for editing: ");
        String branchName = scanner.nextLine();
        bank.editBranchName(branchName);
    }

    private static void editCustomer() {
        scanner.nextLine();
        System.out.println("Enter branch name: ");
        String branchName = scanner.nextLine();
        bank.editCustomerName(branchName);
    }

    private static void editTransaction() {
        scanner.nextLine();
        System.out.println("Enter branch name: ");
        String branchName = scanner.nextLine();
        bank.editTransactionAmount(branchName);
    }

    private static void removeBranch() {
        scanner.nextLine();
        System.out.println("Enter branch name to remove: ");
        String branchName = scanner.nextLine();
        bank.removeBankBranch(branchName);
    }

    private static void removeCustomer() {
        scanner.nextLine();
        System.out.println("Enter branch name: ");
        String branchName = scanner.nextLine();
        bank.removeBranchCustomer(branchName);
    }

    private static void printCustomersInBranch() {
        scanner.nextLine();
        System.out.println("Enter branch name: ");
        String branchName = scanner.nextLine();
        bank.printCustomerData(branchName);
    }

    private static void optionsList() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - Quit.");
        System.out.println("\t 1 - To add a new branch.");
        System.out.println("\t 2 - To add a customer to branch.");
        System.out.println("\t 3 - To make a deposit.");
        System.out.println("\t 4 - To make a withdrawal.");
        System.out.println("\t 5 - To print the list of customers in all available branches.");
        System.out.println("\t 6 - To print the list of transactions.");
        System.out.println("\t 7 - To show the total of customer's transactions.");
        System.out.println("\t 8 - To edit a branch.");
        System.out.println("\t 9 - To edit a customer.");
        System.out.println("\t 10 - To edit a transaction.");
        System.out.println("\t 11 - To remove a branch.");
        System.out.println("\t 12 - To remove a customer.");
        System.out.println("\t 13 - To print customers in branch.");
        System.out.println("\t 14 - To go to main menu.");
    }
}
