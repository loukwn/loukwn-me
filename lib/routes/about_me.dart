import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'home.dart';

class AboutMeRoute extends StatefulWidget {
  final Function onPop;

  const AboutMeRoute({ key: Key, this.onPop });

  @override
  Widget build(BuildContext context) {

  }

  @override
  State<StatefulWidget> createState() {
    return AboutMeRouteState();
  }
}

class AboutMeRouteState extends State<AboutMeRoute> {
  @override
  Widget build(BuildContext context) {
    print("About me building");
    return  NotificationListener<SoftwareButtonActionNotification>(
      onNotification: (notification) {
        if (notification.title == SoftwareButtonActionNotification.BACK ||
            notification.title == SoftwareButtonActionNotification.HOME) {
          print("about me listens");
        }
        return true;
      }, child: Scaffold(
      body: Text("about me"),
    ),
    );
  }

  void pop() {
    widget.onPop();
  }
}