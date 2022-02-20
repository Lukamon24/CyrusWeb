function onLoad() {}

async function onProjectSubmit() {
  const name = document.getElementById("name").value;
  const groupId = document.getElementById("group").value;
  const artifactId = document.getElementById("artifact").value;
  const version = document.getElementById("version").value;
  const buildTool = document.querySelector('input[name="build-type"]:checked').value;
  const project = {
    name,
    groupId,
    artifactId,
    version,
    buildTool
  }

  const result = await doPost("/project/create/", JSON.stringify(project));


if(!result){
return false;
}

  document.getElementById("cancel").click();

  return false;
}

function validateNameInput() {
  const name = document.getElementById("name");
  validateInput(name);
  const input = name.value;
  document.getElementById("group").placeholder =
    "org." + input.toLowerCase().trim().replaceAll(" ", ".");

  let stringBuffer = "";
  let wasSpace = true;
  for (let a = 0; a < input.length; a++) {
    const charAt = input.charAt(a);
    if (charAt === " ") {
      wasSpace = true;
      continue;
    }
    if (wasSpace) {
      stringBuffer = stringBuffer + charAt.toUpperCase();
      wasSpace = false;
    } else {
      stringBuffer = stringBuffer + charAt.toLowerCase();
    }
  }

  document.getElementById("artifact").placeholder = stringBuffer;
}

function validateGroupInput() {
  validateInput(document.getElementById("group"));
}

function validateArtifactInput() {
  validateInput(document.getElementById("artifact"));
}

function validateInput(input) {
  const pattern = input.pattern;
  const userInput = input.value;
  input.style.borderColor = "outset";
  if (userInput !== "" && userInput.match(pattern)) {
    input.style.borderColor = "green";
    return;
  }
  input.style.borderColor = "red";
}

async function doPost(endpoint, body){
return await fetch(endpoint, {
  body,
  method: "POST"
}).then(result => result.json());
}
