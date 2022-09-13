package com.clx.flutter_application.flutter

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class SingleFlutterActivity:FlutterActivity() {


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        val channel = MethodChannel(flutterEngine.dartExecutor, CHANNEL)
        channel.setMethodCallHandler { call, result ->
            if (call.method == "finish") {
                //关闭flutter页面
                finish()
            } else {
                result.notImplemented()
            }
        }

    }

    companion object {
        const val CHANNEL = "flutter/to/android"
        // 缓存引擎builder
        fun  withCachedEngine(engineId: String): CachedEngineIntentBuilder {
            return CachedEngineIntentBuilder(SingleFlutterActivity::class.java, engineId)
        }


    }


}
