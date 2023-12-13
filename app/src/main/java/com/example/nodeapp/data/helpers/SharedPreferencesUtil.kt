package com.example.nodeapp.data.helpers

import android.content.Context

object SharedPreferencesUtil {
    fun setSharedData(context: Context, key: String, value: Any) {
        val sharedPref = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
        }
        editor.apply()
    }
    fun getSharedIntData(context: Context, key: String, default: Int = 0): Int {
        val sharedPref = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        return sharedPref.getInt(key, default)
    }
}
