import 'package:flutter/cupertino.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:mybio/data/AboutThisDataBuilder.dart';
import 'package:mybio/screens/common/BioAppScaffold.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';
import 'package:url_launcher/url_launcher.dart';

class AboutThisApp extends StatelessWidget {
  final Function onPop;

  const AboutThisApp({key: Key, this.onPop});

  List<Widget> _getListItems(AboutThisDataModel model) {
    var widgets = <Widget>[];
    widgets.add(Column(
      children: [
        Padding(
          padding: EdgeInsets.only(top: 60),
        ),
        Padding(
          padding: EdgeInsets.fromLTRB(18, 0, 18, 0),
          child: Container(
            padding: EdgeInsets.all(16),
            width: double.infinity,
            decoration: BoxDecoration(
                border: Border(
              left: BorderSide(
                color: Colors.black,
                width: 2,
              ),
              top: BorderSide(
                color: Colors.black,
                width: 2,
              ),
            )),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Center(
                    child: Text(
                  "Built with",
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
                )),
                Padding(
                  padding: const EdgeInsets.only(top: 8),
                  child: Text(model.builtWithSummary),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 8),
                  child: IconButton(
                    icon: Image.asset(
                      "/images/contact_me/github_white.png",
                      color: Colors.black,
                    ),
                    onPressed: () => {
                      launch("https://github.com/loukwn/loukwn-me")
                    },
                    iconSize: 32,
                    splashRadius: 24,
                  ),
                ),
              ],
            ),
          ),
        ),
        Padding(
          padding: EdgeInsets.fromLTRB(18, 18, 18, 20),
          child: Container(
            padding: EdgeInsets.all(16),
            width: double.infinity,
            decoration: BoxDecoration(
                border: Border(
                    left: BorderSide(
                      color: Colors.black,
                      width: 2,
                    ),
                    top: BorderSide(
                      color: Colors.black,
                      width: 2,
                    ))),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Center(
                    child: Text(
                  "Attribution",
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
                )),
                Padding(
                  padding: const EdgeInsets.only(top: 8),
                  child: Text("List of external media that was used:"),
                ),
                for (var unsplashLink in model.unsplashLinks)
                  _getUnsplashItem(unsplashLink.artistLink,
                      unsplashLink.artistName, unsplashLink.unsplashLink),
                for (var link in model.otherMediaLinks)
                  _getOtherMediaLinkItem(link),
                Padding(
                  padding: const EdgeInsets.only(top: 20),
                  child: Text(model.logoTrademarksText),
                ),
              ],
            ),
          ),
        ),
      ],
    ));

    return widgets;
  }

  Widget _getOtherMediaLinkItem(String link) {
    return Padding(
      padding: const EdgeInsets.only(top: 8),
      child: RichText(
          text: TextSpan(
        text: '• ',
        children: [
          TextSpan(
              text: link,
              recognizer: TapGestureRecognizer()..onTap = () => launch(link),
              style: TextStyle(
                color: Colors.blue,
                decoration: TextDecoration.underline,
              )),
        ],
      )),
    );
  }

  Widget _getUnsplashItem(
    String artistLink,
    String artistName,
    String unsplashLink,
  ) {
    return Padding(
      padding: const EdgeInsets.only(top: 8),
      child: RichText(
          text: TextSpan(
        text: '• Photo by ',
        children: [
          TextSpan(
              text: artistName,
              recognizer: TapGestureRecognizer()
                ..onTap = () => launch(artistLink),
              style: TextStyle(
                color: Colors.blue,
                decoration: TextDecoration.underline,
              )),
          TextSpan(text: ' on '),
          TextSpan(
              text: "Unsplash",
              recognizer: TapGestureRecognizer()
                ..onTap = () => launch(unsplashLink),
              style: TextStyle(
                color: Colors.blue,
                decoration: TextDecoration.underline,
              )),
        ],
      )),
    );
  }

  @override
  Widget build(BuildContext context) {
    return BioAppScaffold(
        key: UniqueKey(),
        onPop: onPop,
        config: PortfolioAppConfiguration.ABOUT_APP,
        listItems: _getListItems(AboutThisDataBuilder.getModel()));
  }
}
