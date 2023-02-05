package com.app.firat.gurgurfirat.util

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

object ReadExt {

    private fun <T> parseArray(json: String, typeToken: Type): List<T> {
        val newJson = json.replace("\n", "")
        val gson = GsonBuilder().create()
        val list = arrayListOf<T>()
        try {
            list.addAll(gson.fromJson(newJson, typeToken))
        } catch (e: Exception) {
            Log.e("error","${e.message}")
        }
        return list
    }


    fun <T> readDataListToList(context: Context, jsonPath: String, type: Type): List<T> {
        var json: String? = assetToString(context, fileName = jsonPath)
        val newList : List<T> = parseArray(
            json = json.toString(),
            typeToken = type
        )
        return newList
    }


    fun assetToString(context: Context,fileName : String): String? {

        return  context.assets.open(fileName).bufferedReader().use{
            it.readText()
        }


    }


}