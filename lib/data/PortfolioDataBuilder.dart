class PortfolioDataModel {
  final String description;
  final List<Job> jobs;
  final List<Project> projects;

  PortfolioDataModel({this.description, this.jobs, this.projects});
}

class Job {
  final String title;
  final String description;
  final String durationString;
  final String location;
  final String bgImage;

  Job({this.title, this.description, this.durationString, this.location, this.bgImage});
}

class Project {
  final String name;
  final String description;
  final String link;
  final List<String> tags;
  final String bgImage;

  Project({this.name, this.description, this.link, this.tags, this.bgImage});
}

// Till a better solution is found, this data will remain hardcoded here
class PortfolioDataBuilder {
  static PortfolioDataModel getModel() {
    return PortfolioDataModel(
      description: "List of apps I worked on and things I tinkered with.",
      jobs: [
        Job(
          title: "muzmatch",
          description: "Helping millions of Muslims find their special one.",
          durationString: "Oct 2019 - Present",
          location: "London, UK",
          bgImage: "images/portfolio/muzmatch_bg.png"
        ),
        Job(
            title: "Nup",
            description: "Allowed students to exhange posts, chat messages and pictures under the veil of anonymity ",
            durationString: "Jun 2018 - Jan 2019",
            location: "Athens, GR",
            bgImage: "images/portfolio/nup_bg.png"
        ),
        Job(
            title: "UNIpad",
            description: "Assisted students on their quest to their degree by providing them with notes and helpful details for their uni.",
            durationString: "Jan 2017 - Jan 2019",
            location: "Athens, GR",
            bgImage: "images/portfolio/unipad_bg.png"
        ),
      ],
      projects: [
        Project(
          name: "GifSound It",
          description: "Combine GIFs with sound.\n\nMy kotlin workspace where I try and experiment with things like new architectures, libraries, patterns etc.",
          link: "https://www.github.com/loukwn/GifSound-It",
          bgImage: "images/portfolio/gifsoundit_bg.png"
        )
      ]
    );
  }
}
