package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCAlbumDao;
import database.JDBCArtistDao;
import model.AlbumItem;
import model.ArtistItem;

@SuppressWarnings("serial")
@WebServlet("/artist")
public class ArtistServlet extends HttpServlet{
	private JDBCArtistDao artistDao = new JDBCArtistDao();
	private JDBCAlbumDao albumDao = new JDBCAlbumDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long artistId = Long.valueOf(req.getParameter("artistId"));
		ArtistItem artist = artistDao.getItemById(artistId);
		String name = artist.getName();
		List<AlbumItem> albums = albumDao.getArtistsAlbums(artist);
		
		req.setAttribute("albums", albums);
		req.setAttribute("artist", name);
		req.getRequestDispatcher("/WEB-INF/artists/artist.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		long artistId = Long.valueOf(req.getParameter("artistId"));
		AlbumItem newAlbum = new AlbumItem();
		newAlbum.setTitle(name);
		newAlbum.setArtistId(artistId);
		albumDao.addAlbum(newAlbum);
		
		resp.sendRedirect("/artist?artistId=" + artistId);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.valueOf(req.getParameter("id"));
		
		if(artistDao.removeArtist(id)) {
			resp.getWriter().println("{ \"success\": true }");
		}
		
	}
}
