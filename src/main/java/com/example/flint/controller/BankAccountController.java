//package com.example.flint.controller;
//
//
//import com.example.flint.model.BankAccount;
//
//import com.example.flint.service.BankAccountService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@RestController
//public class BankAccountController {
//
//    @Autowired
//    BankAccountService bankAccountServe;
//
//    @RequestMapping(value="bankaccount", method = RequestMethod.POST)
//    public ResponseEntity<Void> create(@RequestBody BankAccount bankAccount){ //UriComponentsBuilder ucBuilder
//        log.info("Opening new account");
//        Long id = bankAccount.getId();
//        if (bankAccountServe.exists(id)) {
//            log.info("Account already exists...");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
//        bankAccountServe.createNewBankAccount(bankAccount);
////        HttpHeaders headers = new HttpHeaders();
////        headers.setLocation(ucBuilder.path("/bankaccount/{id}").buildAndExpand(bankAccount.getId().toUri()));
//        return new ResponseEntity<Void>(HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value="/bankaccount", method = RequestMethod.GET)
//    public ResponseEntity<List<BankAccount>> findAll() {
//        log.info("Getting all bank accounts");
//        List<BankAccount> bankAccountList = bankAccountServe.getAllBankAccounts();
//        if(bankAccountList == null || bankAccountList.isEmpty()){
//            log.info("No bank accounts found...");
//            return new ResponseEntity<List<BankAccount>>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<List<BankAccount>>(bankAccountList, HttpStatus.OK);
//    }
//
//    @RequestMapping(value="/bankaccount/{id}", method = RequestMethod.GET)
//    public ResponseEntity<BankAccount> get(@PathVariable("id") Long id) {
//        log.info("Getting bank account");
//        Optional<BankAccount> bankAccount = bankAccountServe.getBankAccount(id);
//        return bankAccount.map(response -> ResponseEntity.ok().body(response))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//
//}
