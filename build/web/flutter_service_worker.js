'use strict';
const MANIFEST = 'flutter-app-manifest';
const TEMP = 'flutter-temp-cache';
const CACHE_NAME = 'flutter-app-cache';
const RESOURCES = {
  "bio_app.html": "865abb7aa88eb28e09b739b73ef0767f",
"main.dart.js": "adf8fc63c90dbb0a565d934bd7ad6109",
"icons/Icon-192.png": "ac9a721a12bbc803b44f645561ecb1e1",
"icons/Icon-512.png": "96e752610906ba2a93c65f8abe1645f1",
"style.css": "54cb71e1d3de46f5b63a96a7a2d9db7d",
"favicon.png": "5dcef449791fa27946b3d35ad8803796",
"manifest.json": "7b0432251e494c429db144b9fa241947",
"index.html": "84cf122e52e3493c7e0dd9bc1d184c6f",
"/": "84cf122e52e3493c7e0dd9bc1d184c6f",
"version.json": "faa6bfc6d6be8d47260c41bdf808dda3",
"images/github.png": "3de53798e45531a859db5a5bd996aa0c",
"images/about_this_bg_main.jpg": "1b64de6341f8d0dd5bc2203110b5100f",
"images/about_me_bg_main.jpg": "819c994d42da4f519c938cfeb6c7f768",
"images/mail.png": "51d4be6dfa55a7909921954cb3ce8d51",
"images/medium.png": "eeff12b3d29c0a922585500f54a65e73",
"images/phone_frame.png": "63165e154e3082b50aba47d863453dfb",
"images/experience_bg_main.jpg": "73e077853a2e0e484916ee9dc4e7395e",
"images/desktop_bg.jpg": "0c929f94b9640109794b969cfa3c624c",
"images/contact_me_bg_main.jpg": "c3add7531c51af563287d1f90c69c620",
"images/linkedin.png": "87940700092e53f67ab0f1ed433f2b1c",
"assets/fonts/MaterialIcons-Regular.otf": "1288c9e28052e028aba623321f7826ac",
"assets/packages/cupertino_icons/assets/CupertinoIcons.ttf": "6d342eb68f170c97609e9da345464e5e",
"assets/AssetManifest.json": "2b8e6a362c8339b77a46191fd12606f2",
"assets/NOTICES": "9c7ffc4f1dee7b580ff3e87c490a53ce",
"assets/assets/fonts/oswald/Oswald-Medium.ttf": "351c498bd487b476454b8f09a34139a9",
"assets/assets/fonts/oswald/Oswald-Regular.ttf": "e1996192b98a516646ff9a8c0c0ca90c",
"assets/assets/fonts/oswald/Oswald-SemiBold.ttf": "c8ff3929086488642de6b260f5c14e5b",
"assets/assets/fonts/ostrich-sans/OstrichSans-Bold.otf": "733371ef3e0bdd8051067808e79416b5",
"assets/assets/images/contact_me_bg.jpg": "cf8218fa44c0c617f54e1068b66ae07b",
"assets/assets/images/about_me.png": "39bd0ede7e0c98dfb07a207b028646e2",
"assets/assets/images/avatar.png": "073503899db87e4cba0ceb8c0d248559",
"assets/assets/images/about_me_bg.png": "e129bfdac6be4f6417b42d42cd2ea4fd",
"assets/assets/images/phone_bg.png": "8555a08f70a9123cddab1dd55af5d26e",
"assets/assets/images/medium.png": "eeff12b3d29c0a922585500f54a65e73",
"assets/assets/images/linkedin_white.png": "87a43b807ea4ae5cfdd345294b4971c1",
"assets/assets/images/mail_white.png": "d917e67134bc706c52d2cd2cbbebee19",
"assets/assets/images/github_white.png": "1dea9ae98fea6a744446dd1ac1215b2b",
"assets/FontManifest.json": "43e3d85422321e745c2a78d5eb07f2cd",
"main.js": "45eb65d13ce9082573e66c63872e152d"
};

// The application shell files that are downloaded before a service worker can
// start.
const CORE = [
  "/",
"main.dart.js",
"index.html",
"assets/NOTICES",
"assets/AssetManifest.json",
"assets/FontManifest.json"];
// During install, the TEMP cache is populated with the application shell files.
self.addEventListener("install", (event) => {
  self.skipWaiting();
  return event.waitUntil(
    caches.open(TEMP).then((cache) => {
      return cache.addAll(
        CORE.map((value) => new Request(value, {'cache': 'reload'})));
    })
  );
});

