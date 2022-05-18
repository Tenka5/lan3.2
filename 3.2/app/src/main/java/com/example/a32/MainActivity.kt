package com.example.a32

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*

fun String.hasLetters() = any { it.isLetter() }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rad : RadioGroup = findViewById(R.id.rad)
        val t: EditText = findViewById(R.id.t)
        val b: Button = findViewById(R.id.button)

        rad.setOnCheckedChangeListener { _, checkedId -> when (checkedId) {
                R.id.r1 -> {
                    t.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI)
                }
                R.id.r2 -> {
                    t.setInputType(InputType.TYPE_CLASS_PHONE)
                }
                R.id.r3 -> {
                    t.setInputType(InputType.TYPE_CLASS_PHONE)
                }
                R.id.r4 -> {
                    t.setInputType(InputType.TYPE_CLASS_TEXT)
                }
            }
        }

        b.setOnClickListener(){
            val value: String = t.text.toString()


                when (rad.checkedRadioButtonId){
                    R.id.r1 -> {
                        if (value.startsWith("http://", ignoreCase = true) ||
                            value.startsWith("https://", ignoreCase = true))
                                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
                        else
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + value)))
                    }
                    R.id.r2 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + value)))
                    }
                    R.id.r3 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + value)))
                    }
                    R.id.r4 -> {
                        if (value.hasLetters()){
                            if (value.startsWith("http://", ignoreCase = true) || value.startsWith("https://", ignoreCase = true)) startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
                            else startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + value)))
                        }
                        else if( value.contains(" ")) startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + value)))
                        else startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + value)))
                    }
                }


        }
    }
}