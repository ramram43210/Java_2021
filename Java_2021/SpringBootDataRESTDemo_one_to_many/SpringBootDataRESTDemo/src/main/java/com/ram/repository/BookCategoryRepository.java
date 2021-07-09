package com.ram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ram.model.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer>
{
}
