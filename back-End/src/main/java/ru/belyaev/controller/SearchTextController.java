package ru.belyaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.belyaev.entity.Product;
import ru.belyaev.model.JsonCart;
import ru.belyaev.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class SearchTextController {

    @Autowired
    private ProductService productService;

    @GetMapping("/searchProduct")
    public List<Product> showSearchResult(@RequestParam(value = "fragment") String fragment) {
        List<Product> productList = productService.findProductForSearchTextForm(fragment);
        return productList;
    }

    @GetMapping("/search")
    public List<Product> showSearchResult(@RequestParam("minLen") BigDecimal minLen, @RequestParam("maxLen") BigDecimal maxLen,
                                   @RequestParam("minWidth") BigDecimal minWidth, @RequestParam("maxWidth") BigDecimal maxWidth,
                                   @RequestParam("minHeight") BigDecimal minHeight, @RequestParam("maxHeight") BigDecimal maxHeight,
                                   @RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice) {
        List<Product> list = productService.productBySearchFilter(minLen, maxLen, minWidth, maxWidth, minHeight,
                                                                    maxHeight,minPrice, maxPrice);
        int count = productService.countProductBySearchFilter(minLen, maxLen, minWidth, maxWidth, minHeight,
                maxHeight,minPrice, maxPrice);
        return list;
    }

    @GetMapping("/searchButtonCount")
    public JsonCart showSearchCountResult(@RequestParam("minLen") BigDecimal minLen, @RequestParam("maxLen") BigDecimal maxLen,
                                          @RequestParam("minWidth") BigDecimal minWidth, @RequestParam("maxWidth") BigDecimal maxWidth,
                                          @RequestParam("minHeight") BigDecimal minHeight, @RequestParam("maxHeight") BigDecimal maxHeight,
                                          @RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice) {
        int count = productService.countProductBySearchFilter(minLen, maxLen, minWidth, maxWidth, minHeight,
                maxHeight, minPrice, maxPrice);
        JsonCart jsonCart = new JsonCart();
        jsonCart.setTotalCount(count);
        return jsonCart;
    }
}
