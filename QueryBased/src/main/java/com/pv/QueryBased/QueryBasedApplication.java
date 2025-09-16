package com.pv.QueryBased;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.pv.QueryBased.DTO.MovieDTO;
import com.pv.QueryBased.Service.MovieService;

@SpringBootApplication
public class QueryBasedApplication implements CommandLineRunner{

	
	private static final Logger LOGGER = LogManager.getLogger(QueryBasedApplication.class);
	@Autowired
	MovieService ser;
	public static void main(String[] args) {
		SpringApplication.run(QueryBasedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//findAllwithDirectorName("Christopher Nolan");
		findMovieNameDirectorName();
		//averageRating(2000);
	}
	
	public void findAllwithDirectorName(String name)
	{
		LOGGER.info(ser.findAllwithDirectorName(name));
	}
	public void findMovieNameDirectorName()
	{
		List<Object[]> lst = ser.findMovieNameDirectorName();
		lst.forEach(obj ->{LOGGER.info(obj[0]+"\t\t"+obj[1]);});
	}
	public void averageRating(Integer year)
	{
		LOGGER.info(ser.averageRating(year));
	}

}
