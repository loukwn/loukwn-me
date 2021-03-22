import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/widgets/PortfolioApp.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class AboutAppRoute extends StatelessWidget {
  final Function onPop;

  const AboutAppRoute({key: Key, this.onPop});

  List<Widget> _getListItems() {
    return [
      Padding(padding: EdgeInsets.only(top: 50)),
      Text("https://www.google.com/imgres?imgurl=https%3A%2F%2Fpng.pngtree.com%2Fpng-vector%2F20200211%2Fourlarge%2Fpngtree-illustration-of-romantic-islamic-muslim-couple-falling-in-love-png-image_2143732.jpg&imgrefurl=https%3A%2F%2Fpngtree.com%2Ffreepng%2Fillustration-of-romantic-islamic-muslim-couple-falling-in_5320311.html&tbnid=nSkYzluJrLVz2M&vet=12ahUKEwjEu5LM-7XvAhUrgM4BHZinB-EQMygHegUIARDdAQ..i&docid=3pXxZMytlX13IM&w=640&h=640&q=muslim%20icouple%20llustration&hl=en-US&client=ubuntu&ved=2ahUKEwjEu5LM-7XvAhUrgM4BHZinB-EQMygHegUIARDdAQ"),
      Text(""),
      Text("https://pngtree.com/freepng/islamic-muslim-couple-character-fallin-in-love-in-flat-illustration_5320294.html"),
      Text("muslim PNG Designed By depotvisual from <a href=\"https://pngtree.com/\">Pngtree.com</a>\"")
    ];
  }

  @override
  Widget build(BuildContext context) {
    return PortfolioApp(
        key: UniqueKey(),
        onPop: onPop,
        config: PortfolioAppConfiguration.ABOUT_APP,
        listItems: _getListItems());
  }
}
