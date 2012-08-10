package restshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 * Servlet implementation class RestShopClient
 */
@WebServlet("/RestShopClient")
public class RestShopClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestShopClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("selectMethod");
		String uri=request.getParameter("uri");
		String content=request.getParameter("content");
		PrintWriter out=response.getWriter();
		
		Client client=Client.create();
		client.addFilter(new LoggingFilter(System.out));
		WebResource wr=null;
		try {
			wr=client.resource(uri);
			ClientResponse res;
			if(method.equals("POST")) {
				res=wr.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, content);
			} else if(method.equals("GET")) {
				res=wr.get(ClientResponse.class);
			} else if(method.equals("PUT")) {
				res=wr.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, content);
			} else if(method.equals("DELETE")) {
				res=wr.delete(ClientResponse.class);
			} else {
				res=null;
			}
			
			
			if(res!=null) {
				out.println(res.toString());
				out.println(res.getEntity(String.class));
			} else {
				out.println("Not a valid method.");
			}
		} catch (ClientHandlerException e) {
			out.println("Bad URI.");
		}
		
	}

}
