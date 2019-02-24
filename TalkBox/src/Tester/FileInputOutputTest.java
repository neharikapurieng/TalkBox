package Tester;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import TalkBoxConfig.FileInputOutput;

class FileInputOutputTest {

	@Test
	void testFindFilesReturnArrayofFiles() throws IOException {
		
		FileInputOutput file  =  new FileInputOutput();
		Object[] files = file.finder("src/Audio/");
		assertEquals(File[].class,files.getClass());              
	}
	
	@Test
	void testFindFilesNonExistantDir() {
		
		try {
			
		
		FileInputOutput file  =  new FileInputOutput();
		Object[] files = file.finder("hello");
		assertFalse(true,"Expected exception to be thrown");
		
		}
		catch(IOException e) {
			
			assertTrue(true);
		}
          
	}
	
	
	
	@Test
	void testReturnsEmptyFileArrayIfPathIsNull() throws IOException {
		
		FileInputOutput file  =  new FileInputOutput();
		Object[] files = file.finder(null);
		assertEquals(0,files.length);  
	}
	
	
	@Test
	void testReturnsAllWavsInDir() throws IOException {
		
		FileInputOutput file  =  new FileInputOutput();
		Object[] files = file.finder("src/Audio");
		assertEquals(10,files.length);  
	}


	
	@Test
	void testGetsWavFileSuccess() {
		
		try {
			FileInputStream wavFile = FileInputOutput.getWavFile("recordings/default_name.wav");
			assertNotNull(wavFile);
		}
		catch(Exception e) {
			
		}
	}
	
	
	@Test
	void testGetWavFileFail() {
		try {
		
			FileInputStream wavFile = FileInputOutput.getWavFile("invalid_path");
			assertFalse(true,"Expected exception to be thrown");
		}
		catch(Exception e) {
			
			assertEquals("Audio clip path does not exist",e.getMessage());
			assertTrue(true);
			
		}
	}
	
}