/*
 * Copyright (C) 2018-2021 Тимашков Иван
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
package com.mcal.mcpelauncher.data

import com.mcal.mcpelauncher.BuildConfig

/**
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */
object Constants {
    @JvmField
    var bannerAdId =
        if (BuildConfig.DEBUG) "ca-app-pub-3940256099942544/6300978111" else "ca-app-pub-1411495427741055/8160854950"

    @JvmField
    var INTERESTIAL_AD_ID =
        if (BuildConfig.DEBUG) "ca-app-pub-3940256099942544/1033173712" else "ca-app-pub-1411495427741055/5778606290"
    const val STRING_VALUE_DEFAULT = "default"
    const val NMOD_DATA_TAG = "nmod_data"
    const val NMOD_API_VERSION = "1.4.1"
}