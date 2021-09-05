import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mybio/data/PortfolioDataBuilder.dart';
import 'package:url_launcher/url_launcher.dart';

class ProjectItemWidget extends StatelessWidget {
  final Project project;

  const ProjectItemWidget({Key key, this.project}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(30, 10, 30, 10),
      child: DecoratedBox(
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.only(
              topLeft: Radius.circular(10),
              topRight: Radius.circular(10),
              bottomLeft: Radius.circular(10),
              bottomRight: Radius.circular(10)),
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.5),
              spreadRadius: 2,
              blurRadius: 6,
              offset: Offset(5, 5), // changes position of shadow
            ),
          ],
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.all(16),
              child: Text(
                project.name,
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
              ),
            ),
            Padding(
              padding: const EdgeInsets.fromLTRB(16, 8, 16, 8),
              child: Text(
                project.description,
                style: TextStyle(fontSize: 15),
              ),
            ),
            Align(
              alignment: Alignment.center,
              child: SizedBox(
                height: 150,
                child: Image.asset(project.bgImage),
              ),
            ),
            Align(
              alignment: Alignment.centerRight,
              child: Padding(
                padding: const EdgeInsets.fromLTRB(12, 0, 12, 12),
                child: IconButton(
                  icon: Image.asset(
                    "assets/images/github_white.png",
                    width: 32,
                    height: 32,
                    color: Colors.black,
                  ),
                  onPressed: () => {launch(project.link)},
                  iconSize: 32,
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
