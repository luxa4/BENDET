/*
 * Created by Vologda Developer
 * Date: 10.07.2020
 * Time: 12:55
 */


package ru.belyaev.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.belyaev.entity.Category;
import ru.belyaev.entity.Product;
import ru.belyaev.repository.CategoryRepository;
import ru.belyaev.repository.ProductRepository;
import ru.belyaev.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Long countAllProduct() {
        return productRepository.count();
    }

//    @Override
//    public List<Product> listAllProducts() {
//        List<Product> products = productRepository.listAllProduct();
//        return products;

//    }
    @Override
    public Page listAllProducts(Pageable pageable) {
        Page <Product> products = productRepository.findAll(pageable);
//        List<Product> products = productRepository.listAllProduct(pageable);
//        LOGGER.info("Pag find-First {}",products1.stream().findFirst());
//        LOGGER.info("Pag find-First {}",products1.getContent());
        return products;
    }

    @Override
    public Page findProductByCategory(Category category, Pageable pageable) {
        Page<Product> products = productRepository.findProductByCategory(category, pageable);
        return products;
    }

    @Override
    public BigDecimal showMaxHeight() {
        return productRepository.findMaxHeight();
    }

    @Override
    public BigDecimal showMinHeight() {
        return productRepository.findMinHeight();
    }

    @Override
    public BigDecimal showMaxWidth() {
        return productRepository.findMaxWidth();
    }

    @Override
    public BigDecimal showMinWidth() {
        return productRepository.findMinWidth();
    }

    @Override
    public BigDecimal showMaxLength() {
        return productRepository.findMaxLength();
    }

    @Override
    public BigDecimal showMinLength() {
        return productRepository.findMinLength();
    }

    @Override
    public BigDecimal showMaxPrice() {
        return productRepository.findMaxPrice();
    }

    @Override
    public BigDecimal showMinPrice() {
        return productRepository.findMinPrice();
    }

    @Override
    public Product showProductPageByProductId(int id) {
        Product product = productRepository.findProductById(id);
        return product;
    }

    @Override
    public List<Product> productBySearchFilter(BigDecimal minLen, BigDecimal maxLen, BigDecimal minWidth,
                                               BigDecimal maxWidth, BigDecimal minHeight, BigDecimal maxHeight,
                                               BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.searchFilters(minLen, maxLen, minWidth, maxWidth, minHeight, maxHeight,
                minPrice, maxPrice);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public int countProductBySearchFilter(BigDecimal minLen, BigDecimal maxLen, BigDecimal minWidth,
                                          BigDecimal maxWidth, BigDecimal minHeight, BigDecimal maxHeight,
                                          BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.searchFiltersCount(minLen, maxLen, minWidth, maxWidth, minHeight, maxHeight,
                minPrice, maxPrice);
    }

    @Override
    public void deleteProducts(Integer id) {
        Product product = productRepository.findProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Product> findProductForSearchTextForm(String fragment) {
        List<Product> products = productRepository.findProductsByNameContainingIgnoreCase(fragment);
        return products;
    }

    @Override
    public long countProductForSearchTextForm(String fragment) {
        return productRepository.countProductByNameContainingIgnoreCase(fragment);
    }
}
