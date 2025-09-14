package com.pv.QueryBased.Repository;

import java.util.List;

import com.pv.QueryBased.Entity.Movie;

public interface MovieRepository {
	public List<Movie> findAllwithDirectorName(String name) ;
	public List<Object[]> findMovieNameDirectorName();
	public Double averageRating(Integer year);
}
