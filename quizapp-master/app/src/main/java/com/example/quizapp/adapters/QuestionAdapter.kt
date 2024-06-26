package com.example.quizapp.adapters

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.example.quizapp.models.Quiz

class QuestionAdapter(private val context: Context) {
    fun setUpFirestore(quizTitle: String, callback: (List<Quiz>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("quizzes").whereEqualTo("title", quizTitle)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot != null && !snapshot.isEmpty) {
                    val quizzes = snapshot.toObjects(Quiz::class.java)
                    callback(quizzes)
                }
            }
    }
}