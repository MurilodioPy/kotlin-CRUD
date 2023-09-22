package br.com.murilodio.salao

open class Automovel constructor (nome : String, fabricante : String)
{
    var nome : String
    var fabricante : String

    init {
        this.nome = nome
        this.fabricante = fabricante
    }

    override fun toString(): String {
        return "Nome: " +this.nome+" - Fabricante: " +this.fabricante
    }
}