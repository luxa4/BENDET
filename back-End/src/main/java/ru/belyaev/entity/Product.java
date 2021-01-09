/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 14:24
 */


package ru.belyaev.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NamedQuery(name = "Product.findMaxHeight", query = "SELECT MAX(height) FROM Product")
@NamedQuery(name = "Product.findMinHeight", query = "SELECT MIN(height) FROM Product")
@NamedQuery(name = "Product.findMaxWidth", query = "SELECT MAX(width) FROM Product")
@NamedQuery(name = "Product.findMinWidth", query = "SELECT MIN(width) FROM Product")
@NamedQuery(name = "Product.findMaxLength", query = "SELECT MAX(length) FROM Product")
@NamedQuery(name = "Product.findMinLength", query = "SELECT MIN(length) FROM Product")
@NamedQuery(name = "Product.findMaxPrice", query = "SELECT MAX(price) FROM Product")
@NamedQuery(name = "Product.findMinPrice", query = "SELECT MIN(price) FROM Product")
@NamedQuery(name = "Product.searchFilters", query = "SELECT p FROM Product p WHERE (p.length between ?1 and ?2) and (p.width between ?3 and ?4) and (p.height between ?5 and ?6) and " +
        "(p.price between ?7 and ?8)")
@NamedQuery(name = "Product.searchFiltersCount", query = "SELECT count(p) FROM Product p WHERE (p.length between ?1 and ?2) and (p.width between ?3 and ?4) and (p.height between ?5 and ?6) and " +
        "(p.price between ?7 and ?8)")
@Entity
@Table(name ="product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String brand;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private BigDecimal length;

    @Column
    private BigDecimal width;

    @Column
    private BigDecimal height;

    @Column
    private BigDecimal weight;

    @Column
    private BigDecimal price;

    @Column
    private String status;

    @Column(name = "IMAGE_URL_1")
    private String imageUrl1;

    @Column(name = "IMAGE_URL_2")
    private String imageUrl2;

    @Column(name = "IMAGE_URL_3")
    private String imageUrl3;

    @Column(name = "IMAGE_URL_4")
    private String imageUrl4;

    @Column(name = "IMAGE_URL_5")
    private String imageUrl5;

    @Column
    private String description;

    @Column
    private Integer totalCount;

    public Product() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }

    public String getImageUrl4() {
        return imageUrl4;
    }

    public void setImageUrl4(String imageUrl4) {
        this.imageUrl4 = imageUrl4;
    }

    public String getImageUrl5() {
        return imageUrl5;
    }

    public void setImageUrl5(String imageUrl5) {
        this.imageUrl5 = imageUrl5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(brand, product.brand) &&
                Objects.equals(name, product.name) &&
                Objects.equals(type, product.type) &&
                Objects.equals(length, product.length) &&
                Objects.equals(width, product.width) &&
                Objects.equals(height, product.height) &&
                Objects.equals(weight, product.weight) &&
                Objects.equals(price, product.price) &&
                Objects.equals(status, product.status) &&
                Objects.equals(imageUrl1, product.imageUrl1) &&
                Objects.equals(imageUrl2, product.imageUrl2) &&
                Objects.equals(imageUrl3, product.imageUrl3) &&
                Objects.equals(imageUrl4, product.imageUrl4) &&
                Objects.equals(imageUrl5, product.imageUrl5) &&
                Objects.equals(description, product.description) &&
                Objects.equals(totalCount, product.totalCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, name, type, length, width, height, weight, price, status, imageUrl1, imageUrl2, imageUrl3, imageUrl4, imageUrl5, description, totalCount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", imageUrl='" + imageUrl1 + '\'' +
                '}';
    }
}
