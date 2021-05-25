package com.ram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ram.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>
{
}
