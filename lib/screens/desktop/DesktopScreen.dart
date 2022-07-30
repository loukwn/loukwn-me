import 'package:flutter/material.dart';
import 'package:mybio/navigation/MainContentNavigator.dart';
import 'package:mybio/screens/desktop/DesktopAppShortcut.dart';
import 'package:universal_html/html.dart' as html;

import 'package:mybio/screens/common/BioAppConfiguration.dart';

class DesktopScreen extends StatefulWidget {
  final Function onPush;

  const DesktopScreen({key: Key, required this.onPush});

  @override
  State<StatefulWidget> createState() {
    html.window.parent?.postMessage('desktop', '*');
    return DesktopScreenState();
  }
}

class DesktopScreenState extends State<DesktopScreen> {
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
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.ABOUT_ME,
                  onClick: () {
                    widget.onPush(
                      MainContentRoutes.about_me,
                      PortfolioAppConfiguration.ABOUT_ME.jsEventName,
                    );
                  }),
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.PORTFOLIO,
                  onClick: () {
                    widget.onPush(
                      MainContentRoutes.experience,
                      PortfolioAppConfiguration.PORTFOLIO.jsEventName,
                    );
                  }),
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.CONTACT_ME,
                  onClick: () {
                    widget.onPush(
                      MainContentRoutes.contact_me,
                      PortfolioAppConfiguration.CONTACT_ME.jsEventName,
                    );
                  }),
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.ABOUT_APP,
                  onClick: () {
                    widget.onPush(
                      MainContentRoutes.about_app,
                      PortfolioAppConfiguration.ABOUT_APP.jsEventName,
                    );
                  }),
            ],
          ),
        ));
  }
}
