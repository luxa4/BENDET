/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 14:48
 */

package ru.belyaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.belyaev.entity.Product;
import ru.belyaev.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService productService;

//    @GetMapping("/api/products")
//    public @ResponseBody List<Product> getProduct() {
//        List<Product> productList = productService.listAllProducts();
//        return productList;
//    }

    @GetMapping("/api/productsWithPagination/{page}")
    public @ResponseBody Page getProduct1(@PathVariable Integer page) {
        Page<Product> productList = productService.listAllProductsWithPage(page);
        return productList;
    }

    @GetMapping("/api/products/{id}")
    public @ResponseBody Product getProduct(@PathVariable Integer id) {
        Product product = productService.showProductPageByProductId(id);
        return product;
    }

    @PutMapping("/api/products/update/{id}")
    public ResponseEntity<Map<String, Boolean>> getProduct(@PathVariable Integer id, @RequestBody Product productInfo) {
        Product product = productService.showProductPageByProductId(id);
        product.setBrand(productInfo.getBrand());
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
        product.setType(productInfo.getType());
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

}
