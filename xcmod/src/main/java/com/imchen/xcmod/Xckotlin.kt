package com.imchen.xcmod

import android.content.Context
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage


/**
 * Created by imchen on 2017/11/8.
 */

class Xckotlin : IXposedHookLoadPackage {

    internal lateinit var applicationContext: Context
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {


        if (lpparam!!.packageName == "com.qihoo.browser") {

            val insCls = XposedHelpers.findClassIfExists("android.app.Instrumentation", lpparam.classLoader)
//            val insCls = XposedHelpers.findClass("android.app.Instrumentation",lpparam.classLoader)
            findAndHookMethod(insCls, "execStartActivity", object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                    // execStartActivity( Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options)
                    var context = param!!.args[0]
                    var iBinder = param.args[1]
                    var token = param.args[2]
                    var target = param.args[3]
                    var intent = param.args[4]
                    var requestCode = param.args[5]
                    var options = param.args[6]
                    XposedBridge.log("context" + context.toString() + " " + iBinder.toString() + " " + token.toString()
                            + " " + target.toString() + " " + intent.toString() + " " + requestCode + " " + options.toString())
                }
            })


            val clz = XposedHelpers.findClass("android.content.ContextWrapper", lpparam.classLoader)
            findAndHookMethod(clz, "getApplicationContext", object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    if (param != null) {
                        val obj = param.result
                        applicationContext = obj as Context
//                        val handler = Handler(Looper.getMainLooper())
//                        handler.post({
//                            Toast.makeText(applicationContext, "hello world", Toast.LENGTH_SHORT)
//                        })

                    }
                }
            })
//            var cls = Context::class.java.classLoader.loadClass("android.content.ContextWrapper")
//            val construct = XposedHelpers.findConstructorBestMatch(cls, Context::class.java)
//            val field = XposedHelpers.findField(construct.newInstance(), "mBase")
//            val obj = field.get(cls)
        }

    }


}