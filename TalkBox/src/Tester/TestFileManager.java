package Tester;

import TalkBoxConfig.GuiConfig;
import javafx.application.Application;
import javafx.stage.Stage;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import TalkBoxConfig.FileManager;

class FileManagerTest {

	@Test
	void testFindFilesReturnArrayofFiles() throws IOException {
		
		FileManager file  =  new FileManager();
		Object[] files = file.finder("Yes");
		assertEquals(File[].class,files.getClass());              
	}
	
	@Test
	void testFindFilesNonExistantDir() {
		
		try {
			
		
		FileManager file  =  new FileManager();
		Object[] files = file.finder("hello");
		assertFalse(true,"Expected exception to be thrown");
		
		}
		catch(IOException e) {
			
			assertTrue(true);
		}
          
	}
	
	
	
	@Test
	void testReturnsEmptyFileArrayIfPathIsNull() throws IOException {
		
		FileManager file  =  new FileManager();
		Object[] files = file.finder(null);
		assertEquals(0,files.length);  
	}
	
	
	@Test
	void testReturnsallWavsInDirl() throws IOException {
		
		FileManager file  =  new FileManager();
		Object[] files = file.finder("waves");
		assertEquals(1,files.length);  
	}


	
}