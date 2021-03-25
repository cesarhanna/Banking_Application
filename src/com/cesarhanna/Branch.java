package com.cesarhanna;

import java.util.*;

public class Branch {

    private String branchName;
    private final ArrayList<Customer> customers;
    private static final Scanner scanner = new Scanner(System.in);

    public Branch(String branchName) {
        this.branchName = branchName;
        this.customers = new ArrayList<>();
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void addCustomer(String customerName, Double initialTransaction) {
        UUID customerIdentifier = UUID.randomUUID();
        int customerIdentifierPosition = findCustomerId(customerIdentifier);
        if (customerIdentifierPosition < 0) {
            customers.add(new Customer(customerName, customerIdentifier, initialTransaction));
        }
        else {
            System.out.println("Customer already exist");
        }
        Collections.sort(customers, Comparator.comparing(Customer::getCustomerName)); // sorts the Customer ArrayList based on the customer names
    }

    public void addTransaction(UUID customerAccNum) throws InputMismatchException {
        int x = 1;
        do {
            try {
                int customerIdPosition = findCustomerId(customerAccNum);
                if (customerIdPosition >= 0) {
                    Customer customer = this.customers.get(customerIdPosition);
                    System.out.println("Add transaction: ");
                    Scanner scanner = new Scanner(System.in);
                    Double transaction = scanner.nextDouble();
                    customer.addTransaction(transaction);
                }
                else {
                    System.out.println("Customer doesn't exist");
                }
                x = 2;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (x == 1);
    }

    public void withdrawTransaction(UUID customerAccNum) throws InputMismatchException {
        int x = 1;
        do {
            try {
                int customerIdPosition = findCustomerId(customerAccNum);
                if (customerIdPosition >= 0) {
                    Customer customer = this.customers.get(customerIdPosition);
                    System.out.println("Add transaction: ");
                    Scanner scanner = new Scanner(System.in);
                    Double transaction = scanner.nextDouble();
                    if (transaction > 0) {
                        customer.withdrawTransaction(transaction);
                    } else {
                        System.out.println("Enter a valid amount");
                    }
                }
                else {
                    System.out.println("Customer doesn't exist");
                }
                x = 2;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (x == 1);
    }

    public void printCustomersTrans() throws IllegalArgumentException {
        int x = 1;
        do {
            try {
                scanner.nextLine();
                System.out.println("Customer Account Number: ");
                String customerId = scanner.nextLine();
                UUID customerUuid = UUID.fromString(customerId);
                int customerIdPosition = findCustomerId(customerUuid);
                if (customerIdPosition >= 0) {
                    System.out.println("List of transactions: " + customers.get(customerIdPosition).getCustomerName());
                    Customer selectedCustomer = this.customers.get(customerIdPosition);
                    ArrayList<Transaction> customerTransactions = selectedCustomer.getTransactions();
                    Double balanceAmount = 0.0;
                    for (Transaction customerTransaction : customerTransactions) {
                        System.out.println("\t" + "\t" + customerTransaction.getDate() + " --> " + customerTransaction.getAmount());
                        balanceAmount += customerTransaction.getAmount();
                        System.out.println("\t" + "\t" + "***********BALANCE***********" + "     " + balanceAmount);
                    }
                }
                else {
                    System.out.println("Customer does not exist");
                }
                x = 2;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account number. Please try again");
            }
        } while (x == 1);
    }

    public void calculateTransTotal() throws IllegalArgumentException {
        int x=1;
        do {
            try {
                scanner.nextLine();
                System.out.println("Customer Account Number: ");
                String customerId = scanner.nextLine();
                UUID customerUuid = UUID.fromString(customerId);
                int customerIdPosition = findCustomerId(customerUuid);
                if (customerIdPosition >= 0) {
                    Customer selectedCustomer = this.customers.get(customerIdPosition);
                    ArrayList<Transaction> transactionsList = selectedCustomer.getTransactions();
                    Double total = 0.0;
                    for (Transaction transaction : transactionsList) {
                        total = total + transaction.getAmount();
                    }
                    System.out.println("Total balance for " + customers.get(customerIdPosition).getCustomerName() + " is: " + total);
                }
                else {
                    System.out.println("Customer doesn't exist");
                }
                x = 2;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account number. Please try again");
            }
        } while (x == 1);
    }

    public void editCustomerInBranch() throws IllegalArgumentException {
        int x=1;
        do {
            try {
                System.out.println("Enter the customer's account number: ");
                String customerId = scanner.nextLine();
                UUID customerUuid = UUID.fromString(customerId);
                int customerIdPosition = findCustomerId(customerUuid);
                if (customerIdPosition >= 0) {
                    Customer selectedCustomer = this.customers.get(customerIdPosition);
                    scanner.nextLine();
                    System.out.println("Enter new customer name: ");
                    String newCustomerName = scanner.nextLine();
                    selectedCustomer.setCustomerName(newCustomerName);
                }
                else {
                    System.out.println("Customer doesn't exist");
                }
                x = 2;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account number. Please try again");
            }
        } while (x == 1);
    }

    public void editCustomerTransaction() throws IllegalArgumentException {
        int x = 1;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter customer's account number: ");
                String customerId = scanner.nextLine();
                UUID customerUuid = UUID.fromString(customerId);
                int customerIdPosition = findCustomerId(customerUuid);
                if (customerIdPosition >= 0) {
                    Customer selectedCustomer = this.customers.get(customerIdPosition);
                    selectedCustomer.editCustomerTransAmount();
                }
                else {
                    System.out.println("Customer doesn't exist");
                }
                x = 2;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account number. Please try again");
            }
        } while (x == 1);
    }

    public void removeCustomerFromBranch() throws IllegalArgumentException {
        int x = 1;
        do {
            try {
                System.out.println("Enter customer's account number: ");
                String customerId = scanner.nextLine();
                UUID customerUuid = UUID.fromString(customerId);
                int customerIdPosition = findCustomerId(customerUuid);
                if (customerIdPosition >= 0) {
                    customers.remove(customerIdPosition);
                } else {
                    System.out.println("Customer doesn't exist");
                }
                x = 2;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account number. Please try again");
            }
        } while (x == 1);
    }

    private int findCustomerId(UUID customerIdentifier) {
        for (int i=0; i<customers.size(); i++) {
            Customer customer = this.customers.get(i);
            if (customer.getCustomerUniqueIdentifier().equals(customerIdentifier)) {
                return  i;
            }
        }
        return -1;
    }
}
