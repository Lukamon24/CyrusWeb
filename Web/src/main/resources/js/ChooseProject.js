function selectProject() {
  document.getElementById("project-tab").classList.add("active");
  document.getElementById("help-tab").classList.remove("active");

  const options = document.getElementById("options");
  options.innerHTML =
    '<a class="basic-button" href="./create/project">New</a><p class="basic-button">Import</p><p class="basic-button">Git</p>';
}

function selectHelp() {
  document.getElementById("project-tab").classList.remove("active");
  document.getElementById("help-tab").classList.add("active");
  

  const options = document.getElementById("options");
  options.innerHTML = "<p>Version: Unknown</p>";
}

async function onUpdateProjects() {
  //const json = await doPost("/data/projects/");

  const json = {
    projects: [
  {
    id: "test1",
    name: "test"
  },
  {
    id: "test2",
    name: "test2"
  }
]}

  const projects = json.projects;

  const projectList = document.getElementById("project-list");
  projectList.innerHTML = projects.map(
    (project) =>
      '<li id="project-' + project.id + '" class="list-group-item project-element" onclick="onProjectClick(\'' +
      project.id +
      '\')">' +
      project.name +
      "</li>"
  ).join("");
}

async function onProjectClick(projectId) {
  //const json = await doPost("/data/project/min/", JSON.stringify({projectId: projectId + ""}));

  const projects = {
    projects: [
  {
    id: "test1",
    name: "test",
    version: "1.0.0",
    groupId: "org.test",
    artifactId: "Test",
    buildTool: "Simple"
  },
  {
    id: "test2",
    name: "test2",
    version: "1.0.0-SNAPSHOT",
    groupId: "com.test",
    artifactId: "Two",
    buildTool: "Maven"
  }
]}

const json = projects.projects.find(project => project.id === projectId);


  const elements = document.getElementsByClassName("project-element");
  for(i = 0; i < elements.length; i++){
    elements[i].classList.remove("active")
  }

  console.log("project-" + projectId);

  document.getElementById("project-" + projectId).classList.add("active");

  const info = document.getElementById("info-list");
  info.innerHTML = '<h5 class="card-header text-center">' 
    + json.name 
    + ' info</h5>' 
    + '<div class="card-body">' 
    + '<table class="table table-sm">'
    + '<thead></thead>'
    + '<tbody>' 
    + '<tr class="table-active">' 
    + '<th scope="row">Name</th>' 
    + '<td><input type="text" value="' + json.name + '" class="form-control" /></td>' 
    + '</tr>'
    + '<tr>'
    + '<th scope="row">Version</th>'
    + '<td><input type="text" value="' + json.version + '" class="form-control" /></td>'
    + '</tr>' 
    + '<tr>'
    + '<th scope="row">Group</th>'
    + '<td><p>' + json.groupId + '"</p></td>'
    + '</tr>' 
    + '<tr>'
    + '<th scope="row">Artifact</th>'
    + '<td><p>' + json.artifactId + '</p></td>'
    + '</tr>' 
    + '<tr>'
    + '<th scope="row">BuildTool</th>'
    + '<td><p>' + json.buildTool + '</p></td>'
    + '</tr>' 
    + '</tbody></table>'
    + '</div>' 
    + '<div class="card-footer">' 
    + '<a href="./editor?projectId='+ json.id + '" class="btn btn-primary">Load</a>' 
    + '<a style="float: right" class="btn btn-danger">Delete</a></div>';
}

async function doPost(endpoint, body){
return await fetch(endpoint, {
  body,
  method: "POST"
}).then(result => result.json());
}
