class AboutThisDataModel {
  final String builtWithSummary;
  final List<UnsplashLink> unsplashLinks;
  final List<String> otherMediaLinks;
  final String logoTrademarksText;

  AboutThisDataModel({
    this.builtWithSummary,
    this.unsplashLinks,
    this.otherMediaLinks,
    this.logoTrademarksText,
  });
}

class UnsplashLink {
  final String artistName;
  final String artistLink;
  final String unsplashLink;

  UnsplashLink({this.artistName, this.artistLink, this.unsplashLink});
}

// Till a better solution is found, this data will remain hardcoded here
class AboutThisDataBuilder {
  static AboutThisDataModel getModel() {
    return AboutThisDataModel(
      builtWithSummary:
          "This is a Flutter app that exists inside a simple HTML/JS static page. There is some basic communication between the two (using messages and universal_html package) so that bgs change when an \"app\" is opened. For more info check out the GitHub repo of the project:",
      unsplashLinks: [
        UnsplashLink(
          artistName: "NEW DATA SERVICES",
          artistLink:
              "https://unsplash.com/@new_data_services?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/s/photos/notes?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        UnsplashLink(
          artistName: "Cynthia Young",
          artistLink:
              "https://unsplash.com/@cynthiayoung?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/@cynthiayoung?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        UnsplashLink(
          artistName: "Kilian Seiler",
          artistLink:
              "https://unsplash.com/@kilianfoto?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/@kilianfoto?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        UnsplashLink(
          artistName: "Mathyas Kurmann",
          artistLink:
              "https://unsplash.com/@mathyaskurmann?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/s/visual/6950cac1-76d6-4c60-9b00-5cb96fb48dcc?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        UnsplashLink(
          artistName: "Sean Lim",
          artistLink:
              "https://unsplash.com/@seanlimm?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/s/visual/e076f4a1-6404-4ea7-949c-8cd55f39fc63?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        UnsplashLink(
          artistName: "Max van den Oetelaar",
          artistLink:
              "https://unsplash.com/@maxvdo?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/s/visual/2672bb5c-a940-475e-b35c-59a5b12deef6?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        UnsplashLink(
          artistName: "Brian Patrick Tagalog",
          artistLink:
              "https://unsplash.com/@briantagalog?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/s/visual/509ee80a-8273-4541-bc2d-8e0ed4a688a1?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        UnsplashLink(
          artistName: "Daniel Romero",
          artistLink:
              "https://unsplash.com/@rmrdnl?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
          unsplashLink:
              "https://unsplash.com/s/photos/android?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
      ],
      otherMediaLinks: [
        "https://pngtree.com/freepng/islamic-muslim-couple-character-fallin-in-love-in-flat-illustration_5320294.html",
        "https://undraw.co/"
      ],
      logoTrademarksText:
          "Logos of Spotify, GitHub, Medium and LinkedIn used are licensed trademarks of their respective owners. I do not own any of these.",
    );
  }
}
