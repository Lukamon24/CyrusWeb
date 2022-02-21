package org.cyrus.webserver.request;

import org.cyrus.webserver.request.data.action.create.project.CreateProjectRequest;
import org.cyrus.webserver.request.data.action.suggestion.SuggestionRequest;
import org.cyrus.webserver.request.data.get.chooser.LoginHtmlRequest;
import org.cyrus.webserver.request.data.get.chooser.LoginJavaScriptRequest;
import org.cyrus.webserver.request.data.get.chooser.LoginStyleSheetRequest;
import org.cyrus.webserver.request.data.get.chooser.create.CreateProjectHtmlRequest;
import org.cyrus.webserver.request.data.get.chooser.create.CreateProjectJavaScriptRequest;
import org.cyrus.webserver.request.data.get.editor.EditorCssRequest;
import org.cyrus.webserver.request.data.get.editor.EditorHtmlRequest;
import org.cyrus.webserver.request.data.get.editor.EditorJavaScriptRequest;
import org.cyrus.webserver.request.data.json.project.ProjectMinRequest;
import org.cyrus.webserver.request.data.json.project.ProjectsRequest;

import java.util.Set;

public interface WebRequests {

    LoginHtmlRequest LOGIN_HTML = new LoginHtmlRequest();
    LoginJavaScriptRequest LOGIN_JS = new LoginJavaScriptRequest();
    LoginStyleSheetRequest LOGIN_CSS = new LoginStyleSheetRequest();

    CreateProjectHtmlRequest CREATE_PROJECT_HTML = new CreateProjectHtmlRequest();
    CreateProjectJavaScriptRequest CREATE_PROJECT_JS = new CreateProjectJavaScriptRequest();

    EditorHtmlRequest EDITOR_HTML = new EditorHtmlRequest();
    EditorCssRequest EDITOR_CSS = new EditorCssRequest();
    EditorJavaScriptRequest EDITOR_JS = new EditorJavaScriptRequest();

    ProjectsRequest DATA_PROJECTS = new ProjectsRequest();
    ProjectMinRequest DATA_PROJECT_MINIFIED = new ProjectMinRequest();

    SuggestionRequest ACTION_SUGGESTION = new SuggestionRequest();

    CreateProjectRequest CREATE_PROJECT = new CreateProjectRequest();


    static Set<? extends WebRequest> getRequests() {
        return Set.of(LOGIN_HTML, LOGIN_JS, LOGIN_CSS, CREATE_PROJECT_HTML, CREATE_PROJECT_JS, EDITOR_HTML,
                EDITOR_CSS, EDITOR_JS,
                DATA_PROJECTS,
                DATA_PROJECT_MINIFIED, ACTION_SUGGESTION, CREATE_PROJECT);
    }
}
