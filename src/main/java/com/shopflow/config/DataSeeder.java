package com.shopflow.config;

import com.shopflow.model.Product;
import com.shopflow.model.User;
import com.shopflow.repository.ProductRepository;
import com.shopflow.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(ProductRepository products, UserRepository users) {
        return args -> {
            if (products.count() == 0) {
                products.save(product("Laptop Pro", "High-performance business laptop", 75000, 15, "Electronics"));
                products.save(product("Wireless Mouse", "Ergonomic wireless mouse", 2000, 50, "Electronics"));
                products.save(product("Office Chair", "Comfortable ergonomic chair", 12000, 20, "Home"));
                products.save(product("Java Programming Book", "Core Java interview preparation book", 900, 30, "Books"));
            }
            if (users.findByEmail("testuser@shopflow.com").isEmpty()) {
                User user = new User();
                user.setFirstName("Test");
                user.setLastName("User");
                user.setEmail("testuser@shopflow.com");
                user.setPassword("Test@123");
                user.setPhone("9999999999");
                users.save(user);
            }
        };
    }

    private Product product(String n, String d, double p, int s, String c) {
        Product x = new Product();
        x.setName(n); x.setDescription(d); x.setPrice(p); x.setStock(s); x.setCategory(c);
        return x;
    }
}
