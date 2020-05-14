import 'package:flutter/material.dart';
import 'package:mybio/routes/contact_me.dart';
import 'package:mybio/routes/desktop.dart';
import 'package:mybio/routes/about_me.dart';
import 'package:mybio/routes/experience.dart';

class MainContentRoutes {
  static const String desktop = '/';
  static const String about_me = '/about_me';
  static const String experience = '/experience';
  static const String contact_me = '/contact_me';
  static const String about_app = '/about_app';
}

class MainContentNavigator extends StatefulWidget {
  MainContentNavigator({this.navigatorKey});

  final GlobalKey<NavigatorState> navigatorKey;
  final GlobalKey<DesktopRouteState> desktopKey =
      GlobalKey<DesktopRouteState>();

  @override
  State<StatefulWidget> createState() => MainContentNavigatorState();
}

class MainContentNavigatorState extends State<MainContentNavigator> {
  void _push(BuildContext context, String dest) {
    var routeBuilders = _routeBuilders(context);

    Navigator.push(context,
        MaterialPageRoute(builder: (context) => routeBuilders[dest](context)));
  }

  Map<String, WidgetBuilder> _routeBuilders(BuildContext context) {
    return {
      MainContentRoutes.desktop: (context) => DesktopRoute(
            onPush: (dest) => _push(context, dest),
          ),
      MainContentRoutes.about_me: (context) => AboutMeRoute(
            id: 1,
          ),
      MainContentRoutes.experience: (context) => ExperienceRoute(
            id: 2,
          ),
      MainContentRoutes.contact_me: (context) => ContactMeRoute(
            id: 3,
          ),
    };
  }

  HeroController _heroController;

  @override
  void initState() {
    super.initState();
    _heroController = HeroController();
  }

  @override
  Widget build(BuildContext context) {
    var routeBuilders = _routeBuilders(context);

    return Navigator(
        key: widget.navigatorKey,
        observers: [_heroController],
        initialRoute: MainContentRoutes.desktop,
        onGenerateRoute: (routeSettings) {
          return MaterialPageRoute(
              builder: (context) => routeBuilders[routeSettings.name](context));
        });
  }
}
