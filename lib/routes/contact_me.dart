import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'dart:html' as html;

class ContactMeRoute extends StatefulWidget {
  const ContactMeRoute({this.id});

  final int id;

  @override
  State<StatefulWidget> createState() {
    html.window.parent.postMessage('contact me', '*');
    return ContactMeRouteState();
  }
}

class ContactMeRouteState extends State<ContactMeRoute> {
  ScrollController _scrollController;

  @override
  void initState() {
    super.initState();
    _scrollController = new ScrollController();
    _scrollController.addListener(() => setState(() {}));
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  Widget _buildIcon() {
    double top = 124.0; //default top margin, -4 for exact alignment
    if (_scrollController.hasClients) {
      top -= _scrollController.offset;
    }
    if (top < 28) top = 28;
    return new Positioned(
      top: top,
      right: 16.0,
      child: Hero(
          tag: 'avatar_${widget.id}',
          child: Card(
            child: Image.asset(
              'images/avatar.png',
              width: 56,
              height: 56,
            ),
            elevation: 4,
            shape: CircleBorder(),
            clipBehavior: Clip.antiAlias,
          )),
    );
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
        onWillPop: () {
          html.window.parent.postMessage('desktop', '*');
          return new Future(() => true);
        },
        child: new Scaffold(
          body: new Stack(
            children: <Widget>[
              new CustomScrollView(
                controller: _scrollController,
                slivers: [
                  new SliverAppBar(
                    expandedHeight: 150.0,
                    pinned: true,
                    backgroundColor: Color.fromRGBO(138, 139, 137, 1),
                    flexibleSpace: new FlexibleSpaceBar(
                      title: new Text(
                        "Contact me",
                        style: TextStyle(fontFamily: 'OstrichSans'),
                      ),
                      background: Image.asset(
                        "images/contact_me_bg.jpg",
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                  new SliverList(
                    delegate: new SliverChildListDelegate(
                      new List.generate(
                        20,
                        (int index) =>
                            new ListTile(title: new Text("Item $index")),
                      ),
                    ),
                  ),
                ],
              ),
              _buildIcon()
            ],
          ),
        ));
  }
}
