package com.pv.IBook_Postgres.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.pv.IBook_Postgres.Exception.InfyBookException;
import com.pv.IBook_Postgres.Repository.BookReporsitory;
import com.pv.IBook_Postgres.Validator.Validator;
import com.pv.IBook_Postgres.dto.BookDTO;
import com.pv.IBook_Postgres.entity.Book;


@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookReporsitory repo;
	@Autowired
	private Validator val;
	@Autowired
	Environment env; 
	 ModelMapper mp = new ModelMapper(); 
	 
	@Override
	public BookDTO getBookDetails(Integer bookId) throws InfyBookException {
		// TODO Auto-generated method stub
		Optional<Book> opt = repo.findById(bookId);
		Book book = opt.orElseThrow(()->new InfyBookException("Service.BOOK_NOT_FOUND_IN_DATABASE"+bookId));
		BookDTO bdto = mp.map(book, BookDTO.class);
		return bdto;
	}

	@Override
	public void addBook(BookDTO bookDTO) throws InfyBookException {
		Optional<Book> opt = repo.findById(bookDTO.getBookId());
		if(opt.isPresent()) throw new InfyBookException("Service.BOOK_ALREADY_PRESENT: "+bookDTO.getBookId());
		val.validate(bookDTO);
		Book book = mp.map(bookDTO, Book.class);
		repo.save(book);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(Integer bookId, Integer price) throws InfyBookException {
		Optional<Book> opt = repo.findById(bookId);
		Book book = opt.orElseThrow(()->new InfyBookException("Service.BOOK_NOT_FOUND_IN_DATABASE: "+bookId));
		book.setPrice(price);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(Integer bookId) throws InfyBookException {
		Optional<Book> opt = repo.findById(bookId);
		Book book = opt.orElseThrow(()->new InfyBookException("Service.BOOK_NOT_FOUND_IN_DATABASE"));
		repo.deleteById(bookId);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookDTO> getBookByAuthorName(String authorName) throws InfyBookException {
		Iterable<Book> opt = repo.findByAuthorName(authorName);
		List<BookDTO> lst = new ArrayList<>(); ;
		if(opt==null)
		{
			String msg = env.getProperty("Service.BOOK_DETAILS_NOT_FOUND");
			throw new InfyBookException(msg);
		}
		else
		{
			for(Book i : opt) {
				BookDTO bdto = mp.map(i, BookDTO.class);
				lst.add(bdto);
			}
			return lst;
		}
	}

	@Override
	public List<BookDTO> getBookPriceGreaterThan(Integer price) throws InfyBookException {
		// TODO Auto-generated method stub
		
		Iterable<Book> opt = repo.findByPriceGreaterThan(price);
		List<BookDTO> lst = new ArrayList<>(); ;
		if(opt==null)
		{
			String msg = env.getProperty("Service.BOOK_DETAILS_NOT_FOUND");
			throw new InfyBookException(msg);
		}
		else
		{
			for(Book i : opt) {
				BookDTO bdto = mp.map(i, BookDTO.class);
				lst.add(bdto);
			}
			return lst;
		}
		
	}

	@Override
	public List<BookDTO> getBookPriceLessThan(Integer price) throws InfyBookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> getBookPublishedYearBetween(LocalDate fromDate, LocalDate toDate) throws InfyBookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> getBookPublishedYearAfter(LocalDate date) throws InfyBookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> getBookAuthorNameAndPublisher(String authorName, String publisher) throws InfyBookException {
		// TODO Auto-generated method stub
		return null;
	}

}
