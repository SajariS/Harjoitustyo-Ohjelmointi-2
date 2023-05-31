package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AlbumItem;
import model.ArtistItem;

public class JDBCAlbumDao {

	public List<AlbumItem> getAllAlbums() {
		//Luo listan kaikista albumeista ja palauttaa sen
		List<AlbumItem> albums = new ArrayList<>();
		
		try 
			(Connection connection = SQLConnection.connect();
			PreparedStatement statement = connection.prepareStatement("SELECT AlbumId, Title, ArtistId FROM Album ORDER BY Title ASC;");
			ResultSet results = statement.executeQuery()) {
			
			while (results.next()) {
				long id = results.getLong("AlbumId");
				long artistId = results.getLong("ArtistId");
				String title = results.getString("Title");
				AlbumItem item = new AlbumItem(id, artistId, title);
				albums.add(item);		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albums;
	}
	
	public long addAlbum(AlbumItem album) {
		//Lisää albumin kantaan
		String title = album.getTitle();
		long artistId = album.getArtistId();
		long albumId = -1;
		
		try
			(Connection connection = SQLConnection.connect();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Album (Title, ArtistId) VALUES (?, ?)")) {
			
			statement.setString(1, title);
			statement.setLong(2, artistId);
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();
			albumId = generatedKeys.getLong(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albumId;
	}
	
	public AlbumItem getAlbumByTitle(String title) {
		//Hakee nimen perusteella albumin kannasta
		List<AlbumItem> albums = getAllAlbums();
		AlbumItem item = null;
	   	for(int i = 0; i < albums.size(); i++) {
    		item = albums.get(i);
    		
    		if(item.getTitle().equalsIgnoreCase(title)) {
    			break;
    		}
    	}
    	return item;
	}
	public AlbumItem getAlbumById(long id) {
		//Hakee id:n perusteella albumin kannasta
		List<AlbumItem> albums = getAllAlbums();
		AlbumItem item = null;
	   	for(int i = 0; i < albums.size(); i++) {
    		item = albums.get(i);
    		
    		if(item.getId() == id) {
    			break;
    		}
    	}
    	return item;
	}
	
	public AlbumItem setAlbumTracks(String title) {
		//Hakee albumin nimen perusteella, asettaa sille laulut listaan ja palauttaa albumin
		AlbumItem album = getAlbumByTitle(title);
		List<String> tracks = new ArrayList<>();
		
		try
			(Connection connection = SQLConnection.connect();
			PreparedStatement statement = connection.prepareStatement("SELECT Name FROM Track WHERE AlbumId = ?")) {
			
			statement.setLong(1, album.getId());
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				String name = results.getString("Name");
				tracks.add(name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		album.setSongs(tracks);
		return album;
	}
	
	public List<AlbumItem> getArtistsAlbums(ArtistItem artist) {
		//Hakee kaikki saadun artistin albumit ja palauttaa ne listana
		List<AlbumItem> artistsAlbums = new ArrayList<>();
		List<AlbumItem> allAlbums = getAllAlbums();
		
		for(int i = 0; i < allAlbums.size(); i++) {
			AlbumItem album = allAlbums.get(i);
			
			if(album.getArtistId() == artist.getId()) {
				artistsAlbums.add(album);
			}
		}
		return artistsAlbums;
	}
	
	public boolean removeAlbum(AlbumItem album) {
		//Poistaa saadun albumin tietokannasta
		long id = album.getId();
		
		try 
		(Connection connection = SQLConnection.connect();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM Album WHERE AlbumId = ?")) {
			
			statement.setLong(1, id);
			statement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
