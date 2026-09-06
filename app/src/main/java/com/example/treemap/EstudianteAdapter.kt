package com.example.treemap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstudianteAdapter(
    private var estudiantes: List<Estudiante>
) : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder>() {

    class EstudianteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIniciales: TextView = itemView.findViewById(R.id.tvIniciales)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvNotas: TextView = itemView.findViewById(R.id.tvNotas)
        val tvPromedio: TextView = itemView.findViewById(R.id.tvPromedio)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estudiante, parent, false)
        return EstudianteViewHolder(vista)
    }

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        val estudiante = estudiantes[position]

        val iniciales = estudiante.nombre
            .split(" ")
            .filter { it.isNotEmpty() }
            .take(2)
            .joinToString("") { it.first().uppercase() }

        holder.tvIniciales.text = iniciales
        holder.tvNombre.text = estudiante.nombre
        holder.tvNotas.text = "PP1: ${estudiante.pp1}   PP2: ${estudiante.pp2}   PP3: ${estudiante.pp3}"
        holder.tvPromedio.text = "Promedio: %.2f".format(estudiante.promedio)
        holder.tvEstado.text = estudiante.estado

        holder.tvEstado.setTextColor(
            when (estudiante.estado) {
                "Aprobado" -> 0xFF2E7D32.toInt()
                "Sustitutorio" -> 0xFFF9A825.toInt()
                else -> 0xFFC62828.toInt()
            }
        )
    }

    override fun getItemCount(): Int = estudiantes.size

    fun actualizarLista(nuevaLista: List<Estudiante>) {
        estudiantes = nuevaLista
        notifyDataSetChanged()
    }
}