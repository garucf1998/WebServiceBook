package Entity;

import java.io.Serializable;


public class Book implements Serializable{
	
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String name) {
		super();
		this.name = name;
	}
	
}
