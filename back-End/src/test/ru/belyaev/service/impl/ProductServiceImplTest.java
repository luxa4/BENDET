package ru.belyaev.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.belyaev.repository.ProductRepository;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCountAllProduct() {
        productService.countAllProduct();
        verify(productRepository, times(1)).count();
    }

    @Test
    public void testListAllProducts() {
        productService.listAllProducts();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testShowMaxHeight() {
        productService.showMaxHeight();
        verify(productRepository, times(1)).findMaxHeight();
    }

    @Test
    public void testShowMinHeight() {
        productService.showMinHeight();
        verify(productRepository, times(1)).findMinHeight();
    }

    @Test
    public void testShowMaxWidth() {
        productService.showMaxWidth();
        verify(productRepository, times(1)).findMaxWidth();
    }

    @Test
    public void testShowMinWidth() {
        productService.showMinWidth();
        verify(productRepository, times(1)).findMinWidth();
    }

    @Test
    public void testShowMaxLength() {
        productService.showMaxLength();
        verify(productRepository, times(1)).findMaxLength();
    }

    @Test
    public void testShowMinLength() {
        productService.showMinLength();
        verify(productRepository, times(1)).findMinLength();
    }

    @Test
    public void testShowMaxPrice() {
        productService.showMaxPrice();
        verify(productRepository, times(1)).findMaxPrice();
    }

    @Test
    public void testShowMinPrice() {
        productService.showMinPrice();
        verify(productRepository, times(1)).findMinPrice();
    }

    @Test
    public void testShowProductPageByProductId() {
        productService.showProductPageByProductId(1);
        verify(productRepository, times(1)).findProductById(1);
    }

    @Test
    public void testProductBySearchFilter() {
        productService.productBySearchFilter(BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                            BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                            BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                            BigDecimal.valueOf(1),BigDecimal.valueOf(1));
        verify(productRepository, times(1)).searchFilters(BigDecimal.valueOf(1),
                                                                                    BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                                                    BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                                                    BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                                                    BigDecimal.valueOf(1));
    }

    @Test
    public void testCountProductBySearchFilter() {
        productService.countProductBySearchFilter(BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                BigDecimal.valueOf(1),BigDecimal.valueOf(1));
        verify(productRepository, times(1)).searchFiltersCount(BigDecimal.valueOf(1),
                                                                        BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                                        BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                                        BigDecimal.valueOf(1),BigDecimal.valueOf(1),
                                                                        BigDecimal.valueOf(1));
    }

    @Test
    public void testFindProductForSearchTextForm() {
        productService.findProductForSearchTextForm("hemnes");
        verify(productRepository, times(1)).findProductsByNameContainingIgnoreCase("hemnes");
    }

    @Test
    public void testCountProductForSearchTextForm() {
        productService.countProductForSearchTextForm("hemnes");
        verify(productRepository, times(1)).countProductByNameContainingIgnoreCase("hemnes");
    }

}