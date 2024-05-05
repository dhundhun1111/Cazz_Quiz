package com.example.quizapp.adapters

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.example.quizapp.activities.Authentication
import com.example.quizapp.models.User

class RegisterAdapter(private val context: Context, private val onSuccess: () -> Unit): Authentication() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var user: User? = null

    fun createUserWithEmailAndPassword(email: String, password: String, confirmPassword: String) {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(context, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(context, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }

        if (!email.endsWith("@students.iiests.ac.in")) {
            Toast.makeText(context, "Only @students.iiests.ac.in emails are allowed", Toast.LENGTH_SHORT).show()
            return
        }
        user = User().apply {
            this.email = email
            this.password = password
        }
        auth()
    }

    override fun auth() {
        user?.let {
            firebaseAuth.createUserWithEmailAndPassword(it.email, user!!.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val databaseR = Firebase.database.reference
                        val userId = databaseR.child("users").push().key ?: ""
                        databaseR.child("users").child(userId).setValue(user)

                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                        onSuccess.invoke() // Invoke the success callback
                    } else {
                        Toast.makeText(context, "Error Creating User", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
