
public class FileGenerator {
	
	private int FileNumber;
	private String Directory;
	
	public FileGenerator(int FileNumber, String Directory) {
		
		this.FileNumber = FileNumber;
		this.Directory = Directory;
		
	}
	public void generateFiles(){
		
		for (int i = 0; i < FileNumber; i++) {
			
			Writer FileWriter = new Writer(Directory, (i+1)+".txt");
			FileWriter.write( new StringBuilder( (i+1) + "" ) );
			FileWriter.closeFile();
			
		}
	}
	
	public void clearDirectory(String ReadDirectory, String WriteDirectory){
		
		new Writer(WriteDirectory).clearDirectory();
		new Writer(ReadDirectory).clearDirectory();
		
	}
}
