package com.nts.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoDto {
	private static final DateTimeFormatter DATE_PATTERN_DATA = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	private long id;
	private String name;
	private LocalDateTime regdate;
	private int sequence;
	private String title;
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException("Name is null");
		}
		this.name = name;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) throws NullPointerException {
		if (regdate == null) {
			throw new NullPointerException("regdate is null");
		}
		this.regdate = regdate.toLocalDateTime();
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws NullPointerException {
		if (title == null) {
			throw new NullPointerException("title is null");
		}
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) throws NullPointerException {
		if (type == null) {
			throw new NullPointerException("type is null");
		}
		this.type = type;
	}

	public String getRegdateAsString() {
		return regdate.format(DATE_PATTERN_DATA);
	}

}
