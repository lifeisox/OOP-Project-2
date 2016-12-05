package symphony;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.CreateException;
import sql.DAOSysException;
import sql.FinderException;

@SuppressWarnings("serial")
public class SymphonyComposition extends HttpServlet {

	/** 
	 * Implement the command from Html pages
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException ServletException
	 * @throws IOException IOException
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI(); // get URI
		// extract the command from URI
		String command = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".do"));
		if (command != null && !command.isEmpty()) {
			if (command.trim().equals("composer")) {
				composer(request, response);
			} else if (command.trim().equals("composition")) {
				composition(request, response);
			} else if (command.trim().equals("movements")) {
				movements(request, response);
			} else {	// send fail html page
				fail(response, "Can not load the page!");
			}
		}
	}

	/**
	 * Implement composer process
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws IOException IOException
	 */
	private void composer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Collection<Composer> composerList = null;
		int topRow = getPageTop(request); // top of lists
		int totalRow = getTotalRow(request, response, Composer.class, null, null); // get the number of rows in composer table
		int totalPage = (totalRow - 1) / PAGE_SIZE;
		int currentPage = topRow / PAGE_SIZE;

		try {
			composerList = Composer.findQuery("SELECT DISTINCT composer FROM composition ORDER BY composer ASC LIMIT " +
					topRow + ", " + PAGE_SIZE + "; ");
		} catch (FinderException fe) {
			fail(response, fe.getLocalizedMessage());
		} catch (CreateException ce) {
			fail(response, ce.getLocalizedMessage());
		}

		/*	Set the content type for this response
		 *	Create the response HTML header and the output stream
		 */
		response.setContentType( "text/html" );
		PrintWriter outStream = new PrintWriter( response.getOutputStream() );

		/*	Create the standard HTML header				*/
		StringBuffer buf = createHTMLHeader( "Composer List", "Composer List" );
		buf.append( "<form method='POST'>" );

		/*	Body for this HTML response page goes here			*/
		for (Composer composer : composerList)	{
			buf.append( "&nbsp;&nbsp; ");
			buf.append( "<a href=\"./composition.do?composer=" + composer.getComposer() + "&top=0 \">" );
			buf.append( composer.getComposer() );
			buf.append( "</a>" );
			buf.append( "<br/>" );
		}

		buf.append( "<br/><br/>" );

		/* navigation button display */
		String tag;
		if ( currentPage > 0 ) {
			int prevList = (currentPage - 1) * PAGE_SIZE;
			tag = "&nbsp;&nbsp;<button type='submit' formaction='composer.do?top=" + prevList + "' " 
					+ " formtarget='_self' >Prev Page</button>&nbsp;&nbsp;&nbsp;&nbsp;";
		} else {
			tag = "&nbsp;&nbsp;<button type='submit' disabled >Prev Page</button>&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		buf.append( tag );

		if ( currentPage < totalPage ) {
			int nextList = (currentPage + 1) * PAGE_SIZE;
			tag = "<button type='submit' formaction='composer.do?top=" + nextList + "' " 
					+ " formtarget='_self' >Next Page</button>";
		} else {
			tag = "<button type='submit' disabled >Next Page</button>";
		}
		buf.append( tag );

		buf.append( "</form>" );
		buf.append( "Click very first data if you want full test.");
		buf.append( createHTMLFooter());

		/*	Wrap up for this servlet. Send our string buffer down the stream,
		 *	Flush and close it.
		 */
		outStream.println( buf.toString() );
		outStream.flush();
		outStream.close();
	}

	/**
	 * Implement composition process
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws IOException IOException
	 */
	private void composition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Collection<Composition> compositionList = null;
		String composer = request.getParameter("composer");
		int topRow = getPageTop(request); // top of lists
		int totalRow = getTotalRow(request, response, Composition.class, composer, null); // get the number of rows in composer table
		int totalPage = (totalRow - 1) / PAGE_SIZE;
		int currentPage = topRow / PAGE_SIZE;

		try {
			compositionList = Composition.findQuery("SELECT composer, compositionName FROM composition WHERE composer = '"
					+ composer + "' ORDER BY compositionName ASC LIMIT " + topRow + ", " + PAGE_SIZE + "; ");
		} catch (FinderException fe) {
			fail(response, fe.getLocalizedMessage());
		} catch (CreateException ce) {
			fail(response, ce.getLocalizedMessage());
		}
		
