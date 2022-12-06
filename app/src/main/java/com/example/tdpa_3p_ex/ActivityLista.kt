package com.example.tdpa_3p_ex

import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cursoradapter.widget.CursorAdapter
import com.example.tdpa_3p_ex.DB.SQLiteHelper
import com.example.tdpa_3p_ex.databinding.ActivityListaBinding
import com.example.tdpa_3p_ex.databinding.AlumnoListaBinding

class ActivityLista : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val quer = SQLiteHelper(this, "administracion",null, 1)
        val db = quer.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM alumnos",null)

        val adaptador = CursorAdapterList(this, cursor)
        binding.lista.adapter = adaptador
        db.close()
    }

    inner class CursorAdapterList(context: Context, cursor: Cursor): CursorAdapter(context,cursor, FLAG_REGISTER_CONTENT_OBSERVER ){
        override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            return inflater.inflate(R.layout.alumno_lista,parent, false)
        }

        override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
            val bindingItems = AlumnoListaBinding.bind(view!!)
            val id = cursor!!.getString(0)
            val nombre = cursor!!.getString(1)
            val materia = cursor!!.getString(2)
            val calPrim = cursor!!.getDouble(3)
            val calSeg = cursor!!.getDouble(4)
            bindingItems.txtID.text = "ID: ${cursor!!.getString(0)}"
            bindingItems.txtNombreAlumno.text = "Alumno: ${cursor!!.getString(1)}"
            bindingItems.txtMateriaAlumno.text = "Materia: ${cursor!!.getString(2)}"

            view.setOnClickListener{
                val alumno = Intent(context, DetalleAlumno::class.java)
                alumno.putExtra("ID",id)
                alumno.putExtra("NOMBRE",nombre)
                alumno.putExtra("MATERIA",materia)
                alumno.putExtra("CAL1",calPrim)
                alumno.putExtra("CAL2",calSeg)
                startActivity(alumno)
                //Toast.makeText(this@ActivityLista,"${bindingItems.txtNombreAlumno.text} se encontró " +
                       // "correctamente", Toast.LENGTH_SHORT).show()
            }
        }

    }
}