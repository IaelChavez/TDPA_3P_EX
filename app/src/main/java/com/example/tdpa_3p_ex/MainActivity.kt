package com.example.tdpa_3p_ex

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tdpa_3p_ex.DB.SQLiteHelper
import com.example.tdpa_3p_ex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInsertar.setOnClickListener{
            alta()
        }

        binding.btnBuscar.setOnClickListener{
            buscarAlumno()
        }

        binding.btnActualizar.setOnClickListener{
            modificarAlumno()
        }

        binding.btnEliminar.setOnClickListener{
            borrarAlumno()
        }
        binding.btnLista.setOnClickListener{
            listaAlumnos()
        }
    }

    fun alta(){
        val quer = SQLiteHelper(this, "administracion",null, 1)
        val db = quer.writableDatabase
        val registro = ContentValues()

        registro.put("nombre",binding.txtNombre.text.toString())
        registro.put("materia",binding.txtMateria.text.toString())
        registro.put("calPrim",binding.txtCal1.text.toString())
        registro.put("calSeg",binding.txtCal2.text.toString())
        db.insert("alumnos",null,registro)

        db.close()

        binding.txtId.setText("")
        binding.txtNombre.setText("")
        binding.txtMateria.setText("")
        binding.txtCal1.setText("")
        binding.txtCal2.setText("")

        Toast.makeText(this, "Dato insertado Correctamente", Toast.LENGTH_SHORT).show()
    }

    fun buscarAlumno(){
        val quer = SQLiteHelper(this, "administracion",null, 1)
        val db = quer.writableDatabase
        val fila = db.rawQuery("SELECT nombre, materia, calPrim, calSeg FROM " +
                "alumnos WHERE _id=${binding.txtId.text.toString()}",null)
        if(fila.moveToFirst()){
            binding.txtNombre.setText(fila.getString(0))
            binding.txtMateria.setText(fila.getString(1))
            binding.txtCal1.setText(fila.getString(2))
            binding.txtCal2.setText(fila.getString(3))
        }
        else{
            Toast.makeText(this, "No se encontraron alumnos con ese ID", Toast.LENGTH_SHORT).show()
        }
    }

    fun borrarAlumno(){
        val quer = SQLiteHelper(this, "administracion",null, 1)
        val db = quer.writableDatabase
        val cant = db.delete("alumnos", "_id=${binding.txtId.text.toString()}",null)

        db.close()
        if(cant == 1){
            binding.txtId.setText("")
            binding.txtNombre.setText("")
            binding.txtMateria.setText("")
            binding.txtCal1.setText("")
            binding.txtCal2.setText("")
            Toast.makeText(this, "El alumno se eliminó correctamente", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "No se encontraron alumnos con ese ID", Toast.LENGTH_SHORT).show()
        }
    }

    fun modificarAlumno(){
        val quer = SQLiteHelper(this, "administracion",null, 1)
        val db = quer.writableDatabase
        val registro = ContentValues()
        registro.put("_id",binding.txtId.text.toString())
        registro.put("nombre",binding.txtNombre.text.toString())
        registro.put("materia",binding.txtMateria.text.toString())
        registro.put("calPrim",binding.txtCal1.text.toString())
        registro.put("calSeg",binding.txtCal2.text.toString())
        val cant = db.update("alumnos", registro,"_id=${binding.txtId.text.toString()}",null)
        db.close()
        if(cant == 1){
            binding.txtId.setText("")
            binding.txtNombre.setText("")
            binding.txtMateria.setText("")
            binding.txtCal1.setText("")
            binding.txtCal2.setText("")
            Toast.makeText(this, "Los datos se actualizaron correctamente", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "No se encontraron alumnos con ese ID", Toast.LENGTH_SHORT).show()
        }
    }

    fun listaAlumnos(){
        val intentListView = Intent(this, ActivityLista::class.java)
        startActivity(intentListView)
    }
}