package com.kyant.taglib

import kotlin.jvm.JvmStatic

/**
 * An object that provides access to the native TagLib library.
 */
public expect object TagLib {
    /**
     * Get audio properties from file descriptor.
     *
     * @param fd File descriptor
     * @param readStyle Read style for audio properties to balance speed and accuracy
     */
    @JvmStatic
    public fun getAudioProperties(
        fd: Int,
        readStyle: AudioPropertiesReadStyle = AudioPropertiesReadStyle.Average,
    ): AudioProperties?

    /**
     * Get metadata from file descriptor.
     *
     * @param fd File descriptor
     * @param readPictures Whether to read pictures
     */
    @JvmStatic
    public fun getMetadata(fd: Int, readPictures: Boolean = true): Metadata?

    /**
     * Get metadata property values from file descriptor.
     *
     * @param fd File descriptor
     * @param propertyName Property name
     */
    @JvmStatic
    public fun getMetadataPropertyValues(fd: Int, propertyName: String): Array<String>?

    /**
     * Get pictures from file descriptor. There may be multiple pictures with different types.
     */
    @JvmStatic
    public fun getPictures(fd: Int): Array<Picture>

    /**
     * Get front cover from file descriptor.
     */
    @JvmStatic
    public fun getFrontCover(fd: Int): Picture?

    /**
     * Save metadata by file descriptor.
     *
     * @param fd File descriptor
     * @param propertyMap Property map to save
     *
     * @return Whether the operation was successful
     */
    @JvmStatic
    public fun savePropertyMap(fd: Int, propertyMap: PropertyMap): Boolean

    /**
     * Save pictures by file descriptor.
     *
     * @param fd File descriptor
     * @param pictures Pictures to save
     *
     * @return Whether the operation was successful
     */
    @JvmStatic
    public fun savePictures(fd: Int, pictures: Array<Picture>): Boolean
}
