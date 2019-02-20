package Tester;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TalkBoxConfig.ImportAudio;
import TalkBoxConfig.TalkBoxConfiguration;


class TestTest {

	TestGui t;
	ImportAudio imobj = new ImportAudio();
	TalkBoxConfiguration tbc = new TalkBoxConfiguration();
		
	@SuppressWarnings("static-access")
	@BeforeEach
	void setup() {
		t = new TestGui();
		t.launch(TestGui.class);
	}
	
	
	@Test
	void AddButtons() {
		assertEquals(5,tbc.getNumberOfAudioButtons());
	}

	
	

}
