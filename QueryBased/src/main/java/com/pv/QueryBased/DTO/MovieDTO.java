package com.pv.QueryBased.DTO;

import java.util.Objects;

public class MovieDTO {

	
	private Integer movieId;
	private String movieName;
	private String directorName;
	private Float imdbRating;
	private Integer year;
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public Float getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(Float imdbRating) {
		this.imdbRating = imdbRating;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "MovieDTO [movieId=" + movieId + ", movieName=" + movieName + ", directorName=" + directorName
				+ ", imdbRating=" + imdbRating + ", year=" + year + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(directorName, imdbRating, movieId, movieName, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieDTO other = (MovieDTO) obj;
		return Objects.equals(directorName, other.directorName) && Objects.equals(imdbRating, other.imdbRating)
				&& Objects.equals(movieId, other.movieId) && Objects.equals(movieName, other.movieName)
				&& Objects.equals(year, other.year);
	}
	
	
}
