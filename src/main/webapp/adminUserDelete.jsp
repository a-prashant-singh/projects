<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Global Banking ..</title>


</head>
<%@page import="com.bank.dao.UserDao"%>
<jsp:useBean id="u" class="com.bank.bean.User"></jsp:useBean>
<jsp:setProperty property="*" name="u" />
<%  
int i=UserDao.deleteUser(u);  
if(i>0){
	out.print("Updated Successfully!!!!");
RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
rd.include(request, response);
}
else
{
	out.print("Sorry,Update failed. please try later");
	RequestDispatcher rd = request.getRequestDispatcher("userDelete1.jsp");
	rd.include(request, response);	
}
	
	
%>
<body>



</body>

</html>