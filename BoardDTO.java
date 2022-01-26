package soo.mv.model;

import java.sql.Date;

public class BoardDTO {
	private int seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	
	// (1) Constructor
	public BoardDTO() {
	}
	
	public BoardDTO(int seq, String writer, String email, String subject, String content, Date rdate) {
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.rdate = rdate;
	}
	
	// (2) setter
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	// (3) getter
	public int getSeq() {
		return seq;
	}
	public String getWriter() {
		return writer;
	}
	public String getEmail() {
		return email;
	}
	public String getSubject() {
		return subject;
	}
	public String getContent() {
		return content;
	}
	public Date getRdate() {
		return rdate;
	}
	
}
