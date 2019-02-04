package TalkBoxConfig;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serializer {
	
	
public static void Save(Serializable data, String s) throws Exception{
	ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(Paths.get(s)));
	os.writeObject(data);
}	
	
public static Object Load( String s) throws Exception{
	ObjectInputStream is = new ObjectInputStream(Files.newInputStream(Paths.get(s)));
	return is.readObject();
}
}



