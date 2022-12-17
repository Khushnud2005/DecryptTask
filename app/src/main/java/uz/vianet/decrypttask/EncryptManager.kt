package uz.vianet.decrypttask

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
@RequiresApi(Build.VERSION_CODES.O)
object EncryptManager {

    private var secretKey = "itworksdonttouchit"
    private var secretKeySpec: SecretKeySpec? = null
    lateinit var key:ByteArray

    // set Key
    fun setKey(myKey: String) {
        var sha: MessageDigest? = null
        try {
            key = myKey.toByteArray(charset("UTF-8"))
            sha = MessageDigest.getInstance("SHA-1")
            key = sha.digest(key)
            key = Arrays.copyOf(key, 16)
            secretKeySpec = SecretKeySpec(key, "AES")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    // method to encrypt the secret text using key
    fun encrypt(strToEncrypt: String): String? {
        try {
            setKey(secretKey)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
            return Base64.getEncoder().encodeToString(cipher.doFinal
                (strToEncrypt.toByteArray(charset("UTF-8"))))
        } catch (e: Exception) {

            println("Error while encrypting: $e")
        }
        return null
    }

    // method to encrypt the secret text using key

    fun decrypt(strToDecrypt: String?): String? {
        try {
            setKey(secretKey)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
            return String(cipher.doFinal(Base64.getDecoder().
            decode(strToDecrypt)))
        } catch (e: Exception) {
            println("Error while decrypting: $e")
        }
        return null
    }
}