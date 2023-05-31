package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCArtistDao;
import model.ArtistItem;

@SuppressWarnings("serial")
@WebServlet("/artists")
public class ArtistListServlet extends HttpServlet {
	
	private JDBCArtistDao dao = new JDBCArtistDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ArtistItem> allArtists = dao.getAllItems();
		
		req.setAttribute("artists", allArtists);
		req.getRequestDispatcher("/WEB-INF/artists/artistList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String action  = req.getParameter("action");
		
		if(action.equals("ADD")) {
			ArtistItem artist = new ArtistItem(name);
			dao.addItem(artist);
			resp.sendRedirect("/artists");
		}
		else if(action.equals("SEARCH")) {
			List<ArtistItem> searchedArtists = dao.searchArtistByName(name);
			req.setAttribute("artists", searchedArtists);
			req.setAttribute("searchedName", name);
			req.getRequestDispatcher("/WEB-INF/artists/artistListSearch.jsp").forward(req, resp);
		}
	}
}
