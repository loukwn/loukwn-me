import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AppWidget extends StatefulWidget {
  final String title;
  final String icon;
  final int id;
  final Function onClick;

  AppWidget({Key key, this.title, this.icon, this.onClick, this.id})
      : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _AppWidgetState();
  }
}

class _AppWidgetState extends State<AppWidget> {
  var scaleOfWidget = 1.0;
  var rebuildCausedBySettingState = false;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        GestureDetector(
          onTapDown: (TapDownDetails details) {
            setState(() {
              scaleOfWidget = 0.95;
              rebuildCausedBySettingState = true;
            });
          },
          onTapUp: (TapUpDetails details) {
            setState(() {
              scaleOfWidget = 1.0;
            });
          },
          onTapCancel: () {
            if (!rebuildCausedBySettingState) {
              setState(() {
                scaleOfWidget = 1.0;
              });
            }
          },
          onLongPressEnd: (LongPressEndDetails details) {
            widget.onClick.call();
            setState(() {
              scaleOfWidget = 1.0;
            });
          },
          onTap: () {
            widget.onClick.call();
            setState(() {
              scaleOfWidget = 1.0;
            });
          },
          child: Hero(
            tag: 'avatar_${widget.id}',
            child: Image.asset(
              widget.icon,
              width: 40 * scaleOfWidget,
              height: 40 * scaleOfWidget,
            ),
          ),
        ),
        SizedBox(height: 10),
        Hero(
            tag: 'text_${widget.id}',
            child: Text(
              widget.title,
              style:
                  TextStyle(color: Colors.white, fontSize: 13 * scaleOfWidget),
            ))
      ],
    );
  }
}
