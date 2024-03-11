package com.sparklead.swipefy.domain.use_case

import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.domain.repository.DownloadSongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDownloadSongUseCase @Inject constructor(private val downloadSongRepository: DownloadSongRepository) {

    operator fun invoke(): Flow<List<Song>> {
        return downloadSongRepository.getAllDownloadSongs()
    }
}