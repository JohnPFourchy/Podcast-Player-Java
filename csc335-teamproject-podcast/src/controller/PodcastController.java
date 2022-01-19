package controller;

import java.io.File;
import java.io.IOException;

import model.Episode;
import model.PodcastModel;

/**
 * The controller of the Podcast Manager application, which serves
 * as the link between the GUI view that allows for user interaction,
 * and the model the stores the necessary information about the
 * files the view can display.
 * 
 * @author Akli Amrous
 * @author Rhys Davis
 * @author Joey Enslen
 * @author John Fourchy
 */
public class PodcastController {

	private PodcastModel model;

	/**
	 * The constructor of the controller which contains a model object to allow the view
	 * to interact with audio files.
	 * 
	 * @param model - the object that allows for interaction with audio files
	 */
	public PodcastController(PodcastModel model) {
		this.model = model;
	}

	/**
	 * Gets the model to play the audio file.
	 */
	public void play() {
		model.playAudio();
	}

	/**
	 * Gets the model to pause the audio file.
	 */
	public void pause() {
		model.pauseAudio();
	}

	/**
	 * Returns the media status of the audio file.
	 * 
	 * @return the media status of the audio file
	 */
	public String getMediaStatus() {
		return model.getMediaStatus();
	}

	/**
	 * Gets the model to fast forward the audio file by 10 seconds.
	 */
	public void fastForward() {
		model.fastForward();
	}

	/**
	 * Gets the model to rewind the audio file by 10 seconds.
	 */
	public void rewind() {
		model.rewind();
	}

	/**
	 * Gets the model to load a new audio file.
	 * 
	 * @param audio - the new audio file to load
	 */
	public void loadNewAudioFile(File audio) {
		model.loadNewMedia(audio);
	}

	/**
	 * Gets the model to increase the volume.
	 */
	public void volumeUp() {
		model.volumeUp();
	}

	/**
	 * Gets the model to decrease the volume.
	 */
	public void volumeDown() {
		model.volumeDown();
	}

	/**
	 * Returns the current volume of the audio file.
	 * 
	 * @return the current volume of the audio file.
	 */
	public double getVolume() {
		return model.getCurrentVolume();
	}

	/**
	 * Sets a new playback speed of the audio file.
	 */
	public void setPlaybackSpeed() {
		model.setPlaybackSpeed();
	}

	/**
	 * Gets the current playback speed from the model.
	 * 
	 * @return the current playback speed
	 */
	public String getPlaybackSpeed() {
		return model.getPlaybackSpeed();
	}
	
	/**
	 * Gets the model to download an episode of a podcast, and returns
	 * the file path of where it was downloaded.
	 * 
	 * @param episode - the episode to download
	 * @return file path of the location of the downloaded file
	 */
	public String downloadFile(Episode episode) {
		try {
			return model.downloadFile(episode);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Sets models default path to download files to.
	 * 
	 * @param path - the new file location specifying where to download files
	 */
	public void setDefaultPath(String path) {
		model.setDefaultPath(path);
	}

	/**
	 * Gets the initial default file path to allow the user to browse
	 * their computer for podcast files.
	 * 
	 * @return the initial default file path of a users files
	 */
	public String getInitialDefaultPath() {
		return model.getInitialDefaultPath();
	}

}
