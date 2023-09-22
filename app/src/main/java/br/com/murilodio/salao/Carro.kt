package br.com.murilodio.salao

class Carro constructor (tipo: Boolean, nome: String, fabricante: String) : Automovel (nome, fabricante)
{
    var tipo : Boolean

    init {
        this.tipo = tipo
    }

    fun Tipo(tipo : Boolean) : String{
        if (tipo){
            return "Completo"
        }else{
            return "Básico"
        }
    }

    override fun toString(): String {
        return super.toString()+" Tipo: "+ Tipo(this.tipo)
    }
}