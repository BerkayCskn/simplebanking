package com.eteration.simplebanking;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	AccountRepository accountRepository;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}
	@Bean
	public void demo(){
		String randomNum = String.valueOf(ThreadLocalRandom.current().nextInt(0, 100000));
		Account account = new Account("Berkay", randomNum);
		account.post(new DepositTransaction(1000));
		account.post(new WithdrawalTransaction(200));
		account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));
		accountRepository.save(account);
//		assertEquals(account.getBalance(), 703.50, 0.0001);

	}

}
