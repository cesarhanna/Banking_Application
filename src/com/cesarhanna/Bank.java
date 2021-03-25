package com.cesarhanna;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bank implements IFunctionalities {

    private final String mainBankName;
    private final ArrayList<Branch> bank;
    private static final Scanner scanner = new Scanner(System.in);

    public Bank(String mainBankName) {
        this.mainBankName = mainBankName;
        this.bank = new ArrayList<>();
    }

    public String getMainBankName() {
        return mainBankName;
    }

    public void addBranch(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition < 0) {
            bank.add(new Branch(branchName));
        }
        else {
            System.out.println("Branch already exist");
        }
        Collections.sort(bank, Comparator.comparing(Branch::getBranchName)); // sorts the Branch ArrayList based on branch names
    }

    public void addCustomerToBranch(String branchName, String customerName, Double transaction) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            Branch branch = this.bank.get(branchPosition);
            branch.addCustomer(customerName, transaction);
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void addTransactionToCustomer(String branchName) throws IllegalArgumentException {
        int x = 1;
        do {
            try {
                int branchPosition = findBranch(branchName);
                if (branchPosition >= 0) {
                    Branch branch = this.bank.get(branchPosition);
                    System.out.println("Customer Account Number: ");
                    String customerAccNum = scanner.nextLine();
                    UUID customerAccountNumber = UUID.fromString(customerAccNum); // Here we are putting into a String space a parameter of type UUID.
                    branch.addTransaction(customerAccountNumber);
                }
                else {
                    System.out.println("Branch doesn't exist");
                }
                x = 2;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (x == 1);

    }

    public void withdrawTransaction(String branchName) throws IllegalArgumentException {
        int x = 1;
        do {
            try {
                int branchPosition = findBranch(branchName);
                if (branchPosition >= 0) {
                    Branch branch = this.bank.get(branchPosition);
                    System.out.println("Customer Account Number: ");
                    String customerAccNum = scanner.nextLine();
                    UUID customerAccountNumber = UUID.fromString(customerAccNum); // Here we are putting into a String space a parameter of type UUID.
                    branch.withdrawTransaction(customerAccountNumber);
                }
                else {
                    System.out.println("Branch doesn't exist");
                }
                x = 2;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again");
            }
        } while (x == 1);
    }

    public void printCustomers() {
        if (!bank.isEmpty()) {
            for (Branch value : bank) {
                System.out.println("\n");
                System.out.println("List of customers in branch: " + value.getBranchName());
                Branch branch = value;
                ArrayList<Customer> branchCustomers = branch.getCustomers();
                for (int j = 0; j < branchCustomers.size(); j++) {
                    Customer customer = branchCustomers.get(j);
                    System.out.println("\t" + j + 1 + ". " + branchCustomers.get(j).getCustomerName() + " - Cust. Account ID: " + branchCustomers.get(j).getCustomerUniqueIdentifier());
                    ArrayList<Transaction> branchTransactions = customer.getTransactions();
                    for (Transaction branchTransaction : branchTransactions) {
                        System.out.println("\t" + "\t" + branchTransaction.getDate() + " --> Transaction: " + branchTransaction.getAmount());
                        System.out.println("\t" + "***************************************************************");
                    }
                }
            }
        }
        else {
            System.out.println("List of customers can not be printed");
        }
    }

    public void printTransactionsList() {
        scanner.nextLine();
        System.out.println("In branch: ");
        String branchName = scanner.nextLine();
        int branchPosition = findBranch(branchName);
        if (branchPosition >=0) {
            Branch selectedBranch = this.bank.get(branchPosition);
            selectedBranch.printCustomersTrans();
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void customerTransTotal(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            Branch selectedBranch = this.bank.get(branchPosition);
            selectedBranch.calculateTransTotal();
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void editBranchName(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            Branch selectedBranch = this.bank.get(branchPosition);
            scanner.nextLine();
            System.out.println("Enter new branch name: ");
            String newBranchName = scanner.nextLine();
            selectedBranch.setBranchName(newBranchName);
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void editCustomerName(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            Branch selectedBranch = this.bank.get(branchPosition);
            selectedBranch.editCustomerInBranch();
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void editTransactionAmount(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            Branch selectedBranch = this.bank.get(branchPosition);
            selectedBranch.editCustomerTransaction();
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void removeBankBranch(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            bank.remove(branchPosition);
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void removeBranchCustomer(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            Branch selectedBranch = this.bank.get(branchPosition);
            selectedBranch.removeCustomerFromBranch();
        }
        else {
            System.out.println("Branch doesn't exist");
        }
    }

    public void printCustomerData(String branchName) {
        int branchPosition = findBranch(branchName);
        if (branchPosition >= 0) {
            Branch branch = this.bank.get(branchPosition);
            ArrayList<Customer> customers = branch.getCustomers();
            for (int i=0; i<customers.size(); i++) {
                System.out.println("\t" + i + 1 + ". " + customers.get(i).getCustomerName() + " - " + "Cust. Account ID: " + customers.get(i).getCustomerUniqueIdentifier());
                ArrayList<Transaction> customerTransactions = customers.get(i).getTransactions();
                Double balance = 0.0;
                for (Transaction customerTransaction : customerTransactions) {
                    balance += customerTransaction.getAmount();
                }
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                dateFormat.format(date);
                System.out.println("\t" + "\t" + date + " --> Balance: " + balance);
                System.out.println("\t" + "***************************************************************");
            }
        }
        else {
            System.out.println("Branch " + branchName + " does not exist");
        }
    }

    private int findBranch(String nameOfBranch) {
        for (int i = 0; i < bank.size(); i++) {
            Branch branch = this.bank.get(i);
            if (branch.getBranchName().equals(nameOfBranch)) {
                return i;
            }
        }
        return -1;
    }
}
