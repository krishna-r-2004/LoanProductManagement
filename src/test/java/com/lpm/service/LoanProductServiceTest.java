package com.lpm.service;

import com.lpm.model.LoanProduct;
import com.lpm.repository.LoanProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the LoanProductService class.
 */
class LoanProductServiceTest {

    @Mock
    private LoanProductRepository loanProductRepository;

    @InjectMocks
    private LoanProductService loanProductService;

    private LoanProduct loanProduct1;
    private LoanProduct loanProduct2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loanProduct1 = new LoanProduct();
        loanProduct1.setLoanProductId(1);
        loanProduct1.setProductName("Home Loan");
        loanProduct1.setInterestRate(BigDecimal.valueOf(5.5));
        loanProduct1.setMinAmount(BigDecimal.valueOf(100000.0));
        loanProduct1.setMaxAmount(BigDecimal.valueOf(5000000.0));
        loanProduct1.setTenure(20);

        loanProduct2 = new LoanProduct();
        loanProduct2.setLoanProductId(2);
        loanProduct2.setProductName("Car Loan");
        loanProduct2.setInterestRate(BigDecimal.valueOf(7.0));
        loanProduct2.setMinAmount(BigDecimal.valueOf(50000.0));
        loanProduct2.setMaxAmount(BigDecimal.valueOf(1000000.0));
        loanProduct2.setTenure(7);
    }

    @Test
    void addLoanProduct() {
        loanProductService.addLoanProduct(loanProduct1);
        verify(loanProductRepository, times(1)).save(loanProduct1);
    }

    @Test
    void updateLoanProduct() {
        loanProductService.updateLoanProduct(loanProduct1);
        verify(loanProductRepository, times(1)).save(loanProduct1);
    }

    @Test
    void getLoanProductDetails_ProductFound() {
        when(loanProductRepository.findById(1)).thenReturn(Optional.of(loanProduct1));
        LoanProduct retrievedProduct = loanProductService.getLoanProductDetails(1);
        assertNotNull(retrievedProduct);
        assertEquals("Home Loan", retrievedProduct.getProductName());
        assertEquals(BigDecimal.valueOf(5.5), retrievedProduct.getInterestRate());
    }

    @Test
    void getLoanProductDetails_ProductNotFound() {
        when(loanProductRepository.findById(anyInt())).thenReturn(Optional.empty());
        LoanProduct retrievedProduct = loanProductService.getLoanProductDetails(99);
        assertNull(retrievedProduct);
    }

    @Test
    void getAllLoanProducts() {
        List<LoanProduct> allProducts = Arrays.asList(loanProduct1, loanProduct2);
        when(loanProductRepository.findAll()).thenReturn(allProducts);
        List<LoanProduct> retrievedProducts = loanProductService.getAllLoanProducts();
        assertNotNull(retrievedProducts);
        assertEquals(2, retrievedProducts.size());
        assertEquals("Home Loan", retrievedProducts.get(0).getProductName());
        assertEquals("Car Loan", retrievedProducts.get(1).getProductName());
    }
}