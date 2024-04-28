class Link {
  final String type;
  final String link;

  Link({required this.type, required this.link});
}

class ContactMeDataModel {
  final String description;
  final List<Link> links;

  ContactMeDataModel({required this.description, required this.links});
}

// Till a better solution is found, this data will remain hardcoded here
class ContactMeDataBuilder {
  static ContactMeDataModel getModel() {
    return ContactMeDataModel(
      description: 'You can find / read about me in the following places:',
      links: [
        Link(type: 'gm', link: 'mailto:loukwn@gmail.com'),
        Link(type: 'li', link: 'https://www.linkedin.com/in/klountzis'),
        Link(type: 'gh', link: 'https://github.com/loukwn'),
        Link(type: 'me', link: 'https://medium.com/@loukwn')

      ],
    );
  }
}