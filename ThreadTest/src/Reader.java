import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	String FolderName;
	File[] FileList;	
	public File[] loadFileFromFolder(String Directory){
		FileList=new File(Directory).listFiles();
		return FileList;
	}	
	
	public static StringBuilder read(File file) {
		
		StringBuilder FileData=new StringBuilder();
		Scanner reader;
		
		try {
			
			reader = new Scanner(new FileReader(file));
			 
			 while( reader.hasNext()){
				 FileData.append(reader.nextLine()+"\n");
			 }			 
		
		} catch (FileNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Reader class e read function  e problem");
		}
		
		return FileData;
		
		
	}
	
	public void showAllFileName(File[] FileList){
		for(File file:FileList){
			if(file.isFile()){
				System.out.println(file.getName());
			}
		}
	}
}
