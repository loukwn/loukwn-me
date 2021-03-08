import 'package:flutter/widgets.dart';

class SlideAnimationRouteBuilder extends PageRouteBuilder {
  final Widget page;

  SlideAnimationRouteBuilder({this.page})
      : super(
            pageBuilder: (BuildContext context, Animation<double> animation,
                    Animation<double> secondaryAnimation) =>
                page,
            transitionsBuilder: (BuildContext context,
                    Animation<double> animation,
                    Animation<double> secondaryAnimation,
                    Widget child) =>
                SlideTransition(
                  position: Tween<Offset>(
                    begin: const Offset(1, 0),
                    end: Offset.zero,
                  ).animate(animation),
                  child: child,
                ),
            transitionDuration: Duration(milliseconds: 500));
}

/**
 * Animation tips!
 * 1) Substitute to the following for access to interpolators:
 *
 * .animate(CurvedAnimation(
 *     parent: animation,
 *     curve: Curves.decelerate,
 *  )
 *
 *  2) In the SlideTransition above, I can specify as child another
 *   transition to effectively create a TransitionSet.
 */
