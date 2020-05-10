import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AppWidget extends StatefulWidget {
  final String title;
  final String icon;
  final Function onClick;

  AppWidget({Key key, this.title, this.icon, this.onClick}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _AppWidgetState();
  }
}

class _AppWidgetState extends State<AppWidget> {
  var scaleOfWidget = 1.0;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        GestureDetector(
          onTapDown: (TapDownDetails details) {
            setState(() {
              scaleOfWidget = 0.95;
            });
          },
          onTapUp: (TapUpDetails details) {
            setState(() {
              scaleOfWidget = 1.0;
            });
          },
          onTapCancel: () {
            setState(() {
              scaleOfWidget = 1.0;
            });
          },
          onLongPressEnd: (LongPressEndDetails details) {
            widget.onClick.call();
          },
          onTap: () {
            widget.onClick.call();
          },
          child: Image.asset(
            widget.icon,
            width: 40 * scaleOfWidget,
            height: 40 * scaleOfWidget,
          ),
        ),
        SizedBox(height: 10),
        Text(
          widget.title,
          style: TextStyle(color: Colors.white, fontSize: 13 * scaleOfWidget),
        )
      ],
    );
  }
}
