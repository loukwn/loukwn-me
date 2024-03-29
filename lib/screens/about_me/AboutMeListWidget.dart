import 'package:flutter/material.dart';

class AboutMeListWidget extends StatefulWidget {
  final List<AboutMeListItemModel> _items;

  AboutMeListWidget(this._items);

  @override
  State<StatefulWidget> createState() => _AboutMeListWidgetState();
}

class _AboutMeListWidgetState extends State<AboutMeListWidget> {
  late List<bool> _expandedItems;

  @override
  void initState() {
    super.initState();
    _expandedItems = widget._items.map((e) => false).toList();
    _expandedItems[0] = true;
  }

  @override
  Widget build(BuildContext context) {
    return Column(
        children: widget._items
            .mapWithIndex((item, index) => _itemMapper(item, index))
            .toList());
  }

  _AboutMeListItemWidget _itemMapper(AboutMeListItemModel item, int index) {
    return _AboutMeListItemWidget(
      item: item,
      expanded: _expandedItems[index],
      onTap: () => {
        setState(() => {_expandedItems[index] = !_expandedItems[index]})
      },
    );
  }
}

class _AboutMeListItemWidget extends StatefulWidget {
  final AboutMeListItemModel item;
  final bool expanded;
  final Function onTap;

  _AboutMeListItemWidget({
    required this.item,
    required this.expanded,
    required this.onTap,
  });

  @override
  State<StatefulWidget> createState() => _AboutMeListItemWidgetState();
}

class _AboutMeListItemWidgetState extends State<_AboutMeListItemWidget>
    with SingleTickerProviderStateMixin {
  late AnimationController _expandController;
  late Animation<double> _animation;

  @override
  void initState() {
    super.initState();
    _prepareAnimations();
    _runExpandCheck();
  }

  void _prepareAnimations() {
    _expandController =
        AnimationController(vsync: this, duration: Duration(milliseconds: 200));
    _animation = CurvedAnimation(
      parent: _expandController,
      curve: Curves.fastOutSlowIn,
    );
  }

  void _runExpandCheck() {
    if (widget.expanded) {
      _expandController.forward();
    } else {
      _expandController.reverse();
    }
  }

  @override
  void didUpdateWidget(_AboutMeListItemWidget oldWidget) {
    super.didUpdateWidget(oldWidget);
    _runExpandCheck();
  }

  @override
  void dispose() {
    _expandController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: EdgeInsets.fromLTRB(18, 0, 18, 0),
        child: Column(children: [
          // Title
          Material(
              color: Color.fromRGBO(79, 93, 115, 1),
              child: InkWell(
                onTap: () => { widget.onTap() },
                child: Container(
                    width: double.infinity,
                    padding: EdgeInsets.all(8),
                    child: Text(
                      widget.item.title,
                      style: TextStyle(
                          fontFamily: 'Oswald',
                          fontWeight: FontWeight.w500,
                          color: widget.item.textColor,
                          fontSize: 18),
                    )),
              )),
          SizeTransition(
            sizeFactor: _animation,
            child: widget.item.content,
          )
        ]));
  }
}

class AboutMeListItemModel {
  final String title;
  final Widget content;
  final Color textColor;

  AboutMeListItemModel({
    required this.title,
    required this.content,
    required this.textColor,
  });
}

extension MapWithIndex<T> on List<T> {
  List<R> mapWithIndex<R>(R Function(T, int i) callback) {
    List<R> result = [];
    for (int i = 0; i < this.length; i++) {
      R item = callback(this[i], i);
      result.add(item);
    }
    return result;
  }
}
