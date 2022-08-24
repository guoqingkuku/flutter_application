package com.clx.flutter_application.app

import android.app.Application
import io.flutter.embedding.engine.FlutterEngineGroup

class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化
        instance = this
        engineGroup = FlutterEngineGroup(this)
    }

    companion object {
        lateinit var instance: MApplication

        lateinit var engineGroup: FlutterEngineGroup
    }


}