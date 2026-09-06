package com.example.treemap

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.TreeMap

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etPP1: EditText
    private lateinit var etPP2: EditText
    private lateinit var etPP3: EditText
    private lateinit var btnGuardar: Button

    private val estudiantes = TreeMap<String, Estudiante>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etPP1 = findViewById(R.id.etPP1)
        etPP2 = findViewById(R.id.etPP2)
        etPP3 = findViewById(R.id.etPP3)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val pp1 = etPP1.text.toString().trim()
            val pp2 = etPP2.text.toString().trim()
            val pp3 = etPP3.text.toString().trim()

            if (nombre.isEmpty() || pp1.isEmpty() || pp2.isEmpty() || pp3.isEmpty()) {
                mostrarDialogoValidacion()
            }
            // La lógica para guardar en el TreeMap se agrega en el siguiente commit
        }
    }

    private fun mostrarDialogoValidacion() {
        AlertDialog.Builder(this)
            .setTitle("Atención")
            .setMessage("Por favor ingrese datos")
            .setPositiveButton("OK", null)
            .show()
    }
}