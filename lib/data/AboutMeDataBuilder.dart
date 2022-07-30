class AboutMeDataModel {
  final String summary;
  final String generalText;
  final Education education;
  final String interestsText;

  AboutMeDataModel({
    required this.summary,
    required this.generalText,
    required this.education,
    required this.interestsText,
  });
}

class Education {
  final String title;
  final String schoolName;
  final String iconPath;
  final String durationString;

  Education({
    required this.title,
    required this.schoolName,
    required this.durationString,
    required this.iconPath,
  });
}

// Till a better solution is found, this data will remain hardcoded here
class AboutMeDataBuilder {
  static AboutMeDataModel getModel() {
    return AboutMeDataModel(
        summary: "Welcome to my corner on the internet!",
        generalText:
            "Hello there!\n\nMy name is Konstantinos, I am from Greece and currently living and working in London, UK as a full time Android Engineer.\n\nCoding mostly on native Android and a bit of Flutter but love all things mobile dev in general. In my spare time I enjoy messing around with other programming languages like Rust and working on various side projects on my GitHub.",
        education: Education(
            title:
                "Integrated Master, Computer Engineering and Informatics Department",
            schoolName: "University of Patras",
            iconPath: "images/about_me/uop.png",
            durationString: "2012-2019"),
        interestsText:
            "When I am not working / coding I like to play video games, cycle, and learn about other languages (non programming ones).\n\nI also enjoy listening to all sorts of music but particularly Synthwave / Retrowave. Check my playlist below if you want to get a taste!");
  }
}
