import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/data/ContactMeDataBuilder.dart';

import 'package:mybio/screens/common/BioAppScaffold.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';

import 'package:url_launcher/url_launcher.dart';

class ContactMeApp extends StatelessWidget {
  final config = PortfolioAppConfiguration.CONTACT_ME;
  final Function onPop;

  ContactMeApp({key: Key, this.onPop});

  // Based on the ui model, the data will be set
  List<Widget> _getListItems(ContactMeDataModel model) {
    var widgets = <Widget>[];
    widgets.add(Padding(
      padding: const EdgeInsets.fromLTRB(18, 15, 82, 25),
      child: Text(
        model.description,
        style: TextStyle(fontSize: 15),
      ),
    ));

    model.links.forEach((element) {
      var linkText = element.link
          .replaceAll("https://", "")
          .replaceAll("mailto:", "")
          .replaceAll("www.", "");

      var bgColor;
      var textColor;
      var imageFileName;
      switch (element.type) {
        case "gm":
          bgColor = Color.fromRGBO(185, 64, 52, 1);
          textColor = Colors.white;
          imageFileName = "mail_white.png";
          break;
        case "li":
          bgColor = Color.fromRGBO(0, 115, 177, 1);
          textColor = Colors.white;
          imageFileName = "linkedin_white.png";
          break;
        case "gh":
          bgColor = Colors.black;
          textColor = Colors.white;
          imageFileName = "github_white.png";
          break;
        case "me":
          bgColor = Colors.white;
          textColor = Colors.black;
          imageFileName = "medium.png";
          break;
      }

      widgets.add(Padding(
        padding: const EdgeInsets.fromLTRB(15, 6, 15, 6),
        child: Stack(
          alignment: Alignment.centerLeft,
          children: <Widget>[
            Card(
              child: Material(
                color: bgColor,
                child: InkWell(
                  onTap: () {
                    launch(element.link);
                  },
                  child: Container(
                    width: double.infinity,
                    child: Padding(
                      padding: const EdgeInsets.fromLTRB(70, 12, 10, 12),
                      child: Text(
                        linkText,
                        style: TextStyle(color: textColor, fontSize: 16),
                      ),
                    ),
                  ),
                ),
              ),
              elevation: 2,
            ),
            Card(
              child: Container(
                  width: 58,
                  height: 58,
                  child: Padding(
                    padding: const EdgeInsets.all(10),
                    child: Image.asset('images/contact_me/$imageFileName'),
                  )),
              color: bgColor,
              elevation: 4,
            ),
          ],
        ),
      ));
    });

    return widgets;
  }

  @override
  Widget build(BuildContext context) {
    return BioAppScaffold(
      key: UniqueKey(),
      config: config,
      onPop: onPop,
      listItems: _getListItems(ContactMeDataBuilder.getModel()),
    );
  }
}
