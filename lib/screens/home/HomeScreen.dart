import 'package:flutter/material.dart';
import 'dart:async';
import 'package:universal_html/html.dart' as html;

import 'package:mybio/navigation/MainContentNavigator.dart';

class HomeScreen extends StatefulWidget {
  final navigatorKey = GlobalKey<NavigatorState>();
  final mainContentNavigatorKey = GlobalKey<MainContentNavigatorState>();

  @override
  _HomeScreenState createState() {
    var now = DateTime.now();
    var timeToDisplay =
        "${now.hour.toString().padLeft(2, '0')}:${now.minute.toString().padLeft(2, '0')}";

    return _HomeScreenState(timeToDisplay);
  }
}

class _HomeScreenState extends State<HomeScreen> {
  var timeToDisplay = "";

  Widget _mainContentWidget;

  _HomeScreenState(String timeToDisplay) {
    this.timeToDisplay = timeToDisplay;
  }

  @override
  void initState() {
    super.initState();
    _startTimerForClock();
  }

  Widget _getBgImage() {
    return Image.asset('assets/images/phone_bg.png',
        fit: BoxFit.cover, height: double.infinity, width: double.infinity);
  }

  Widget _getStatusBar(String timeToDisplay) {
    return Container(
      color: Color(0xff000000),
      padding: const EdgeInsets.fromLTRB(10.0, 5.0, 10.0, 5.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Text(timeToDisplay, style: TextStyle(color: Colors.white)),
          Wrap(
            spacing: 5,
            children: <Widget>[
              Icon(
                Icons.alarm,
                color: Colors.white,
                size: 16.0,
              ),
              Icon(
                Icons.network_wifi,
                color: Colors.white,
                size: 16.0,
              ),
              Icon(
                Icons.network_cell,
                color: Colors.white,
                size: 16.0,
              ),
              Icon(
                Icons.battery_full,
                color: Colors.white,
                size: 16.0,
              ),
            ],
          )
        ],
      ),
    );
  }

  Widget _getMainContent() {
    if (_mainContentWidget == null) {
      _mainContentWidget = Expanded(
          child: MainContentNavigator(
        navigatorKey: widget.navigatorKey,
      ));
    }

    return _mainContentWidget;
  }

  Widget _getSoftButtons() {
    return Container(
        padding: EdgeInsets.symmetric(horizontal: 15.0),
        color: Colors.black,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            RotatedBox(
              quarterTurns: 3,
              child: IconButton(
                icon: new Icon(
                  Icons.change_history,
                  color: Colors.white,
                  size: 24.0,
                ),
                onPressed: () {
                  // Send a notification to close the current screen
                  widget.navigatorKey.currentState
                      .popUntil((route) => route.isFirst);

                  // Let outside html know that we are back to desktop
                  html.window.parent.postMessage('desktop', '*');
                },
              ),
            ),
            IconButton(
                icon: new Icon(
                  Icons.panorama_fish_eye,
                  color: Colors.white,
                  size: 20.0,
                ),
                onPressed: () {
                  // Send a notification to close the current screen
                  widget.navigatorKey.currentState
                      .popUntil((route) => route.isFirst);

                  // Let outside html know that we are back to desktop
                  html.window.parent.postMessage('desktop', '*');
                }),
            IconButton(
                icon: new Icon(
                  Icons.check_box_outline_blank,
                  color: Colors.white,
                  size: 20.0,
                ),
                onPressed: () {}),
          ],
        ));
  }

  void _startTimerForClock() {
    Timer.periodic(new Duration(seconds: 1), (timer) {
      var now = DateTime.now();
      setState(() {
        timeToDisplay =
            "${now.hour.toString().padLeft(2, '0')}:${now.minute.toString().padLeft(2, '0')}";
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: <Widget>[
          _getBgImage(),
          Column(
            children: <Widget>[
              _getStatusBar(timeToDisplay),
              _getMainContent(),
              _getSoftButtons()
            ],
          )
        ],
      ),
    );
  }
}
