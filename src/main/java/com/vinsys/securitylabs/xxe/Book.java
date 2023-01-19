package com.vinsys.securitylabs.xxe;

public class Book {
	private String author;
	private String title;
	private String publisher;
	private String isbn;

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
