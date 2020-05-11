import 'package:flutter/cupertino.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
//import 'dart:html' as html;

class ExperienceRoute extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
//    html.window.parent.postMessage('experience', '*');
    return ExperienceRouteState();
  }
}

class ExperienceRouteState extends State<ExperienceRoute> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: RichText(
            text: new TextSpan(
      children: [
        new TextSpan(
          text: 'This is no Link, ',
          style: new TextStyle(color: Colors.black),
        ),
        new TextSpan(
          text: 'but this is',
          style: new TextStyle(color: Colors.blue),
          recognizer: new TapGestureRecognizer()
            ..onTap = () {
              launch(
                  'https://docs.flutter.io/flutter/services/UrlLauncher-class.html');
            },
        ),
      ],
    )));
  }
}
