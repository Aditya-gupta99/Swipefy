package com.sparklead.swipefy.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sparklead.core.data.mapper.toSong
import com.sparklead.core.data.model.Song
import com.sparklead.swipefy.data.service.SpotifyService

class SongPagingSource(private val service: SpotifyService) :
    PagingSource<Int, Song>() {

    override fun getRefreshKey(state: PagingState<Int, Song>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                position
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        return try {
            val position = params.key ?: 0
            val songs = service.getRecommendedSongList(
                listOf(),
                listOf()
            ).tracks.map { it.toSong() }
            LoadResult.Page(
                data = songs,
                prevKey = if (position <= 0) null else position - 1,
                nextKey = if (position >= 10) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}