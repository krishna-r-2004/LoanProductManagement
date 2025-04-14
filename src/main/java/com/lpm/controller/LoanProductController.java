package com.lpm.controller;

import com.lpm.model.LoanProduct;
import com.lpm.service.LoanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for managing loan product related operations.
 * This controller handles requests for adding, updating, viewing, and displaying
 * details of loan products. It interacts with the {@link LoanProductService}
 * to perform the business logic.
 */
@Controller
@RequestMapping("/loanProduct")
public class LoanProductController {

    /**
     * Service dependency for handling loan product operations.
     */
    @Autowired
    private LoanProductService loanProductService;

    /**
     * Displays the form for adding a new loan product.
     *
     * @return A {@link ModelAndView} object for the "loanProducts" view,
     * with an empty {@link LoanProduct} object and an "add" flag set to true.
     */
    @GetMapping("/add")
    public ModelAndView showAddLoanProductForm() {
        ModelAndView modelAndView = new ModelAndView("loanProducts");
        modelAndView.addObject("loanProduct", new LoanProduct());
        modelAndView.addObject("add", "true");
        return modelAndView;
    }

    /**
     * Handles the submission of the add loan product form.
     * It calls the {@link LoanProductService#addLoanProduct(LoanProduct)} method
     * to persist the new loan product.
     *
     * @param loanProduct        The {@link LoanProduct} object populated from the form.
     * @param redirectAttributes Used to pass flash attributes for displaying messages after redirection.
     * @return A redirect view to the "/loanProduct/viewAll" URL.
     */
    @PostMapping("/add")
    public String addLoanProduct(@ModelAttribute LoanProduct loanProduct, RedirectAttributes redirectAttributes) {
        try {
            loanProductService.addLoanProduct(loanProduct);
            redirectAttributes.addFlashAttribute("message", "Loan product added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding loan product.");
        }
        return "redirect:/loanProduct/viewAll";
    }

    /**
     * Displays the form for updating an existing loan product.
     * It retrieves the loan product details based on the provided ID.
     *
     * @param id The ID of the loan product to be updated.
     * @return A {@link ModelAndView} object for the "loanProducts" view,
     * with the retrieved {@link LoanProduct} object and an "update" flag set to true.
     * If the loan product is not found, it redirects to the "/loanProduct/viewAll" URL.
     */
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateLoanProductForm(@PathVariable int id) {
        LoanProduct loanProduct = loanProductService.getLoanProductDetails(id);
        if (loanProduct == null) {
            return new ModelAndView("redirect:/loanProduct/viewAll");
        }
        ModelAndView modelAndView = new ModelAndView("loanProducts");
        modelAndView.addObject("loanProduct", loanProduct);
        modelAndView.addObject("update", "true");
        return modelAndView;
    }

    /**
     * Handles the submission of the update loan product form.
     * It calls the {@link LoanProductService#updateLoanProduct(LoanProduct)} method
     * to update the existing loan product.
     *
     * @param loanProduct        The {@link LoanProduct} object populated from the form.
     * @param redirectAttributes Used to pass flash attributes for displaying messages after redirection.
     * @return A redirect view to the "/loanProduct/viewAll" URL.
     */
    @PostMapping("/update")
    public String updateLoanProduct(@ModelAttribute LoanProduct loanProduct, RedirectAttributes redirectAttributes) {
        try {
            loanProductService.updateLoanProduct(loanProduct);
            redirectAttributes.addFlashAttribute("message", "Loan product updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating loan product.");
        }
        return "redirect:/loanProduct/viewAll";
    }

    /**
     * Displays a list of all available loan products.
     * It retrieves the list of loan products from the {@link LoanProductService}.
     *
     * @return A {@link ModelAndView} object for the "loanProducts" view,
     * with a list of {@link LoanProduct} objects.
     */
    @GetMapping("/viewAll")
    public ModelAndView viewAllLoanProducts() {
        List<LoanProduct> loanProducts = loanProductService.getAllLoanProducts();
        ModelAndView modelAndView = new ModelAndView("loanProducts");
        modelAndView.addObject("loanProducts", loanProducts);
        return modelAndView;
    }

    /**
     * Displays the details of a specific loan product.
     * It retrieves the loan product details based on the provided ID.
     *
     * @param id The ID of the loan product to display.
     * @return A {@link ModelAndView} object for the "loanProducts" view,
     * with the retrieved {@link LoanProduct} object and a "details" flag set to true.
     * If the loan product is not found, it redirects to the "/loanProduct/viewAll" URL.
     */
    @GetMapping("/details/{id}")
    public ModelAndView getLoanProductDetails(@PathVariable int id) {
        LoanProduct loanProduct = loanProductService.getLoanProductDetails(id);
        if (loanProduct == null) {
            return new ModelAndView("redirect:/loanProduct/viewAll");
        }
        ModelAndView modelAndView = new ModelAndView("loanProducts");
        modelAndView.addObject("loanProduct", loanProduct);
        modelAndView.addObject("details", "true");
        return modelAndView;
    }
}