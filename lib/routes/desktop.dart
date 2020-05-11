import 'package:flutter/material.dart';
import 'package:mybio/main_content_navigator.dart';
import 'package:mybio/widgets/AppWidget.dart';
//import 'dart:html' as html;

class DesktopRoute extends StatefulWidget {
  final Function onPush;
  final Function onPop;

  const DesktopRoute({key: Key, this.onPush, this.onPop});

  @override
  State<StatefulWidget> createState() {
//    html.window.parent.postMessage('desktop', '*');
    return DesktopRouteState();
  }
}

class DesktopRouteState extends State<DesktopRoute> {
  var currentRoute = MainContentRoutes.desktop;

  @override
  Widget build(BuildContext context) {
    return getDesktop();
  }

  Widget getDesktop() {
    return Container(
        color: Colors.transparent,
        child: Center(
          child: GridView.count(
            padding: const EdgeInsets.all(20),
            crossAxisCount: 3,
            children: <Widget>[
              //<a href="https://iconscout.com/icons/avatar" target="_blank">Avatar Icon</a> by <a href="https://iconscout.com/contributors/dmitriy-bondarchuk">Dmitriy Bondarchuk</a> on <a href="https://iconscout.com">Iconscout</a>
              AppWidget(
                  key: UniqueKey(),
                  title: 'About me',
                  icon: 'images/avatar.png',
                  onClick: () {
                    widget.onPush(MainContentRoutes.about_me);
                  }),
              AppWidget(
                  key: UniqueKey(),
                  title: 'Experience',
                  icon: 'images/about_me.png',
                  onClick: () {
                    widget.onPush(MainContentRoutes.experience);
                  }),
              AppWidget(
                  key: UniqueKey(),
                  title: 'Contact me',
                  icon: 'images/about_me.png',
                  onClick: () {
                    widget.onPush(MainContentRoutes.experience);
                  }),
              AppWidget(
                  key: UniqueKey(),
                  title: 'About this',
                  icon: 'images/about_me.png',
                  onClick: () {
                    widget.onPush(MainContentRoutes.about_app);
                  }),
            ],
          ),
        ));
  }
}
