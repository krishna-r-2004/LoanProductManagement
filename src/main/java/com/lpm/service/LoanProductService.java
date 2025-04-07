package com.lpm.service;

import com.lpm.model.LoanProduct;
import com.lpm.repository.LoanProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing loan products.
 * This class provides methods to add, update, retrieve, and list loan products.
 */
@Service
public class LoanProductService {

    @Autowired
    private LoanProductRepository loanProductRepository;

    /**
     * Adds a new loan product to the repository.
     *
     * @param loanProduct The loan product to be added.
     */
    public void addLoanProduct(LoanProduct loanProduct) {
        loanProductRepository.save(loanProduct);
    }

    /**
     * Updates an existing loan product in the repository.
     *
     * @param loanProduct The loan product to be updated.
     */
    public void updateLoanProduct(LoanProduct loanProduct) {
        loanProductRepository.save(loanProduct);
    }

    /**
     * Retrieves the details of a loan product by its ID.
     *
     * @param loanProductId The ID of the loan product.
     * @return The loan product details, or null if not found.
     */
    public LoanProduct getLoanProductDetails(int loanProductId) {
        return loanProductRepository.findById(loanProductId).orElse(null);
    }

    /**
     * Retrieves a list of all loan products in the repository.
     *
     * @return A list of all loan products.
     */
    public List<LoanProduct> getAllLoanProducts(){
        return loanProductRepository.findAll();
    }
}