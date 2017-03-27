import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PublicKey;

public class Writer {
	
	String FileName;
	FileWriter writer = null;
	private String Directory;
	
	public Writer(String Directory, String FileName) {
		
		this.FileName = FileName;
		
		File file=new File(Directory+FileName);
		
		try {
			
			if(file.exists())file.delete();
			
			writer = new FileWriter(file,true);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println( "writer class e constructor e problem" );
			
		}
	}
	
	public Writer(String Directory){
		
		this.Directory = Directory;
		
	}
	
	public void write(StringBuilder Data){
		
		try {
			
			writer.append(Data.append('\n'));			
			writer.flush();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println( "writer class e write function  e problem" );
			
		}
	}
	public void closeFile(){
		
		try {
			
			writer.close();
			
		} catch (IOException e) {
			
			System.out.println( "Writer:closeFile function  e problem" );
			e.printStackTrace();
			
		}
	}
	
	public void clearDirectory(){
		
		File[] FilesinDirectory = new Reader().loadFileFromFolder(Directory);
		 
		for (File file:FilesinDirectory) {
			
			try{
				
				if(file.exists())file.delete();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println( "Writer:directoryClear->problem" );
				
			}
			
		}
	}
}
