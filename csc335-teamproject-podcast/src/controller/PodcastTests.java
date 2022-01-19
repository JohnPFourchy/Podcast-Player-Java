package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Episode;
import model.PodcastModel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// The filepath to the file used for testing is hardcoded and will 
// need to be switched to double check coverage.
public class PodcastTests {
	@Test
	void create_loadEpisodes_coverController() throws IOException {
		PodcastModel testModel = new PodcastModel();
		PodcastController testControls = new PodcastController(testModel);
		testModel.getInitialDefaultPath();
		testControls.setDefaultPath("C:\\Users\\ensle\\git\\csc-335-podcast-player-team6-the-joe-john-akli-rhys-hodgepodge\\csc335-teamproject-podcast\\src\\Podcasts");
		File xmlFile = new File("C:\\Users\\ensle\\git\\csc-335-podcast-player-team6-the-joe-john-akli-rhys-hodgepodge\\csc335-teamproject-podcast\\src\\Podcasts\\Zapatistas.xml");
		ArrayList<Episode> episodeList = new ArrayList<Episode>();
		episodeList = testModel.parseRSSFile(xmlFile);
		String fileLoc = testControls.downloadFile(episodeList.get(0));
		File audioFile = new File(fileLoc);
		// When the file is already downloaded we end up getting less coverage because 
		// this code downloads our episodes, but if they are already downloaded, we skip parts
		// of the model code
		for (Episode curEp : episodeList) {
			curEp.getImageURL();
			curEp.getPodcastTitle();
			curEp.getAuthor();
			curEp.getEpisodeNumber();
			testControls.downloadFile(curEp);
		}
		testControls.play();
		testControls.pause();
		testControls.getMediaStatus();
		testControls.fastForward();
		testControls.rewind();
		testControls.volumeUp();
		testControls.volumeDown();
		assertTrue(0.0 == testControls.getVolume());
		testControls.setPlaybackSpeed();
		testControls.getPlaybackSpeed();
		testControls.getInitialDefaultPath();
	}
	
	@Test
	void create_loadEpisodes_coverModelAdditional() {
		PodcastModel testModel = new PodcastModel();
		PodcastController testControls = new PodcastController(testModel);
		testControls.setDefaultPath("C:\\Users\\ensle\\git\\csc-335-podcast-player-team6-the-joe-john-akli-rhys-hodgepodge\\csc335-teamproject-podcast\\src\\Podcasts");
		File xmlFile = new File("C:\\Users\\ensle\\git\\csc-335-podcast-player-team6-the-joe-john-akli-rhys-hodgepodge\\csc335-teamproject-podcast\\src\\Podcasts\\Zapatistas.xml");
		ArrayList<Episode> episodeList = new ArrayList<Episode>();
		episodeList = testModel.parseRSSFile(xmlFile);
		String fileLoc = testControls.downloadFile(episodeList.get(0));
		File audioFile = new File(fileLoc);
	}
}
