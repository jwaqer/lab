package base;

import java.util.ArrayList;

public class Notebook {
	private ArrayList<Folder> folders;
	
	public Notebook() {
		folders=new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title){
		TextNote note=new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title){
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	} 
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}

	
	public boolean insertNote(String folderName, Note note) {
	
		Folder f = null;
		for (Folder f1:folders) {
			if(f1.getName() == folderName) {
				f = f1;		
			}
		}
		if(f == null) {
			f = new Folder(folderName);
			folders.add(f);
			//f.addNote(note);			
			//return true;
		}
		
		for(Note n:f.getNotes()) {
			if(f.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
			
				
		}
		f.addNote(note);			
		return true;
		
	}
	
	
}