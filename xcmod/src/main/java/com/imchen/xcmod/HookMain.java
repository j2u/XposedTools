package com.imchen.xcmod;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by imchen on 2017/11/10.
 */

public class HookMain implements IXposedHookLoadPackage {

    public static Context mContext = null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if ("com.qihoo.browser".equals(lpparam.packageName)||"com.tmall.wireless".equals(lpparam.packageName)||"com.taobao.taobao".equals(lpparam.packageName)||
                "com.jingdong.app.mall".equals(lpparam.packageName)) {
//            XposedBridge.log("Hook 360 browser!");
            Class<?> insCls = XposedHelpers.findClass("android.app.Instrumentation", lpparam.classLoader);
            if (mContext == null) {
                initContext();
            }
            String packageName= SuperRunningPackage.getForegroundApp();
            if ("com.qihoo.browser".equals(packageName)||"com.tmall.wireless".equals(packageName)||"com.taobao.taobao".equals(packageName)||
                    "com.jingdong.app.mall".equals(packageName)){
                Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                mContext.startActivity(intent);
            }

            XposedHelpers.findAndHookMethod(insCls, "execStartActivity", Context.class, IBinder.class, IBinder.class
                    , Activity.class, Intent.class, int.class, Bundle.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
//                            Context context = (Context) param.args[0];
//                            IBinder iBinder = (IBinder) param.args[1];
//                            IBinder token = (IBinder) param.args[2];
//                            Activity target = (Activity) param.args[3];
//                            Intent intent = (Intent) param.args[4];
//                            int requestCode = (int) param.args[5];
//                            Bundle options = (Bundle) param.args[6];
//                            XposedBridge.log("context" + context.toString() + " " + iBinder.toString() + " " + token.toString()
//                                    + " " + target.toString() + " " + intent.toString() + " " + requestCode + " " + options + "\n result:");

                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
//                            Class<?> cls = XposedHelpers.findClass("com.android.commands.am.Am", Context.class.getClassLoader());
//                            Method mainMethod = cls.getDeclaredMethod("main", String[].class);
//                            mainMethod.invoke(cls, new String[]{"com.qihoo.browser"});
                            Intent intent = new Intent(Intent.ACTION_MAIN, null);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            mContext.startActivity(intent);
                        }
                    });


        }
    }

    public void initContext() {
        Class<?> cls = null;
        try {
            cls = Context.class.getClassLoader().loadClass("android.content.ContextWrapper");
            XposedHelpers.findAndHookMethod(cls, "getApplicationContext", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    mContext = (Context) param.getResult();
//                    Handler handler = new Handler(Looper.myLooper());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(mContext, "test", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void Alert() {

    }


}
