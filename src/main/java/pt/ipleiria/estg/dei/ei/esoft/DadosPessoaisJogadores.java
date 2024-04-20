package pt.ipleiria.estg.dei.ei.esoft;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class DadosPessoaisJogadores implements Serializable {
    private String nome;
    private String nacionalidade;
    private Date dataNascimento;
    private Double altura;
    private Double peso;
    private String peDominante;
    private String posicaoPreferida;

    private transient ArrayList<DadosPessoaisJogadoresListener> listeners;


    public DadosPessoaisJogadores() {
        this.nome = "";
        this.nacionalidade = "";
        this.dataNascimento = null;
        this.altura = null;
        this.peso = null;
        this.peDominante = "";
        this.posicaoPreferida = "";

        listeners = new ArrayList<>();
    }


    public void addTabelaRecordesListener(DadosPessoaisJogadoresListener list) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(list);
    }

    private void notifyRecordesActualizados() {
        if (listeners != null) {
            for (DadosPessoaisJogadoresListener list : listeners)
                list.dadosActualizados(this);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getPeso() {
        return peso;
    }

    public String getPeDominante() {
        return peDominante;
    }

    public String getPosicaoPreferida() {
        return posicaoPreferida;
    }

    public void setJogador(String nome, String nacionalidade, Date dataNascimento, Double altura, Double peso, String peDominante, String posicaoPreferida)
    {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.peso = peso;
        this.peDominante = peDominante;
        this.posicaoPreferida = posicaoPreferida;

        notifyRecordesActualizados();
    }

    /*public void removeTabelaRecordesListener(DadosPessoaisJogadoresListener list) {
        if (listeners != null) listeners.remove(list);
    }*/
}
