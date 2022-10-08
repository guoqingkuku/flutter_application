//调用Android通道方法

import 'package:flutter/services.dart';

class NativeChannelToAndroid {
  static const MethodChannel _channel = MethodChannel("flutter/to/android");

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> finish() async {
    await _channel.invokeMethod('finish');
  }
}
