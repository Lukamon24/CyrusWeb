package org.cyrus.webserver.request;

import org.cyrus.webserver.request.data.json.project.ProjectMinRequest;
import org.cyrus.webserver.request.data.json.project.ProjectsRequest;
import org.cyrus.webserver.request.data.login.LoginHtmlRequest;
import org.cyrus.webserver.request.data.login.LoginJavaScriptRequest;
import org.cyrus.webserver.request.data.login.LoginStyleSheetRequest;
import org.cyrus.webserver.request.data.login.create.CreateProjectHtmlRequest;
import org.cyrus.webserver.request.data.login.create.CreateProjectJavaScriptRequest;

import java.util.Set;

public interface WebRequests {

    LoginHtmlRequest LOGIN_HTML = new LoginHtmlRequest();
    LoginJavaScriptRequest LOGIN_JS = new LoginJavaScriptRequest();
    LoginStyleSheetRequest LOGIN_CSS = new LoginStyleSheetRequest();

    CreateProjectHtmlRequest CREATE_PROJECT_HTML = new CreateProjectHtmlRequest();
    CreateProjectJavaScriptRequest CREATE_PROJECT_JS = new CreateProjectJavaScriptRequest();

    ProjectsRequest DATA_PROJECTS = new ProjectsRequest();
    ProjectMinRequest DATA_PROJECT_MINIFIED = new ProjectMinRequest();


    static Set<? extends WebRequest> getRequests() {
        return Set.of(LOGIN_HTML, LOGIN_JS, LOGIN_CSS, CREATE_PROJECT_HTML, CREATE_PROJECT_JS, DATA_PROJECTS,
                DATA_PROJECT_MINIFIED);
    }
}
