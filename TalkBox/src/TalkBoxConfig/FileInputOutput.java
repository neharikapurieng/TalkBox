package TalkBoxConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileInputOutput {


	
	public File[] finder(String dirName) throws IOException {
	
	

		try {
			
			File directoryPath = new File(dirName);
			
			File[] files = directoryPath.listFiles(new FilenameFilter() {
	
				@Override
				public boolean accept(File dir, String name) {    
			
					return name.endsWith(".wav");}
				});
			
			
			if(files == null) {
				
			
				throw new IOException("That path does not exist.");
			}
			   
			return files;
		}
		catch(NullPointerException e) {
			
			return new File[0];
		}
		
		
		
	}

	
	public static FileInputStream getWavFile(String fileName) throws Exception {
		
		File f = new File(fileName);
		
		if(f.exists()) {
			
			return new FileInputStream(f);
		}
		else {
			
			throw new Exception("Audio clip path does not exist");
		}
		
	}
	
}
