package com.ust.Expensive_application.service;

import com.ust.Expensive_application.dto.Expensivedto;
import com.ust.Expensive_application.exception.ExpensiveNotFoundException;
import com.ust.Expensive_application.model.Expensive;
import com.ust.Expensive_application.repo.Expensiverepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpensiveService {
    @Autowired
    private Expensiverepo repo;
    public Expensive addExpensive(Expensivedto dto) {
        Expensive expensive=new Expensive();
        expensive.setName(dto.getName());
        expensive.setTypetranscations(dto.getTypetranscations());
        expensive.setAmount(dto.getAmount());
        expensive.setPaymentType(dto.getPaymentType());
        expensive.setDescription(dto.getDescription());
        return repo.save(expensive);
    }

    
    public Expensive getExpensive(UUID id) throws ExpensiveNotFoundException {
        Expensive expensive=repo.findById(id)
                .orElseThrow(()->new ExpensiveNotFoundException("Expensive not found with id: "+id));
        return expensive;
    }

    // Fetch total income or expense for a specific date
    public double getTotalForDate(Date date, String typetranscations) {
        List<Expensive> transactions = repo.findAll()
                .stream()
                .filter(expensive -> expensive.getTypetranscations().equalsIgnoreCase(typetranscations)
                        && expensive.getCreateDate().equals(date))
                .collect(Collectors.toList());

        return transactions.stream().mapToDouble(Expensive::getAmount).sum();
    }

    // Fetch total for a specific payment method (cash, credit, debit)
    public double getTotalForPaymentMethod(Date date, String paymentMethod) {
        List<Expensive> transactions = repo.findAll()
                .stream()
                .filter(expensive -> expensive.getPaymentType().equalsIgnoreCase(paymentMethod)
                        && expensive.getCreateDate().equals(date))
                .collect(Collectors.toList());

        return transactions.stream().mapToDouble(Expensive::getAmount).sum();
    }

    // Fetch total income/expense for a specific month
    public double getTotalForMonth(int month, String typetranscations) {
        List<Expensive> transactions = repo.findAll()
                .stream()
                .filter(expensive -> expensive.getTypetranscations().equalsIgnoreCase(typetranscations)
                        && expensive.getCreateDate().getMonth() == month)
                .collect(Collectors.toList());

        return transactions.stream().mapToDouble(Expensive::getAmount).sum();
    }

    // Calculate the total balance (income - expenses)
    public double getBalance() {
        double totalIncome = getTotalForMonth(new Date().getMonth(), "Income");
        double totalExpense = getTotalForMonth(new Date().getMonth(), "Expense");
        return totalIncome - totalExpense;
    }
}
