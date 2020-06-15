import 'dart:convert';

class ContactMeDataBuilder {
  static ContactMeDataModel getModelFromJson(String jsonString) {
    if (jsonString == null) return null;
    return ContactMeDataModel.fromJson(json.decode(jsonString));
  }
}

class Link {
  final String type;
  final String link;

  Link({this.type, this.link});

  Link.fromJson(Map<String, dynamic> data)
      : type = data['type'],
        link = data['link'];
}

class ContactMeDataModel {
  final String description;
  final List<Link> links;

  ContactMeDataModel({this.description, this.links});

  factory ContactMeDataModel.fromJson(Map<String, dynamic> data) {
    var description = data['description'];
    var list = data['links'] as List;
    List<Link> links = list.map((i) => Link.fromJson(i)).toList();

    return ContactMeDataModel(description: description, links: links);
  }
}