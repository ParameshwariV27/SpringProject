package com.pv.QueryBased.Repository;

import java.util.List;
import java.util.Optional;

import com.pv.QueryBased.Entity.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
public class MovieRepositoryImpl implements MovieRepository{

	
	@PersistenceContext
	EntityManager en;
	ModelMapper modelMapper = new ModelMapper();
	@Override
	public List<Movie> findAllwithDirectorName(String name) {
		// TODO Auto-generated method stub
		String q  = "select m from Movie m where m.directorName=?1";
		Query query = en.createQuery(q);
		query.setParameter(1, name);
		List<Movie> movie = query.getResultList();
		return movie;
	}

	@Override
	public List<Object[]> findMovieNameDirectorName() {
		// TODO Auto-generated method stub
		String q = "select m.movieName, m.directorName from Movie m where m.directorName LIKE 'D%'";
		Query query = en.createQuery(q);
		List<Object[]> lst = query.getResultList();
		return lst;
	}

	@Override
	public Double averageRating(Integer year) {
		// TODO Auto-generated method stub
		String q= "select sum(m.imdbRating) from Movie m where m.year>=?1";
		Query query = en.createQuery(q);
		query.setParameter(1, year);
		return  (Double) query.getSingleResult();
		
	}




}
