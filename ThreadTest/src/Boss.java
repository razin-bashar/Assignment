import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Boss {
		
	public File[] FileList;
	public File[] UpperHalfofFileList = null;
	public File[] LowerHalfofFileList = null;
	public File[] TemporaryFile = null;
	private File[][] ClustersOfFile;
	
	public String WriteDirectory;
	public String ReadDirectory;
	
	private int Threadnumber;
	
	public Boss(String WriteDirectory, String ReadDirectory, int Threadnumber ) {
		
		this.WriteDirectory = WriteDirectory;
		this.ReadDirectory = ReadDirectory;
		this.Threadnumber = Threadnumber;
		
	}
	
	public void giveOrderForMerging(){
		
		prepareFile(ReadDirectory, WriteDirectory);
		
		Worker[] WorkerofMerging = new Worker[Threadnumber];
		
		
		for (int i = 0; i < WorkerofMerging.length; i++) {
			
			WorkerofMerging[i]=new Worker(ClustersOfFile[i], "Output"+i+".txt", WriteDirectory,i);
			WorkerofMerging[i].start();
			
		}
		
		
		for (int i = 0; i < WorkerofMerging.length; i++) {
			try {
				
				WorkerofMerging[i].join();
				
			} catch (InterruptedException e) {
				
				System.out.println( "Boss:giveOrder-> WorkerofMerging["+i+"] join() problem");
				e.printStackTrace();
				
			};
		}
		
		Worker FinalWorkerofMerging = new Worker( TemporaryFile, "Final.txt", WriteDirectory, -100 );
		FinalWorkerofMerging.start();
		
		try {
			
			FinalWorkerofMerging.join();
			
		} catch (InterruptedException e) {
			
			System.out.println( "Boss:giveOrder->FinalWorkerofMerging join() problem" );
			e.printStackTrace();
			
		}
		
		System.out.println( "Succesfully Done" );
	}
	
	public void prepareFile(String ReadDirectory, String WriteDirectory){
		
		Reader Razin = new Reader();	
		FileList = Razin.loadFileFromFolder( ReadDirectory );
		
		Arrays.sort(FileList, new Comparator<File>() {
			
		    public int compare(File f1, File f2) {
		    	
		        try {
		        	
		            int i1 = Integer.parseInt( f1.getName().replaceAll(".txt", "") );
		            int i2 = Integer.parseInt( f2.getName().replaceAll(".txt", "") );
		            return i1 - i2;
		            
		        } catch(NumberFormatException e) {
		        			           
		            System.out.println( "Boss:prepareFile->Arrays.sort problem" ); 
		            throw new AssertionError(e);
		            
		        }
		        
		    }
		    
		});
		
		int ClusterSize = (int)Math.ceil( (double)FileList.length/(double)Threadnumber );
		
		ClustersOfFile = new File[Threadnumber][ClusterSize];
		
		int Start = 0;
		int counter=0;
		
		while((ClusterSize+Start)<FileList.length){
			
			System.arraycopy( FileList,Start,ClustersOfFile[counter], 0, ClusterSize );
			
			Razin.showAllFileName( ClustersOfFile[counter] );
			System.out.println( "................" );
			
			Start += ClusterSize;
			counter++;
			
		}
		
		ClustersOfFile[counter] = new File[FileList.length-Start];	//Remaining element
		
		System.arraycopy( FileList,Start,ClustersOfFile[counter], 0, FileList.length-Start );
		
		Razin.showAllFileName( ClustersOfFile[counter] );
		
		TemporaryFile = new File[Threadnumber];
		
		for (int i = 0; i < ClustersOfFile.length; i++) {
			
			TemporaryFile[i] = new File( WriteDirectory+"Output"+i+".txt" );
		}
		
	}

}
