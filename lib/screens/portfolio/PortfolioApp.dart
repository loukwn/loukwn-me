import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/data/ExperienceDataBuilder.dart';
import 'package:mybio/screens/portfolio/JobItemWidget.dart';
import 'package:mybio/screens/common/BioAppScaffold.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';

class PortfolioApp extends StatelessWidget {
  final Function onPop;

  const PortfolioApp({key: Key, this.onPop});

  // Based on the ui model, the data will be set
  List<Widget> _getListItems(ExperienceDataModel model) {
    var widgets = <Widget>[];
    widgets.add(Padding(
      padding: const EdgeInsets.fromLTRB(18, 15, 82, 25),
      child: Text(
        model.description,
        style: TextStyle(fontSize: 15),
      ),
    ));

    widgets.add(Padding(
      padding: const EdgeInsets.fromLTRB(18, 0, 0, 15),
      child: Text(
        "Professional",
        style: TextStyle(
            fontFamily: "Oswald", fontWeight: FontWeight.w500, fontSize: 18),
      ),
    ));

    for (var i = 0; i < model.jobs.length; i++) {
      if (i == 0) {
        widgets.add(JobItemWidget.first(job: model.jobs[0]));
      } else if (i == model.jobs.length - 1) {
        widgets.add(JobItemWidget.last(job: model.jobs[model.jobs.length - 1]));
      } else {
        widgets.add(JobItemWidget(job: model.jobs[i]));
      }
    }
    return widgets;
  }

  @override
  Widget build(BuildContext context) {
    return BioAppScaffold(
        key: UniqueKey(),
        config: PortfolioAppConfiguration.PORTFOLIO,
        onPop: onPop,
        listItems: _getListItems(ExperienceDataBuilder.getModel()));
  }
}
