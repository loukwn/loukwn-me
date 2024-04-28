import 'package:flutter/material.dart';
import 'package:mybio/navigation/MainContentNavigator.dart';
import 'package:mybio/screens/desktop/DesktopAppShortcut.dart';
import 'package:mybio/screens/desktop/DesktopAppSizeConfiguration.dart';
import 'package:mybio/util/SizeUtils.dart';
import 'package:universal_html/html.dart' as html;

import 'package:mybio/screens/common/BioAppConfiguration.dart';

class DesktopScreen extends StatefulWidget {
  final Function onPush;

  const DesktopScreen({key = Key, required this.onPush});

  @override
  State<StatefulWidget> createState() {
    html.window.parent?.postMessage('desktop', '*');
    return DesktopScreenState();
  }
}

class DesktopScreenState extends State<DesktopScreen> {
  var currentRoute = MainContentRoutes.desktop;

  _ScreenSizeConfig _getScreenSizeConfig(BuildContext context) {
    final screenWidthDp = getScreenWidthDp(context);
    final screenHeightDp = getScreenHeightDp(context);

    var desktopIconSize = (screenWidthDp / 3) - 42;
    if (desktopIconSize > 48) {
      desktopIconSize = 48;
    } else if (desktopIconSize > 40) {
      desktopIconSize = 40;
    } else if (desktopIconSize > 32) {
      desktopIconSize = 32;
    } else if (desktopIconSize > 24) {
      desktopIconSize = 24;
    }

    var spacerSize = 8;
    if (screenHeightDp < 550) {
      spacerSize = 0;
    } else if (screenHeightDp < 580) {
      spacerSize = 4;
    }

    final int crossAxisCount;
    if (screenHeightDp >= 850 || screenWidthDp >= 580) {
      crossAxisCount = 4;
    } else {
      crossAxisCount = 3;
    }

    return _ScreenSizeConfig(
      desktopIconSize: desktopIconSize,
      spacerSize: spacerSize.toDouble(),
      crossAxisCount: crossAxisCount,
    );
  }

  @override
  Widget build(BuildContext context) {
    return getDesktop(_getScreenSizeConfig(context));
  }

  Widget getDesktop(_ScreenSizeConfig screenSizeConfig) {
    return Container(
        color: Colors.transparent,
        child: Center(
          child: GridView.count(
            padding: const EdgeInsets.all(20),
            crossAxisCount: screenSizeConfig.crossAxisCount,
            children: <Widget>[
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.ABOUT_ME,
                  sizeConfig: DesktopAppSizeConfiguration(
                    desktopIconSize: screenSizeConfig.desktopIconSize,
                    spacerSize: screenSizeConfig.spacerSize,
                  ),
                  onClick: () {
                    widget.onPush(
                      MainContentRoutes.about_me,
                      PortfolioAppConfiguration.ABOUT_ME.jsEventName,
                    );
                  }),
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.PORTFOLIO,
                  sizeConfig: DesktopAppSizeConfiguration(
                    desktopIconSize: screenSizeConfig.desktopIconSize,
                    spacerSize: screenSizeConfig.spacerSize,
                  ),
                  onClick: () {
                    widget.onPush(
                      MainContentRoutes.experience,
                      PortfolioAppConfiguration.PORTFOLIO.jsEventName,
                    );
                  }),
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.CONTACT_ME,
                  sizeConfig: DesktopAppSizeConfiguration(
                    desktopIconSize: screenSizeConfig.desktopIconSize,
                    spacerSize: screenSizeConfig.spacerSize,
                  ),
                  onClick: () {
                    widget.onPush(
                      MainContentRoutes.contact_me,
                      PortfolioAppConfiguration.CONTACT_ME.jsEventName,
                    );
                  }),
              DesktopAppShortcut(
                  key: UniqueKey(),
                  config: PortfolioAppConfiguration.ABOUT_APP,
                  sizeConfig: DesktopAppSizeConfiguration(
                    desktopIconSize: screenSizeConfig.desktopIconSize,
                    spacerSize: screenSizeConfig.spacerSize,
                  ),
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

class _ScreenSizeConfig {
  final double desktopIconSize;
  final double spacerSize;
  final int crossAxisCount;

  _ScreenSizeConfig({
    required this.desktopIconSize,
    required this.spacerSize,
    required this.crossAxisCount,
  });
}
