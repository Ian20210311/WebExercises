package controller.actInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jsoninfo.musicDetail;
import model.jsoninfo.musicShows;

/**
 * Servlet implementation class musicDetailServlet
 */
@WebServlet("/musicDetailServlet")
public class musicDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public musicDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param_id = request.getParameter("id");
		//System.out.println("AAAAAAAAAAAAAAAAAAAA -> " + param_id);
		
		musicShows[] allShows = (musicShows[])request.getSession().getAttribute("allShow");
		//System.out.println("BBBBBBBBBBBBBBBBBBBBB -> " + allShows);
		
		List<musicDetail> musicDetailList = getmusicDetail(allShows, param_id);
		//System.out.println("WWWWWWWWWWWWWWWWWWWW -> " + musicDetailList);
		
		request.getSession().setAttribute("musicDetails", musicDetailList) ;
		request.getRequestDispatcher("WEB-INF/jsp/musicDetail.jsp").forward(request, response);
		
	}
	
	//取得該音樂劇細項資料
	private List<musicDetail> getmusicDetail(musicShows[] allShows, String param_id)
	{
		List<musicDetail> musicDetailList = null;
		for(musicShows m : allShows)
		{
			if(m.getMyUID().equalsIgnoreCase(param_id.trim()))
			{
				musicDetailList = m.getShowInfo();
			}
		}
		return musicDetailList;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
