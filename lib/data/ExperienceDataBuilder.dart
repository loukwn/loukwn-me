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

  Job({this.title, this.description, this.durationString, this.location, this.isCurrent, this.accentColor});
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
          title: "Android Engineer @ muzmatch",
          description: "",
          durationString: "October 2019 - Now",
          location: "London, UK",
          isCurrent: true,
        ),
        Job(
            title: "Android Engineer @ Nup / UNIpad",
            description: "",
            durationString: "October 2019 - Now",
            location: "London, UK",
            isCurrent: true
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
