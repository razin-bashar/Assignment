import java.io.File;

public class ThreadTest {	
	
	
	final public static String WRITE_DIRECTORY = "D:\\Output\\";
	final public static String READ_DIRECTORY = "D:\\input\\";
	 
	public static void main(String[] args) {
		
		int FileNumber = 15;
		int ThreadNumber = 10;
		
		FileGenerator Generator = new FileGenerator(FileNumber, READ_DIRECTORY);
		Generator.clearDirectory(READ_DIRECTORY, WRITE_DIRECTORY);
		Generator.generateFiles();
		
		Boss Boss = new Boss(WRITE_DIRECTORY, READ_DIRECTORY, ThreadNumber);
		Boss.giveOrderForMerging();
		

	}

}
