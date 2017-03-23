import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	String FileName;
	FileWriter writer=null;
	public Writer(String Directory,String FileName) {
		this.FileName=FileName;
		File f=new File(Directory+FileName);
		try {
			if(f.exists())f.delete();
			writer=new FileWriter(f,true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("writer class e constructor e problem");
		}
	}
	public void write(StringBuilder Data){
		try {
			writer.append(Data.append('\n'));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("writer class e write function  e problem");
		}
	}
}
