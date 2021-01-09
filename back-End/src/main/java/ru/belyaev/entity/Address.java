package ru.belyaev.entity;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer index;

    @Column
    private String region;

    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "home_number")
    private String homeNumber;

    @Column
    private Integer flat;

    @Column
    private String recipient;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void validateCollectShippingAddress(ValidationContext context) {
        MessageContext messages = context.getMessageContext();
        if (recipient.isEmpty()) {
            messages.addMessage(new MessageBuilder().error().source("recipient").
                    defaultText("Recipient is required").build());
        }
        if (index ==null) {
            messages.addMessage(new MessageBuilder().error().source("index").
                    defaultText("index is required").build());
        }
        if (region.isEmpty()) {
            messages.addMessage(new MessageBuilder().error().source("region").
                    defaultText("Region is required ").build());
        }
        if (city.isEmpty()) {
            messages.addMessage(new MessageBuilder().error().source("city").
                    defaultText("City is required").build());
        }
        if (street.isEmpty()) {
            messages.addMessage(new MessageBuilder().error().source("street").
                    defaultText("Street is required").build());
        }
        if (homeNumber.isEmpty()) {
            messages.addMessage(new MessageBuilder().error().source("house").
                    defaultText("House number is required").build());
        }
        if (flat==null) {
            messages.addMessage(new MessageBuilder().error().source("flat").
                    defaultText("Flat number is required").build());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
               Objects.equals(index, address.index) &&
               Objects.equals(region, address.region) &&
               Objects.equals(city, address.city) &&
               Objects.equals(homeNumber, address.homeNumber) &&
               Objects.equals(flat, address.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, region, city, homeNumber, flat);
    }

}
