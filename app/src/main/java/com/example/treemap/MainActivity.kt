package com.example.treemap

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.TreeMap

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etPP1: EditText
    private lateinit var etPP2: EditText
    private lateinit var etPP3: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnOrdenAlfabetico: Button
    private lateinit var btnOrdenNumerico: Button
    private lateinit var rvEstudiantes: RecyclerView

    private lateinit var adapter: EstudianteAdapter

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
        btnOrdenAlfabetico = findViewById(R.id.btnOrdenAlfabetico)
        btnOrdenNumerico = findViewById(R.id.btnOrdenNumerico)
        rvEstudiantes = findViewById(R.id.rvEstudiantes)

        adapter = EstudianteAdapter(emptyList())
        rvEstudiantes.layoutManager = LinearLayoutManager(this)
        rvEstudiantes.adapter = adapter

        btnGuardar.setOnClickListener {
            guardarEstudiante()
        }

        btnOrdenAlfabetico.setOnClickListener {
            ordenarAlfabeticamente()
        }

        btnOrdenNumerico.setOnClickListener {
            ordenarNumericamente()
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

        ordenarAlfabeticamente()
        limpiarCampos()
    }

    // Orden natural del TreeMap: alfabético por nombre (clave)
    private fun ordenarAlfabeticamente() {
        adapter.actualizarLista(estudiantes.values.toList())
    }

    // Toma los valores del TreeMap y los ordena por promedio, de mayor a menor
    private fun ordenarNumericamente() {
        val listaOrdenada = estudiantes.values.sortedByDescending { it.promedio }
        adapter.actualizarLista(listaOrdenada)
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