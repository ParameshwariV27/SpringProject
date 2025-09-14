package com.pv.IBook_Postgres.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pv.IBook_Postgres.entity.Book;
@Repository
public interface BookReporsitory extends CrudRepository<Book, Integer>{

	 List<Book> findByAuthorName(String authorName);
	 List<Book> findByPriceGreaterThan(Integer price);
	 List<Book> findByPriceLessThan(Integer price);
	 List<Book> findByPublishedYearBetween(LocalDate fromDate, LocalDate toDate);
	 List<Book> findByPublishedYearAfter(LocalDate date);
	 List<Book> findByAuthorNameAndPublisher(String authorName, String publisher);
	
}
