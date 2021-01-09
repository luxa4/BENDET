/*
 * Created by Vologda Developer
 * Date: 10.07.2020
 * Time: 12:55
 */

package ru.belyaev.service;

import org.springframework.data.domain.Page;
import ru.belyaev.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Page listAllProductsWithPag(Integer page);

    void save(Product product);

    void deleteProducts(Integer id);

    List<Product> findProductForSearchTextForm(String fragment);

    long countProductForSearchTextForm(String fragment);

    Long countAllProduct();

    List<Product> listAllProducts();

    Product showProductPageByProductId(int id);

    BigDecimal showMaxHeight();

    BigDecimal showMinHeight();

    BigDecimal showMaxWidth();

    BigDecimal showMinWidth();

    BigDecimal showMaxLength();

    BigDecimal showMinLength();

    BigDecimal showMaxPrice();

    BigDecimal showMinPrice();

    List<Product> productBySearchFilter(BigDecimal minLen, BigDecimal maxLen, BigDecimal minWidth, BigDecimal maxWidth,
                                        BigDecimal minHeight, BigDecimal maxHeight, BigDecimal minPrice, BigDecimal maxPrice);

    int countProductBySearchFilter(BigDecimal minLen, BigDecimal maxLen, BigDecimal minWidth, BigDecimal maxWidth,
                                        BigDecimal minHeight, BigDecimal maxHeight, BigDecimal minPrice, BigDecimal maxPrice);

}
