package com.lpm.controller;

import com.lpm.exception.LoanProductNotFoundException;
import com.lpm.model.LoanProduct;
import com.lpm.service.LoanProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the LoanProductController class.
 */
class LoanProductControllerTest {

    @Mock
    private LoanProductService loanProductService;

    @InjectMocks
    private LoanProductController loanProductController;

    @Mock
    private RedirectAttributes redirectAttributes;

    private LoanProduct loanProduct;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loanProduct = new LoanProduct();
        loanProduct.setLoanProductId(1);
        loanProduct.setProductName("Home Loan");
        loanProduct.setInterestRate(BigDecimal.valueOf(5.5));
        loanProduct.setMinAmount(BigDecimal.valueOf(100000.0));
        loanProduct.setMaxAmount(BigDecimal.valueOf(5000000.0));
        loanProduct.setTenure(12);
    }

    /**
     * Tests the showAddLoanProductForm method.
     */
    @Test
    public void showAddLoanProductForm() {
        ModelAndView modelAndView = loanProductController.showAddLoanProductForm();
        assertNotNull(modelAndView);
        assertEquals("loanProducts", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("loanProduct"));
        assertTrue(modelAndView.getModel().containsKey("add"));
    }

    /**
     * Tests the addLoanProduct method.
     */
    @Test
    public void addLoanProduct() {
        doNothing().when(loanProductService).addLoanProduct(any(LoanProduct.class));
        String result = loanProductController.addLoanProduct(loanProduct, redirectAttributes);
        assertEquals("redirect:/loanProduct/viewAll", result);
        verify(redirectAttributes).addFlashAttribute("message", "Loan product added successfully!");
    }

    /**
     * Tests the showUpdateLoanProductForm method when the loan product is found.
     */
    @Test
    public void showUpdateLoanProductForm_ProductFound() throws LoanProductNotFoundException {
        when(loanProductService.getLoanProductDetails(1)).thenReturn(loanProduct);
        ModelAndView modelAndView = loanProductController.showUpdateLoanProductForm(1);
        assertNotNull(modelAndView);
        assertEquals("loanProducts", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("loanProduct"));
        assertTrue(modelAndView.getModel().containsKey("update"));
        assertEquals(loanProduct, modelAndView.getModel().get("loanProduct"));
    }

    /**
     * Tests the updateLoanProduct method.
     */
    @Test
    public void updateLoanProduct() {
        doNothing().when(loanProductService).updateLoanProduct(any(LoanProduct.class));
        String result = loanProductController.updateLoanProduct(loanProduct, redirectAttributes);
        assertEquals("redirect:/loanProduct/viewAll", result);
        verify(redirectAttributes).addFlashAttribute("message", "Loan product updated successfully!");
    }

    /**
     * Tests the viewAllLoanProducts method.
     */
    @Test
    public void viewAllLoanProducts() {
        List<LoanProduct> loanProducts = Arrays.asList(loanProduct);
        when(loanProductService.getAllLoanProducts()).thenReturn(loanProducts);
        ModelAndView modelAndView = loanProductController.viewAllLoanProducts();
        assertNotNull(modelAndView);
        assertEquals("loanProducts", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("loanProducts"));
        assertEquals(loanProducts, modelAndView.getModel().get("loanProducts"));
    }

    /**
     * Tests the getLoanProductDetails method when the loan product is found.
     */
    @Test
    public void getLoanProductDetails_ProductFound() throws LoanProductNotFoundException {
        when(loanProductService.getLoanProductDetails(1)).thenReturn(loanProduct);
        ModelAndView modelAndView = loanProductController.getLoanProductDetails(1);
        assertNotNull(modelAndView);
        assertEquals("loanProducts", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("loanProduct"));
        assertTrue(modelAndView.getModel().containsKey("details"));
        assertEquals(loanProduct, modelAndView.getModel().get("loanProduct"));
    }

    /**
     * Tests that loan product parameters are not null.
     */
    @Test
    public void testLoanProductParametersNotNull() {
        assertNotNull(loanProduct.getLoanProductId(), "Loan Product ID should not be null");
        assertNotNull(loanProduct.getInterestRate(), "Interest Rate should not be null");
        assertNotNull(loanProduct.getMinAmount(), "Minimum Amount should not be null");
        assertNotNull(loanProduct.getMaxAmount(), "Maximum Amount should not be null");
        assertNotNull(loanProduct.getTenure(), "Tenure should not be null");
    }

    /**
     * Tests that the loan product name is not empty.
     */
    @Test
    public void testLoanProductNameNotEmpty() {
        assertFalse(loanProduct.getProductName().isEmpty(), "Product Name should not be empty");
    }
}