package com.sparklead.datastore.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class SongDb : RealmObject {

    @PrimaryKey
    val id: String = ""

    val name: String = ""

    val duration: Int = 0

    val previewUrl: String? = null

    val imageUrl: String = ""

    val artist: List<ArtistDb> = realmListOf()
}
