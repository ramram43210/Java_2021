package com.example.mongodbdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbdemo.domain.Book;
import com.example.mongodbdemo.service.BookService;

@RestController
public class BookController
{

	@Autowired
	private BookService bookService;

	@PostMapping("/saveBook")
	public Book saveBook(@RequestBody Book book)
	{
		return bookService.saveBook(book);
	}

	@GetMapping("/getBook/{bookId}")
	public Book getBook(@PathVariable String bookId)
	{
		return bookService.getBook(bookId);
	}

	@PutMapping("/updateBook/{bookId}")
	public Book updateBook(@RequestBody Book book, @PathVariable String bookId)
	{
		return bookService.updateBook(book,bookId);
	}
	
	@DeleteMapping("/deleteBook/{bookId}")
	public String deleteBook(@PathVariable String bookId)
	{
		bookService.deleteBook(bookId);
		return "Deleted Successfully";
	}


	@GetMapping("/getAllBook")
	public List<Book> getAllBook()
	{
		return bookService.getAllBook();
	}
}
