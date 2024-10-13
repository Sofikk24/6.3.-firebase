package com.example.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        emailEditText = EditText(this)
        passwordEditText = EditText(this)
        signInButton = Button(this)

        emailEditText.hint = "Email"
        passwordEditText.hint = "Password"
        signInButton.text = "Sign In"

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        layout.addView(emailEditText)
        layout.addView(passwordEditText)
        layout.addView(signInButton)

        setContentView(layout)

        auth = FirebaseAuth.getInstance()

        signInButton.setOnClickListener {
            signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Sign in successful.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Sign in failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
