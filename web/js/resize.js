const MIN_PHONE_HEIGHT = 500;
const MAX_PHONE_HEIGHT = 1200;
const WINDOW_SMALL_WIDTH_INITIAL_LOAD = 780;
const MIN_PHONE_VERTICAL_MARGIN = 20;
const INITIAL_APP_CONTENT_HEIGHT = 696;
const INITIAL_APP_CONTENT_WIDTH = 358;
const INITIAL_APP_CONTENT_TOP = 56;
const INITIAL_APP_CONTENT_LEFT = 21;
const INITIAL_PHONE_HEIGHT = 800;
const INITIAL_PHONE_WIDTH = 400;
const WINDOW_MIN_HEIGHT_ZOOM_CHANGE = 740;

var windowSizeBeforeTransitionToSmall = -1;

if (window.innerWidth < WINDOW_SMALL_WIDTH_INITIAL_LOAD) {
  // This handles the case where we open the website with a small width. This number is "magic" but after resizing
  // it should not matter anymore.
  windowSizeBeforeTransitionToSmall = WINDOW_SMALL_WIDTH_INITIAL_LOAD;
  handleSmallWindowWidth();
} else {
  handleResize();
}

window.onresize = handleResize;

function handleResize(event) {
  let phoneContainer = document.getElementById("phone-container");
  let phoneContainerWidth = phoneContainer.getBoundingClientRect().width;

  let detailsContainerRight = document
    .getElementById("details-container")
    .getBoundingClientRect().right;

  let windowWidth = window.innerWidth;

  if (
    windowWidth > windowSizeBeforeTransitionToSmall &&
    windowSizeBeforeTransitionToSmall !== -1
  ) {
    windowSizeBeforeTransitionToSmall = -1;
    handleLargeWindowWidth();
    resizePhone(phoneContainer);
    console.log("1");
  } else if (windowWidth <= phoneContainerWidth + detailsContainerRight) {
    if (windowSizeBeforeTransitionToSmall === -1) {
      windowSizeBeforeTransitionToSmall = windowWidth;
    }
    handleSmallWindowWidth();
    console.log("2");
  } else {
    resizePhone(phoneContainer);
    console.log("3");
  }

  if (window.innerHeight < WINDOW_SMALL_WIDTH_INITIAL_LOAD) {
    document.body.style.zoom = 0.9;
  } else {
    document.body.style.zoom = 1.0;
  }
}

function handleSmallWindowWidth() {
  // Content is presented as a column now
  let contentContainer = document.getElementById("content-container");
  contentContainer.style.flexDirection = "column";

  // Phone container takes all available space
  let phoneContainer = document.getElementById("phone-container");
  phoneContainer.style.height = "100%";
  phoneContainer.style.width = "100%";

  // App content occupies all available space
  let appContent = document.getElementById("app-content");
  appContent.style.top = "0px";
  appContent.style.left = "0px";
  appContent.style.height = "100%";
  appContent.style.width = "100%";

  // Phone frame is gone
  let phoneFrame = document.getElementById("phone-frame");
  phoneFrame.style.display = "none";

  // Loading screen has no rounded corners
  let loadingScreen = document.getElementById("loading-screen");
  loadingScreen.style.borderRadius = "0px";

  // Hide main social component
  let socialMain = document.getElementById("row-social-main");
  socialMain.style.display = "none";

  // If secondary social component is shown (inside phone) then show it
  let socialSecondary = document.getElementById("row-social-secondary");
  if (socialSecondary !== null) {
    socialSecondary.style.display = "block";
  }
}

function handleLargeWindowWidth() {
  // Content is presented as a row now
  let contentContainer = document.getElementById("content-container");
  contentContainer.style.flexDirection = "row";

  // Phone container resized to its standard size
  let phoneContainer = document.getElementById("phone-container");
  phoneContainer.style.height = "800px";
  phoneContainer.style.width = "400px";

  // App content occupies all available space
  let appContent = document.getElementById("app-content");
  appContent.style.top = "56px";
  appContent.style.left = "19px";

  // Phone frame is shown
  let phoneFrame = document.getElementById("phone-frame");
  phoneFrame.style.display = "inline";

  // Loading screen has no rounded corners
  let loadingScreen = document.getElementById("loading-screen");
  loadingScreen.style.borderRadius = "50px";

  // Show main social component
  let socialMain = document.getElementById("row-social-main");
  socialMain.style.display = "block";

  // If secondary social component is shown (inside phone) then hide it
  let socialSecondary = document.getElementById("row-social-secondary");
  if (socialSecondary !== null) {
    socialSecondary.style.display = "none";
  }
}

function resizePhone(phoneContainer) {
  let windowHeight = window.innerHeight;
  let newPhoneContainerHeight = Math.min(
    Math.max(windowHeight - 2 * MIN_PHONE_VERTICAL_MARGIN, MIN_PHONE_HEIGHT),
    MAX_PHONE_HEIGHT,
  );
  let newPhoneContainerWidth = newPhoneContainerHeight / 2;
  phoneContainer.style.height = newPhoneContainerHeight + "px";
  phoneContainer.style.width = newPhoneContainerWidth + "px";

  resizeAppContent(newPhoneContainerWidth, newPhoneContainerHeight);
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
