package com.cesarhanna;

public interface IFunctionalities {
    void addBranch(String branchName);
    void addCustomerToBranch(String branchName, String customerName, Double transaction);
    void addTransactionToCustomer(String branchName);
    void withdrawTransaction(String branchName);
    void printCustomers();
    void printTransactionsList();
    void customerTransTotal(String branchName);
    void editBranchName(String branchName);
    void editCustomerName(String branchName);
    void editTransactionAmount(String branchName);
    void removeBankBranch(String branchName);
    void removeBranchCustomer(String branchName);
}
