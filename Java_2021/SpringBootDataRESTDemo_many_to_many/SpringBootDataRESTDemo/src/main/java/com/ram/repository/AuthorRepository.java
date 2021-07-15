package com.ram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ram.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>
{
}
