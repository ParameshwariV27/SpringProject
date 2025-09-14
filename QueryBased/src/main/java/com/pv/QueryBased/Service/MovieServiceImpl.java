package com.pv.QueryBased.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pv.QueryBased.DTO.MovieDTO;
import com.pv.QueryBased.Entity.Movie;
import com.pv.QueryBased.Repository.MovieRepository;


@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	MovieRepository repo;
	ModelMapper mp = new ModelMapper();
	@Override
	public List<MovieDTO> findAllwithDirectorName(String name) {
		// TODO Auto-generated method stub
		List<Movie> lst = repo.findAllwithDirectorName(name);
		List<MovieDTO> l = new ArrayList<>();
		lst.forEach(m->{MovieDTO mdto = mp.map(m, MovieDTO.class); l.add(mdto);});
		return l;
	}

	@Override
	public List<Object[]> findMovieNameDirectorName() {
		// TODO Auto-generated method stub
		List<Object[]> lst = repo.findMovieNameDirectorName();
		return lst;
	}

	@Override
	public Double averageRating(Integer year) {
		// TODO Auto-generated method stub
		
		return repo.averageRating(year);
	}

}
