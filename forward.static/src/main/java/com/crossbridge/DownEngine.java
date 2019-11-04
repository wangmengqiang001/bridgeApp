package com.crossbridge;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownEngine
 */
public class DownEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DownEngine() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String version = request.getParameter("version");
		 
		if("1.0.0".equals(version)){
			response.addHeader("Authentication", "AES233933933939447782ABCD");
			response.sendRedirect("http://localhost:8990/osgi_engine/osgi-engine-3.5.1-1.0.0.zip");
		}else if("2.0.0".equals(version)) {
			response.sendRedirect("http://localhost:8990/osgi_engine/osgi-engine-3.12.100-1.0.0-SNAPSHOT.zip");
		}else {//default
			response.sendRedirect("http://localhost:8990/osgi_engine/osgi-engine-3.5.1-1.0.0.zip");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
