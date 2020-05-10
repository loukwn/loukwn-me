import 'package:flutter/material.dart';
import 'package:mybio/main_content_navigator.dart';
import 'package:mybio/widgets/AppWidget.dart';

class DesktopRoute extends StatefulWidget {
  final Function onPush;

  DesktopRoute({key: Key, this.onPush});

  @override
  State<StatefulWidget> createState() {
    return _DesktopRouteState();
  }
}

class _DesktopRouteState extends State<DesktopRoute> {
  Widget _desktop;

  @override
  Widget build(BuildContext context) {
    if (_desktop == null) {
      _desktop = getDesktop();
    }
    return _desktop;
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
                  onClick: () => {widget.onPush(MainContentRoutes.about_me)}),
              AppWidget(
                  key: UniqueKey(),
                  title: 'Experience',
                  icon: 'images/about_me.png',
                  onClick: () => {}),
              AppWidget(
                  key: UniqueKey(),
                  title: 'Contact me',
                  icon: 'images/about_me.png',
                  onClick: () => {}),
              AppWidget(
                  key: UniqueKey(),
                  title: 'About',
                  icon: 'images/about_me.png',
                  onClick: () => {}),
            ],
          ),
        ));
  }
}
