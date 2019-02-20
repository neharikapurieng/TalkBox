package TalkBoxConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serializer {
	
/*
 * Serializes and saves the data from TalkBoxConfiguration
 * and creates a directory called TalkBoxData in bin where the
 * TalkBoxData.tbc will be stores
 */
public static void Save(Serializable data, String s) throws Exception{
	
	try {
	File directory = new File(s);
	directory.mkdir();
	ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("bin/TalkBoxData/TalkBoxData.tbc"));
	os.writeObject(data);
	os.close();
	}
	catch(FileNotFoundException e) {
		
		e.printStackTrace();
	}
	
	catch(IOException e) {
		
		e.printStackTrace();
	}
}	
	
/*
 * Deserializes TalkBoxData.tbc and allows access to all the information
 */
public static Object Load( String s) throws Exception{
	ObjectInputStream is = new ObjectInputStream(Files.newInputStream(Paths.get(s)));
	return is.readObject();
}
}



