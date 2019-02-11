package TalkBoxSim;

import TalkBoxConfig.Serializer;
import TalkBoxConfig.TalkBoxConfiguration;

public class Profiles {

	TalkBoxConfiguration tbc;
	public void profiles() throws Exception {
		tbc = (TalkBoxConfiguration) Serializer.Load("bin/TalkBoxData/TalkBoxData.tbc");
		
		
	}
}
