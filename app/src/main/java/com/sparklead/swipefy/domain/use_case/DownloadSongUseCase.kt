package com.sparklead.swipefy.domain.use_case

import com.sparklead.core.data.mapper.toSongDb
import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.domain.repository.SongListRepository
import javax.inject.Inject

class DownloadSongUseCase @Inject constructor(private val songListRepository: SongListRepository) {

    suspend operator fun invoke(song: Song) {
        songListRepository.downloadSong(song.toSongDb())
    }

}