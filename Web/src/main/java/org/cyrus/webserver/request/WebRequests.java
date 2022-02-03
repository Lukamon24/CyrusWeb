package org.cyrus.webserver.request;

import org.cyrus.webserver.request.data.json.project.ProjectMinRequest;
import org.cyrus.webserver.request.data.json.project.ProjectsRequest;
import org.cyrus.webserver.request.data.login.LoginHtmlRequest;
import org.cyrus.webserver.request.data.login.LoginJavaScriptRequest;
import org.cyrus.webserver.request.data.login.LoginStyleSheetRequest;

import java.util.Set;

public interface WebRequests {

    LoginHtmlRequest LOGIN_HTML = new LoginHtmlRequest();
    LoginJavaScriptRequest LOGIN_JS = new LoginJavaScriptRequest();
    LoginStyleSheetRequest LOGIN_CSS = new LoginStyleSheetRequest();

    ProjectsRequest DATA_PROJECTS = new ProjectsRequest();
    ProjectMinRequest DATA_PROJECT_MINIFIED = new ProjectMinRequest();


    static Set<? extends WebRequest> getRequests() {
        return Set.of(LOGIN_HTML, LOGIN_JS, LOGIN_CSS, DATA_PROJECTS, DATA_PROJECT_MINIFIED);
    }
}
