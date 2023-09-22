package br.com.murilodio.salao

class Caminhao constructor (pesoSuportado: Float, fabricante: String, nome: String) : Automovel(nome, fabricante)
{
    var pesoSuportado:Float = 0.0f

    init {
        this.pesoSuportado = pesoSuportado
    }

    override fun toString(): String {
        return super.toString()+" Peso suportado: "+ this.pesoSuportado
    }
}