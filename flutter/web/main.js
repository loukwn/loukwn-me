let messagedOnce = false;

const screens = {
    DESKTOP: 'desktop',
    ABOUT_ME: 'about_me',
    PORTFOLIO: 'portfolio',
    CONTACT_ME: 'contact_me',
    ABOUT_THIS: 'about_this'
}

//const theme = {
//    LIGHT: 'light',
//    DARK: 'dark'
//}

/*
 * Function that listens to events from flutter app
 */
window.addEventListener("message", function (event) {

    if (!messagedOnce) {
        messagedOnce = true;
        this.document.getElementById('loading-screen').remove()
    }

    updateWebsiteLook(event.data)
});

function updateWebsiteLook(screen) {
    console.log(screen)

    let bgWrapper = document.getElementById("background-container")

    switch (screen) {
        case screens.DESKTOP:
            bgWrapper.children[1].classList.remove("opaque")
            bgWrapper.children[2].classList.remove("opaque")
            bgWrapper.children[3].classList.remove("opaque")
            bgWrapper.children[4].classList.remove("opaque")
            bgWrapper.children[0].classList.add("opaque")
            break
        case screens.ABOUT_ME:
            bgWrapper.children[0].classList.remove("opaque")
            bgWrapper.children[1].classList.add("opaque")
            break
        case screens.PORTFOLIO:
            bgWrapper.children[0].classList.remove("opaque")
            bgWrapper.children[2].classList.add("opaque")
            break
        case screens.CONTACT_ME:
            bgWrapper.children[0].classList.remove("opaque")
            bgWrapper.children[3].classList.add("opaque")
            break
        case screens.ABOUT_THIS:
            bgWrapper.children[0].classList.remove("opaque")
            bgWrapper.children[4].classList.add("opaque")
            break
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

const MIN_PHONE_HEIGHT = 500;
const MAX_PHONE_HEIGHT = 1200;
const WINDOW_WIDTH_SMALL_SCREEN_CUTOFF_POINT = 780;
const MIN_PHONE_VERTICAL_MARGIN = 80;
const INITIAL_APP_CONTENT_HEIGHT = 696;
const INITIAL_APP_CONTENT_WIDTH = 358;
const INITIAL_APP_CONTENT_TOP = 56;
const INITIAL_APP_CONTENT_LEFT = 21;
const INITIAL_PHONE_HEIGHT = 800;
const INITIAL_PHONE_WIDTH = 400;

handleResize();
window.onresize = handleResize;

function handleResize(event) {
    resizePhone()
}

function resizePhone() {
    let phonecontainer = document.getElementById("phone-container");

    if (window.innerWidth <= WINDOW_WIDTH_SMALL_SCREEN_CUTOFF_POINT) {
        phonecontainer.style.height = "100%";
        phonecontainer.style.width = "100%";
        resizeAppContentToMax()
        return;
    }

    let windowHeight = window.innerHeight;
    let newPhoneContainerHeight = Math.min(Math.max(windowHeight - 2 * MIN_PHONE_VERTICAL_MARGIN, MIN_PHONE_HEIGHT), MAX_PHONE_HEIGHT);
    let newPhoneContainerWidth = newPhoneContainerHeight / 2;
    phonecontainer.style.height = newPhoneContainerHeight + "px";
    phonecontainer.style.width = newPhoneContainerWidth + "px";

    resizeAppContent(newPhoneContainerWidth, newPhoneContainerHeight);
}

function resizeAppContentToMax() {
    let appContent = document.getElementById("app-content");

    appContent.style.top = "0px";
    appContent.style.left = "0px";
    appContent.style.height = "100%";
    appContent.style.width = "100%";
}

function resizeAppContent(containerWidth, containerHeight) {
    let appContent = document.getElementById("app-content");

    let containerHeightRatio = containerHeight / INITIAL_PHONE_HEIGHT;
    let containerWidthRatio = containerWidth / INITIAL_PHONE_WIDTH;

    let newHeight = INITIAL_APP_CONTENT_HEIGHT * containerHeightRatio;
    let newWidth = INITIAL_APP_CONTENT_WIDTH * containerWidthRatio;
    let newTop = INITIAL_APP_CONTENT_TOP * containerHeightRatio;
    let newLeft = INITIAL_APP_CONTENT_LEFT * containerWidthRatio;

    appContent.style.top = newTop + "px";
    appContent.style.left = newLeft + "px";
    appContent.style.height = newHeight + "px";
    appContent.style.width = newWidth + "px";
}