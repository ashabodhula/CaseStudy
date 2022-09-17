package com.bookservice.request;

import com.bookservice.model.Reader;

public class BuyRequest {

	
	private int bookId;
	private Reader reader;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
}