		/*	Set the content type for this response
		 *	Create the response HTML header and the output stream
		 */
		response.setContentType( "text/html" );
		PrintWriter outStream = new PrintWriter( response.getOutputStream() );

		/*	Create the standard HTML header				*/
		StringBuffer buf = createHTMLHeader( "Composition List", "Composition List" );
		buf.append( "<p>&nbsp;&nbsp;Composer Name: " + composer + "</p>" );
		
		if ( totalRow <= 0 ) {
			buf.append( "&nbsp;&nbsp;<h1>No data!!!</h1>" );
		} else {
			buf.append( "<form method='POST'>" );

			/*	Body for this HTML response page goes here			*/
			for (Composition composition : compositionList)	{
				buf.append( "&nbsp;&nbsp;" );
				buf.append( "<a href=\"./" + response.encodeURL("movements.do?composer=" + composition.getComposer() 
					+ "&compositionName=" + composition.getCompositionName()) + "&top=0\">" );
				buf.append( composition.getCompositionName() );
				buf.append( "</a>" );
				buf.append( "<br/>" );
			}

			buf.append( "<br/><br/>" );

			/* navigation button display */
			String tag;
			if ( currentPage > 0 ) {
				int prevList = (currentPage - 1) * PAGE_SIZE;
				tag = "&nbsp;&nbsp;<button type='submit' formaction='composition.do?composer=" + composer + "&top=" + prevList + "' " 
						+ " formtarget='_self' >Prev Page</button>&nbsp;&nbsp;&nbsp;&nbsp;";
			} else {
				tag = "&nbsp;&nbsp;<button type='submit' disabled >Prev Page</button>&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			buf.append( tag );

			if ( currentPage < totalPage ) {
				int nextList = (currentPage + 1) * PAGE_SIZE;
				tag = "<button type='submit' formaction='composition.do?composer=" + composer + "&top=" + nextList + "' " 
						+ " formtarget='_self' >Next Page</button>";
			} else {
				tag = "<button type='submit' disabled >Next Page</button>";
			}
			buf.append( tag );
			buf.append( "</form>" );
			buf.append( "Click very first data if you want full test.");
		}
		buf.append( createHTMLFooter());

		/*	Wrap up for this servlet. Send our string buffer down the stream,
		 *	Flush and close it.
		 */
		outStream.println( buf.toString() );
		outStream.flush();
		outStream.close();
	}

	/**
	 * Implement movements process
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws IOException IOException
	 */
	private void movements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Collection<Movements> movementsList = null;
		String composer = request.getParameter("composer");
		String compositionName = request.getParameter("compositionName");
		int topRow = getPageTop(request); // top of lists
		int totalRow = getTotalRow(request, response, Movements.class, composer, compositionName); // get the number of rows in composer table
		int totalPage = (totalRow - 1) / PAGE_SIZE;
		int currentPage = topRow / PAGE_SIZE;

		try {
			movementsList = Movements.findQuery(
					"SELECT composer, compositionName, movementNumber, movementName FROM movements WHERE composer = '"
							+ composer + "' AND compositionName = '" + compositionName 
							+ "' LIMIT " + topRow + ", " + PAGE_SIZE + "; ");
		} catch (FinderException fe) {
			fail(response, fe.getLocalizedMessage());
		} catch (CreateException ce) {
			fail(response, ce.getLocalizedMessage());
		}

		/*	Set the content type for this response
		 *	Create the response HTML header and the output stream
		 */
		response.setContentType( "text/html" );
		PrintWriter outStream = new PrintWriter( response.getOutputStream() );

