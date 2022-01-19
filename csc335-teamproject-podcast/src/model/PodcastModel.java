package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * The model of the Podcast Manager application that handles the bulk
 * of work dealing with the managing of files, and their settings.
 * 
 * This includes downloading files from the internet, the file path
 * of where to download these files, the loading, pausing, playing,
 * volume changes, and time stamp locations of these files, as well
 * as the parsing of them to gain various pieces of information from them.
 * 
 * @author Akli Amrous
 * @author Rhys Davis
 * @author Joey Enslen
 * @author John Fourchy
 */
public class PodcastModel { 

	private Media media;
	private String mediaStatus;
	private MediaPlayer player;
	private String defaultPath;
	
	/**
	 * The default constructor of the model that initializes all fields
	 * as necessary when there is no current audio filed loaded.
	 */
	public PodcastModel() {
		media = null;
		mediaStatus = "none loaded";
		player = null;
	}
	
	/**
	 * The constructor of the model that initializes all fields
	 * as necessary when there is an audio filed to be loaded.
	 * 
	 * @param audio_file - the audio file to be loaded
	 */
	public PodcastModel(File audio_file) {
		this.media = new Media(audio_file.toURI().toString());
		mediaStatus = "paused";
		this.player = new MediaPlayer(this.media);
	}
	
	/**
	 * Loads a new audio file as the current podcast being played.
	 * 
	 * @param audio_file - the new audio file to load
	 */
	public void loadNewMedia(File audio_file) {
		this.media = new Media(audio_file.toURI().toString());
		this.player = new MediaPlayer(this.media);
		this.mediaStatus = "paused";
	}
	
	/**
	 * Plays the audio file.
	 */
	public void playAudio() {
		if (player != null) {
			this.player.play();
			mediaStatus = "playing";
		}
	}
	
	/**
	 * Pauses the audio file.
	 */
	public void pauseAudio() {
		if (player != null) {
			this.player.pause();
			mediaStatus = "paused";
		}
	}
	
	/**
	 * Returns the current status of the audio file, whether it is
	 * currently being played or not.
	 * 
	 * Returns "playing" if the audio file is playing, and
	 * "paused" if the audio file is paused.
	 * 
	 * @return the current status of the audio file
	 */
	public String getMediaStatus() {
		return mediaStatus;
	}
	/**
	 * Skips ahead by 10 seconds in the audio file.
	 */
	public void fastForward() {
		if (player != null) {
			Duration currPlaybackTime = player.getCurrentTime();
			Duration nextPlaybackTime = currPlaybackTime.add(Duration.seconds(10.0));
			player.seek(nextPlaybackTime);
		}
	}
	
	/**
	 * Skips backwards by 10 seconds in the audio file.
	 */
	public void rewind() {
		if (player != null) {
			Duration currPlaybackTime = player.getCurrentTime();
			Duration nextPlaybackTime = currPlaybackTime.subtract(Duration.seconds(10.0));
			player.seek(nextPlaybackTime);
		}
	}
	
	/**
	 * Increases the volume by .1, up to a max of 1.
	 */
	public void volumeUp() {
		if (player != null) {
			if (this.player.getVolume() + 0.1 > 1) {
				return;
			} else {
				player.setVolume(player.getVolume() + 0.1);
			}
		}
	}
	
	/**
	 * Decreases the volume by .1, to a minimum of 0.
	 */
	public void volumeDown() {
		if (player != null) {
			if (this.player.getVolume() - 0.1 < 0) {
				return;
			} else {
				player.setVolume(player.getVolume() - 0.1);
			}
		}
	}
	
	/**
	 * Returns the current volume of the audio file.
	 * 
	 * @return a decimal value representation of the volume of the audio file
	 */
	public double getCurrentVolume() {
		if (player != null) {
			return this.player.getVolume();
		}
		return 0.0;
	}

	/**
	 * Sets the playback speed of the audio file.
	 * 
	 * This represents how fast the audio file is being played,
	 * compared to its normal speed. 1.0 being the normal speed
	 * and anything higher being a faster playback speed.
	 */
	public void setPlaybackSpeed() {
		if (player != null) {
			if(this.player.getRate() == 1.0) {
				this.player.setRate(1.25);
				return;
			} else if (this.player.getRate() == 1.25) {
				this.player.setRate(1.5);
				return;
			} else if (this.player.getRate() == 1.5) {
				this.player.setRate(2.0);
				return;
			} else if (this.player.getRate() == 2.0) {
				this.player.setRate(1.0);
				return;
			}
		} 
	}
	
