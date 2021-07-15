package com.ram.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author",
			joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
	private Set<Author> authors;

	public Book()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Set<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors(Set<Author> authors)
	{
		this.authors = authors;
	}

}
