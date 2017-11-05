package database;

public class WordArtTwitterVO {
	private int id;
	private int word_id;
	private int art_id;
	private int count;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWord_id() {
		return word_id;
	}
	public void setWord_id(int word_id) {
		this.word_id = word_id;
	}
	public int getArt_id() {
		return art_id;
	}
	public void setArt_id(int art_id) {
		this.art_id = art_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
