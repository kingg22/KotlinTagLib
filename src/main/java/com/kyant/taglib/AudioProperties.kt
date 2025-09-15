package com.kyant.taglib

/**
 * AudioProperties contains information about the audio properties of a file.
 *
 * @property length Length in milliseconds
 * @property bitrate Bitrate in kbps
 * @property sampleRate Sample rate in Hz
 * @property channels Number of channels
 */
public class AudioProperties(
    public val length: Int,
    public val bitrate: Int,
    public val sampleRate: Int,
    public val channels: Int,
)
