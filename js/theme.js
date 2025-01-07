let messagedOnce = false;

const screens = {
  DESKTOP: "desktop",
  ABOUT_ME: "about_me",
  PORTFOLIO: "portfolio",
  CONTACT_ME: "contact_me",
  ABOUT_THIS: "about_this",
};

//const theme = {
//    LIGHT: 'light',
//    DARK: 'dark'
//}

/*
 * Function that listens to events from the "phone"
 */
window.addEventListener("message", function (event) {
  if (!messagedOnce) {
    messagedOnce = true;
    this.document.getElementById("loading-screen").remove();
  }

  updateWebsiteLook(event.data);
});

function updateWebsiteLook(screen) {
  console.log(screen);

  let bgWrapper = document.getElementById("background-container");

  switch (screen) {
    case screens.DESKTOP:
      bgWrapper.children[1].classList.remove("opaque");
      bgWrapper.children[2].classList.remove("opaque");
      bgWrapper.children[3].classList.remove("opaque");
      bgWrapper.children[4].classList.remove("opaque");
      bgWrapper.children[0].classList.add("opaque");
      break;
    case screens.ABOUT_ME:
      bgWrapper.children[0].classList.remove("opaque");
      bgWrapper.children[1].classList.add("opaque");
      break;
    case screens.PORTFOLIO:
      bgWrapper.children[0].classList.remove("opaque");
      bgWrapper.children[2].classList.add("opaque");
      break;
    case screens.CONTACT_ME:
      bgWrapper.children[0].classList.remove("opaque");
      bgWrapper.children[3].classList.add("opaque");
      break;
    case screens.ABOUT_THIS:
      bgWrapper.children[0].classList.remove("opaque");
      bgWrapper.children[4].classList.add("opaque");
      break;
  }
}
//
//function setThemeOfHeader(value) {
//    let title = document.getElementById('title')
//    let socialWrapper = document.getElementById('social-wrapper')
//
//    switch (value) {
//        case theme.LIGHT:
//            title.classList.remove("turn-to-dark")
//            title.classList.add("turn-to-light")
//            socialWrapper.classList.remove("turn-to-dark")
//            socialWrapper.classList.add("turn-to-light")
//            break
//        case theme.DARK:
//            title.classList.remove("turn-to-light")
//            title.classList.add("turn-to-dark")
//            socialWrapper.classList.remove("turn-to-light")
//            socialWrapper.classList.add("turn-to-dark")
//            break
//    }
//}
