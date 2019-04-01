package hello.message

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MessageController {

    @PostMapping("/messages")
    fun putMessage(@RequestBody message: String): String {
        val hash = getHashString(message)
        MyJedis.putValue(hash, message)
        return hash
    }

    @GetMapping("/messages/{hash}")
    fun getMessage(@PathVariable(value = "hash") hash: String): String {
        val message = MyJedis.getValue(hash) ?: throw ResourceNotFoundException()
        return message
    }

}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This hash has no corresponding message")
class ResourceNotFoundException : RuntimeException()
