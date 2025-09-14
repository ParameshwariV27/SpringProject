package com.pv.IBook_Postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.pv.IBook_Postgres.Exception.InfyBookException;
import com.pv.IBook_Postgres.Service.BookService;
import com.pv.IBook_Postgres.dto.BookDTO;
import com.pv.IBook_Postgres.entity.Book;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;

@SpringBootApplication
public class IBookPostgresApplication implements CommandLineRunner{
	
	
	private final static Logger LOGGER = LogManager.getLogger(IBookPostgresApplication.class);
	
	@Autowired
	Environment env;
	@Autowired
	private BookService ser;
	public static void main(String[] args) {
		SpringApplication.run(IBookPostgresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		BookDTO bdto = new BookDTO();
		bdto.setAuthorName("Arhata");
		bdto.setBookId(1025);
		bdto.setIbsn(5678027234L);
		bdto.setPrice(1020);
		bdto.setPublishedYear(LocalDate.of(2025, 9, 13));
		bdto.setPublisher("PV");
		bdto.setTitle("ARHATA");
		
		//addBook(bdto);
		//getBookGreaterThanOrEqualPrice(100);
		getBookByAuthorName("Ahata");
//		deleteBook(1010);
//		deleteBook(1009);
//		deleteBook(1019);
//		deleteBook(1016);
		//getBookDetails(1001);
	}
	
	 public void getBookDetails(Integer bookId) throws InfyBookException
	 {
		 LOGGER.info(ser.getBookDetails(bookId));
	 }
	public void addBook(BookDTO bookDTO)throws InfyBookException
	{
		System.out.println("called add");
		ser.addBook(bookDTO);
		LOGGER.info(env.getProperty("UserInterface.INSERT_SUCCESS"));
		
	}
	 public void updateBook(Integer bookId, Integer price)throws InfyBookException
	 {
		 ser.updateBook(bookId, price);
		 LOGGER.info(env.getProperty("UserInterface.UPDATE_SUCCESS"));
	 }
	 public void deleteBook(Integer bookId) throws InfyBookException
	 {
		 ser.deleteBook(bookId);
		 LOGGER.info(env.getProperty("UserInterface.DELETE_SUCCESS"));
	 }
	 public void getBookByAuthorName(String authorName)throws InfyBookException
	 {
		 List<BookDTO> lst = ser.getBookByAuthorName(authorName);
		 LOGGER.info("\n"+lst);
	 }
	 public void getBookGreaterThanOrEqualPrice(int price)throws InfyBookException
	 {
		 System.out.println("called price");
		 List<BookDTO> lst = ser.getBookPriceGreaterThan(price);
		 LOGGER.info("\n"+lst);
	 }
//	  getBookLesserThanOrEqualPrice(int price)throws InfyBookException;
//	  getBookPublishedYearBetween(LocalDate fromDate, LocalDate toDate)throws InfyBookException;
//	 getBookPublishedYearAfter(LocalDate date)throws InfyBookException;
//	 getBookAuthorNameAndPublisher(String authorName, String publisher)throws InfyBookException;
	

}
