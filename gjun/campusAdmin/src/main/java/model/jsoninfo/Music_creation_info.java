package model.jsoninfo;

/**
 * 
 * @author Morris
 * 
 *         創作音樂得獎作品
 * 
 *         https://data.gov.tw/dataset/114447
 * 
 *         String filePath = "/WEB-INF/json/Music_creation.json";
 * 
 */
public class Music_creation_info {
	private String year;
	private String music_name;
	private String author;

	public Music_creation_info() {
		super();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}