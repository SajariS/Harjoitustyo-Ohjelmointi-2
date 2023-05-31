package model;

import java.util.List;

public class AlbumItem {
	private long id;
	private long artistId;
	private String title;
	private List<String> tracks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getArtistId() {
		return artistId;
	}

	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}

	public String getTitle() {
		return title;
	}
	
	
	public List<String> getSongs() {
		return tracks;
	}

	public void setSongs(List<String> tracks) {
		this.tracks = tracks;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AlbumItem(long id, long artistId, String title) {
		super();
		this.id = id;
		this.artistId = artistId;
		this.title = title;
	}

	public AlbumItem() {
		super();
	}

}
