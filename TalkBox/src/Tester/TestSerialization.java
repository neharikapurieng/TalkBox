package Tester;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TalkBoxConfig.ImportAudio;
import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;


class TestSerialization {

	TestGui t;
	ImportAudio imobj = new ImportAudio();
	TalkBoxConfiguration tbc = new TalkBoxConfiguration();
	
	@BeforeEach
	void setup() throws Exception {
		tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc"); 
	}
	
	
	@Test
	void AddButtons() {
		assertEquals(5,tbc.getNumberOfAudioButtons());
	}

	@Test
	void PathtoAudio() {
		assertEquals("src/Audio/",tbc.path);
	}
	
	@Test
	void NumofProfiles() {
		assertEquals(1,tbc.Profiles.length);
}
	
	@Test
	void NumofAudioFiles() {
		assertEquals(2,tbc.NumOfAudioSets);
	}
	
	
	

}
