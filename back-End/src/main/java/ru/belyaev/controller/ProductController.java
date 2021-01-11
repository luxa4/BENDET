/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 14:48
 */

package ru.belyaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.belyaev.entity.Product;
import ru.belyaev.service.ProductService;
import ru.belyaev.service.impl.CategoryService;
import ru.belyaev.service.impl.ProducerServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProducerServiceImpl producerService;

    @GetMapping("/")
    public String greetingPage() {
        return "index";
    }

    @GetMapping("/api/products")
    public @ResponseBody List<Product> getAllProduct() {
        List<Product> productList = productService.getAllProducts();
        return productList;
    }

    @GetMapping("/api/products/{id}")
    public @ResponseBody Product getProductById(@PathVariable Integer id) {
        Product product = productService.showProductPageByProductId(id);
        return product;
    }

    @PutMapping("/api/products/update/{id}")
    public ResponseEntity<Map<String, Boolean>> getProduct(@PathVariable Integer id, @RequestBody Product productInfo) {
        Product product = productService.showProductPageByProductId(id);
        product.setCategory(productInfo.getCategory());
        product.setName(productInfo.getName());
        product.setLength(productInfo.getLength());
        product.setWidth(productInfo.getWidth());
        product.setHeight(productInfo.getHeight());
        product.setWeight(productInfo.getWeight());
        product.setPrice(productInfo.getPrice());
        product.setDescription(productInfo.getDescription());
        product.setImageUrl1(productInfo.getImageUrl1());
        product.setImageUrl2(productInfo.getImageUrl2());
        product.setImageUrl3(productInfo.getImageUrl3());
        product.setImageUrl4(productInfo.getImageUrl4());
        product.setImageUrl5(productInfo.getImageUrl5());
        product.setStatus(productInfo.getStatus());
        product.setProducer(productInfo.getProducer());
        productService.save(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("updated", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/api/products/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer id) {
        productService.deleteProducts(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


//    @GetMapping("/api/products")
//    public @ResponseBody List<Product> getProduct() {
//        List<Product> productList = productService.listAllProducts();
//        return productList;
//    }

//    @GetMapping("/api/productsFilter/{page}")
//    public @ResponseBody Page getAllProductFilter(@PathVariable Integer page,
//                                                  @Param("id_category") List<Integer> id_categoryList,
//                                                  @Param("id_producer") List<Integer> id_producerList,
//                                                  @Param("min_price") BigDecimal min_price,
//                                                  @Param("max_price") BigDecimal max_price) {
//        Pageable pageable = PageRequest.of(page,9);
//        Page<Product> productList = productService.listAllProducts(pageable);
//        return productList;
//    }

//
//    @GetMapping("/api/categories")
//    public @ResponseBody List<Category> getAllCategories() {
//        return categoryService.getAllCategories();
//    }
//
//    @GetMapping("/api/producers/")
//    public @ResponseBody List<Producer> getProducerByCategory(@Param("id_category") Integer id_category) {
//        Category category = categoryService.getCategoryById(id_category);
//        return producerService.getProducersByCategory(category);
//    }



//    @GetMapping("/api/prices")
//    public @ResponseBody List<BigDecimal> getMinAndMaxPrices() {
//        List<BigDecimal> prices = new ArrayList<>();
//        prices.add(productService.showMinPrice());
//        prices.add(productService.showMaxPrice());
//        return prices;
//    }

//    @GetMapping("/api/producers")
//    public List<Producer> getAllProducers() {
//
//    }

//
//    @GetMapping("/api/products/{id_category}/{page}")
//    public @ResponseBody Page getProductByCategory(@PathVariable(name = "id_category") Integer id_category, @PathVariable(name = "page") Integer page) {
//        Pageable pageable = PageRequest.of(page, 9);
//        Category category = productService.findCategoryById(id_category);
//        Page<Product> productList = productService.findProductByCategory(category, pageable);
//        return productList;
//    }

}
