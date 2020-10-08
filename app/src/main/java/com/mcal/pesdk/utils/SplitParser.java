/*
 * Copyright (C) 2018-2020 Тимашков Иван
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.mcal.pesdk.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.mcal.mcpelauncher.data.Preferences;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 * @author Vologhat
 */
public class SplitParser {
    private static String[] minecraftLibs = new String[]{"libminecraftpe.so", "libc++_shared.so", "libfmod.so"};

    public static void parse(@NotNull Context mContext) {
        File lib = new File(mContext.getCacheDir().getPath() + "/lib");
        if (!lib.exists()) {
            lib.mkdir();
        }

        File arm64 = new File(lib + "/" + Build.CPU_ABI);
        if (!arm64.exists()) {
            arm64.mkdir();
        }

        try {
            if(SplitParser.isBundle(mContext)) {
                if (mcpe(mContext) != null) {
                    String split_path = Arrays.asList(mcpe(mContext).splitPublicSourceDirs).get(0);
                    byte[] buffer = new byte[2048];
                    for (String so : minecraftLibs) {
                        InputStream is = new ZipFile(split_path).getInputStream(new ZipEntry("lib/" + Build.CPU_ABI + "/" + so));
                        FileOutputStream fos = new FileOutputStream(arm64 + "/" + so);
                        do {
                            int numread = is.read(buffer);
                            if (numread <= 0) {
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        } while (true);
                        fos.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Contract(pure = true)
    public static boolean isBundle(@NotNull Context mContext) {
        return mcpe(mContext).splitPublicSourceDirs.length != 0;
    }

    private static ApplicationInfo mcpe(@NotNull Context mContext) {
        ApplicationInfo mcpe = null;
        try {
            mcpe = mContext.getPackageManager().getPackageInfo(Preferences.getMinecraftPEPackageName(), 0).applicationInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return mcpe;
    }
}