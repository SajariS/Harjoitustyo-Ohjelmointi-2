package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ArtistItem;

public class JDBCArtistDao {

	public List<ArtistItem> getAllItems() {
		//Luodaan lista, johon lisätään kaikki artistit tietokannasta ja palautetaan lista.
		List<ArtistItem> items = new ArrayList<>();

		try (Connection connection = SQLConnection.connect();
				PreparedStatement statement = connection.prepareStatement("SELECT ArtistId, Name FROM Artist ORDER BY Name ASC;");
				ResultSet results = statement.executeQuery()) {
			
			while (results.next()) {
				long id = results.getLong("Artistid");
				String name = results.getString("Name");
				ArtistItem item = new ArtistItem(id, name);
				items.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public long addItem(ArtistItem newItem) {
		//Lisää tietokantaan tietueen saadun olion perusteella
		String title = newItem.getName();
		long addedId = -1;
		
		try
			(Connection connection = SQLConnection.connect();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Artist (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
			
			statement.setString(1, title);
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			generatedKeys.next();
			addedId = generatedKeys.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addedId;
	}
	
	//Hakee artistin nimen tai Id:n perusteella
	public ArtistItem getItemByName(String name) {
		List<ArtistItem> items = getAllItems();
    	ArtistItem item = null;
    	for(int i = 0; i < items.size(); i++) {
    		item = items.get(i);
    		
    		if(item.getName().equalsIgnoreCase(name)) {
    			break;
    		}
    	}
    	return item;
	}
	public ArtistItem getItemById(long id) {
		List<ArtistItem> items = getAllItems();
    	ArtistItem item = null;
    	for(int i = 0; i < items.size(); i++) {
    		item = items.get(i);
    		
    		if(item.getId() == id) {
    			break;
    		}
    	}
    	return item;
	}
	
	public boolean removeArtist(long id) {
		//Poistaa artistin tietokannasta saadun ID:n perusteella 
		
		try
		(Connection connection = SQLConnection.connect();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM Artist WHERE ArtistId = ?")) {
			
			statement.setLong(1, id);
			statement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<ArtistItem> searchArtistByName(String givenName) {
		//Hakee artistin kannasta saadun nimen perusteella 
		List<ArtistItem> foundArtists = new ArrayList<>();
		
		try
		(Connection connection = SQLConnection.connect();
		PreparedStatement statement = connection.prepareStatement("SELECT ArtistId, Name FROM Artist WHERE Name LIKE ? ORDER BY Name ASC;")) {
			
			statement.setString(1, "%" + givenName + "%");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				long id = results.getLong("ArtistId");
				String name = results.getString("Name");
				ArtistItem artist = new ArtistItem(id, name);
				foundArtists.add(artist);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundArtists;
	}
}
