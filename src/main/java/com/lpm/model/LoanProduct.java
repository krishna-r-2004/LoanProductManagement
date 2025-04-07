package com.lpm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "LoanProduct")
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int loanProductId;

    private String productName;
    private BigDecimal interestRate;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private int tenure;

    public LoanProduct() {}

    public LoanProduct(String productName, BigDecimal interestRate, BigDecimal minAmount, BigDecimal maxAmount, int tenure) {
        this.productName = productName;
        this.interestRate = interestRate;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.tenure = tenure;
    }

    public int getLoanProductId() { return loanProductId; }
    public void setLoanProductId(int loanProductId) { this.loanProductId = loanProductId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }

    public BigDecimal getMinAmount() { return minAmount; }
    public void setMinAmount(BigDecimal minAmount) { this.minAmount = minAmount; }

    public BigDecimal getMaxAmount() { return maxAmount; }
    public void setMaxAmount(BigDecimal maxAmount) { this.maxAmount = maxAmount; }

    public int getTenure() { return tenure; }
    public void setTenure(int tenure) { this.tenure = tenure; }
}
