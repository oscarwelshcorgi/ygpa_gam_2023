<%!
public static void proxy_get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String urlStr =  URLDecoder.decode(request.getParameter("url"), "UTF-8");
	String params = "";
	if(request.getParameter("params") != null) {
		params = URLDecoder.decode(request.getParameter("params"), "UTF-8");

	}
	URL url = new URL(urlStr+params);
	URLConnection connection = url.openConnection();
	HttpURLConnection huc = (HttpURLConnection)connection;
	huc.setRequestMethod("GET");
	huc.setDoOutput(true);
	huc.setDoInput(true);
	huc.setUseCaches(false);
	huc.setDefaultUseCaches(false);

	response.reset();
	response.setContentType(huc.getContentType());

	OutputStream ios = response.getOutputStream();

	IOUtils.copy(huc.getInputStream(), ios);

	ios.close();
}

public static void proxy_post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String urlStr =  URLDecoder.decode(request.getParameter("url"), "UTF-8");
	BufferedReader rd = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
   	StringBuffer params = new StringBuffer();
    String line;

    while ((line = rd.readLine()) != null) {
    	params.append(line);
    }

    URL url = new URL(urlStr);
	URLConnection connection = url.openConnection();
	HttpURLConnection huc = (HttpURLConnection)connection;
	huc.setRequestMethod("POST");
	huc.setDoOutput(true);
	huc.setDoInput(true);
	huc.setUseCaches(false);
	huc.setDefaultUseCaches(false);

	PrintWriter pOut = new PrintWriter(huc.getOutputStream());

	pOut.println(params.toString());
	pOut.close();

	response.reset();
	response.setContentType(huc.getContentType());

	OutputStream ios = response.getOutputStream();

	IOUtils.copy(huc.getInputStream(), ios);
	ios.close();

}
%>
<%
	log("getOutputStream!!!!!");
	try {
		if(request.getMethod().toString().equals("GET"))
			proxy_get(request, response);
		else
			proxy_post(request, response);
	}
	catch (Exception e) {
		log("getOutputStream!!!!!");
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setContentType("text/plain");
		%>
		<%=e.getStackTrace()[0].getMethodName() + ":" + e.getStackTrace()[0].getLineNumber()%><%
	}
	if (true) {
		return;
	}
%>
<%@ page language="java"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="org.apache.commons.io.*"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.PrintWriter"%>
