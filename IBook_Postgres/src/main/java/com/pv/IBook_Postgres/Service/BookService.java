package com.pv.IBook_Postgres.Service;

import java.time.LocalDate;
import java.util.List;

import com.pv.IBook_Postgres.Exception.InfyBookException;
import com.pv.IBook_Postgres.dto.BookDTO;
import com.pv.IBook_Postgres.entity.Book;

public interface BookService {

	 public BookDTO getBookDetails(Integer bookId) throws InfyBookException;
	 public void addBook(BookDTO bookDTO)throws InfyBookException;
	 public void updateBook(Integer bookId, Integer price)throws InfyBookException;
	 public void deleteBook(Integer bookId) throws InfyBookException;
	 public List<BookDTO> getBookByAuthorName(String authorName)throws InfyBookException;
	 public List<BookDTO> getBookPriceGreaterThan(Integer price)throws InfyBookException;
	 public List<BookDTO> getBookPriceLessThan(Integer price)throws InfyBookException;
	 public List<BookDTO> getBookPublishedYearBetween(LocalDate fromDate, LocalDate toDate)throws InfyBookException;
	 public List<BookDTO> getBookPublishedYearAfter(LocalDate date)throws InfyBookException;
	 public List<BookDTO> getBookAuthorNameAndPublisher(String authorName, String publisher)throws InfyBookException;
	
}
