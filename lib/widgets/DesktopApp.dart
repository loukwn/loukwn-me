import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class DesktopApp extends StatefulWidget {
  final PortfolioAppConfiguration config;
  final Function onClick;

  DesktopApp({Key key, this.config, this.onClick}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _DesktopAppState();
  }
}

class _DesktopAppState extends State<DesktopApp> {
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
            tag: 'avatar_${widget.config.id}',
            child: Image.asset(
              widget.config.avatarImagePath,
              width: 40 * scaleOfWidget,
              height: 40 * scaleOfWidget,
            ),
          ),
        ),
        SizedBox(height: 10),
        Hero(
            tag: 'text_${widget.config.id}',
            child: Text(
              widget.config.title,
              style:
                  TextStyle(color: Colors.white, fontSize: 13 * scaleOfWidget),
            ))
      ],
    );
  }
}
