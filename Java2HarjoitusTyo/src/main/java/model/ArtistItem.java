package model;

public class ArtistItem {
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArtistItem(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public ArtistItem(String name) {
		super();
		this.name = name;
	}
	public ArtistItem() {
		super();
	}
	
}
