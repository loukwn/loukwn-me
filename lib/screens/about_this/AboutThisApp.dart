import 'package:flutter/cupertino.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:mybio/screens/common/BioAppScaffold.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';
import 'package:url_launcher/url_launcher.dart';

class AboutThisApp extends StatelessWidget {
  final Function onPop;

  const AboutThisApp({key: Key, this.onPop});

  List<Widget> _getListItems() {
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
                    ))),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Center(
                    child: Text(
                      "Built with",
                      style: TextStyle(
                          fontWeight: FontWeight.bold, fontSize: 16),
                    )),
                Padding(
                  padding: const EdgeInsets.only(top: 8),
                  child: Text(
                      "This is a Flutter app that exists inside a simple HTML/JS static page. There is some basic communication between the two (using messages and universal_html package) so that bgs change when an \"app\" is opened. For more info check out the GitHub repo of the project:"),
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 8),
                  child: IconButton(
                    icon: Image.asset(
                      "assets/images/github_white.png",
                      color: Colors.black,
                    ),
                    onPressed: () => {},
                    iconSize: 32,
                    splashRadius: 24,
                  ),
                ),
              ],
            ),
          ),
        ),
        Padding(
          padding: EdgeInsets.fromLTRB(18, 18, 18, 0),
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
                      style: TextStyle(
                          fontWeight: FontWeight.bold, fontSize: 16),
                    )),
                Padding(
                  padding: const EdgeInsets.only(top: 8),
                  child: Text("List of external media that was used:"),
                ),
                _getUnsplashItem(
                  "https://unsplash.com/@new_data_services?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
                  "NEW DATA SERVICES",
                  "https://unsplash.com/s/photos/notes?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 16),
                  child: Text(
                      "Logos of Spotify, GitHub, Medium and LinkedIn used are licensed trademarks of their respective owners. I do not own any of these."),
                ),
              ],
            ),
          ),
        ),
      ],
    ));

    return widgets;

    // return [
    // Padding(padding: EdgeInsets.only(top: 50)),
    // Text("https://www.google.com/imgres?imgurl=https%3A%2F%2Fpng.pngtree.com%2Fpng-vector%2F20200211%2Fourlarge%2Fpngtree-illustration-of-romantic-islamic-muslim-couple-falling-in-love-png-image_2143732.jpg&imgrefurl=https%3A%2F%2Fpngtree.com%2Ffreepng%2Fillustration-of-romantic-islamic-muslim-couple-falling-in_5320311.html&tbnid=nSkYzluJrLVz2M&vet=12ahUKEwjEu5LM-7XvAhUrgM4BHZinB-EQMygHegUIARDdAQ..i&docid=3pXxZMytlX13IM&w=640&h=640&q=muslim%20icouple%20llustration&hl=en-US&client=ubuntu&ved=2ahUKEwjEu5LM-7XvAhUrgM4BHZinB-EQMygHegUIARDdAQ"),
    // Text(""),
    // Text("https://pngtree.com/freepng/islamic-muslim-couple-character-fallin-in-love-in-flat-illustration_5320294.html"),
    // Text("muslim PNG Designed By depotvisual from <a href=\"https://pngtree.com/\">Pngtree.com</a>\""),
    // Text(""),
    // Text("https://iconarchive.com/show/beautiful-flat-one-color-icons-by-elegantthemes/dev-icon.html"),
    // Text(""),
    // Text("https://www.pngegg.com/en/png-bzcpy")
    //"Photo by <a href="https://unsplash.com/@cynthiayoung?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Cynthia Young</a> on <a href="/@cynthiayoung?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a>")
    //Photo by <a href="https://unsplash.com/@kilianfoto?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Kilian Seiler</a> on <a href="/?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a> https://unsplash.com/photos/PZLgTUAhxMM
    //Photo by <a href="https://unsplash.com/@maxvdo?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Max van den Oetelaar</a> on <a href="/s/visual/2672bb5c-a940-475e-b35c-59a5b12deef6?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a> https://unsplash.com/photos/F3rDBnQQbQU
    // Photo by <a href="https://unsplash.com/@mathyaskurmann?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Mathyas Kurmann</a> on <a href="/s/visual/6950cac1-76d6-4c60-9b00-5cb96fb48dcc?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a> https://unsplash.com/photos/fb7yNPbT0l8
    // Photo by <a href="https://unsplash.com/@seanlimm?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Sean Lim</a> on <a href="/s/visual/e076f4a1-6404-4ea7-949c-8cd55f39fc63?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a> https://unsplash.com/photos/NPlv2pkYoUA
    // Photo by <a href="https://unsplash.com/@briantagalog?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Brian Patrick Tagalog</a> on <a href="/s/visual/509ee80a-8273-4541-bc2d-8e0ed4a688a1?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a> https://unsplash.com/photos/_8hGFBxWD0A
    // Photo by <a href="https://unsplash.com/@rmrdnl?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Daniel Romero</a> on <a href="/s/photos/android?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a>
    // Photo by <a href="https://unsplash.com/@new_data_services?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">NEW DATA SERVICES</a> on <a href="https://unsplash.com/s/photos/notes?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText">Unsplash</a>

    // ];
  }

  Widget _getUnsplashItem(String artistLink, String artistName,
      String unsplashLink) {
    return Padding(
      padding: const EdgeInsets.only(top: 8),
      child: RichText(
          text: TextSpan(
            text: '- Photo by ',
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
          )
      ),);
  }

  @override
  Widget build(BuildContext context) {
    return BioAppScaffold(
        key: UniqueKey(),
        onPop: onPop,
        config: PortfolioAppConfiguration.ABOUT_APP,
        listItems: _getListItems());
  }
}
