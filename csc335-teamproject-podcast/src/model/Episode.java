package model;

/**
 * Episode class to store all information necessary for an episode
 * including url to download the mp3 file and image, and the title 
 * and author of the episode.
 * 
 * @author Akli Amrous
 * @author Rhys Davis
 * @author Joey Enslen
 * @author John Fourchy
 */
public class Episode {
	private String mp3URL;
	private String imageURL;
	private String episodeTitle;
	private String podcastTitle;
	private String author;
	private int episodeNumber;

	/**
	 * Creates an episode of a podcast, storing all necessary information related
	 * to that episode in order to properly download, access, and display it.
	 * 
	 * @param mp3URL        - the url of an mp3 file of this podcast
	 * @param imageURL      - the url of the image of this podcast
	 * @param episodeTitle  - the title of this episode of the podcast
	 * @param podcastTitle  - the title of the podcast
	 * @param author        - the author of the podcast
	 * @param episodeNumber - the episode number of the podcast
	 */
	public Episode(String mp3URL, String imageURL, String episodeTitle,
			String podcastTitle, String author, int episodeNumber) {
		this.mp3URL = mp3URL;
		this.imageURL = imageURL;
		this.episodeTitle = episodeTitle;
		this.podcastTitle = podcastTitle;
		this.author = author;
		this.episodeNumber = episodeNumber;
	}

	/**
	 * Returns the mp3 URL of this episode.
	 * 
	 * This is the URL of the mp3 file which can be downloaded and played.
	 * 
	 * @return the mp3URL of this episode.
	 */
	public String getMp3URL() {
		return mp3URL;
	}

	/**
	 * Returns the image URL of this episode.
	 * 
	 * This is the URL of the image file associated with this episode of the podcast.
	 * 
	 * @return the image URL of this episode.
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * Returns the podcast title of the podcast.
	 * 
	 * @return the title of this podcast.
	 */
	public String getPodcastTitle() {
		return podcastTitle;
	}

	/**
	 * Returns the title of the episode.
	 * 
	 * @return the title of the episode.
	 */
	public String getEpisodeTitle() {
		return episodeTitle;
	}

	/**
	 * Returns the author of this episode.
	 * 
	 * @return the author of this episode.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Returns the episode number of this episode.
	 * 
	 * @return the episode number of this episode.
	 */
	public int getEpisodeNumber() {
		return episodeNumber;
	}
}
