package com.sparklead.core.data.model_db

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class SongDb : RealmObject {

    @PrimaryKey
    var _id: String = ""

    var name: String = ""

    var duration: Int = 0

    var previewUrl: String? = null

    var imageUrl: String = ""

    var artist: RealmList<ArtistDb> = realmListOf()
}