import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:mybio/widgets/PortfolioApp.dart';
import 'package:mybio/widgets/PortfolioAppConfiguration.dart';

class ContactMeRoute extends StatelessWidget {
  List<Widget> _getListItems() {
    return [
      SizedBox(
        height: 30,
      ),
      ListTile(
        title: Text(
          'GMAIL',
          style: TextStyle(color: Colors.red),
        ),
        trailing: Icon(Icons.directions_car),
      ),
      ListTile(
        title: Text('Github'),
        trailing: Icon(Icons.motorcycle),
      ),
      ListTile(
        title: Text('LinkedIn'),
        trailing: Icon(Icons.flight),
      ),
      ListTile(
        title: Text(
          'GMAIL',
          style: TextStyle(color: Colors.red),
        ),
        trailing: Icon(Icons.directions_car),
      ),
      ListTile(
        title: Text('Github'),
        trailing: Icon(Icons.motorcycle),
      ),
      ListTile(
        title: Text('LinkedIn'),
        trailing: Icon(Icons.flight),
      ),
      ListTile(
        title: Text(
          'GMAIL',
          style: TextStyle(color: Colors.red),
        ),
        trailing: Icon(Icons.directions_car),
      ),
      ListTile(
        title: Text('Github'),
        trailing: Icon(Icons.motorcycle),
      ),
      ListTile(
        title: Text('LinkedIn'),
        trailing: Icon(Icons.flight),
      ),
      ListTile(
        title: Text(
          'GMAIL',
          style: TextStyle(color: Colors.red),
        ),
        trailing: Icon(Icons.directions_car),
      ),
      ListTile(
        title: Text('Github'),
        trailing: Icon(Icons.motorcycle),
      ),
      ListTile(
        title: Text('LinkedIn'),
        trailing: Icon(Icons.flight),
      ),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return PortfolioApp(
        key: UniqueKey(),
        config: PortfolioAppConfiguration.CONTACT_ME,
        listItems: _getListItems());
  }
}
