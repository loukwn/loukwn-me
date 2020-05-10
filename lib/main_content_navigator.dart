import 'package:flutter/material.dart';
import 'package:mybio/routes/desktop.dart';
import 'package:mybio/routes/about_me.dart';

class MainContentRoutes {
  static const String root = '/';
  static const String about_me = '/about_me';
  static const String experience = '/experience';
  static const String contact_me = '/contact_me';
  static const String about = '/about';
}

class MainContentNavigator extends StatelessWidget {
  MainContentNavigator({this.navigatorKey});

  final GlobalKey<NavigatorState> navigatorKey;
  final GlobalKey<AboutMeRouteState> aboutMeKey =
      GlobalKey<AboutMeRouteState>();
  var currentRoute = MainContentRoutes.root;

  void _push(BuildContext context, String dest) {
    var routeBuilders = _routeBuilders(context);

    Navigator.push(context,
        MaterialPageRoute(builder: (context) => routeBuilders[dest](context)));

    currentRoute = dest;
  }

  void _pop(BuildContext context) {
    Navigator.pop(context);
    currentRoute = MainContentRoutes.root;
  }

  void pop() {
    if (currentRoute != MainContentRoutes.root)
    aboutMeKey.currentState.pop();
  }

  Map<String, WidgetBuilder> _routeBuilders(BuildContext context) {
    return {
      MainContentRoutes.root: (context) => DesktopRoute(
            onPush: (dest) => _push(context, dest),
          ),
      MainContentRoutes.about_me: (context) =>
          AboutMeRoute(key: aboutMeKey, onPop: () => _pop(context)),
    };
  }

  @override
  Widget build(BuildContext context) {
    var routeBuilders = _routeBuilders(context);

    return Navigator(
        key: navigatorKey,
        initialRoute: MainContentRoutes.root,
        onGenerateRoute: (routeSettings) {
          return MaterialPageRoute(
              builder: (context) => routeBuilders[routeSettings.name](context));
        });
  }
}
