package hello.message

import java.security.MessageDigest

object MessageHolder {

    val messageMap: MutableMap<String, String> = HashMap()


    fun putMessage(message: String): String {
        val hash = getHashString(message)
        messageMap[hash] = message
        return hash
    }

    fun getMessage(hash: String): String? {
        return messageMap[hash]
    }

    private fun getHashString(message: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
                .getInstance("SHA-256")
                .digest(message.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }
}