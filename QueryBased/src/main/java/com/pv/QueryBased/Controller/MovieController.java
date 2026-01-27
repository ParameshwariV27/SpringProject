package com.pv.QueryBased.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pv.QueryBased.DTO.MovieDTO;
import com.pv.QueryBased.Service.MovieServiceImpl;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieServiceImpl service;
	
	@GetMapping("/{name}")
	public List<MovieDTO> findAllwithDirectorName(@PathVariable("name") String name)
	{
		return service.findAllwithDirectorName(name);
	}
}
