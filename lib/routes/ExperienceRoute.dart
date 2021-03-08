import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/widgets/PortfolioApp.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class ExperienceRoute extends StatelessWidget {
  final Function onPop;

  const ExperienceRoute({key: Key, this.onPop});

  @override
  Widget build(BuildContext context) {
    return PortfolioApp(
        key: UniqueKey(),
        config: PortfolioAppConfiguration.EXPERIENCE,
        onPop: onPop,
        listItems: []);
  }
}
