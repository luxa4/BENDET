package ru.belyaev.model;

import ru.belyaev.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductInfo {

    Long countProduct;
    BigDecimal maxLen;
    BigDecimal minLen;
    BigDecimal maxHei;
    BigDecimal minHei;
    BigDecimal maxWid;
    BigDecimal minWid;
    BigDecimal maxPrice;
    BigDecimal minPrice;

    public ProductInfo() {
    }


    public Long getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Long countProduct) {
        this.countProduct = countProduct;
    }

    public BigDecimal getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(BigDecimal maxLen) {
        this.maxLen = maxLen;
    }

    public BigDecimal getMinLen() {
        return minLen;
    }

    public void setMinLen(BigDecimal minLen) {
        this.minLen = minLen;
    }

    public BigDecimal getMaxHei() {
        return maxHei;
    }

    public void setMaxHei(BigDecimal maxHei) {
        this.maxHei = maxHei;
    }

    public BigDecimal getMinHei() {
        return minHei;
    }

    public void setMinHei(BigDecimal minHei) {
        this.minHei = minHei;
    }

    public BigDecimal getMaxWid() {
        return maxWid;
    }

    public void setMaxWid(BigDecimal maxWid) {
        this.maxWid = maxWid;
    }

    public BigDecimal getMinWid() {
        return minWid;
    }

    public void setMinWid(BigDecimal minWid) {
        this.minWid = minWid;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
}
