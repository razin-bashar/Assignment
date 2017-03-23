import java.io.File;

public class Boss {
	
	public String WriteDirectory="D:\\";
	public String ReadDirectory="C:\\Users\\Acer\\Documents\\workspace-sts-3.8.3.RELEASE\\Hihi\\Myfile\\";
	
	public File[] FileList;
	public File[] UpperHalfofFileList=null;
	public File[] LowerHalfofFileList=null;
	public File[] TemporaryFile=null;
	
	
	public void giveOrder(){
		prepareFile();
		Worker worker1=new Worker(UpperHalfofFileList,"output1.txt",WriteDirectory);
		Worker worker2=new Worker(LowerHalfofFileList,"output2.txt",WriteDirectory);
		Worker worker3=new Worker(TemporaryFile,"Final.txt",WriteDirectory);
		worker1.start();
		worker2.start();
		try {
			worker1.join();
			worker2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		worker3.start();
		try {
			worker3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Succesfully Done");
	}
	
	public void prepareFile(){
		Reader Razin=new Reader();
		
		FileList=Razin.loadFileFromFolder(ReadDirectory);
		
		UpperHalfofFileList=new File[FileList.length/2];
		LowerHalfofFileList=new File[(int)Math.ceil(((double)FileList.length)/2)];
		
		System.arraycopy(FileList,0,UpperHalfofFileList, 0, FileList.length/2);
		System.arraycopy(FileList, FileList.length/2, LowerHalfofFileList, 0,(int)Math.ceil(((double)FileList.length)/2));
		
		Razin.showAllFileName(UpperHalfofFileList);
		System.out.println("...................");
		Razin.showAllFileName(LowerHalfofFileList);
		
		//System.out.println((int)Math.ceil(((double)FileList.length)/2)+" "+FileList.length);
		
		TemporaryFile=new File[2];
		TemporaryFile[0]=new File(WriteDirectory+"output1.txt");
		TemporaryFile[1]=new File(WriteDirectory+"output2.txt");
	}

}
