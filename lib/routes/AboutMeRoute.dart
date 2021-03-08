import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:mybio/widgets/PortfolioApp.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class AboutMeRoute extends StatelessWidget {
  final Function onPop;

  const AboutMeRoute({key: Key, this.onPop});

  @override
  Widget build(BuildContext context) {
    return PortfolioApp(
      key: UniqueKey(),
      config: PortfolioAppConfiguration.ABOUT_ME,
      onPop: onPop,
      listItems: [],
    );
  }
}
