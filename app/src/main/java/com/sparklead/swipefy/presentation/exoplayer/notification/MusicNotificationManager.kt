package com.sparklead.swipefy.presentation.exoplayer.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.ui.PlayerNotificationManager
import com.sparklead.swipefy.R
import com.sparklead.swipefy.common.Constants.NOTIFICATION_CHANNEL_NAME
import com.sparklead.swipefy.common.Constants.NOW_PLAYING_CHANNEL_ID
import com.sparklead.swipefy.common.Constants.NOW_PLAYING_NOTIFICATION_ID
import dagger.hilt.android.qualifiers.ApplicationContext

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class MusicNotificationManager(
    @ApplicationContext private val context: Context,
    private val player: ExoPlayer
) {

    private var notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    fun startNotificationService(
        mediaSessionService: MediaSessionService,
        mediaSession: MediaSession
    ) {
        buildNotification(mediaSession)
        startForegroundNotification(mediaSessionService)
    }

    private fun buildNotification(mediaSession: MediaSession) {
        PlayerNotificationManager.Builder(context, NOW_PLAYING_NOTIFICATION_ID, NOW_PLAYING_CHANNEL_ID)
            .setMediaDescriptionAdapter(
                MusicNotificationAdapter(
                    context = context,
                    pendingIntent = mediaSession.sessionActivity
                )
            )
            .setSmallIconResourceId(R.drawable.ic_song)
            .build()
            .also {
                it.setMediaSessionToken(mediaSession.sessionCompatToken)
                it.setUseNextActionInCompactView(true)
                it.setUsePreviousActionInCompactView(true)
                it.setPriority(NotificationCompat.PRIORITY_LOW)
                it.setPlayer(player)
            }
    }

    private fun startForegroundNotification(mediaSessionService: MediaSessionService) {
        val notification = Notification.Builder(context, NOW_PLAYING_CHANNEL_ID)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        mediaSessionService.startForeground(NOW_PLAYING_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOW_PLAYING_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}