package base;
import java.util.Date;
import java.util.Objects;

public class Note {
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		this.date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}
	
	//This function is designed for comparing whether two Note objects are the same In this lab, two notes are the same if their titles are the same. 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
}
