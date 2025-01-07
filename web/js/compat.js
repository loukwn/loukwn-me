function isWasmGcSupported() {
  const bytes = [0, 97, 115, 109, 1, 0, 0, 0, 1, 5, 1, 95, 1, 120, 0];
  return WebAssembly.validate(new Uint8Array(bytes));
}

function handleWasmGcNotSupported() {
  const container = createUnderConstructionContainer();

  var icon = document.createElement("h1");
  icon.textContent = "🚧";
  container.appendChild(icon);

  var title = document.createElement("h2");
  title.textContent = "Under construction";
  title.style.color = "white";
  title.style.fontFamily = "Oswald";
  container.appendChild(title);

  var description = document.createElement("h3");
  description.textContent =
    "Your browser is not supported at the moment. In the meantime please check out my other links!";
  description.style.color = "#b5b2b2";
  description.style.paddingInline = "20px";
  description.style.paddingBlockEnd = "20px";
  description.style.fontFamily = "Oswald";
  container.appendChild(description);

  let links = document.getElementById("row-social-main");
  let clonedLinks = links.cloneNode(true);
  clonedLinks.id = "row-social-secondary";
  container.appendChild(clonedLinks);

  clearPhoneContainer();
  const phoneContainer = document.getElementById("phone-container");
  phoneContainer.appendChild(container);
  handleResize();
}

function createUnderConstructionContainer() {
  var div = document.createElement("div");
  div.id = "app-content";
  div.style.background = "black";
  div.style.overflowY = "scroll";
  div.style.display = "flex";
  div.style.justifyContent = "center";
  div.style.alignItems = "center";
  div.style.flexDirection = "column";

  return div;
}

function clearPhoneContainer() {
  const phoneContainer = document.getElementById("phone-container");
  const loadingScreen = document.getElementById("loading-screen");
  const appContent = document.getElementById("app-content");

  phoneContainer.removeChild(loadingScreen);
  phoneContainer.removeChild(appContent);
}

if (!isWasmGcSupported()) {
  handleWasmGcNotSupported();
}
