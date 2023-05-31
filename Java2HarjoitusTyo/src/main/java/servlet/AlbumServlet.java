package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCAlbumDao;
import model.AlbumItem;

@SuppressWarnings("serial")
@WebServlet("/album")
public class AlbumServlet extends HttpServlet {
	private JDBCAlbumDao dao = new JDBCAlbumDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.valueOf(req.getParameter("albumId"));
		AlbumItem album = dao.getAlbumById(id);
		AlbumItem trackedAlbum = dao.setAlbumTracks(album.getTitle());
		List<String> tracks = trackedAlbum.getSongs();
		req.setAttribute("tracks", tracks);
		req.setAttribute("album", trackedAlbum.getTitle());
		
		//artistId JS redirectiä varten
		req.setAttribute("artistId", trackedAlbum.getArtistId());
		
		req.getRequestDispatcher("/WEB-INF/artists/album.jsp").forward(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.valueOf(req.getParameter("id"));
		AlbumItem album = dao.getAlbumById(id);
		dao.removeAlbum(album);
		req.setAttribute("artist", album.getArtistId());
		req.getRequestDispatcher("/WEB-INF/artists/album.jsp").forward(req, resp);
	}
}

