import 'package:flutter/material.dart';
import 'package:mybio/main_content_navigator.dart';
import 'package:mybio/widgets/DesktopApp.dart';
import 'dart:html' as html;

import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class DesktopRoute extends StatefulWidget {
  final Function onPush;
  final Function onPop;

  const DesktopRoute({key: Key, this.onPush, this.onPop});

  @override
  State<StatefulWidget> createState() {
    html.window.parent.postMessage('desktop', '*');
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
              DesktopApp(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.ABOUT_ME,
                  onClick: () {
                    widget.onPush(MainContentRoutes.about_me);
                  }),
              DesktopApp(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.EXPERIENCE,
                  onClick: () {
                    widget.onPush(MainContentRoutes.experience);
                  }),
              DesktopApp(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.CONTACT_ME,
                  onClick: () {
                    widget.onPush(MainContentRoutes.contact_me);
                  }),
              DesktopApp(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.ABOUT_APP,
                  onClick: () {
                    widget.onPush(MainContentRoutes.about_app);
                  }),
            ],
          ),
        ));
  }
}
