package com.example.nodeapp.data.helpers

import com.example.nodeapp.data.database.entities.NodeEntity
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object NodeHelper {
    fun generateNodeName(parentNode: NodeEntity?, nextId: Int): String {
        val nodeData = "${parentNode?.name?:""}${nextId}${nextId.hashCode()}"
        val bytes = nodeData.toByteArray(StandardCharsets.UTF_8)
        val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
        val last20Bytes = digest.takeLast(20).toByteArray()
        return bytesToHex(last20Bytes)
    }
    private fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString(separator = "") { byte -> "%02x".format(byte) }
    }
}
