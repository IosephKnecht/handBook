package com.example.aamezencev.handbook.data

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type

class UriTypeHierarchyAdapter : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Uri? {
        return json?.run { Uri.parse(this.asString) }
    }

    override fun serialize(src: Uri?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.toString())
    }
}