// During activate, the cache is populated with the temp files downloaded in
// install. If this service worker is upgrading from one with a saved
// MANIFEST, then use this to retain unchanged resource files.
self.addEventListener("activate", function(event) {
  return event.waitUntil(async function() {
    try {
      var contentCache = await caches.open(CACHE_NAME);
      var tempCache = await caches.open(TEMP);
      var manifestCache = await caches.open(MANIFEST);
      var manifest = await manifestCache.match('manifest');
      // When there is no prior manifest, clear the entire cache.
      if (!manifest) {
        await caches.delete(CACHE_NAME);
        contentCache = await caches.open(CACHE_NAME);
        for (var request of await tempCache.keys()) {
          var response = await tempCache.match(request);
          await contentCache.put(request, response);
        }
        await caches.delete(TEMP);
        // Save the manifest to make future upgrades efficient.
        await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
        return;
      }
      var oldManifest = await manifest.json();
      var origin = self.location.origin;
      for (var request of await contentCache.keys()) {
        var key = request.url.substring(origin.length + 1);
        if (key == "") {
          key = "/";
        }
        // If a resource from the old manifest is not in the new cache, or if
        // the MD5 sum has changed, delete it. Otherwise the resource is left
        // in the cache and can be reused by the new service worker.
        if (!RESOURCES[key] || RESOURCES[key] != oldManifest[key]) {
          await contentCache.delete(request);
        }
      }
      // Populate the cache with the app shell TEMP files, potentially overwriting
      // cache files preserved above.
      for (var request of await tempCache.keys()) {
        var response = await tempCache.match(request);
        await contentCache.put(request, response);
      }
      await caches.delete(TEMP);
      // Save the manifest to make future upgrades efficient.
      await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
      return;
    } catch (err) {
      // On an unhandled exception the state of the cache cannot be guaranteed.
      console.error('Failed to upgrade service worker: ' + err);
      await caches.delete(CACHE_NAME);
      await caches.delete(TEMP);
      await caches.delete(MANIFEST);
    }
  }());
});

// The fetch handler redirects requests for RESOURCE files to the service
// worker cache.
self.addEventListener("fetch", (event) => {
  if (event.request.method !== 'GET') {
    return;
  }
  var origin = self.location.origin;
  var key = event.request.url.substring(origin.length + 1);
  // Redirect URLs to the index.html
  if (key.indexOf('?v=') != -1) {
    key = key.split('?v=')[0];
  }
  if (event.request.url == origin || event.request.url.startsWith(origin + '/#') || key == '') {
    key = '/';
  }
  // If the URL is not the RESOURCE list then return to signal that the
  // browser should take over.
  if (!RESOURCES[key]) {
    return;
  }
  // If the URL is the index.html, perform an online-first request.
  if (key == '/') {
    return onlineFirst(event);
  }
  event.respondWith(caches.open(CACHE_NAME)
    .then((cache) =>  {
      return cache.match(event.request).then((response) => {
        // Either respond with the cached resource, or perform a fetch and
        // lazily populate the cache.
        return response || fetch(event.request).then((response) => {
          cache.put(event.request, response.clone());
          return response;
        });
      })
    })
  );
});

self.addEventListener('message', (event) => {
  // SkipWaiting can be used to immediately activate a waiting service worker.
  // This will also require a page refresh triggered by the main worker.
  if (event.data === 'skipWaiting') {
    self.skipWaiting();
    return;
  }
  if (event.data === 'downloadOffline') {
    downloadOffline();
    return;
  }
});

// Download offline will check the RESOURCES for all files not in the cache
// and populate them.
async function downloadOffline() {
  var resources = [];
  var contentCache = await caches.open(CACHE_NAME);
  var currentContent = {};
  for (var request of await contentCache.keys()) {
    var key = request.url.substring(origin.length + 1);
    if (key == "") {
      key = "/";
    }
    currentContent[key] = true;
  }
  for (var resourceKey of Object.keys(RESOURCES)) {
    if (!currentContent[resourceKey]) {
      resources.push(resourceKey);
    }
  }
  return contentCache.addAll(resources);
}

// Attempt to download the resource online before falling back to
// the offline cache.
function onlineFirst(event) {
  return event.respondWith(
    fetch(event.request).then((response) => {
      return caches.open(CACHE_NAME).then((cache) => {
        cache.put(event.request, response.clone());
        return response;
      });
    }).catch((error) => {
      return caches.open(CACHE_NAME).then((cache) => {
        return cache.match(event.request).then((response) => {
          if (response != null) {
            return response;
          }
          throw error;
        });
      });
    })
  );
}
