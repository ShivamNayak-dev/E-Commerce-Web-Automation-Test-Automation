package com.shopflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String orderNumber;

    private String email;
    private double totalAmount;
    private String status;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String v) { orderNumber = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { email = v; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double v) { totalAmount = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { status = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime v) { createdAt = v; }
}
