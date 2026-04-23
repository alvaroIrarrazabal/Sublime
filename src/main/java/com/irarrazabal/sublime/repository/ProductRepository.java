package com.irarrazabal.sublime.repository;

import com.irarrazabal.sublime.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
