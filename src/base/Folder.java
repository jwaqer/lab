package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder>{

	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		notes = new ArrayList<Note>();
		this.name = name;
	}
	
	public void addNote(Note e) {
		notes.add(e);
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note note: notes) {
			if (note instanceof TextNote) {
				nText++;
			}
			if (note instanceof ImageNote) {
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder o) {
	
		return name.compareTo(o.name);
	}
		
	public void sortNotes(){
		Collections.sort(notes);

	}
	

	public List<Note> searchNotes(String keywords){
		
		ArrayList<Note> list = new ArrayList<Note> ();
		String[] search  = keywords.split(" ");
		search = keywords.toLowerCase().split("or");
		for(Note note: notes){
			for(String s :search){
				if(note instanceof ImageNote){
					if (note.getTitle().toLowerCase().contains(s)){
						list.add(note);	
                    }
				}

				else if (note instanceof TextNote){
                    if (((TextNote)note).getContent().toLowerCase().contains(s)){
						if (note.getTitle().toLowerCase().contains(s)){
							list.add(note);	
						}
					}
				}
			}
		}
		return list;
		
		
		
	}
	

}
