package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note{
	private String content;
	public TextNote(String title) {
		super(title);
	}

	public TextNote(String title, String content){
		super(title);
		this.content = content;
        
	}
	
	public String getContent(){
        return content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
		}
	
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		// TODO
		
			FileInputStream fis = null;
			ObjectInputStream in = null;
			try {
				fis = new FileInputStream(absolutePath);
				in = new ObjectInputStream(fis);
				in.close();
				result = (String) in.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return result;
	
	}

	public void exportTextToFile(String pathFolder) {
		//TODO
		
		

		
		// TODO
		
		
		
		}
		
		
}

