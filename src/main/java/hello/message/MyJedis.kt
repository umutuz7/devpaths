import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import java.net.URI

object MyJedis {

    fun getPool(): JedisPool {
        val redisURI = URI(System.getenv("REDIS_URL"))
        val poolConfig = JedisPoolConfig()
        poolConfig.maxTotal = 10
        poolConfig.maxIdle = 5
        poolConfig.minIdle = 1
        poolConfig.testOnBorrow = true
        poolConfig.testOnReturn = true
        poolConfig.testWhileIdle = true
        val pool = JedisPool(poolConfig, redisURI)
        return pool
    }

    fun putValue(key: String, value: String) {
        try {
            val jedis = getPool().resource
            jedis.set(key, value)
        } catch (e: Exception) {

        }
    }

    fun getValue(key: String): String? {
        try {
            val jedis = getPool().resource
            return jedis.get(key)
        } catch (e: Exception) {

        }
        return null
    }
}
