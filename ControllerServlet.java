package com.javatpoint;
import java.io.File;
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;  
import java.util.*;





public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final HashMap<String,Integer>charToIntegerMap = new HashMap<String,Integer>()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
		put('A'+"",10);
		put('B'+"",11);
		put('C'+"",12);
		put('D'+"",13);
		put('E'+"",14);
		put('F'+"",15);
		}
		
		
	};
	
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		 String encodedValue=request.getParameter("encodedvalue");
		 String [] stream = encodedValue.split(" ");
		 String res = "";
		 int sum=0,flag=0;
		 
		 HttpSession session=request.getSession();
		
		 RequestDispatcher rd = null;
		 
		if("".equals(encodedValue) || !(encodedValue.contains("x"))){
			rd = request.getRequestDispatcher("decodeError.jsp");
			rd.forward(request, response);
			return;
		}
		
		 for(String s : stream){
			 String str = s.substring(2,4);
			 System.out.println("str is"+str);
			 String rev = new StringBuilder(str).reverse().toString();
			 sum =0;
			 for(int i=0;i<rev.length();i++){
				 char c = rev.charAt(i);
				 if(c>='0' && c<='9'){
					 sum+=(Integer.parseInt(c+""))*(int)(Math.pow(new Integer(16).doubleValue(), new Integer(i).doubleValue()));
					
				 }
				 else{
				 if((c>='A' && c<='Z')){
					 sum+=charToIntegerMap.get(c+"")*(int)(Math.pow(new Integer(16).doubleValue(), new Integer(i).doubleValue()));
				 }
				 else{
					 flag=1;
					 break;
					
				 }
				 }
				 
			 }
			 if(flag==1) break;
			 res+=(char)sum + " ";
			
		 }
		
		
			
	          
	         
		
		 
		System.out.println("char "+charToIntegerMap);
		if(flag==1){
			rd = request.getRequestDispatcher("decodeError.jsp");
			rd.forward(request, response);
		}
		else{
			 byte[] bytes = DatatypeConverter.parseBase64Binary(res);
	        System.out.println("byte is "+new String(bytes));
		    session.setAttribute("decode", new String(bytes));
		    rd=request.getRequestDispatcher("decodeSuccess.jsp");
		    rd.forward(request, response);
		}
		
		
		
		
	}
	

}
