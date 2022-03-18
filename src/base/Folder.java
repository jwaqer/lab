package base;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;


public class Folder implements Comparable<Folder>, Serializable{
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 2L;

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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	public String toString() {
		int nText = 0;
		int nImage = 0;

		for(Note n : notes) {

			if(n instanceof TextNote) {
				nText++;
			} else if(n instanceof ImageNote) {
				nImage++;
			}
		}

		return name + ":" + nText + ":" + nImage;
	}

	// Lab3 Start
	@Override
	public int compareTo(Folder f) {
		return name.compareTo(f.getName());
	}

	public void sortNotes() {
		Collections.sort(notes);
	}


	public List<Note> searchNotes(String keywords) {
		List<Note> res = new ArrayList<Note>();

		ArrayList<ArrayList<String>> patterns = new ArrayList<ArrayList<String>>();

		//process keywords
		String[] keyList = keywords.split(" ");
		int i=0;
		while(i<keyList.length) {
			if(keyList[i].toLowerCase().equals("or")) {
				i++;
				patterns.get(patterns.size() - 1).add(keyList[i].toLowerCase());
			} else {
				ArrayList<String> newArr = new ArrayList<String>();
				newArr.add(keyList[i].toLowerCase());
				patterns.add(newArr);
			}
			i++;
		}

		for(Note n : notes) {
			String toBeSearched = n.getTitle();
			if(n instanceof TextNote) {
				toBeSearched +=  ((TextNote)n).getContent();
			}
			toBeSearched = toBeSearched.toLowerCase();

			boolean flag = true;
			for(ArrayList<String> pattern : patterns) {
				boolean flag2 = false;
				for(String oneKey : pattern) {
					if(toBeSearched.contains(oneKey)) {
						flag2 = true;
						break;
					}
				}
				if(!flag2) {
					flag = false;
					break;
				}
			}
			if(flag) res.add(n);
		}

		return res;
	}





}
