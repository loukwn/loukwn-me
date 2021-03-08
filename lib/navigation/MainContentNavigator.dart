import 'package:flutter/material.dart';
import 'package:mybio/navigation/SlideAnimationRouteBuilder.dart';
import 'package:mybio/routes/AboutAppRoute.dart';
import 'package:mybio/routes/ContactMeRoute.dart';
import 'package:mybio/routes/DesktopRoute.dart';
import 'package:mybio/routes/AboutMeRoute.dart';
import 'package:mybio/routes/ExperienceRoute.dart';
import 'dart:html' as html;

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
  HeroController _heroController;

  void _push(BuildContext context, String dest, String htmlEventPayload) {
    Navigator.push(
      context,
      SlideAnimationRouteBuilder(page: _getRouteFromDest(context, dest))
    );

    // Notify outer JS that we navigated to another screen
    html.window.parent.postMessage(htmlEventPayload, '*');
  }

  void _pop(BuildContext context) {
    Navigator.pop(context);

    // Notify outer JS that we navigated back
    html.window.parent.postMessage('desktop', '*');
  }

  Map<String, WidgetBuilder> _routeBuilders(BuildContext context) {
    return {
      MainContentRoutes.desktop: (context) => DesktopRoute(
            onPush: (dest, eventPayload) => _push(context, dest, eventPayload),
          ),
      MainContentRoutes.about_me: (context) => AboutMeRoute(
            onPop: () => _pop(context),
          ),
      MainContentRoutes.experience: (context) => ExperienceRoute(
            onPop: () => _pop(context),
          ),
      MainContentRoutes.contact_me: (context) => ContactMeRoute(
            onPop: () => _pop(context),
          ),
      MainContentRoutes.about_app: (context) => AboutAppRoute(
            onPop: () => _pop(context),
          ),
    };
  }

  Widget _getRouteFromDest(BuildContext context, String dest) {
    var routeBuilders = _routeBuilders(context);

    return routeBuilders[dest](context);
  }

  @override
  void initState() {
    super.initState();
    _heroController = HeroController();
  }

  @override
  Widget build(BuildContext context) {
    return Navigator(
        key: widget.navigatorKey,
        observers: [_heroController],
        initialRoute: MainContentRoutes.desktop,
        onGenerateRoute: (routeSettings) {
          return MaterialPageRoute(
            builder: (context) =>
                _getRouteFromDest(context, routeSettings.name),
          );
        });
  }
}
