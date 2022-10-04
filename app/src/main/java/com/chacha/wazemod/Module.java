package com.chacha.wazemod;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Module implements IXposedHookLoadPackage {
    private static final String WAZE_PCKG_NAME = "com.waze";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {

        if (lpparam.packageName.equals(WAZE_PCKG_NAME)) { //Get all icons from Waze
            findAndHookMethod(WAZE_PCKG_NAME + ".MoodManager", lpparam.classLoader,
                    "canSetMood", android.content.Context.class, java.lang.String.class, XC_MethodReplacement.returnConstant(true));

            findAndHookMethod(WAZE_PCKG_NAME + ".MoodManager", lpparam.classLoader,
                    "isBaby", XC_MethodReplacement.returnConstant(false));
        }
    }
}