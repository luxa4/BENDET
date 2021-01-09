package ru.belyaev.model;

import java.math.BigDecimal;

public class JsonCart {

    private int totalCount;
    private BigDecimal totalCost;

    public JsonCart() {
    }

    public JsonCart(int totalCount, BigDecimal totalCost) {
        this.totalCount = totalCount;
        this.totalCost = totalCost;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
