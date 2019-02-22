package TalkBoxConfig;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileManager {

	/* Returns an array of all Files with the given suffix as .wav 
	 * 
	 * 
	 */
	public FileManager() {
		
		
		
	}
	
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
	


}