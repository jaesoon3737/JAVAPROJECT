package soo.mv.model;

import java.sql.Date;

public class AddrDTO {
		
	private int eriCount;
	private String id;
	private String name;
	private String title;
	private Date borderdate;
	private String contents;
	public AddrDTO(){
		super();
	}
	public AddrDTO(int eriCount, String id , String name , String title, Date borderdate) {
		this.eriCount = eriCount;
		this.borderdate = borderdate;
		this.id = id;
		this.name =  name;
		this.title = title;
	}
	
	public AddrDTO(int eriCount, String id , String name , String title, Date borderdate , String contents) {
		this.eriCount = eriCount;
		this.borderdate = borderdate;
		this.id = id;
		this.name =  name;
		this.title = title;
		this.contents = contents;
 	}
	
	

	
	public void setBorderdate(Date borderdate){
		this.borderdate = borderdate;
	}
	

	
	public void setEricount(int eriCount){
		this.eriCount = eriCount;
	}

	public void setContents(String contents){
		this.contents = contents;
	}

	public void setId(String id){
		this.id = id;
	}

	public void setName(String name){
		this.name =  name;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public int getEricount() {
		return eriCount;
	}
	public String getId(){

		return id;
	}
	public String getContents(){
	
		return contents;
	}
	public Date getBorderdate(){

		return borderdate;
	}
	
	public String getName(){

		return name;
	}
	public String getTitle(){

		return title;
	}

}
