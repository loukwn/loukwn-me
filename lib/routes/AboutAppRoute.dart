import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/widgets/PortfolioApp.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class AboutAppRoute extends StatelessWidget {
  final Function onPop;

  const AboutAppRoute({key: Key, this.onPop});

  @override
  Widget build(BuildContext context) {
    return PortfolioApp(
        key: UniqueKey(),
        onPop: onPop,
        config: PortfolioAppConfiguration.ABOUT_APP,
        listItems: []);
  }
}
