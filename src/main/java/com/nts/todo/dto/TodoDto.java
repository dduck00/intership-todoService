package com.nts.todo.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 데이터 저장 클래스
 * @author 이상덕
 * @version 1.0
 */
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

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		try {
			this.regdate = regdate.toLocalDateTime();
		} catch (NullPointerException e) {
			System.out.println("regDate is null");
			this.regdate = null;
		}
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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegdateAsString() {
		if (regdate == null) {
			return "Wrong Date Fromat";
		}
		return regdate.format(DATE_PATTERN_DATA);
	}

}
