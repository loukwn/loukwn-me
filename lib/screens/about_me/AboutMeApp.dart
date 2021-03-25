import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:mybio/screens/common/BioAppScaffold.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';

class AboutMeApp extends StatelessWidget {
  final Function onPop;

  const AboutMeApp({key: Key, this.onPop});

  @override
  Widget build(BuildContext context) {
    return BioAppScaffold(
      key: UniqueKey(),
      config: PortfolioAppConfiguration.ABOUT_ME,
      onPop: onPop,
      listItems: [],
    );
  }
}
