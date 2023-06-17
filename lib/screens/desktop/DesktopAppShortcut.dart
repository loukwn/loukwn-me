import 'package:flutter/material.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';
import 'package:mybio/screens/desktop/DesktopAppSizeConfiguration.dart';
import 'package:mybio/util/SizeUtils.dart';
import 'package:tuple/tuple.dart';

class DesktopAppShortcut extends StatelessWidget {
  final PortfolioAppConfiguration config;
  final DesktopAppSizeConfiguration sizeConfig;
  final Function onClick;

  DesktopAppShortcut({
    Key? key,
    required this.config,
    required this.sizeConfig,
    required this.onClick,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Material(
          shape: CircleBorder(),
          color: Colors.transparent,
          child: InkWell(
            customBorder: CircleBorder(),
            onTap: () => {onClick()},
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Hero(
                tag: 'avatar_${config.id}',
                child: Image.asset(
                  config.avatarImagePath,
                  width: sizeConfig.desktopIconSize,
                  height: sizeConfig.desktopIconSize,
                ),
              ),
            ),
          ),
        ),
        SizedBox(height: sizeConfig.spacerSize),
        Text(
          config.title,
          style: TextStyle(color: Colors.white, fontSize: 13),
        )
      ],
    );
  }
}

