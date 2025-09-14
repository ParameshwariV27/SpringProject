package com.pv.QueryBased.Service;

import java.util.List;

import com.pv.QueryBased.DTO.MovieDTO;


public interface MovieService {
	public List<MovieDTO> findAllwithDirectorName(String name) ;
	public List<Object[]> findMovieNameDirectorName();
	public Double averageRating(Integer year);
}
