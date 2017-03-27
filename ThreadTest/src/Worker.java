import java.io.File;

public class Worker extends Thread {
	
	File[] FileList;
	Writer writer;
	int ID;
	
	public Worker(File[] FileList, String OutputFile, String OutputDirectory, int ID) {
		
		this.FileList = FileList;
		this.ID = ID;
		writer = new Writer(OutputDirectory,OutputFile);
		
	}
	@Override
	public void run() {
		
		for(File file:FileList){
			
			try{
				
				if(file.isFile()){
					
					StringBuilder Data = Reader.read(file);
					writer.write(Data);
					
				}
				
			}catch(Exception e){
				
				System.out.println( "File Slot Empty" );
				
			}
			
		}
		
	}
}
