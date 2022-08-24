package com.clx.flutter_application.flutter

import com.clx.flutter_application.app.MApplication
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

object FlutterEngineManager {

    fun flutterEngine(engineId: String, parameter: List<String>): FlutterEngine? {
        // 1. 从缓存中获取FlutterEngine
        var engine = FlutterEngineCache.getInstance().get(engineId)
        if (engine == null) {
            // 如果缓存中没有FlutterEngine
            // 1. 新建FlutterEngine，执行的入口函数是entryPoint

            engine = MApplication.engineGroup.createAndRunDefaultEngine(MApplication.instance)

            // Start executing Dart code to pre-warm the FlutterEngine.
            engine?.dartExecutor?.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault(), parameter
            )

            // 2. 存入缓存
            FlutterEngineCache.getInstance().put(engineId, engine)
        }
        return engine
    }


}