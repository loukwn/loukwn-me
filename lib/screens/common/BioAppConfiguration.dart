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
      'images/about_me/about_me_bg.png',
      'images/about_me/avatar.png',
      'about_me',
      Color.fromRGBO(112, 111, 113, 1),
      AnimationType.HORIZONTAL,
      100);

  static const PORTFOLIO = const PortfolioAppConfiguration._internal(
      2,
      'Portfolio',
      'images/portfolio/portfolio_bg.jpg',
      'images/portfolio/portfolio_icon.png',
      'portfolio',
      Color.fromRGBO(128, 132, 142, 1),
      AnimationType.VERTICAL,
      100);

  static const CONTACT_ME = const PortfolioAppConfiguration._internal(
      3,
      'Contact me',
      'images/contact_me/contact_me_bg.jpg',
      'images/contact_me/phone.png',
      'contact_me',
      Color.fromRGBO(138, 139, 137, 1),
      AnimationType.HORIZONTAL,
      100);

  static const ABOUT_APP = const PortfolioAppConfiguration._internal(
      4,
      'About this',
      'images/about_app/about_app_bg.jpg',
      'images/about_app/about_app.png',
      'about_this',
      Color.fromRGBO(138, 139, 137, 1),
      AnimationType.VERTICAL,
      100);
}

enum AnimationType {
  VERTICAL, HORIZONTAL
}
