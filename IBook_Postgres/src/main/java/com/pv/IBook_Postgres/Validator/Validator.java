package com.pv.IBook_Postgres.Validator;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.pv.IBook_Postgres.Exception.InfyBookException;
import com.pv.IBook_Postgres.dto.BookDTO;
@Component
public class Validator {
	
	@Autowired
	private Environment env;
	public final static Logger LOGGER = LogManager.getLogger(Validator.class);
	public  void validate(BookDTO bookDTO) throws InfyBookException
	{
		LOGGER.info("In Validator class "+validateYear(bookDTO.getPublishedYear()));
		if(!validateYear(bookDTO.getPublishedYear())) {
			String msg = env.getProperty("Validator.INVALID_YEAR");
			throw new InfyBookException(msg);
		}
	}
	public  boolean validateYear(LocalDate year)
	{
		LOGGER.info("LocalDate.now() "+LocalDate.now());
		LOGGER.info("year "+year);
		if(year.isBefore(LocalDate.now())||year.isEqual(LocalDate.now()))
		
		return true;
		else
			return false;
	}

}
