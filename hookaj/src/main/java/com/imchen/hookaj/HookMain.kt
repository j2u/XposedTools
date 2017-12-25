package com.imchen.hookaj

import android.app.AndroidAppHelper
import android.app.Instrumentation
import android.content.Context
import android.net.LocalSocket
import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

/**
 * Created by im on 17/12/20.
 */
class HookMain : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        val TAG = "xposed_hook_anjian"
        //按键精灵
        if ("com.cyjh.mobileanjian".equals(lpparam!!.packageName)) {
            XposedHelpers.findAndHookMethod("com.cyjh.mqm.MQCompiler", lpparam!!.classLoader, "compile",
                    String::class.java, String::class.java, String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
//                    super.afterHookedMethod(param)
//                    XposedBridge.log(param!!.args[0].toString())
                    for (i in param!!.args.indices) {
                        Log.d("xposed_hook_anjian:", "compile args:" + i + "->" + param.args[i].toString())
                    }

                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                }
            })

            XposedHelpers.findAndHookMethod("com.goldcoast.sdk.domain.EntryPoint", lpparam.classLoader,
                    "exec", Array<String>::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "exec result->>" + param!!.result)
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    for (i in param!!.args.indices) {
                        Log.d("xposed_hook_anjian", "execParam" + i + " ->" + param!!.args[i])
                    }
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog", lpparam.classLoader,
                    "i", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog.i->" + param!!.args[1])
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog", lpparam.classLoader,
                    "v", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog.v->" + param!!.args[1])
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog", lpparam.classLoader,
                    "e", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog.e->" + param!!.args[1])
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog", lpparam.classLoader,
                    "w", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog.w->" + param!!.args[1])
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog", lpparam.classLoader,
                    "d", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog.d->" + param!!.args[1])
                }
            })
            //second log

//            var field:Field=XposedHelpers.findField(Class.forName("com.cyjh.mobileanjian.ipc.engine.utils.CLog"),"a")
//            field.setBoolean(lpparam.classLoader,true)
            val clz = XposedHelpers.findClass("com.cyjh.mobileanjian.ipc.engine.utils.CLog", lpparam.classLoader)
            XposedHelpers.setStaticBooleanField(clz, "a", true)

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.engine.utils.CLog", lpparam.classLoader,
                    "v", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog_IPC_ROOT.v->" + param!!.args[1])
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.engine.utils.CLog", lpparam.classLoader,
                    "e", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog_IPC_ROOT.e->" + param!!.args[1])
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.engine.utils.CLog", lpparam.classLoader,
                    "w", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog_IPC_ROOT.w->" + param!!.args[1])
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.engine.utils.CLog", lpparam.classLoader,
                    "d", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog_IPC_ROOT.d->" + param!!.args[1])
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.engine.utils.CLog", lpparam.classLoader,
                    "i", String::class.java, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian", "CLog_IPC_ROOT.i->" + param!!.args[1])
                }
            })

            XposedHelpers.findAndHookConstructor("com.cyjh.mobileanjian.ipc.b", lpparam.classLoader,
                    LocalSocket::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    val localSocket: LocalSocket = param!!.args[0] as LocalSocket
                    Log.d("xposed_hook_anjian", "localsocket address->" + localSocket.localSocketAddress)
//                    Log.d("xposed_hook_anjian","remotesocket address->"+localSocket.remoteSocketAddress)
//                    throw Exception("imchen exception")
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mqm.MQLanguageStub", lpparam.classLoader,
                    "Run", String::class.java, String::class.java, String::class.java,
                    Int::class.java, Int::class.java, Long::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    for (i in param!!.args.indices) {
                        Log.d(TAG, "native rRunS args-" + i + " " + param.args[i])
                    }
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mqm.MQLanguageStub", lpparam.classLoader,
                    "Run", ByteArray::class.java, String::class.java, String::class.java,
                    Int::class.java, Int::class.java, Long::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    for (i in param!!.args.indices) {
                        Log.d(TAG, "native RunB args-" + i + " " + param.args[i])
                    }
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.utils.LogUtils", lpparam.classLoader, "isDebug",
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam?) {
                            param!!.result = true
                        }
                    })

            val clzScript4Run = XposedHelpers.findClass("com.cyjh.mobileanjian.ipc.stuff.Script4Run", lpparam.classLoader)
            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.MqRunner", lpparam.classLoader,
                    "setScript", clzScript4Run, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    Log.d(TAG, "Script4RunObj->" + param!!.args[0].toString())
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.activity.MainActivity", lpparam.classLoader,
                    "onCreate", object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    var args0 = "/storage/emulated/0/MobileAnJian/MQTemp"
                    var args1 = readscript("/storage/emulated/0/MobileAnJian/Record/我的录制1(f3f7734f-7f5a-40a8-bf4a-cc3d5bb3b84d).mq")
                    var args2 = null
                    var args3 = "/storage/emulated/0/MobileAnJian/MQTemp/compile.lc"

                }
            })

        }
    }

    //exec script file
    private fun startRunRecordScript(lpparam: XC_LoadPackage.LoadPackageParam,
                                     tempPath: String, scriptContent: String, nullStr: String,
                                     lcPath: String) {
        val ssm = "com.cyjh.mobileanjian.view.floatview.suplus.StartScriptManager"
        val sdm="com.cyjh.mobileanjian.view.floatview.help.ScripDateManager"
        val scriptCLz="com.cyjh.mobileanjian.view.floatview.model"

        val ssmClz = XposedHelpers.findClass(ssm, lpparam.classLoader)
        val smClz=XposedHelpers.findClass(sdm,lpparam.classLoader)
        val smInstance=XposedHelpers.callStaticMethod(smClz,"getInstance")
        XposedHelpers.callMethod(smInstance,"setScript",scriptCLz)
        val context = AndroidAppHelper.currentApplication().applicationContext
//        val method = XposedHelpers.findMethodBestMatch(clz,
//                "startRunRecordScript", Context::class.java)

        val obj = XposedHelpers.callStaticMethod(ssmClz, "getInstance")
        XposedHelpers.callMethod(obj, "startRunRecordScript",context )
    }

    private fun readscript(path: String): String {
        var file = File(path)
        if (file.exists()) {
            var line: String? = null
            var sbf = StringBuffer()
            var frd = FileReader(file)
            var bufr = BufferedReader(frd)
            while (bufr.readLine().apply { line = this } != null) {
                sbf.append(line + "\n")
            }
            return sbf.toString()
        }
        return ""
    }
}