class PortfolioDataModel {
  final String description;
  final List<Job> jobs;
  final List<Project> projects;

  PortfolioDataModel({
    required this.description,
    required this.jobs,
    required this.projects,
  });
}

class Job {
  final String title;
  final String description;
  final String durationString;
  final String location;
  final String bgImage;

  Job({
    required this.title,
    required this.description,
    required this.durationString,
    required this.location,
    required this.bgImage,
  });
}

class Project {
  final String name;
  final String description;
  final String link;
  final String bgImage;

  Project({
    required this.name,
    required this.description,
    required this.link,
    required this.bgImage,
  });
}

// Till a better solution is found, this data will remain hardcoded here
class PortfolioDataBuilder {
  static PortfolioDataModel getModel() {
    return PortfolioDataModel(
        description: "List of apps I worked on and things I tinkered with.",
        jobs: [
          Job(
              title: "Muzz",
              description:
                  "Helping millions of Muslims find their special one.",
              durationString: "Oct 2019 - Present",
              location: "London, UK",
              bgImage: "images/portfolio/muzmatch_img.png"),
          Job(
              title: "Nup",
              description:
                  "Allowed students to exhange posts, chat messages and pictures under the veil of anonymity ",
              durationString: "Jun 2018 - Jan 2019",
              location: "Athens, GR",
              bgImage: "images/portfolio/nup_img.png"),
          Job(
              title: "UNIpad",
              description:
                  "Assisted students on their quest to their degree by providing them with notes and helpful details for their uni.",
              durationString: "Jan 2017 - Jan 2019",
              location: "Athens, GR",
              bgImage: "images/portfolio/unipad_img.png"),
        ],
        projects: [
          Project(
              name: "GifSound It",
              description:
                  "Combine GIFs with sound.\n\nMy kotlin workspace where I try and experiment with things like new architectures, libraries, patterns etc.",
              link: "https://www.github.com/loukwn/GifSound-It",
              bgImage: "images/portfolio/gifsoundit_img.png"),
          Project(
              name: "StageStepBar",
              description:
                  "Library that helps create custom ProgressBars with stages and configurable steps per stages. Available both as a View and a Composable version.",
              link: "https://www.github.com/loukwn/StageStepBar",
              bgImage: "images/portfolio/stagestepbar_img.png")
        ]);
  }
}
