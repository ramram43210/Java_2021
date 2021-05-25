package com.ram.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<BookPublisher> bookPublishers;

	public Book()
	{
	}

	public Book(String name)
	{
		this.name = name;
		bookPublishers = new HashSet<>();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<BookPublisher> getBookPublishers()
	{
		return bookPublishers;
	}

	public void setBookPublishers(Set<BookPublisher> bookPublishers)
	{
		this.bookPublishers = bookPublishers;
	}
}
