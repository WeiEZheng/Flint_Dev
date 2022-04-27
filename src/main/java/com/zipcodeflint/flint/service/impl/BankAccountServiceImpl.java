package com.zipcodeflint.flint.service.impl;

import com.zipcodeflint.flint.domain.BankAccount;
import com.zipcodeflint.flint.repository.BankAccountRepository;
import com.zipcodeflint.flint.service.BankAccountService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BankAccount}.
 */
@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        log.debug("Request to save BankAccount : {}", bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        log.debug("Request to save BankAccount : {}", bankAccount);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public Optional<BankAccount> partialUpdate(BankAccount bankAccount) {
        log.debug("Request to partially update BankAccount : {}", bankAccount);

        return bankAccountRepository
            .findById(bankAccount.getId())
            .map(existingBankAccount -> {
                if (bankAccount.getAccountNumber() != null) {
                    existingBankAccount.setAccountNumber(bankAccount.getAccountNumber());
                }
                if (bankAccount.getAccountName() != null) {
                    existingBankAccount.setAccountName(bankAccount.getAccountName());
                }
                if (bankAccount.getBalance() != null) {
                    existingBankAccount.setBalance(bankAccount.getBalance());
                }
                if (bankAccount.getAccountType() != null) {
                    existingBankAccount.setAccountType(bankAccount.getAccountType());
                }

                return existingBankAccount;
            })
            .map(bankAccountRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankAccount> findAll() {
        log.debug("Request to get all BankAccounts");
        return bankAccountRepository.findAllWithEagerRelationships();
    }

    public Page<BankAccount> findAllWithEagerRelationships(Pageable pageable) {
        return bankAccountRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BankAccount> findOne(Long id) {
        log.debug("Request to get BankAccount : {}", id);
        return bankAccountRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankAccount : {}", id);
        bankAccountRepository.deleteById(id);
    }
}
