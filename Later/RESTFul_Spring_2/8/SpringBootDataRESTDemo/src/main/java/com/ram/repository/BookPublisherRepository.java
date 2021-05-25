package com.ram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ram.model.BookPublisher;

public interface BookPublisherRepository extends JpaRepository<BookPublisher, Integer>
{
}
