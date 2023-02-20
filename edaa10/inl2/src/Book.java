
public class Book 
{
	private String title;
	private String author;
	
	public Book (String author, String title) 
	{
		this.author = author;
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String title() {
		return title;
	}
	
	public String toString() {
		return author + " - " + title;
	}
}
