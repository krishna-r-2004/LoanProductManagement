package com.lpm.controller;

import com.lpm.model.LoanProduct;
import com.lpm.service.LoanProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/loanProduct")
public class LoanProductController {

    private static final Logger logger = LoggerFactory.getLogger(LoanProductController.class);

    @Autowired
    private LoanProductService loanProductService;

    @GetMapping("/add")
    public String showAddLoanProductForm(Model model) {
        logger.info("Displaying add loan product form.");
        model.addAttribute("loanProduct", new LoanProduct());
        return "addLoanProduct";
    }

    @PostMapping("/add")
    public String addLoanProduct(@ModelAttribute LoanProduct loanProduct, RedirectAttributes redirectAttributes) {
        try {
            loanProductService.addLoanProduct(loanProduct);
            logger.info("Loan product added successfully: {}", loanProduct);
            redirectAttributes.addFlashAttribute("message", "Loan product added successfully!");
        } catch (Exception e) {
            logger.error("Error adding loan product: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error adding loan product.");
        }
        return "redirect:/loanProduct/viewAll";
    }

    @GetMapping("/update/{id}")
    public String showUpdateLoanProductForm(@PathVariable int id, Model model) {
        logger.info("Displaying update loan product form for ID: {}", id);
        LoanProduct loanProduct = loanProductService.getLoanProductDetails(id);
        if (loanProduct == null) {
            logger.warn("Loan product not found for ID: {}", id);
            return "redirect:/loanProduct/viewAll";
        }
        model.addAttribute("loanProduct", loanProduct);
        return "updateLoanProduct";
    }

    @PostMapping("/update")
    public String updateLoanProduct(@ModelAttribute LoanProduct loanProduct, RedirectAttributes redirectAttributes) {
        try {
            loanProductService.updateLoanProduct(loanProduct);
            logger.info("Loan product updated successfully: {}", loanProduct);
            redirectAttributes.addFlashAttribute("message", "Loan product updated successfully!");
        } catch (Exception e) {
            logger.error("Error updating loan product: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error updating loan product.");
        }
        return "redirect:/loanProduct/viewAll";
    }

    @GetMapping("/viewAll")
    public String viewAllLoanProducts(Model model) {
        logger.info("Displaying all loan products.");
        List<LoanProduct> loanProducts = loanProductService.getAllLoanProducts();
        model.addAttribute("loanProducts", loanProducts);
        return "viewAllLoanProducts";
    }

    @GetMapping("/details/{id}")
    public String getLoanProductDetails(@PathVariable int id, Model model) {
        logger.info("Displaying loan product details for ID: {}", id);
        LoanProduct loanProduct = loanProductService.getLoanProductDetails(id);
        if (loanProduct == null) {
            logger.warn("Loan product not found for ID: {}", id);
            return "redirect:/loanProduct/viewAll";
        }
        model.addAttribute("loanProduct", loanProduct);
        return "loanProductDetails";
    }
}