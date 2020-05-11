import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
//import 'dart:html' as html;

class AboutMeRoute extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
//    html.window.parent.postMessage('about me', '*');
    return AboutMeRouteState();
  }
}

class AboutMeRouteState extends State<AboutMeRoute> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: <Widget>[
          SizedBox(
            height: 50,
          ),
          Row(
            children: <Widget>[
              Hero(
                  tag: 'avatar_About me',
                  child: Image.asset(
                    'images/avatar.png',
                    width: 80,
                    height: 80,
                  )),
              SizedBox(
                width: 20,
              ),
              Hero(tag: 'text_About me', child: Text('About me'))
            ],
          )
        ],
      ),
    );
  }
}
