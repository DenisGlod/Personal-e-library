package com.example.pel.images;

public enum ImgPath {
	ABOUT("/images/about.png"),
	ADD("/images/add.png"),
	BOOK_ADD("/images/book_add.png"),
	BOOK_DELETE("/images/book_delete.png"),
	BOOK_EDIT("/images/book_edit.png"),
	BOOK_OPEN("/images/book_open.png"),
	BOOK_SAVE("/images/book_save.png"),
	CONNECT("/images/connect.png"),
	DELETE("/images/delete.png"),
	EXIT("/images/exit.png"),
	ICO("/images/ico.png"),
	LOG_OUT("/images/log_out.png"),
	MESSAGE_SAVE_ICO("/images/message_save_ico.png");

	private String str;

	private ImgPath(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return str;
	}
}
