package com.example.flint.service;

import com.example.flint.model.BankAccount;
import com.example.flint.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepo;


    public List<BankAccount> getAllBankAccounts() {
        return (List<BankAccount>) bankAccountRepo.findAll();
    }

    public Optional<BankAccount> getBankAccount(Long id) {
        return bankAccountRepo.findById(id);
    }

    public BigDecimal getBankAccountBalance(Long id) {
        BankAccount bankAccount = bankAccountRepo.getById(id);
            return bankAccount.getBalance();
    }

    public BankAccount createNewBankAccount(BankAccount bankAccount) {
        return bankAccountRepo.save(bankAccount);
    }

    public void deleteAccount(Long id) { bankAccountRepo.deleteById(id);}


    public boolean exists(Long id) {
        bankAccountRepo.existsById(id);
        return false;
    }

}
