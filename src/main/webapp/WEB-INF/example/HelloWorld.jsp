<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="clinic.finance.beans.BankAccount" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
<!--

h2 {
	align : center;
	text-align : center;
}
.accountTable {
	align : center;
	text-align : left;
}
.title {
	color: blue;
	font-size: 22;
	align : center;
	text-align : center;
}
.values {
	align : center;
	text-align : center;
}
.sumTotal{
	color: red;
}
.accessibleTotal{
	color: green
}
-->
</style>
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
<table class="accountTable">
<tr><td colspan="4">Here are your latest account details:</td></tr>
<tr class="title"><td>Account Name</td><td>Account Type</td><td>Account Balance</td><td>Interest Rate (%)</td></tr>
<%
//System.out.println(bankAccounts.size());
for (int i=0; i< bankAccounts.size(); i++){
	BankAccount bankAccount = bankAccounts.get(i);
%>
	<tr class="values">
		<td><%=bankAccount.getAccountName()%></td>
		<td><%=bankAccount.getAccountTypeName()%></td>
		<td><%=bankAccount.getAccountBalance()%></td>
		<td><%=bankAccount.getAccountInterest()%></td>
	</tr>
<%	
	}
%>

<tr class="sumTotal">
	<td>Sum Total</td><td><%=sumTotal%></td><td></td><td></td>
</tr>
<tr class="accessibleTotal">
	<td>Accessible Total</td><td><%=accessibleTotal%></td><td></td><td></td>
</tr>

</table>
</body>
</html>
