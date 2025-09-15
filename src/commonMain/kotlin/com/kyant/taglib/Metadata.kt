package com.kyant.taglib

/**
 * Metadata contains audio properties, property map and pictures of an audio file.
 */
public class Metadata(public val propertyMap: PropertyMap, public val pictures: Array<Picture>) {
    override fun toString(): String = "Metadata(propertyMap=${
        propertyMap.mapValues { it.value.contentToString() }
    }, pictures=${pictures.contentToString()})"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Metadata) return false

        if (propertyMap != other.propertyMap) return false

        return pictures.contentEquals(other.pictures)
    }

    override fun hashCode(): Int {
        var result = propertyMap.hashCode()
        result = 31 * result + pictures.contentHashCode()
        return result
    }
}
