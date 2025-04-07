package com.lpm.service;

import com.lpm.model.LoanProduct;
import com.lpm.repository.LoanProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanProductService {

    @Autowired
    private LoanProductRepository loanProductRepository;

    public LoanProduct addLoanProduct(LoanProduct loanProduct) {
        return loanProductRepository.save(loanProduct);
    }

    public LoanProduct updateLoanProduct(LoanProduct loanProduct) {
        return loanProductRepository.save(loanProduct);
    }

    public LoanProduct getLoanProductDetails(int loanProductId) {
        return loanProductRepository.findById(loanProductId).orElse(null);
    }

    public List<LoanProduct> getAllLoanProducts(){
        return loanProductRepository.findAll();
    }
}