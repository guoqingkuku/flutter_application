package com.clx.flutter_application.flutter

import com.clx.flutter_application.app.MApplication
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.plugin.common.MethodChannel

object FlutterEngineManager {

    fun createFlutterEngine(engineId: String, parameter: List<String>): FlutterEngine? {
        // 1. 从缓存中获取FlutterEngine
        var engine = FlutterEngineCache.getInstance().get(engineId)
        if (engine == null) {
            // 如果缓存中没有FlutterEngine
            // 1. 新建FlutterEngine，执行的入口函数是entryPoint


            val options = FlutterEngineGroup.Options(MApplication.instance)

            options.dartEntrypointArgs = parameter

            engine = MApplication.engineGroup.createAndRunEngine(options)


            // 2. 存入缓存
            FlutterEngineCache.getInstance().put(engineId, engine)
        }
        return engine
    }

    fun destroyEngine(engineId: String) {
        FlutterEngineCache.getInstance().remove(engineId)
    }

    fun destroyAllEngine() {
        FlutterEngineCache.getInstance().clear()
    }

    // 调用通道方法
    fun invokeMethod(
        engineId: String,
        channelName: String,
        method: String,
        arguments: Any? = null
    ) {
        val engine = FlutterEngineCache.getInstance().get(engineId)
        if (engine != null) {
            val messenger = engine.dartExecutor.binaryMessenger
            val mChannel = MethodChannel(messenger, channelName)
            mChannel.invokeMethod(method, arguments)
        } else {
            println("FlutterEngineCache.getInstance().get(engineId) is null")
        }


    }


}