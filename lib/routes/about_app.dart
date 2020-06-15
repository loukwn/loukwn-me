import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/widgets/PortfolioApp.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class AboutAppRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return PortfolioApp(
        key: UniqueKey(),
        config: PortfolioAppConfiguration.ABOUT_APP,
        listItems: []);
  }
}
