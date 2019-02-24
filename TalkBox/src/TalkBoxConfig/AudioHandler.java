package TalkBoxConfig;


public class AudioHandler<ActionEvent>  implements javafx.event.EventHandler<javafx.event.ActionEvent> {
	
	private AudioClip clip;
	

	
	
	public AudioHandler(String audioPath) {
		this.clip = new AudioClip(audioPath);
	}


	@Override
	public void handle(javafx.event.ActionEvent event) {
		this.clip.play();
	}
	
	
	public boolean isActive() {
		return this.clip.isConnecting();
	}
	
	

}
