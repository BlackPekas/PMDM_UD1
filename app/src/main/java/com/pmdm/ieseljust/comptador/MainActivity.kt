package com.pmdm.ieseljust.comptador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pmdm.ieseljust.comptador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var comptador=0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        if (savedInstanceState != null) {
            comptador = savedInstanceState.getInt("contador",0)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia al TextView
        val textViewContador=findViewById<TextView>(R.id.textViewComptador)
        // Referencia al boto d'Open
        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.text=comptador.toString() // Estem fent una assignacio directament o accedinta algun metode?

        // Referencia al botón

        // Asociaciamos una expresióin lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        binding.btAdd.setOnClickListener {
            comptador++
            textViewContador.text=comptador.toString()
        }

        /*btOpen.setOnClickListener{
            val intent = Intent(baseContext, MostraComptadorActivity::class.java)
            intent.putExtra("comptador", comptador)
            startActivity(intent)
        }*/

        binding.btOpen.setOnClickListener {
            Intent(baseContext, MostraComptadorActivity::class.java).apply {
                putExtra("comptador", comptador)
                startActivity(this)
            }
        }


        binding.btSubtract.setOnClickListener{
            comptador--
            textViewContador.text=comptador.toString()
        }

        binding.btReset.setOnClickListener{
            comptador = 0
            textViewContador.text=comptador.toString()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("contador",comptador)
    }
}