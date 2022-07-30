import 'package:flutter/material.dart';
import 'package:mybio/screens/common/BioAppConfiguration.dart';

/// This widget represents an "app" that a user clicks on my "device". It is
/// built in a way that is reusable and can be configured (items, animations,
/// contents etc) by supplying different AppConfigurations to it

class BioAppScaffold extends StatefulWidget {
  final PortfolioAppConfiguration config;
  final List<Widget> listItems;
  final Function onPop;

  BioAppScaffold({
    Key? key,
    required this.config,
    required this.listItems,
    required this.onPop,
  }) : super(key: key);

  @override
  _BioAppScaffoldState createState() => _BioAppScaffoldState();
}

class _BioAppScaffoldState extends State<BioAppScaffold> {
  // Key to scroll state so that items can be added/animated with a delay
  final GlobalKey<SliverAnimatedListState> _listKey =
      GlobalKey<SliverAnimatedListState>();

  // Scroll controller tracks the scrolling position so that the avatar is animated
  late ScrollController _scrollController;

  // Data structure that helps operate on both local list and animated one
  late _ListModel<Widget> _list;

  @override
  void initState() {
    super.initState();

    _scrollController = new ScrollController();
    _scrollController.addListener(() => setState(() {}));

    _list = _ListModel<Widget>(
      listKey: _listKey,
      initialItems: <Widget>[],
    );
    _loadItems();
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  // Loads the widgets that were provided as arguments but with a delay since we
  // want them animated (SliverAnimatedList can only animate insertions/deletions)
  void _loadItems() {
    final fetchedList = widget.listItems;

    var future = Future(() {});
    for (var i = 0; i < fetchedList.length; i++) {
      future = future.then((_) {
        return Future.delayed(
            Duration(milliseconds: widget.config.itemAnimationDelayDuration),
            () {
          _list.insert(_list.length, fetchedList[i]);
        });
      });
    }
  }

  // Used to build list items that haven't been removed.
  Widget _buildItem(
      BuildContext context, int index, Animation<double> animation) {
    // Setup item animation
    var beginOffset = widget.config.itemAnimationType == AnimationType.VERTICAL
        ? Offset(0, 1)
        : Offset(1, 0);

    return SlideTransition(
        position: CurvedAnimation(
          curve: Curves.fastLinearToSlowEaseIn,
          parent: animation,
        ).drive((Tween<Offset>(
          begin: beginOffset,
          end: Offset(0, 0),
        ))),
        child: _list[index]);
  }

  /// Builds avatar and keeps track of its position, based on the [_scrollController]
  Widget _buildIcon() {
    double top = 118.0; //default top margin, -4 for exact alignment
    if (_scrollController.hasClients) {
      top -= _scrollController.offset;
    }
    if (top < 22) top = 22;
    return new Positioned(
      top: top,
      right: 16.0,
      child: Hero(
          tag: 'avatar_${widget.config.id}',
          child: Card(
            child: Image.asset(
              widget.config.avatarImagePath,
              width: 56,
              height: 56,
            ),
            elevation: 4,
            shape: CircleBorder(),
            clipBehavior: Clip.antiAlias,
          )),
    );
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () {
        widget.onPop();
        return new Future(() => true);
      },
      child: new Stack(
        children: <Widget>[
          Scaffold(
            body: CustomScrollView(
              controller: _scrollController,
              slivers: <Widget>[
                SliverAppBar(
                  expandedHeight: 150.0,
                  pinned: true,
                  backgroundColor: widget.config.appBarBackgroundColor,
                  flexibleSpace: new FlexibleSpaceBar(
                    title: Container(
                      color: widget.config.appBarBackgroundColor.withAlpha(230),
                      padding: EdgeInsets.fromLTRB(10, 0, 10, 0),
                      child: new Text(
                        widget.config.title,
                        style: TextStyle(
                          fontFamily: 'Oswald',
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                    ),
                    background: Image.asset(
                      widget.config.backgroundImagePath,
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                SliverAnimatedList(
                  key: _listKey,
                  initialItemCount: _list.length,
                  itemBuilder: _buildItem,
                ),
              ],
            ),
          ),
          _buildIcon()
        ],
      ),
    );
  }
}

/// Keeps a Dart [List] in sync with an [AnimatedList].
class _ListModel<E> {
  _ListModel({
    required this.listKey,
    required Iterable<E> initialItems,
  })  : _items = List<E>.from(initialItems);

  final GlobalKey<SliverAnimatedListState> listKey;
  final List<E> _items;

  SliverAnimatedListState? get _animatedList => listKey.currentState;

  void insert(int index, E item) {
    _items.insert(index, item);
    _animatedList?.insertItem(index);
  }

  int get length => _items.length;

  E operator [](int index) => _items[index];

  int indexOf(E item) => _items.indexOf(item);
}
