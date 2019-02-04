import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class AudioRecorder {


	public static void main(String[] args) throws LineUnavailableException {
	
		AudioFormat format = new AudioFormat(1600,8,2,true,true);
		
		DataLine.Info info =  new DataLine.Info(TargetDataLine.class, format);
		
		if(! AudioSystem.isLineSupported(info)) {
			
			System.out.println("Line is not supported");
			
		}
		
		
		TargetDataLine  targetDataLine = (TargetDataLine)AudioSystem.getLine(info);
		
		targetDataLine.open();
		
		System.out.println("Start Recording");
		
		targetDataLine.start();
		
		
		
	}



}
