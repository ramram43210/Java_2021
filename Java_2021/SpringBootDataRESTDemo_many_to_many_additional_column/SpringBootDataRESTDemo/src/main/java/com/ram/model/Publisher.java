package com.ram.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Publisher
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToMany(mappedBy = "publisher")
	private Set<BookPublisher> bookPublishers;

	public Publisher()
	{

	}

	public Publisher(String name)
	{
		this.name = name;
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
