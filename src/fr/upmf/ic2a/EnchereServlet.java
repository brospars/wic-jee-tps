package fr.upmf.ic2a;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnchereServlet
 */
@WebServlet("/EnchereServlet")
public class EnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int max;
    
    public EnchereServlet() {
        super();
        this.max = 0;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int enchere = 0;
		
		if(request.getParameterMap().containsKey("enchere")) {
			enchere = Integer.parseInt(request.getParameter("enchere"));
		}
		
		synchronized (""){
			if(enchere>max) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				max=enchere;
			}
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Meilleure enchère : "+max);
		out.close();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
