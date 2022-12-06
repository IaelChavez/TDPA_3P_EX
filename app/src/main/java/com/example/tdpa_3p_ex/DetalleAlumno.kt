package com.example.tdpa_3p_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tdpa_3p_ex.databinding.ActivityDetalleAlumnoBinding
import com.example.tdpa_3p_ex.databinding.ActivityListaBinding
import com.squareup.picasso.Picasso

class DetalleAlumno : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleAlumnoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleAlumnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val id = bundle?.getString("ID")
        val nombre = bundle?.getString("NOMBRE")
        val materia = bundle?.getString("MATERIA")
        val cal1 = bundle?.getDouble("CAL1")
        val cal2 = bundle?.getDouble("CAL2")

        var par1 = cal1?.times(0.2)
        var par2 = cal2?.times(0.2)

        val suma: Double? = par2?.let { par1?.plus(it) }

        val resultado1 = 10 - suma!!
        val resultado2 = 6 - suma!!

        Picasso.get().load("https://picsum.photos/id/${id}/200/300").into(binding.imageContainer);

        binding.txtID.text = "ID: ${id}"
        binding.txtName.text = "Nombre: ${nombre}"
        binding.txtMate.text = "Materia: ${materia}"
        binding.txtCa1.text = "Primer parcial: ${cal1}"
        binding.txtCa2.text = "Segundo parcial: ${cal2}"

        binding.txtFinal1.text = "Tercer parcial con 10: ${resultado1}"
        binding.txtFinal2.text = "Tercer parcial con 6: ${resultado2}"

        if(suma < 4){
            binding.txtMensaje.text = "¡Ya no se logró!"
        }else{
            binding.txtMensaje.text = "¡Todavia puedes!"
        }


    }


}