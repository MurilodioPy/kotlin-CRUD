package br.com.murilodio.salao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //VIEW
    lateinit var lv_auto : ListView
    lateinit var tv_lugares : TextView
    lateinit var tv_edited : TextView
    lateinit var tv_toneladas : TextView
    //BUTTON
    lateinit var bt_inserir : Button
    lateinit var bt_remover : Button
    lateinit var bt_editar : Button
    //EDITTEXT
    lateinit var et_nome : EditText
    lateinit var et_fabricante : EditText
    lateinit var et_number : EditText
    lateinit var ed_item : EditText
    //CHECKBOX
    lateinit var cb_banheiro : CheckBox
    lateinit var cb_completo : CheckBox
    lateinit var radio_Group : RadioGroup
    //ADAPTER
    var adaptador : ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv_auto = findViewById<ListView>(R.id.lv_autos)
        tv_lugares = findViewById(R.id.tv_lugares)
        tv_toneladas = findViewById(R.id.tv_toneladas)
        tv_edited = findViewById(R.id.tv_edit)
        bt_inserir = findViewById(R.id.bt_inserir)
        bt_remover = findViewById(R.id.bt_remover)
        bt_editar = findViewById(R.id.bt_editar)
        et_nome = findViewById(R.id.et_nome_id)
        et_fabricante = findViewById(R.id.et_fabricante_id)
        et_number = findViewById(R.id.ed_number_id)
        ed_item = findViewById(R.id.ed_item_id)
        cb_banheiro = findViewById(R.id.cb_banheiro_id)
        cb_completo = findViewById(R.id.cb_completo_id)
        radio_Group = findViewById(R.id.rd_GrouAuto)

        var listaAutos = ArrayList<String>()
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaAutos)
        lv_auto.adapter = adaptador

        var tipo = 0
        radio_Group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rd_Auto -> {
                    limpar()
                    tv_lugares.visibility = View.GONE
                    cb_banheiro.visibility = View.GONE
                    cb_completo.visibility = View.GONE
                    tv_toneladas.visibility = View.GONE
                    et_number.visibility = View.GONE
                    tipo = 0
                }
                R.id.rd_Bus -> {
                    limpar()
                    cb_completo.visibility = View.GONE
                    tv_toneladas.visibility = View.GONE
                    et_number.visibility = View.VISIBLE
                    cb_banheiro.visibility = View.VISIBLE
                    tv_lugares.visibility = View.VISIBLE
                    tipo = 1
                }
                R.id.rd_Car -> {
                    limpar()
                    et_number.visibility = View.GONE
                    cb_banheiro.visibility = View.GONE
                    tv_toneladas.visibility = View.GONE
                    tv_lugares.visibility = View.GONE
                    cb_completo.visibility = View.VISIBLE
                    tipo = 2
                }
                R.id.rd_Truck -> {
                    limpar()
                    tv_lugares.visibility = View.GONE
                    cb_banheiro.visibility = View.GONE
                    cb_completo.visibility = View.GONE
                    tv_toneladas.visibility = View.VISIBLE
                    et_number.visibility = View.VISIBLE
                    tipo = 3
                }

            }
        }

        bt_inserir.setOnClickListener {
            listaAutos.add(inserir(tipo))
            lv_auto.adapter = adaptador
        }

        bt_remover.setOnClickListener {
            if (testeVazio(ed_item)){
                var index = ed_item.text.toString().toInt()
                if(index < listaAutos.size){
                    listaAutos.removeAt(index)
                }
            }
            lv_auto.adapter = adaptador
        }

        bt_editar.setOnClickListener {
            var editado = ""
            if (testeVazio(ed_item)){
                var index = ed_item.text.toString().toInt()
                if(index < listaAutos.size){
                    editado = listaAutos.get(index)
                    tv_edited.text = editado
                    listaAutos.removeAt(index);
                    listaAutos.add(index , inserir(tipo))
                }
            }
            lv_auto.adapter = adaptador
        }
    }

    fun limpar(){
        et_nome.setText("");
        et_fabricante.setText("");
        et_number.setText("");
    }

    fun testeVazio(valor : EditText) : Boolean{
        if (valor.getText().isNotEmpty()){
            return true
        }
        return false
    }

    fun inserir(tipo : Number) : String{
        var lugares = 0
        var toneladas = 0.0F
        if (testeVazio(et_number)){
            if (tipo == 3){
                toneladas = et_number.text.toString().toFloat()
            }else if(tipo == 1){
                lugares = et_number.text.toString().toInt()
            }
        }
        var meuAuto = when(tipo) {
            1 -> Onibus(lugares, cb_banheiro.isChecked, et_fabricante.text.toString(), et_nome.text.toString()) ;
            2 -> Carro(cb_completo.isChecked, et_nome.text.toString(), et_fabricante.text.toString())
            3 -> Caminhao(toneladas, et_fabricante.text.toString(), et_nome.text.toString())
            else -> Automovel(et_nome.text.toString(), et_fabricante.text.toString())
        }
        return meuAuto.toString()
    }
}