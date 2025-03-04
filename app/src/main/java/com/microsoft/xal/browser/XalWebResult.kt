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
package com.microsoft.xal.browser

/**
 * @author Тимашков Иван
 * @author https://github.com/TimScriptov
 */
object XalWebResult {
    @JvmField
    val mWebResult: IntArray = IntArray(WebResult.values().size)

    init {
        mWebResult[WebResult.SUCCESS.ordinal] = 1
        mWebResult[WebResult.CANCEL.ordinal] = 2
        try {
            mWebResult[WebResult.FAIL.ordinal] = 3
        } catch (e: NoSuchFieldError) {
            e.printStackTrace()
        }
    }
}