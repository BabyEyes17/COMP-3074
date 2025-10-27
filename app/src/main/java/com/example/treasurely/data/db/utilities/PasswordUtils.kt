package com.example.treasurely.data.db.utilities

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

object PasswordUtils {

    private const val SALT_LENGTH = 16

    fun generateSalt(): String {

        val salt = ByteArray(SALT_LENGTH)
        SecureRandom().nextBytes(salt)

        return Base64.getEncoder().encodeToString(salt)
    }

    fun hashPassword(password: String, salt: String): String {

        val digest = MessageDigest.getInstance("SHA-256")
        val saltedPassword = password + salt
        val hash = digest.digest(saltedPassword.toByteArray())

        return Base64.getEncoder().encodeToString(hash)
    }
}