package br.com.murilodio.salao

class Onibus constructor (quantidadeLugares :Int, banheiro: Boolean, fabricante: String, nome: String) : Automovel(nome, fabricante)
{
    var quantidadeLugares : Int = 0
    var banheiro : Boolean = false

    init {
        this.quantidadeLugares = quantidadeLugares
        this.banheiro = banheiro
    }

    fun Banheiro(banheiro : Boolean) : String{
        if (banheiro){
            return "Com banheiro"
        }else{
            return "Sem banheiro"
        }
    }

    override fun toString(): String {
        return super.toString()+" Quantidade de lugares: "+ this.quantidadeLugares + " " + Banheiro(this.banheiro)
    }



}