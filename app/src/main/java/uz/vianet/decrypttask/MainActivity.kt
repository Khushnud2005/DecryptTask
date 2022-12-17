package uz.vianet.decrypttask

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {
    private val SAMPLE_ALIAS = "itworksdonttouchit"
    lateinit var et_cipher: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        et_cipher = findViewById(R.id.et_cipher)

        val btn_encrypt = findViewById<Button>(R.id.btn_encrypt)
        val btn_decrypt = findViewById<Button>(R.id.btn_decrypt)

        btn_encrypt.setOnClickListener { doEncrypt() }
        btn_decrypt.setOnClickListener { doDecrypt() }
    }

    private fun doEncrypt() {
        val originalString = et_cipher.text.toString()
        val encryptedString = EncryptManager.encrypt(originalString)
        et_cipher.text.clear()
        et_cipher.setText(encryptedString)
    }

    private fun doDecrypt() {

        val originalString = et_cipher.text.toString()
        val decryptedString = EncryptManager.decrypt(originalString)
        et_cipher.text.clear()
        et_cipher.setText(decryptedString)

    }

}