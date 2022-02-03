function selectProject() {
  const options = document.getElementById("options");
  options.innerHTML =
    '<p class="basic-button">New</p><p class="basic-button">Import</p><p class="basic-button">Git</p>';
}

function selectHelp() {
  const options = document.getElementById("options");
  options.innerHTML = "<p>Version: Unknown</p>";
}

async function onUpdateProjects() {
  const request = await fetch("/data/projects/", {
    method: "POST",
  });
  const json = await request.json();
  const projects = json.projects;

  const projectList = document.getElementById("project-list");
  projectList.innerHTML = projects.map(
    (project) =>
      '<div class="w-100"><p id="' +
      project.id +
      '" class="basic-button" onclick="onProjectClick(' +
      project.id +
      ')">' +
      project.name +
      "</p></div>"
  );
}

async function onProjectClick(projectId) {
  const request = await fetch("/data/project/min/", {
    method: "POST",
    body: JSON.stringify({
      projectId: projectId + "",
    }),
  });
  const json = await request.json();

  const info = document.getElementById("info-list");
  info.innerHTML =
    '<div class="container"><div class="row"><div class="col-sm"><p>Name: </p></div><div class="col-sm">' +
    json.name +
    '</div></div><div class="row"><div class="col-sm"><p class="basic-button">Load</p></div><div class="col-sm"><p class="basic-button">Delete</p></div></div></div>';
}
