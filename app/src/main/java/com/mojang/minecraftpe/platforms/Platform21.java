package com.mojang.minecraftpe.platforms;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */

@TargetApi(21)
public class Platform21 extends Platform19 {
    public Platform21(boolean initEventHandler) {
        super(initEventHandler);
    }

    public String getABIS() {
        return Build.SUPPORTED_ABIS.toString();
    }
}