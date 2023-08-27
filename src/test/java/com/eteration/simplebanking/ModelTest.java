package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.transactionTypes.DepositTransaction;
import com.eteration.simplebanking.model.transactionTypes.WithdrawalTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {

    @Test
    public void testCreateAccountAndSetBalance0() {
        Account account = new Account("17892", "Kerem Karaca");
        assertTrue(account.getOwnerName().equals("Kerem Karaca"));
        assertTrue(account.getAccountNumber().equals("17892"));
        assertTrue(account.getBalance() == 0);
    }

    @Test
    public void testDepositIntoBankAccount() {
        Account account = new Account("9834", "Demet Demircan");
        account.deposit(100);
        assertTrue(account.getBalance() == 100);
    }

    @Test
    public void testWithdrawFromBankAccount() {
        Account account = new Account("9834", "Demet Demircan");
        account.deposit(100);
        assertTrue(account.getBalance() == 100);
        account.withdraw(50);
        assertTrue(account.getBalance() == 50);
    }

    @Test
    public void testTransactions() throws RuntimeException {
        // Create account
        Account account = new Account("1234", "Canan Kaya");
        assertTrue(account.getTransactions().size() == 0);

        // Deposit Transaction
        DepositTransaction depositTrx = new DepositTransaction(100.0);
        account.post(depositTrx);
        assertTrue(account.getBalance() == 100);
        assertTrue(account.getTransactions().size() == 1);

        // Withdrawal Transaction
        WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60.0);
        account.post(withdrawalTrx);
        assertTrue(account.getBalance() == 40);
        assertTrue(account.getTransactions().size() == 2);
    }
}
