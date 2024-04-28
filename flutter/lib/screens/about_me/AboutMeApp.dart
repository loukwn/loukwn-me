import 'package:flutter/material.dart';
import 'package:mybio/data/AboutMeDataBuilder.dart';
import 'package:mybio/screens/about_me/AboutMeListWidget.dart';

import 'package:mybio/screens/common/BioAppScaffold.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';
import 'package:url_launcher/url_launcher.dart';

class AboutMeApp extends StatefulWidget {
  final Function onPop;

  const AboutMeApp({key = Key, required this.onPop});

  @override
  State<StatefulWidget> createState() => _AboutMeAppState();
}

class _AboutMeAppState extends State<AboutMeApp> {
  var _key = UniqueKey();

  // Based on the ui model, the data will be set
  List<Widget> _getListItems(AboutMeDataModel model) {
    var widgets = <Widget>[];
    widgets.add(Padding(
      padding: const EdgeInsets.fromLTRB(18, 15, 82, 15),
      child: Text(
        model.summary,
        style: TextStyle(fontSize: 15),
      ),
    ));

    widgets.add(AboutMeListWidget(
      [
        AboutMeListItemModel(
          title: "General",
          content: _getGeneralContent(model.generalText),
          textColor: Colors.white,
        ),
        AboutMeListItemModel(
          title: "Education",
          content: _getEducationContent(model.education),
          textColor: Colors.white,
        ),
        AboutMeListItemModel(
          title: "Interests",
          content: _getInterestsContent(model.interestsText),
          textColor: Colors.white,
        )
      ],
    ));

    widgets.add(Padding(padding: EdgeInsets.only(top: 16),));

    return widgets;
  }

  Widget _getGeneralContent(String generalText) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 16, horizontal: 8),
      child: Text(
        generalText,
        style: TextStyle(fontSize: 15),
      ),
    );
  }

  Widget _getEducationContent(Education education) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 16, horizontal: 8),
      child: Row(
        children: [
          Image.asset(
            education.iconPath,
            width: 64,
            height: 64,
          ),
          Expanded(
            child: Padding(
              padding: const EdgeInsets.only(left: 16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    education.title,
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 4),
                    child: Text(
                      education.schoolName,
                      style: TextStyle(fontStyle: FontStyle.italic),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 4),
                    child: Text(education.durationString),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget _getInterestsContent(String interestsText) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 16, horizontal: 8),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            interestsText,
            style: TextStyle(fontSize: 15),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 16),
            child: IconButton(
              icon: Image.asset("images/about_me/spotify.png",
                  width: 32, height: 32),
              onPressed: () => {
                launch(
                  "https://open.spotify.com/playlist/1M9YD0nmAhuAJP0vkfTxta?si=7975a42ed4ed4c3c",
                )
              },
              iconSize: 32,
            ),
          )
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return BioAppScaffold(
      key: _key,
      config: PortfolioAppConfiguration.ABOUT_ME,
      onPop: widget.onPop,
      listItems: _getListItems(AboutMeDataBuilder.getModel()),
    );
  }
}
