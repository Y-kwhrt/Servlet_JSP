package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import validate.Validator;

@WebServlet(urlPatterns = { "/dissts" })
//@SuppressWarnings("unchecked")
//Enumeration<String> names = request.getParaemterNames();
public class OutputServlet extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publi = request.getParameter("publi");
		String price = request.getParameter("price");
		String[] strJan = {"文芸","実用","ビジネス","教養","趣味"};
		List<String> genres = new ArrayList<>();
		//String genres[] = new String[0];
		for(int i = 0; i < strJan.length; i++) {
			String ind = String.valueOf(i);
			genres.add(String.valueOf(request.getParameterValues("genre"+ind)));
		}
		
		String radios = request.getParameter("stock");
		String rem = request.getParameter("remarks");
		Validator val = new Validator();
		
		val.requireCheck(title,"タイトル");
		val.lengthCheck(author,"著者",10);
		if(price != "") {
			val.wordCheck(price,"価格");
			val.lowlimCheck(price,"価格",0);
		}else {
			price = String.valueOf(0);
		}
		
		int null_counter = Collections.frequency(genres,"null");
			
				val.checklengthCheck(genres.size()-null_counter,"ジャンル",4);
			
				val.choiceCheck(null_counter,"ジャンル",genres.size());
			
			
			if(val.getErr().equals("") || val.getErr().equals(null)) {
				
				request.setAttribute("title",title);
				request.setAttribute("author",author);
				request.setAttribute("publi",publi);
				request.setAttribute("price",price);
				for(int i = 0; i < strJan.length; i++) {
					String ind = String.valueOf(i); 
					if(genres != null) {
						if(genres.get(i) != "null") {
							request.setAttribute("genre"+ind,"1");
						}else {
							request.setAttribute("genre"+ind,"0");
						}
					}
				}
					request.setAttribute("radios",radios);
					request.setAttribute("remarks",rem);
					getServletConfig().getServletContext().
					getRequestDispatcher("/output.jsp").forward(request,response);
				
			}else {
				request.setAttribute("err",val.getErr());
				request.setAttribute("title",title);
				request.setAttribute("author",author);
				request.setAttribute("publi",publi);
				request.setAttribute("price",price);
				
				for(int i = 0; i < strJan.length; i++) {
					String ind = String.valueOf(i); 
					if(genres != null) {
						if(genres.get(i) != "null") {
							request.setAttribute("genre"+ ind ,"1");
						}else {
							request.setAttribute("genre" + ind ,"0");
						}
					}
				}
				request.setAttribute("radios",radios);
				request.setAttribute("remarks",rem);
				getServletConfig().getServletContext().
				getRequestDispatcher("/input.jsp").forward(request,response);
			}
		}
}
