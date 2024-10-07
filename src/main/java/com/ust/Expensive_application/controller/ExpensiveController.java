package com.ust.Expensive_application.controller;


import com.ust.Expensive_application.dto.Expensivedto;
import com.ust.Expensive_application.exception.ExpensiveNotFoundException;
import com.ust.Expensive_application.model.Expensive;
import com.ust.Expensive_application.service.ExpensiveService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("/expensive")
public class ExpensiveController {
    @Autowired
    private ExpensiveService expensiveService;

    @PostMapping("/addexpensive")
    public ResponseEntity<Expensive> addExpensive(@RequestBody  @Valid Expensivedto dto) {
        return new ResponseEntity<>(expensiveService.addExpensive(dto), HttpStatus.CREATED);
    }
    @GetMapping("/getexpensive/{id}")
    public ResponseEntity<Expensive> getExpensive(@PathVariable UUID id) throws ExpensiveNotFoundException {
        return  ResponseEntity.ok(expensiveService.getExpensive(id));
    }

    // 1. Get total income or expenses for a specific date
    @GetMapping("/total-for-date")
    public ResponseEntity<Double> getTotalForDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                  @RequestParam String typetranscations) {
        return ResponseEntity.ok(expensiveService.getTotalForDate(date, typetranscations));
    }

    // 2. Get total income/expenses for a specific payment method (cash, credit, debit)
    @GetMapping("/total-for-payment-method")
    public ResponseEntity<Double> getTotalForPaymentMethod(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                           @RequestParam String paymentMethod) {
        return ResponseEntity.ok(expensiveService.getTotalForPaymentMethod(date, paymentMethod));
    }

    // 3. Get total income or expenses for a specific month
    @GetMapping("/total-for-month")
    public ResponseEntity<Double> getTotalForMonth(@RequestParam int month, @RequestParam String typetranscations) {
        return ResponseEntity.ok(expensiveService.getTotalForMonth(month, typetranscations));
    }

    // 4. Get current balance (income - expenses)
    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance() {
        return ResponseEntity.ok(expensiveService.getBalance());
    }

}


