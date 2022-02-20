function onFormat(text) {
  let nText = "";
  let buffer = "";
  let inTag = false;
  for (i = 0; i < text.length; i++) {
    const at = text.at(i);
    if (at === "<") {
      let colours = applyFormat(buffer);
      nText = nText + colours;
      buffer = "";
      inTag = true;
    }
    buffer = buffer + at;
    if (at === ">") {
      inTag = false;
      if (buffer !== "</span>" || buffer !== "<span>") {
        nText = nText + buffer;
        buffer = "";
      }
    }
  }
  buffer = applyFormat(buffer);
  nText = nText + buffer;
  return nText;
}

function applyFormat(text) {
  let nText = text;
  nText = colourPrimitive(nText, "int");
  return nText;
}

function colourPrimitive(text, primitiveName) {
  let ret = text.replaceAll(
    " " + primitiveName + " ",
    ' <span class="primitive">' + primitiveName + "</span> "
  );
  console.log("ret: " + ret);
  if (ret.startsWith(primitiveName + " ")) {
    ret =
      '<span class="primitive">' +
      primitiveName +
      "</span> " +
      ret.substring(primitiveName.length + 1);
  }
  return ret;
}

function onClick(event) {
  const range = window.getSelection().getRangeAt(0);
}

function onIncome(line) {
  const pre = document.getElementById("code-section-pre");
  const nLine = (pre.innerHTML ? pre.innerHTML : "") + "<p>" + line + "</p>";
  const html = onFormat(nLine);
  pre.innerHTML = html;
}

function onInput(event) {
  if (event.key === "Enter" || event.keyCode === 13) {
    const input = document.getElementById("code-input");
    onIncome(input.value);
    input.value = "";
  }
}

async function onInputTyped(event) {
  const input = document.getElementById("code-input");
  const value = input.value;
  if (
    event.key === "." ||
    event.key === "(" ||
    event.key === " " ||
    value.length == 0
  ) {
    const json = await onPost(
      "action/suggest",
      JSON.stringify({
        value,
      })
    );
    const suggestions = json.suggestions.map((suggestion) => {
      let value = suggestion.value;
      if (suggestion.type === "method") {
        value = '<span class="method-suggestion">' + value + "()</span>";
      } else if (suggestion.type === "constructor") {
        value =
          '<span class="constructor-suggestion">new ' + value + "()</span>";
      } else if (suggestion.type === "field") {
        value = '<span class="field-suggestion">' + value + "</span>";
      } else {
        value = '<span class="unknown-suggestion">' + value + "</span>";
      }

      return "<p>" + value + "</p>";
    });
    document.getElementById("code-suggestion").innerHTML = suggestions;
  }
}

async function onPost(endpoint, body) {
  const fet = await fetch(endpoint, {
    body,
  });
  return await fet.json();
}

function onInputFocusLoss() {
  const input = document.getElementById("code-input");
  const text = input.value;
  onIncome(text);
  input.value = "";
}

function updatePostSection() {
  const pre = document.getElementById("code-section-pre");
  const suggestion = document.getElementById("code-suggestion");

  const input = document.getElementById("code-input");
  const post = document.getElementById("code-section-post");
  var body = document.body,
    html = document.documentElement;

  var windowHeight = Math.max(
    body.scrollHeight,
    body.offsetHeight,
    html.clientHeight,
    html.scrollHeight,
    html.offsetHeight
  );
  post.style.height =
    windowHeight -
    pre.clientHeight -
    suggestion.clientHeight -
    input.clientHeight -
    5;
}

function init() {
  const input = document.getElementById("code-input");
  input.onkeyup = onInput;
  input.onkeypress = onInputTyped;
  updatePostSection();
  window.onresize = updatePostSection;
  //document.getElementById("code-section").onkeypress = onCodeInput;
}
