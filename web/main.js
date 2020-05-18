let loadedOnce = false;
window.addEventListener("message", function (event) {
    
    if (!loadedOnce) {
        loadedOnce = true;
        this.document.getElementById('loading-screen').remove()
    }

    document.getElementById("status").innerText = "Currently on: " + event.data;

    // can message back using event.source.postMessage(...)
});