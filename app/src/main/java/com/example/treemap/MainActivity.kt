package com.example.treemap

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.TreeMap

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etPP1: EditText
    private lateinit var etPP2: EditText
    private lateinit var etPP3: EditText
    private lateinit var btnGuardar: Button

    // TreeMap que guarda a los estudiantes ordenados automáticamente por nombre (clave)
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
            guardarEstudiante()
        }
    }

    private fun guardarEstudiante() {
        val nombre = etNombre.text.toString().trim()
        val pp1Texto = etPP1.text.toString().trim()
        val pp2Texto = etPP2.text.toString().trim()
        val pp3Texto = etPP3.text.toString().trim()

        if (nombre.isEmpty() || pp1Texto.isEmpty() || pp2Texto.isEmpty() || pp3Texto.isEmpty()) {
            mostrarDialogoValidacion()
            return
        }

        val pp1 = pp1Texto.toIntOrNull()
        val pp2 = pp2Texto.toIntOrNull()
        val pp3 = pp3Texto.toIntOrNull()

        if (pp1 == null || pp2 == null || pp3 == null) {
            mostrarDialogoValidacion()
            return
        }

        val estudiante = Estudiante(nombre, pp1, pp2, pp3)
        estudiantes[nombre] = estudiante

        Toast.makeText(this, "Estudiante guardado: $nombre", Toast.LENGTH_SHORT).show()

        limpiarCampos()

        // La lista visual (RecyclerView) se conecta en el siguiente commit
    }

    private fun limpiarCampos() {
        etNombre.text.clear()
        etPP1.text.clear()
        etPP2.text.clear()
        etPP3.text.clear()
    }

    private fun mostrarDialogoValidacion() {
        AlertDialog.Builder(this)
            .setTitle("Atención")
            .setMessage("Por favor ingrese datos")
            .setPositiveButton("OK", null)
            .show()
    }
}