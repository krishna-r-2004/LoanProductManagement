package com.lpm.repository;

import com.lpm.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link LoanProduct} entities in the database.
 * This interface extends Spring Data JPA's {@link JpaRepository}, providing
 * basic CRUD (Create, Read, Update, Delete) operations for {@code LoanProduct}
 * objects.
 */
@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Integer> {

}
// If you need custom queries or operations specific to LoanProduct, you can define them here.
// For example:
// List<LoanProduct> findByProductName(String productName);
// List<LoanProduct> findByInterestRateLessThan(BigDecimal rate);