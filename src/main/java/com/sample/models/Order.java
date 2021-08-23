package com.sample.models;

import com.sample.enums.PaymentMethod;
import com.sample.enums.PizzaTopping;
import com.sample.enums.PizzaType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigInteger;

public class Order {

    private PizzaType pizzaType;

    private PizzaTopping firstTopping;

    private PizzaTopping secondTopping;

    private BigInteger quantity;

    private String name;

    private String email;

    private String phone;

    private PaymentMethod paymentMethod;

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    public PizzaTopping getFirstTopping() {
        return firstTopping;
    }

    public void setFirstTopping(PizzaTopping firstTopping) {
        this.firstTopping = firstTopping;
    }

    public PizzaTopping getSecondTopping() {
        return secondTopping;
    }

    public void setSecondTopping(PizzaTopping secondTopping) {
        this.secondTopping = secondTopping;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return new EqualsBuilder()
                .append(pizzaType, order.pizzaType)
                .append(firstTopping, order.firstTopping)
                .append(secondTopping, order.secondTopping)
                .append(quantity, order.quantity)
                .append(name, order.name)
                .append(email, order.email)
                .append(phone, order.phone)
                .append(paymentMethod, order.paymentMethod)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(pizzaType)
                .append(firstTopping)
                .append(secondTopping)
                .append(quantity)
                .append(name)
                .append(email)
                .append(phone)
                .append(paymentMethod)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Order (pizzaType - " + pizzaType +
                ", firstTopping - " + firstTopping +
                ", secondTopping - " + secondTopping +
                ", quantity - " + quantity +
                ", name - '" + name + '\'' +
                ", email - '" + email + '\'' +
                ", phone - '" + phone + '\'' +
                ", paymentMethod - " + paymentMethod + ")";
    }
}
