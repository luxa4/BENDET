/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 15:06
 */

package ru.belyaev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.belyaev.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    long count();

    @Override
    Page<Product> findAll(Pageable pageable);

    //    List<Product> findProductByType(Pageable pageable);

    Product findProductById(int id);

    List<Product> findProductsByNameContainingIgnoreCase(String fragment);

    long countProductByNameContainingIgnoreCase(String fragment);

    BigDecimal findMaxHeight();

    BigDecimal findMinHeight();

    BigDecimal findMaxLength();

    BigDecimal findMinLength();

    BigDecimal findMaxWidth();

    BigDecimal findMinWidth();

    BigDecimal findMaxPrice();

    BigDecimal findMinPrice();

    List<Product> searchFilters(BigDecimal minLen, BigDecimal maxLen, BigDecimal minWidth, BigDecimal maxWidth,
                                BigDecimal minHeight, BigDecimal maxHeight, BigDecimal minPrice, BigDecimal maxPrice);

    int searchFiltersCount(BigDecimal minLen, BigDecimal maxLen, BigDecimal minWidth, BigDecimal maxWidth,
                                BigDecimal minHeight, BigDecimal maxHeight, BigDecimal minPrice, BigDecimal maxPrice);

}
