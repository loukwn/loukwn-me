const allowedDestinations = [
  "home",
  "about-me",
  "portfolio",
  "contact-me",
  "about-this",
];

let updatingFromCompose = false;
let messagedOnce = false;

/// Should send the updated hash part of the url to Compose
/// - Will not send it if the change comes from Compose itself
//  - If the hash part is not recognized, it will default to home
function updateIframeBasedOnHash() {
  if (updatingFromCompose) return;

  const hash = window.location.hash.substring(1);
  const iframe = document.getElementById("app-content");
  let targetHash = hash;

  if (!allowedDestinations.includes(hash)) {
    targetHash = "home";
    window.location.hash = "home";
    return;
  }

  targetHash = `compose/index.html#${targetHash}`;
  if (getHashPartFromString(iframe.src) != getHashPartFromString(targetHash)) {
    iframe.src = targetHash;
  }
}

function getHashPartFromString(url) {
  const hashIndex = url.lastIndexOf("#");
  return hashIndex !== -1 ? url.substring(hashIndex) : "";
}

document.addEventListener("DOMContentLoaded", updateIframeBasedOnHash);
window.addEventListener("hashchange", updateIframeBasedOnHash);

window.addEventListener("message", function (event) {
  if (!messagedOnce) {
    messagedOnce = true;
    this.document.getElementById("loading-screen").remove();
  }

  updatingFromCompose = true;
  window.location.hash = event.data;

  setTimeout(() => {
    updatingFromCompose = false;
  }, 100);
});
