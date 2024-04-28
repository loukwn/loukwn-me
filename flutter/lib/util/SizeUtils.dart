import 'package:flutter/widgets.dart';

double getScreenWidthDp(BuildContext context) {
  final mediaQuery = MediaQuery.of(context);
  return mediaQuery.size.width / mediaQuery.devicePixelRatio;
}

double getScreenHeightDp(BuildContext context) {
  final mediaQuery = MediaQuery.of(context);
  return mediaQuery.size.height / mediaQuery.devicePixelRatio;
}