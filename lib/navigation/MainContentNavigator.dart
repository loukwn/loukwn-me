import 'package:flutter/material.dart';
import 'package:mybio/navigation/SlideAnimationRouteBuilder.dart';
import 'package:mybio/screens/about_this/AboutThisApp.dart';
import 'package:mybio/screens/contact_me/ContactMeApp.dart';
import 'package:mybio/screens/desktop/DesktopScreen.dart';
import 'package:mybio/screens/about_me/AboutMeApp.dart';
import 'package:mybio/screens/portfolio/PortfolioApp.dart';
import 'package:universal_html/html.dart' as html;

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
  final GlobalKey<DesktopScreenState> desktopKey =
      GlobalKey<DesktopScreenState>();

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
      MainContentRoutes.desktop: (context) => DesktopScreen(
            onPush: (dest, eventPayload) => _push(context, dest, eventPayload),
          ),
      MainContentRoutes.about_me: (context) => AboutMeApp(
            onPop: () => _pop(context),
          ),
      MainContentRoutes.experience: (context) => PortfolioApp(
            onPop: () => _pop(context),
          ),
      MainContentRoutes.contact_me: (context) => ContactMeApp(
            onPop: () => _pop(context),
          ),
      MainContentRoutes.about_app: (context) => AboutThisApp(
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
