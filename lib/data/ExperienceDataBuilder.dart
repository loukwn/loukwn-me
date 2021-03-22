import 'dart:ui';

class ExperienceDataModel {
  final String description;
  final List<Job> jobs;
  final List<Project> projects;

  ExperienceDataModel({this.description, this.jobs, this.projects});
}

class Job {
  final String title;
  final String description;
  final String durationString;
  final String location;
  final bool isCurrent;
  final Color accentColor;
  final String bgImage;

  Job({this.title, this.description, this.durationString, this.location, this.isCurrent, this.accentColor, this.bgImage});
}

class Project {
  final String name;
  final String description;
  final String link;
  final List<String> tags;

  Project({this.name, this.description, this.link, this.tags});
}

// Till a better solution is found, this data will remain hardcoded here
class ExperienceDataBuilder {
  static ExperienceDataModel getModel() {
    return ExperienceDataModel(
      description: "List of places I worked and things I tinkered with",
      jobs: [
        Job(
          title: "muzmatch",
          description: "Helping millions of Muslims find their special one.",
          durationString: "October 2019 - Present",
          location: "London, UK",
          isCurrent: true,
          accentColor: Color.fromARGB(0, 251,64,108),
          bgImage: "images/career/muzmatch_bg.png"
        ),
        Job(
            title: "Nup",
            description: "A",
            durationString: "October 2019 - Now",
            location: "Athens, GR",
            isCurrent: true,
            accentColor: Color.fromARGB(0, 241,47,102),
            bgImage: "images/career/nup_bg.png"
        ),
      ],
      projects: [
        Project(
          name: "GifSound It",
          description: "asd",
          link: "https://www.github.com/"
        )
      ]
    );
  }
}
