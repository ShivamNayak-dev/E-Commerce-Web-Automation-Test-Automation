package com.shopflow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 2000)
    private String description;

    private double price;
    private int stock;
    private String category;

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String v) { name = v; }
    public String getDescription() { return description; }
    public void setDescription(String v) { description = v; }
    public double getPrice() { return price; }
    public void setPrice(double v) { price = v; }
    public int getStock() { return stock; }
    public void setStock(int v) { stock = v; }
    public String getCategory() { return category; }
    public void setCategory(String v) { category = v; }
}
