package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable{

	private static final long serialVersionUID = 1L;

	private ArrayList<Folder> folders;

	
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file){
			
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			folders = n.folders;
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean createTextNote(String folderName, String title){
		TextNote note= new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName,String title,String content){
		TextNote note = new TextNote(title, content);
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
				break;
			}
		}
		if(f == null) {
			f = new Folder(folderName);
			folders.add(f);
		
		}
		
		for(Note n : f.getNotes()) {
			if(n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
			
				
		}
		f.addNote(note);			
		return true;
		
	}
	
	public void sortFolders() {
		for (Folder f : folders) {
			f.sortNotes();}

		Collections.sort(folders);


	}
	
	public List<Note> searchNotes (String keywords) {

		ArrayList<Note> search_result = new ArrayList<Note>();
        for (Folder f : folders){
            search_result.addAll( f.searchNotes(keywords) ); }
        return search_result;


	}

	
	public boolean save(String file){
		

		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
		
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
		   return false;
		}
		return true;
	}

	

	

}
