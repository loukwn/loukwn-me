import 'dart:ui';

class PortfolioAppConfiguration {
  final int id;
  final String title;
  final String backgroundImagePath;
  final String avatarImagePath;
  final String jsEventName;
  final Color appBarBackgroundColor;
  final AnimationType itemAnimationType;
  final int itemAnimationDelayDuration;

  const PortfolioAppConfiguration._internal(
    this.id,
    this.title,
    this.backgroundImagePath,
    this.avatarImagePath,
    this.jsEventName,
    this.appBarBackgroundColor,
    this.itemAnimationType,
    this.itemAnimationDelayDuration,
  );

  // Individual screen configuration

  static const ABOUT_ME = const PortfolioAppConfiguration._internal(
      1,
      'About me',
      'images/contact_me_bg.jpg',
      'images/avatar.png',
      'about_me',
      Color.fromRGBO(138, 139, 137, 1),
      AnimationType.HORIZONTAL,
      100);

  static const EXPERIENCE = const PortfolioAppConfiguration._internal(
      2,
      'Experience',
      'images/contact_me_bg.jpg',
      'images/about_me.png',
      'experience',
      Color.fromRGBO(138, 139, 137, 1),
      AnimationType.HORIZONTAL,
      100);

  static const CONTACT_ME = const PortfolioAppConfiguration._internal(
      3,
      'Contact me',
      'images/contact_me_bg.jpg',
      'images/avatar.png',
      'contact_me',
      Color.fromRGBO(138, 139, 137, 1),
      AnimationType.HORIZONTAL,
      100);

  static const ABOUT_APP = const PortfolioAppConfiguration._internal(
      4,
      'About this',
      'images/contact_me_bg.jpg',
      'images/about_me.png',
      'about_this',
      Color.fromRGBO(138, 139, 137, 1),
      AnimationType.HORIZONTAL,
      100);
}

enum AnimationType {
  VERTICAL, HORIZONTAL
}
