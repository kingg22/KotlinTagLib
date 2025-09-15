package com.kyant.taglib

public actual object TagLib {
    @JvmStatic
    private external fun getAudioProperties(fd: Int, readStyle: Int): AudioProperties?

    @JvmStatic
    public actual fun getAudioProperties(fd: Int, readStyle: AudioPropertiesReadStyle): AudioProperties? =
        getAudioProperties(fd, readStyle.ordinal)

    @JvmStatic
    public actual external fun getMetadata(fd: Int, readPictures: Boolean): Metadata?

    @JvmStatic
    public actual external fun getMetadataPropertyValues(fd: Int, propertyName: String): Array<String>?

    @JvmStatic
    public actual external fun getPictures(fd: Int): Array<Picture>

    @JvmStatic
    public actual fun getFrontCover(fd: Int): Picture? {
        val pictures = getPictures(fd)
        return pictures.find { picture -> picture.pictureType == "Front Cover" }
            ?: pictures.firstOrNull()
    }

    @JvmStatic
    public actual external fun savePropertyMap(fd: Int, propertyMap: PropertyMap): Boolean

    @JvmStatic
    public actual external fun savePictures(fd: Int, pictures: Array<Picture>): Boolean

    init {
        System.loadLibrary("taglib")
    }
}
