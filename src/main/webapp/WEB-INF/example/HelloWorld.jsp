<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="clinic.finance.beans.BankAccount" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="style.css">
<html>
<head>
    <title><s:text name="HelloWorld.message"/></title>
</head>
<%
	//Check session attributes
	//for (Enumeration attName = session.getAttributeNames(); attName.hasMoreElements();)
	//System.out.println(attName.nextElement());
	List<BankAccount> bankAccounts = (ArrayList)session.getAttribute("bankAccounts");
	BigDecimal sumTotal = (BigDecimal)session.getAttribute("sumTotal");
	BigDecimal accessibleTotal = (BigDecimal)session.getAttribute("accessibleTotal");
%>

<body>
<h2><tr>Good Afternoon Dom</tr></h2>
<table border="2">
<tr><td colspan="4">Here are your latest account details:</td></tr>
<tr class="title"><td>Account Name</td><td>Account Balance</td><td>Current Interest (%)</td><td>Account Type</td></tr>
<%
for (int i=0; i< bankAccounts.size(); i++){
	BankAccount bankAccount = bankAccounts.get(i);
%>
	<tr>
		<td><%=bankAccount.getAccountName()%></td>
		<td><%=bankAccount.getAccountBalance()%></td>
		<td><%=bankAccount.getAccountInterest()%></td>
		<td><%=bankAccount.getAccountType()%></td>
	</tr>
<%	
	}
%>
<tr>
	<td>Sum Total</td><td><%=sumTotal%></td><td></td><td></td>
</tr>
<tr>
	<td>Accessible Total</td><td><%=accessibleTotal%>	</td><td></td><td></td>
</tr>	
</table>
</body>
</html>
