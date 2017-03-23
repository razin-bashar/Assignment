import java.io.File;

public class Worker extends Thread {
	File[] FileList;
	Writer writer;
	public Worker(File[] FileList,String OutputFile,String OutputDirectory) {
		this.FileList=FileList;
		writer=new Writer(OutputDirectory,OutputFile);
	}
	@Override
	public void run() {
		for(File file:FileList){
			if(file.isFile()){
				StringBuilder Data=Reader.read(file);
				writer.write(Data);
			}
		}
	}
}
