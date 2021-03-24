import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/data/ExperienceDataBuilder.dart';
import 'package:mybio/widgets/JobItemWidget.dart';
import 'package:mybio/widgets/PortfolioApp.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class ExperienceRoute extends StatelessWidget {
  final Function onPop;

  const ExperienceRoute({key: Key, this.onPop});

  // Based on the ui model, the data will be set
  List<Widget> _getListItems(ExperienceDataModel model) {
    return [
      JobItemWidget.first(job: model.jobs[0]),
      JobItemWidget(job: model.jobs[1],),
      JobItemWidget(job: model.jobs[1],),
      JobItemWidget(job: model.jobs[1],),
      JobItemWidget(job: model.jobs[1],),
      JobItemWidget(job: model.jobs[1],),
      JobItemWidget(job: model.jobs[1],),
      JobItemWidget.last(job: model.jobs[1]),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return PortfolioApp(
        key: UniqueKey(),
        config: PortfolioAppConfiguration.PORTFOLIO,
        onPop: onPop,
        listItems: _getListItems(ExperienceDataBuilder.getModel()));
  }
}
