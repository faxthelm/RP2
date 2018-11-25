package com.rp.myapplication.model;

public class TipoDenuncia {
    private String  codigo;
    private String  descricao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /*RETORNA A DESCRICAO NO COMPONENTE SPINNER */
    @Override
    public String toString() {
        return this.descricao;
    }

    public TipoDenuncia(){

    }
    public TipoDenuncia(String codigo, String descricao){

        this.codigo    = codigo;
        this.descricao = descricao;

    }
}
