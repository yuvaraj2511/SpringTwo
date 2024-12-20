package com.example.Expense_Backend.services;

import com.example.Expense_Backend.entity.Expense;
import com.example.Expense_Backend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id " + id));
    }

    public Expense addCashInExpense(Expense expense) {
        expense.setType("Cash In");
        return expenseRepository.save(expense);
    }

    public Expense addCashOutExpense(Expense expense) {
        expense.setType("Cash Out");
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expense updateExpense(Expense updatedExpense, Long id) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id " + id));

        if (updatedExpense.getAmount() != null) {
            existingExpense.setAmount(updatedExpense.getAmount());
        }
        if (updatedExpense.getType() != null) {
            existingExpense.setType(updatedExpense.getType());
        }
        if (updatedExpense.getDate() != null) {
            existingExpense.setDate(updatedExpense.getDate());
        }
        if (updatedExpense.getPurpose() != null) {
            existingExpense.setPurpose(updatedExpense.getPurpose());
        }
        if (updatedExpense.getPaymentMode() != null) {
            existingExpense.setPaymentMode(updatedExpense.getPaymentMode());
        }

        return expenseRepository.save(existingExpense);
    }
}
