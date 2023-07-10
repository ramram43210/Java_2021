package com.example.mongodbdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongodbdemo.domain.Book;
import com.example.mongodbdemo.repository.BookRepository;

@Service
public class BookService
{
	@Autowired
	private BookRepository bookRepository;

	public Book saveBook(Book book)
	{
		return bookRepository.save(book);
	}

	public Book getBook(String bookId)
	{
		Optional<Book> optional = bookRepository.findById(bookId);
		Book book = optional.get();
		return book;
	}

	public Book updateBook(Book book, String bookId)
	{
		book.setBookId(bookId);
		return bookRepository.save(book);
	}

	public void deleteBook(String bookId)
	{
		bookRepository.deleteById(bookId);
	}

	public List<Book> getAllBook()
	{
		return bookRepository.findAll();
	}
}
