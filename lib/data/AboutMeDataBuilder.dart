class AboutMeDataModel {
  final String summary;
  final Education education;
  final List<Skill> skills;

  AboutMeDataModel({this.summary, this.education, this.skills});
}

class Education {
  final String title;
  final String schoolName;
  final String iconPath;
  final String durationString;

  Education({this.title, this.schoolName, this.durationString, this.iconPath});
}

class Skill {
  final String name;
  final int value;

  Skill({this.name, this.value});
}

// Till a better solution is found, this data will remain hardcoded here
class AboutMeDataBuilder {
  static AboutMeDataModel getModel() {
    return AboutMeDataModel(
      summary: "Welcome to my corner on the internet!",
      education:
        Education(
          title: "Integrated Master, Computer Engineering and Informatics Department",
          schoolName: "University of Patras",
          iconPath: "images/about_me/uop.png",
          durationString: "2012-2019"
        )
    );
  }
}
