package com.ram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ram.model.Product;

public interface ProductRespository extends JpaRepository<Product, Long>
{
}
