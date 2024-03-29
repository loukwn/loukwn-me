import 'dart:async';

import 'package:flutter/material.dart';
import 'package:mybio/data/PortfolioDataBuilder.dart';

class JobItemWidget extends StatefulWidget {
  static const double _BULLET_SIZE = 10;
  static const double _BULLET_LEFT_PADDING = 30;
  static const double _BULLET_RIGHT_PADDING = 30;
  static const double _BULLET_TOP_PADDING = 40;

  final GlobalKey _keyBubble = GlobalKey();
  final Job job;
  final bool isFirst;
  final bool isLast;

  JobItemWidget.first({required this.job})
      : this.isFirst = true,
        this.isLast = false;

  JobItemWidget.last({required this.job})
      : this.isLast = true,
        this.isFirst = false;

  JobItemWidget({required this.job})
      : this.isLast = false,
        this.isFirst = false;

  @override
  _JobItemWidgetState createState() => _JobItemWidgetState();
}

class _JobItemWidgetState extends State<JobItemWidget> {
  var heightOfBubble = 0.0;

  Widget _buildDotAndLine(Color dotColor) {
    _LinePainter _linePainter;
    if (widget.isFirst) {
      _linePainter = _LinePainter(JobItemWidget._BULLET_TOP_PADDING, null);
    } else if (widget.isLast) {
      _linePainter = _LinePainter(null, JobItemWidget._BULLET_TOP_PADDING);
    } else {
      _linePainter = _LinePainter(null, null);
    }

    return SizedBox(
      width: (JobItemWidget._BULLET_LEFT_PADDING +
              JobItemWidget._BULLET_RIGHT_PADDING +
              JobItemWidget._BULLET_SIZE)
          .toDouble(),
      height: null,
      child: Padding(
        padding: EdgeInsets.fromLTRB(JobItemWidget._BULLET_LEFT_PADDING, 0,
            JobItemWidget._BULLET_RIGHT_PADDING, 0),
        child: Stack(
          children: [
            SizedBox(
              width: JobItemWidget._BULLET_SIZE,
              height: heightOfBubble,
              child: CustomPaint(
                painter: _linePainter,
              ),
            ),
            Padding(
              padding: EdgeInsets.only(top: JobItemWidget._BULLET_TOP_PADDING),
              child: SizedBox(
                width: JobItemWidget._BULLET_SIZE,
                height: JobItemWidget._BULLET_SIZE,
                child: CustomPaint(painter: _CirclePainter(dotColor)),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildJobBubble(Job job) {
    return Expanded(
      key: widget._keyBubble,
      child: Padding(
        padding: const EdgeInsets.fromLTRB(0, 10, 30, 10),
        child: DecoratedBox(
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.only(
                topLeft: Radius.circular(10),
                topRight: Radius.circular(10),
                bottomLeft: Radius.circular(10),
                bottomRight: Radius.circular(10)),
            boxShadow: [
              BoxShadow(
                color: Colors.grey.withOpacity(0.5),
                spreadRadius: 2,
                blurRadius: 6,
                offset: Offset(5, 5), // changes position of shadow
              ),
            ],
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(16),
                    child: Text(
                      job.title,
                      style:
                          TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(16),
                    child: Text(
                      "@${job.location}",
                      style: TextStyle(
                          fontFamily: "Oswald",
                          color: Colors.grey,
                          fontWeight: FontWeight.w500,
                          fontSize: 15),
                    ),
                  ),
                ],
              ),
              Padding(
                padding: const EdgeInsets.fromLTRB(16, 8, 16, 8),
                child: Text(
                  job.description,
                  style: TextStyle(fontSize: 15),
                ),
              ),
              Align(
                alignment: Alignment.center,
                child: SizedBox(
                  height: 150,
                  child: Image.asset(job.bgImage),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    Timer.run(() => _saveBubbleUpdatedHeightAndRebuild());
  }

  void _saveBubbleUpdatedHeightAndRebuild() {
    final RenderObject? bubbleRenderBox =
        widget._keyBubble.currentContext?.findRenderObject();
    if (bubbleRenderBox != null) {
      setState(() {
        heightOfBubble = (bubbleRenderBox as RenderBox).size.height;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        _buildDotAndLine(Color.fromRGBO(79, 93, 115, 1)),
        _buildJobBubble(widget.job),
      ],
    );
  }
}

class _LinePainter extends CustomPainter {
  double? startY;
  double? endY;

  _LinePainter(this.startY, this.endY);

  final Paint _paint = Paint()
    ..color = Colors.grey.shade300
    ..strokeWidth = 1
    ..style = PaintingStyle.stroke;

  final Path path = Path();

  @override
  void paint(Canvas canvas, Size size) {
    if (startY == null) startY = 0;
    if (endY == null) endY = size.height;

    path.moveTo(size.width / 2, startY!);
    path.lineTo(size.width / 2, endY!);
    canvas.drawPath(path, _paint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) => true;
}

class _CirclePainter extends CustomPainter {
  final Color color;
  final Paint _paint = Paint();

  _CirclePainter(Color dotColor) : this.color = dotColor {
    _paint.color = this.color;
    _paint.style = PaintingStyle.fill;
  }

  @override
  void paint(Canvas canvas, Size size) {
    canvas.drawOval(
      Rect.fromLTWH(0, 0, size.width, size.height),
      _paint,
    );
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) => false;
}
