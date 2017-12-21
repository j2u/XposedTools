package com.imchen.hookaj

import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * Created by im on 17/12/20.
 */
class HookMain : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        //按键精灵
        if ("com.cyjh.mobileanjian".equals(lpparam!!.packageName)){
            XposedHelpers.findAndHookMethod("com.cyjh.mqm.MQCompiler",lpparam!!.classLoader,"compile",
                    String::class.java,String::class.java,String::class.java,String::class.java,object : XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
//                    super.afterHookedMethod(param)
//                    XposedBridge.log(param!!.args[0].toString())
                    for (i in param!!.args.indices) {
                        Log.d("xposed_hook_anjian:","compile args:"+i+"->"+param.args[i].toString())
                    }

                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                }
            })

            XposedHelpers.findAndHookMethod("com.goldcoast.sdk.domain.EntryPoint",lpparam.classLoader,
                    "exec",Array<String>::class.java,object : XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian","exec result->>"+param!!.result)
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    for (i in param!!.args.indices){
                        Log.d("xposed_hook_anjian","execParam"+i+" ->"+param!!.args[i])
                    }
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog",lpparam.classLoader,
                    "i",String::class.java,String::class.java,object :XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian","Clog.i->"+param!!.args[1])
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog",lpparam.classLoader,
                    "v",String::class.java,String::class.java,object :XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian","Clog.v->"+param!!.args[1])
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog",lpparam.classLoader,
                    "e",String::class.java,String::class.java,object :XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian","Clog.e->"+param!!.args[1])
                }
            })

            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog",lpparam.classLoader,
                    "w",String::class.java,String::class.java,object :XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian","Clog.w->"+param!!.args[1])
                }
            })
            XposedHelpers.findAndHookMethod("com.cyjh.mobileanjian.ipc.utils.CLog",lpparam.classLoader,
                    "d",String::class.java,String::class.java,object :XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                }

                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.d("xposed_hook_anjian","Clog.d->"+param!!.args[1])
                }
            })
        }
    }
}