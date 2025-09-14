package com.pv.IBook_Postgres.dto;

import java.time.LocalDate;
import java.util.Objects;



public class BookDTO {
	
	private Integer bookId;
	private String title;
	private String authorName;
	private LocalDate publishedYear;
	private String publisher;
	private Long ibsn;
	private Integer price;
	
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public LocalDate getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(LocalDate publishedYear) {
		this.publishedYear = publishedYear;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Long getIbsn() {
		return ibsn;
	}
	public void setIbsn(Long ibsn) {
		this.ibsn = ibsn;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		return Objects.hash(authorName, bookId, ibsn, price, publishedYear, publisher, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDTO other = (BookDTO) obj;
		return Objects.equals(authorName, other.authorName) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(ibsn, other.ibsn) && Objects.equals(price, other.price)
				&& Objects.equals(publishedYear, other.publishedYear) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", title=" + title + ", authorName=" + authorName + ", publishedYear="
				+ publishedYear + ", publisher=" + publisher + ", ibsn=" + ibsn + ", price=" + price + "]";
	}
	
	

}
