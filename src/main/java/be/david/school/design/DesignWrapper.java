package be.david.school.design;

import be.david.school.beans.Authentication;

/**
 * Created by David on 21/08/2016.
 */
public class DesignWrapper {

    private String content = "";
    private String titel = "Kinepolis :: ";
    private String error_message = "<br><br><p>There where some errors while constructing this page!<br>An Administrator will try to fix these shortly.<br>We Apologise for the inconvenience</p>";
    private boolean access = true;
    private String user_name = "Guest, please <a href=\"index.jsp?module=login&amp;function=login\">login</a> or <a href=\"index.jsp?module=login&amp;function=register\">register</a>";
    private Authentication authentication;

    public DesignWrapper(Authentication auth) {
        this.authentication = auth;
    }

    private String generateMenu() {
        String menu = "";
        menu = menu + "<div id=\"menu\"><a href=\"index.jsp\">Home</a> | <a href=\"index.jsp?module=pages&amp;function=about\">About</a> | <a href=\"index.jsp?module=programming&amp;function=list\">Movie Schedule</a>";
        if(this.authentication.isAdmin()) {
            menu = menu + " | <a href=\"index.jsp?module=administration\">Administration</a>";
        }

        menu = menu + "</div>";
        return menu;
    }

    public String getContent(String inContent, boolean Errors) {
        this.content = this.content + "<html>\n<head>\n<title>" + this.titel + "</title>" + "\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" + "\n<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" + "\n<script language=\"JavaScript\" type=\"text/JavaScript\">" + "\n<!--" + "\nfunction MM_jumpMenu(targ,selObj,restore)\n{ //v3.0" + "\neval(targ+\".location=\'\"+selObj.options[selObj.selectedIndex].value+\"\'\");" + "\nif (restore) selObj.selectedIndex=0;" + "\n}" + "\n//-->" + "\n</script>" + "\n</head>" + "\n<body>" + "\n<div id=\"header\"></div>" + "\n" + this.generateMenu() + "\n<div id=\"content\">\n\n" + "Welcome " + this.user_name + "<br><br>";
        if(this.access) {
            this.content = this.content + inContent;
        } else {
            this.content = this.content + "You do not have acces to this page!";
        }

        if(Errors) {
            this.content = this.content + this.error_message;
        }

        this.content = this.content + "\n\n</div>\n</body>\n</html>";
        return this.content;
    }

    public void setErrorMessage(String error_message) {
        this.error_message = this.error_message + error_message;
    }

    public void setTitel(String titel) {
        this.titel = this.titel + titel;
    }

    public void setUserName(String user_name) {
        if(!user_name.equals("")) {
            this.user_name = user_name + ", <a href=\"index.jsp?module=login&amp;function=logout\">logout</a>";
        }

    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
