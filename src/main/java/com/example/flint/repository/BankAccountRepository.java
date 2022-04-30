package com.example.flint.repository;

import com.example.flint.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


}
