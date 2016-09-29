package fr.upmf.ic2a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CompteurServlet
 */
// @WebServlet("/CompteurServlet")
public class CompteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int populationGlobaleSchtroumpf = 0;
	private int compteur;
	private static int nbInstances = 0;

	public CompteurServlet() {
		super();
		compteur = 0;
		nbInstances++;
	}

	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    try {
	      FileReader fileReader = new FileReader(getServletContext().getRealPath("/") +"parcheminDuGrandSchtroumpf.initial");
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      String initial = bufferedReader.readLine();
	      populationGlobaleSchtroumpf = Integer.parseInt(initial);
	      return;
	    }
	    catch (FileNotFoundException ignored) { }  // no saved state
	    catch (IOException ignored) { }            // problem during read
	    catch (NumberFormatException ignored) { }  // corrupt saved state

	    populationGlobaleSchtroumpf = 0;
	  }

	public void destroy() {
		super.destroy();
		saveState();		
	}

	public void saveState() {
		System.out.println("save state : "+getServletContext().getRealPath("/"));
		
		try {
			FileWriter fileWriter = new FileWriter(getServletContext().getRealPath("/") +"parcheminDuGrandSchtroumpf.initial");
			String initial = Integer.toString(populationGlobaleSchtroumpf);
			fileWriter.write(initial, 0, initial.length());
			fileWriter.close();
			return;
		} catch (IOException e) {
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		compteur++;
		populationGlobaleSchtroumpf++;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html> <body> Salut aux " + compteur
				+ " schtroumpfs qui sont passés par là !</br> Ils habitent dans " + nbInstances
				+ " Villages en champignons et ils sont " + populationGlobaleSchtroumpf + "! </body> </html>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
