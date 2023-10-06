package com.etisalat.recipes.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration

/**
 * Utility class for Android-specific operations.
 */
class AndroidUtils {

    /**
     * Converts a duration string to a formatted string representation.
     *
     * @param duration The duration string to convert.
     * @return The formatted duration string.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDuration(duration: String): String {
        val parsedDuration = Duration.parse(duration)
        val hours = parsedDuration.toHours()
        val minutes = parsedDuration.toMinutesPart()

        return if (hours == 0.toLong()) {
            String.format("%dm", minutes)
        } else {
            String.format("%dh %02dm", hours, minutes)
        }
    }

}