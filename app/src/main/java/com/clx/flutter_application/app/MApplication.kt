package com.clx.flutter_application.app

import android.app.Application
import com.clx.flutter_application.flutter.FlutterEngineManager
import io.flutter.embedding.engine.FlutterEngineGroup

class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化
        instance = this
        engineGroup = FlutterEngineGroup(this)
    }


    //退出应用时调用
    override fun onTerminate() {
        super.onTerminate()
        FlutterEngineManager.destroyAllEngine()
    }

    companion object {
        lateinit var instance: MApplication

        lateinit var engineGroup: FlutterEngineGroup
    }


}