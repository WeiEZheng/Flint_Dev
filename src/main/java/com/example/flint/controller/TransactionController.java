package com.example.flint.controller;

import com.example.flint.model.Transaction;
import com.example.flint.model.enumeration.TransactionType;
import com.example.flint.repository.TransactionRepository;
import com.example.flint.service.TransactionServices;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

//Heavily influenced by the controller generated by JHipster

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionServices transactionServices;

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionServices transactionServices, TransactionRepository transactionRepository) {
        this.transactionServices = transactionServices;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@Validated @RequestBody Transaction transaction) throws URISyntaxException {
        if (transaction.getId() != null) {
//            need to throw exception for existing entity
        }
        Transaction result = transactionServices.save(transaction);
        return ResponseEntity
                .created(new URI("/api/transactions/" + result.getId()))
                .header("message here")
                .body(result);
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> listOfAllTransactions = transactionServices.findAll();
        return ResponseEntity.ok().body(listOfAllTransactions);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionServices.findOne(id);
        return ResponseEntity.ok().body(transaction.get());
    }


    @GetMapping("/transactions/findByDate")
    public ResponseEntity<List<Transaction>> getTransactionsDateOfTransaction(@RequestParam(value = "exact", required = false) Instant dateOfTransaction,
                                                                     @RequestParam(value = "start", required = false) Instant dateOfTransactionStart,
                                                                     @RequestParam(value = "end", required = false) Instant dateOfTransactionEnd) {
        List<Transaction> list;
        if (dateOfTransaction != null) {
            list = transactionServices.findByDateOfTransaction(dateOfTransactionStart);
        } else if (dateOfTransactionStart != null && dateOfTransactionEnd != null){
            list = transactionServices.findByDateOfTransactionIsBetween(dateOfTransactionStart, dateOfTransactionEnd);
        } else {
            list = transactionServices.findAll();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable Long accountNumber,
                                                                      @RequestParam(value = "type", defaultValue = "to") String typeOfRequest) {
        List<Transaction> list;
        if (typeOfRequest.equalsIgnoreCase("to"))
            list = transactionServices.findByToAccountNumber(accountNumber);
        else
            list = transactionServices.findByFromAccountNumber(accountNumber);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/transactions/findByAmount")
    public ResponseEntity<List<Transaction>> getTransactionsByAmount(@RequestParam(value = "type", required = false) String typeOfRequest,
                                                                     @RequestParam(value = "start", required = false) BigDecimal transactionAmountStart,
                                                                     @RequestParam(value = "end", required = false) BigDecimal transactionAmountEnd) {
        List<Transaction> list;
        if (typeOfRequest.equalsIgnoreCase("between") &&
                transactionAmountStart != null && transactionAmountEnd != null) {
            list = transactionServices.findByTransactionAmountIsBetween(transactionAmountStart, transactionAmountStart);
        } else if (typeOfRequest.equalsIgnoreCase("more") && transactionAmountStart != null){
            list = transactionServices.findByTransactionAmountIsGreaterThanEqual(transactionAmountStart);
        }
        else if (typeOfRequest.equalsIgnoreCase("less") && transactionAmountEnd != null) {
            list = transactionServices.findByTransactionAmountIsGreaterThanEqual(transactionAmountStart);
        } else {
            list = transactionServices.findByTransactionAmountLessThanEqual(transactionAmountEnd);
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/transactions/{typeOfTransaction}")
    public ResponseEntity<List<Transaction>> getTransactionByType(@PathVariable TransactionType typeOfTransaction) {
        List<Transaction> list = transactionServices.findByTypeOfTransaction(typeOfTransaction);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionServices.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactionByCategory_Id(@RequestParam (value = "category") Long id) {
        List<Transaction> list = transactionServices.findByCategory_Id(id);
        return ResponseEntity.ok().body(list);
    }

    // todo: need to do update and partial update
}
