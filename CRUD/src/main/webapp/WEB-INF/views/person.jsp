<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>

<head>
	<title>Person Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>


<body>


<h3>
	Add a Person
</h3>

<c:url var="addAction" value="/person/add" ></c:url>
<c:url var="filterAction" value="/person/filter" ></c:url>


<form:form action="${addAction}" commandName="person">
<table>
	<c:if test="${!empty person.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" required = "true"/>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="age">
				<spring:message text="Age"/>
			</form:label>
		</td>
		<td>
			<form:input type = "number" path="age" pattern ="[0-9]{1,3}" />
		</td>
	</tr>
	<tr>
    		<td>
    			<form:label path="isAdmin">
    				<spring:message text="IsAdmin"/>
    			</form:label>
    		</td>
    		<td>
    			<form:input path="isAdmin" type = "number" min="0" max="1" pattern = "[0-1]{1}" value="0" />
    		</td>
    	</tr>
	<tr>

    	<tr>
            	<tr>
		<td colspan="2">
			<c:if test="${!empty person.name}">
				<input type="submit"
					value="<spring:message text="Edit Person"/>" />
			</c:if>
			<c:if test="${empty person.name}">
				<input type="submit"
					value="<spring:message text="Add Person"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>

<form:form action="${filterAction}" method = "POST">
<h3>List of persons</h3>
Person Name: <input type="text" name = "filterName" style="width: 120px;" value = ${filteredValueName} ></>

<input type='submit' value="Filter" />
<br>

	<table class="tg">

	<tr>
		<th width="80">Person ID</th>
		<th width="120">Person name</th>
		<th width="80">Person age</th>
		<th width="60">Person admin status</th>
        <th width="100">Record creation date</th>
		<th width="60" colspan="2"></th>

	</tr>
	<c:forEach items="${listPersons}" var="person">
		<tr>
			<td>${person.id}</td>
			<td>${person.name}</td>
			<td>${person.age}</td>
			<td>${person.isAdmin}</td>
			<td>${person.dateCreated}</td>
			<td><a href="<c:url value='/edit/${person.id}'>
                                                       <c:param name="pageNum" value="${pageNumber}"/>
                                                     </c:url>" >Edit</a></td>
			<td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>

		</tr>
	</c:forEach>
	</table>
</form:form>

<c:set var="pageNext" scope="page" value="${pageNumber+1}"/>

<c:if test="${1<pageNumber}">
<c:set var="pagePrev" scope="page" value="${pageNumber-1}"/>
</c:if>


<c:url value='/persons' var = "pageURLprev">
                  <c:param name="pageNum" value="${pagePrev}"/>
                  <c:param name="nameFiltered" value="${filteredValueName}"/>
                </c:url>
<c:url value='/persons' var = "pageURLnext">
                  <c:param name="pageNum" value="${pageNext}"/>
                  <c:param name="nameFiltered" value="${filteredValueName}"/>
                </c:url>

Current page <input = "text" disabled="true" readonly="true" value = ${pageNumber} />
<a href="${pageURLprev}">prev</a>
<a href="${pageURLnext}">next</a>

</body>
</html>
