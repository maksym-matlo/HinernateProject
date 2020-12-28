<%@ page import="com.maximalus.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<html>
<head>
    <title>Hibernate project</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
    <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h1>User App</h1>
    </div>

    <div class="w3-container w3-padding">
        <%
            if (request.getAttribute("firstName")!= null) {
                out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">X</span>\n" +
                        "   <h5>User <b>" + request.getAttribute("firstName") + " " + request.getAttribute("lastName")  + "</b> has been added!</h5>\n" +
                        "</div>");
            }
        %>
        <div class="w3-card-4">
            <div class="w3-container w3-center w3-green">
                <h2>Add user</h2>
            </div>
            <form method="post" action="/insert" class="w3-selection w3-light-grey w3-padding">
                <label>First name:
                    <input type="text" name="firstName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" required>
                </label><br>
                <label>Last name:
                    <input type="text" name="lastName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" required>
                </label><br>
                <input type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom"/>
            </form>
        </div>
    </div>
    
    <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <div class="w3-card-4">
            <div class="w3-container w3-light-blue">
                <h2>Show all users</h2>
            </div>
            <ul>
                <%
                    List<User> names = (List<User>)request.getAttribute("users");
                    if(names!=null && !names.isEmpty()){
                        out.println("<table><tr><th>Id     </th><th>First name     </th><th>Last name     </th><th></th></tr>");
                        for(User u:names){
                            out.println(
                                    "<tr>" + "<td>" + u.getId() + "</td>" +
                                    "<td>" + u.getFirstName() + "</td>" +
                                    "<td>" + u.getLastName() + "</td>" +
                                    "<td>" + "<form method=\"post\" action=\"/delete\" style=\"display:inline\">"
                                    + "<input type=\"hidden\" name=\"id\" value=\""+ u.getId()+"\">"
                                    + "<input type=\"submit\" value=\"Delete\">"
                                    + "</form></td>"
                            );
                        }
                        out.println("</tr></table>");
                    }else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                            +
                            "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">X</span>\n" +
                            "   <h5>There are no users yet!</h5>\n" +
                            "</div>");
                %>
            </ul>
        </div>
    </div>
    
</body>

</html>
