package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.dto.request.AccountRequest;
import com.eteration.simplebanking.controller.dto.request.TransactionRequest;
import com.eteration.simplebanking.controller.dto.response.TransactionResultResponse;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repositories.AccountRepository;
import com.eteration.simplebanking.services.impl.AccountServiceImpl;
import com.eteration.simplebanking.services.validation.AccountValidationService;
import com.eteration.simplebanking.services.validation.TransactionValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {


    @Mock
    private AccountController controller;


    @InjectMocks
    private AccountServiceImpl service;
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountValidationService accountValidationService;

    @Mock
    private TransactionValidationService transactionValidationService;

    @Mock
    private Transaction transaction;

    @Mock
    List<Transaction> transactions;
    @Mock
    Account account_2;
    @Test
    public void givenId_Credit_thenReturnJson()
            throws Exception {
        // Hesap oluşturma isteği
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber("17892");
        accountRequest.setOwnerName("Kerem Karaca");

        // Hesap oluşturma
        Account account = new Account("17892", "Kerem Karaca");
        account.setTransactions(transactions);
        service.createAccount(accountRequest);

        // İşlem isteği oluşturma
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(1000.0);

        // Servisin sahte çağrısını yapılandırma

        Mockito.when(service.findAccount(accountRequest.getAccountNumber())).thenReturn(account);
        Mockito.when(transaction.executeTransactionIn(Mockito.any(Account.class))).thenReturn("TEST");
        Mockito.when(account_2.post(transaction)).thenReturn("TEST");

        // Denetleyici metodunu çağırma
        ResponseEntity<TransactionResultResponse> result = service.credit(accountRequest.getAccountNumber(), transactionRequest);

        // Sonucun kontrolü
        assertEquals("200 OK", result.getBody().getStatus().toString());

    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson()
            throws Exception {

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber("17892");
        accountRequest.setOwnerName("Kerem Karaca");

        // Hesap oluşturma
        Account account = new Account("17892", "Kerem Karaca");
        account.setTransactions(transactions);
        service.createAccount(accountRequest);


        // Account account = new Account("Kerem Karaca", "17892");
        TransactionRequest transactionCreditRequest = new TransactionRequest();
        transactionCreditRequest.setAmount(1000.0);
        TransactionRequest transactionDebitRequest = new TransactionRequest();
        transactionDebitRequest.setAmount(50.0);

        Mockito.when(service.findAccount(accountRequest.getAccountNumber())).thenReturn(account);
        Mockito.when(transaction.executeTransactionIn(Mockito.any(Account.class))).thenReturn("TEST");
        Mockito.when(account_2.post(transaction)).thenReturn("TEST");

   //     doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<TransactionResultResponse> result = service.credit(account.getAccountNumber(),transactionCreditRequest );
        ResponseEntity<TransactionResultResponse> result2 = service.debit( account.getAccountNumber(),transactionDebitRequest);
        //verify(service, times(1)).findAccount("17892");
       assertEquals("200 OK", result.getBody().getStatus().toString());
       /*  assertEquals("OK", result2.getBody().getStatus());
        assertEquals(950.0, account.getBalance(),0.001);*/
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
            throws RuntimeException {
        Assertions.assertThrows( RuntimeException.class, () -> {
            AccountRequest accountRequest = new AccountRequest();
            accountRequest.setAccountNumber("17892");
            accountRequest.setOwnerName("Kerem Karaca");

            // Hesap oluşturma
            Account account = new Account("17892", "Kerem Karaca");
            account.setTransactions(transactions);
            service.createAccount(accountRequest);


           // Account account = new Account("Kerem Karaca", "17892");
            TransactionRequest transactionCreditRequest = new TransactionRequest();
            transactionCreditRequest.setAmount(1000.0);
            TransactionRequest transactionDebitRequest = new TransactionRequest();
            transactionDebitRequest.setAmount(5000.0);

            Mockito.when(service.findAccount(accountRequest.getAccountNumber())).thenReturn(account);
            Mockito.when(transaction.executeTransactionIn(Mockito.any(Account.class))).thenReturn("TEST");
            Mockito.when(account.post(transaction)).thenReturn("TEST");

//            doReturn(account).when(service).findAccount( "17892");
            ResponseEntity<TransactionResultResponse> result = service.credit(account.getAccountNumber(),transactionCreditRequest);

            assertEquals("200 OK", result.getBody().getStatus().toString());
            assertEquals(1000.0, account.getBalance(),0.001);
            //verify(service, times(1)).findAccount("17892");

            ResponseEntity<TransactionResultResponse> result2 = service.debit(account.getAccountNumber(),transactionDebitRequest);
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson()
            throws Exception {

       // Account account = new Account("Kerem Karaca", "17892");
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccountNumber("17892");
        accountRequest.setOwnerName("Kerem Karaca");

        // Hesap oluşturma
        Account account = new Account("17892", "Kerem Karaca");
        account.setTransactions(transactions);
        service.createAccount(accountRequest);

        Mockito.when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
        //doReturn(account).when(service).findAccount( "17892");
        Account  result = service.findAccount( account.getAccountNumber());
        //verify(service, times(1)).findAccount("17892");
        assertEquals(account, result);
    }

}
