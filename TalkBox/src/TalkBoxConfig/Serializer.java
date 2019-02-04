package TalkBoxConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serializer {
	
	
public static void Save(Serializable data, String s) throws Exception{
	File e = new File(s);
	e.mkdir();
	ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(e));
	os.writeObject(data);
	os.close();
}	
	
public static Object Load( String s) throws Exception{
	ObjectInputStream is = new ObjectInputStream(Files.newInputStream(Paths.get(s)));
	return is.readObject();
}
}



