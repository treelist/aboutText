package database;

import java.sql.Date;

public class ArticleVO {
	private int id;
	private int co_id;
	private Date issued;
	private String title;
	private String contents;
	private Date created;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCo_id() {
		return co_id;
	}
	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}
	public Date getIssued() {
		return issued;
	}
	public void setIssued(Date issued) {
		this.issued = issued;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String toString() {
		return "ArticleVO [id:" + id + ", co_id:" + co_id + ", issued:" + issued + ", title:" + title + "]";
	}
	
}
