
<%=session.getMaxInactiveInterval()%>


<%
Long counter = (Long) session.getAttribute("counter");
if (counter == null) {
	counter = 1L;
	session.setAttribute("counter", counter);
} else {
	counter = counter + 1;
	session.setAttribute("counter", counter);
}
%>
<%=counter%>