		/*	Create the standard HTML header				*/
		StringBuffer buf = createHTMLHeader( "Movement List", "Movement List" );
		buf.append( "<p>&nbsp;&nbsp;Composer Name: " + composer + "<br/>" );
		buf.append( "&nbsp;&nbsp;Composition Name: " + compositionName + "</p>" );
		if ( totalRow <= 0 ) {
			buf.append( "&nbsp;&nbsp;<h1>No data!!!</h1>" );
		} else {
			buf.append( "<form method='POST'>" );
			buf.append( "<table border='1'>" );
			buf.append( "	<thead>" );
			buf.append( "		<tr>" );
			buf.append( "			<th>Movement No.</th>" );
			buf.append( "			<th>Movement Name</th>" );
			buf.append( "		</tr>" );
			buf.append( "	</thead>" );
			buf.append( "	<tfoot>" );
			buf.append( "		<tr>" );

			/*	Body for this HTML response page goes here			*/
			for (Movements movement : movementsList)	{
				buf.append("<td>");
				buf.append( movement.getMovementNumber() );
				buf.append("</td>");
				buf.append("<td>");
				buf.append( movement.getMovementName() );
				buf.append("</td>");
				buf.append("</tr>");
			}
			buf.append( "	</tfoot>" );
			buf.append( "</table>" );
			buf.append( "<br/><br/>" );

			/* navigation button display */
			String tag;
			if ( currentPage > 0 ) {
				int prevList = (currentPage - 1) * PAGE_SIZE;
				tag = "&nbsp;&nbsp;<button type='submit' formaction='movements.do?composer=" + composer 
						+ "&compositionName=" + compositionName + "&top=" + prevList + "' " 
						+ " formtarget='_self' >Prev Page</button>&nbsp;&nbsp;&nbsp;&nbsp;";
			} else {
				tag = "&nbsp;&nbsp;<button type='submit' disabled >Prev Page</button>&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			buf.append( tag );

			if ( currentPage < totalPage ) {
				int nextList = (currentPage + 1) * PAGE_SIZE;
				tag = "<button type='submit' formaction='movements.do?composer=" + composer + "&compositionName="
						+ compositionName + "&top=" + nextList + "' " 
						+ " formtarget='_self' >Next Page</button>";
			} else {
				tag = "<button type='submit' disabled >Next Page</button>";
			}
			buf.append( tag );

			buf.append( "</form>" );
		}
		buf.append( createHTMLFooter());

		/*	Wrap up for this servlet. Send our string buffer down the stream,
		 *	Flush and close it.
		 */
		outStream.println( buf.toString() );
		outStream.flush();
		outStream.close();
	}

	private void fail(HttpServletResponse response, String message) throws IOException {
		/*	Send a response back to the client --------------------------	*/

		/*	Set the content type for this response
		 *	Create the response HTML header and the output stream
		 */
		response.setContentType( "text/html" );
		PrintWriter outStream = new PrintWriter( response.getOutputStream() );

		/*	Create the standard HTML header				*/
		StringBuffer buf = createHTMLHeader( "Fail...", "Fail..." );

		/*	Body for this HTML response page goes here			*/

		buf.append( message );
		buf.append( createHTMLFooter());

		/*	Wrap up for this servlet. Send our string buffer down the stream,
		 *	Flush and close it.
		 */
		outStream.println( buf.toString() );
		outStream.flush();
		outStream.close();
	}

	private int getPageTop(HttpServletRequest request) {
		int top;

		String str = request.getParameter("top");
		if (str == null || str.isEmpty()) top = 0;
		else top = Integer.parseInt(str);

		return top;
	}

	private int getTotalRow( HttpServletRequest request, HttpServletResponse response, Class<?> cls,
			String composer, String compositionName ) throws IOException {
		int row=0;
		try {
			if ( cls == Composer.class ) row = Composer.size();
			else if ( cls == Composition.class ) row = Composition.size( composer );
			else if ( cls == Movements.class ) row = Movements.size( composer, compositionName );
		} catch (DAOSysException de) {
			fail(response, de.getLocalizedMessage());
		}
		return row;
	}
	/**
	 *	<p>Create a standard HTML header.
	 *	@param winTitle Browser Title (Window Title Bar)
	 *	@param hd1Title Second level header for HTML page.
	 *	@return Buffer object with formatted HTML header
	 */
	private StringBuffer createHTMLHeader( String winTitle, String hd1Title )	{
		StringBuffer buf = new StringBuffer();
		buf.append( "<html>" );
		buf.append( "	<head>" );
		buf.append( "		<title>" + winTitle + "</title>" );
		buf.append( "	</head> ");
		buf.append( "	<body> ");
		buf.append( "		<h3>&nbsp;" + hd1Title + "</h3><br/>" );

		return  buf;
	}

	private StringBuffer createHTMLFooter()	{
		StringBuffer buf = new StringBuffer();
		buf.append( "		<br/>");
		buf.append( "	</body> ");
		buf.append( "</html>" );
		return  buf;
	}

	private static final int PAGE_SIZE = 10; // the number of lists to show a screen
}
