package Tester;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;

/*
 * To test the serialization, a specific configuration was used:
 * 
 * 5 Audio Buttons
 * 3 Profiles -> {Profile 1, Profile 2, Profile 3}
 * 8 Audio Files
 * Profile 1 Audio Files -> {Hello, Boring}
 * Profile 2 Audio Files -> {Clap}
 * Profile 3 Audio Files -> {Bye,Laugh,Bye,Laugh,Bye}
 * Audio Path -> src/Audio/
 * 
 * 
 */

class TestSerialization {

	TalkBoxConfiguration tbc = new TalkBoxConfiguration();
	
	@BeforeEach
	void setup() throws Exception {
		tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc"); 
	}
	
	//Test if serialized correct number of buttons
	@Test
	void AddButtons() {
		assertEquals(5,tbc.getNumberOfAudioButtons());
	}
	
	//Test if path to audio files is correct
	@Test
	void PathtoAudio() {
		assertEquals("src/Audio/",tbc.path);
	}
	
	//Test if serialized correct number of profiles
	@Test
	void NumofProfiles() {
		assertEquals(3,tbc.Profiles.length);
	}
	
	//Test if serialized correct number of profiles
	@Test
	void NumofProfiles2() {
		Assert.assertNotSame(2,tbc.Profiles.length);
	}
	
	//Test if serialized correct number of total Audio files
	@Test
	void NumofAudioFiles() {
		assertEquals(8,tbc.NumOfAudioSets);
	}
	
	//Test if serialized correct number of total Audio files
	void NumofAudioFiles2() {
		assertEquals(3,tbc.NumOfAudioSets);
	}
	
	//Test if serialized correct audio files in given profile
	@Test
	void AudioName() {
		int end = tbc.AudioName[0][1].length() -3;
		assertEquals("Hello",tbc.AudioName[0][0].substring(18, end));
	}
	
	@Test
	void AudioName2() {
		int end = tbc.AudioName[2][2].length() - 2;
		assertEquals("Bye",tbc.AudioName[2][2].substring(18, end));
	}
	
	//Test if serialized correct profile name
	@Test
	void getProfile() {
		int end = tbc.Profiles[2].length() - 2;
		Assert.assertNotEquals("Profile 2",tbc.Profiles[2].substring(18,end));
	}
	
	//Test if serialized correct profile name
	@Test
	void getProfile2() {
		int end = tbc.Profiles[0].length() - 2;
		assertEquals("Profile 1", tbc.Profiles[0].substring(18, end));
	}
	
	//Test if serialized correct Audio array
	@Test
	void audioArray() {
		String s = "TreeItem [ value: ";
		String e = " ]";
		String [][] temp = new String[][]
				{{s + "Hello" + e,s + "boring" + e,null,null,null},
			     {s + "Clap" + e,null,null,null,null},
			     {s + "Bye" + e,s+ "Laugh" +e,s+ "Bye" +e,s +"Laugh"+ e,s+ "Bye" +e}};
		Assert.assertArrayEquals(temp,tbc.getAudioFileNames());
	}
	
	
}