	/**
	 * Returns the playback speed of the audio file as a string.
	 * 
	 * @return the string representation of the playback speed of the file
	 */
	public String getPlaybackSpeed() {
		if (player != null) {
			return Double.toString(this.player.getRate());
		} else {
			return "1.0";
		}
	}
	
	
	
	 
	
	 /**
	 * Downloads an episode of a podcast and returns the location it
	 * was downloaded to.
	 * 
	 * Downloads a file located at the episode->mp3URL field and downloads it 
	 * on your local machine in the fDEFAULT_PATH folder, with the same name
	 * as the episode->title field.
	 * 
	 * @param episode - the episode to download
	 * @return the file path of the location of the downloaded file
	 * @throws IOException - if the URL cannot be connected to or the download is
	 * interrupted
	 */
	public String downloadFile(Episode episode) throws IOException {
		String fileURL = episode.getMp3URL();
		String whereToDownload;
		String os = System.getProperty("os.name");
	    if (os.contains("Mac")) {
	    	whereToDownload = defaultPath + "/" + episode.getEpisodeTitle();
	    } else {
	    	whereToDownload = defaultPath + "\\\\" + episode.getEpisodeTitle();
	    }
		File testFile = new File(whereToDownload);
		if (testFile.exists()) {
			return whereToDownload;
		}
		
		// Download the file (not for testing coverage, 
		// this section only gets covered if the files have not been
		// downloaded into the directory yet. Delete them for full coverage results.
		URL website = new URL(fileURL);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(whereToDownload);
		fos.getChannel().transferFrom(rbc, 0, Integer.MAX_VALUE);
		rbc.close();
		fos.close();
		return whereToDownload;
	}
	
	/**
	 * Sets the default path to download files to.
	 * 
	 * @param path - the new file location specifying where to download files
	 */
	public void setDefaultPath(String path) {
		this.defaultPath = path;
	}
	
	/**
	 * Gets the initial default file path to allow the user to browse
	 * their computer for podcast files.
	 * 
	 * This gives different initial default paths based on the operating
	 * system the user is using.
	 * 
	 * @return the initial default file path of a users files
	 */
	public String getInitialDefaultPath() {
		String os = System.getProperty("os.name");
		if (os.contains("Mac")) {
			return "/";
		} else {
			return "\\";
		}
	}
	
	/**
	 * Parses a passed in xml / rss file and creates an arrayList of episodes, with
	 * each episode in the podcast being stored in the arrayList. Assumes that we can use the XML Parser library.
	 * 
	 * Ideal Usage: Can create a Podcast object with this array as the constructor argument.
	 * 
	 * @param file - the name of the xml file to parse
	 * @return an arrayList of episode objects.
	 */
	public ArrayList<Episode> parseRSSFile(File file) {
		ArrayList<Episode> episodeList = new ArrayList<Episode>();
		
		String title = "";
		String imageUrl = "";
		String podcastAuthor = "";
		
		DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dBFactory.newDocumentBuilder();
			Document document = dBuilder.parse(file);
			
			// Grab the title of the Podcast
			NodeList titleList = document.getElementsByTagName("title");
			Element mainTitle = (Element) titleList.item(0);
			title = mainTitle.getTextContent();
			
			// Grab the image url of the Podcast
			NodeList imageJPG = document.getElementsByTagName("url");
			Element image = (Element) imageJPG.item(0);
			imageUrl = image.getTextContent();
			
			// Grab the author of the Podcast
			NodeList author = document.getElementsByTagName("itunes:author");
			Element auth = (Element) author.item(0);
			podcastAuthor = auth.getTextContent();
			
			NodeList individualEpisodes = document.getElementsByTagName("enclosure");
			// For each episode in the podcast, grab its title and mp3url
			int k = 0;
			int j = individualEpisodes.getLength() - 1;
			for(int i = titleList.getLength() - 1; i > 1; i--) {
				// Grab title of episode
				Element episodeTitle = (Element) titleList.item(i);
				String epTitle = episodeTitle.getTextContent();
				
				// Grab mp3Url of episode
				Node mp3 = individualEpisodes.item(j).getAttributes().getNamedItem("url");
				String mp3Url = mp3.getTextContent();

				if(j > 0) {
					j -= 1;
				}
				
				// Create episode object and add it to the list of episodes
				Episode individualEpisode = new Episode(mp3Url, imageUrl, epTitle, title, podcastAuthor, k + 1);
				k += 1;
				episodeList.add(individualEpisode);
			}
		} catch (ParserConfigurationException e) {
			System.out.println("Can't parse the xml file!");
		} catch (IOException e) {
			System.out.println("Can't find the xml file!");
		} catch (SAXException e) {
			System.out.println("An SAX error occured!");
		}
		
		return episodeList;
	}
	
	
}
