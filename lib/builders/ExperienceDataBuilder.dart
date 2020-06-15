import 'dart:convert';

class ExperienceDataBuilder {
  static ExperienceDataModel getModelFromJson(String jsonString) {
    if (jsonString == null) return null;
    return ExperienceDataModel.fromJson(json.decode(jsonString));
  }
}

class Job {
  final String title;
  final String company;
  final String location;
  final String duration;
  final String url;
  final String description;

  Job(
      {this.title,
      this.company,
      this.location,
      this.duration,
      this.url,
      this.description});

  Job.fromJson(Map<String, dynamic> data)
      : title = data['title'],
        company = data['company'],
        location = data['location'],
        duration = data['duration'],
        url = data['url'],
        description = data['description'];
}

class Project {
  final String name;
  final String url;
  final String description;

  Project({this.name, this.url, this.description});

  Project.fromJson(Map<String, dynamic> data)
      : name = data['name'],
        url = data['url'],
        description = data['description'];
}

class ExperienceDataModel {
  final List<Job> jobs;
  final List<Project> projects;

  ExperienceDataModel({this.jobs, this.projects});

  factory ExperienceDataModel.fromJson(Map<String, dynamic> data) {
    var list = data['jobs'] as List;
    List<Job> jobs = list.map((i) => Job.fromJson(i)).toList();

    list = data['projects'] as List;
    List<Project> projects = list.map((i) => Project.fromJson(i)).toList();

    return ExperienceDataModel(jobs: jobs, projects: projects);
  }
}
