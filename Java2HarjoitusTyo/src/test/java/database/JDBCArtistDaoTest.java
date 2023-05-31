package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ArtistItem;

class JDBCArtistDaoTest {
	private JDBCArtistDao dao = new JDBCArtistDao();
	
	@BeforeEach
	public void setUp() throws Exception {
		Connection connection = SQLConnection.connect();
		connection.prepareStatement("DELETE FROM Artist").executeUpdate();
		connection.prepareStatement("INSERT INTO Artist (ArtistId, Name) values (1, 'Coldplay'), (2, 'Nirvana')").executeUpdate();
		connection.close();
	}
	
	@Test
	public void getAllItemsTest() {
		// Hakee testikannan ja vertaa sen nimi arvoja
		List<ArtistItem> items = dao.getAllItems();
		
		assertEquals("Coldplay", items.get(0).getName());
		assertEquals("Nirvana", items.get(1).getName());
	}
	
	@Test
	public void addItemTest() {
		ArtistItem item = new ArtistItem("Oasis");
		long id = dao.addItem(item);
		ArtistItem newItem = dao.getItemByName("Oasis");
		
		assertEquals(3, id);
		assertEquals(3, newItem.getId());
	}
}
