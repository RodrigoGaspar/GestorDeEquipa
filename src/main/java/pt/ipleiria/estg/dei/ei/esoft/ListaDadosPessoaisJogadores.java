package pt.ipleiria.estg.dei.ei.esoft;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListaDadosPessoaisJogadores implements Serializable {
    private LinkedList<DadosPessoaisJogadores> dadosPessoaisJogadores;

    private transient ArrayList<ListaDadosPessoaisJogadoresListener> listeners;


    ListaDadosPessoaisJogadores()
    {
        dadosPessoaisJogadores = new LinkedList<>();

        listeners = new ArrayList<>();
    }


    public void adicionarJogador(DadosPessoaisJogadores dadosPessoaisJogadores)
    {
        this.dadosPessoaisJogadores.add(dadosPessoaisJogadores);
    }

    public DadosPessoaisJogadores getNovoJogador()
    {
        return dadosPessoaisJogadores.getLast();
    }

    public LinkedList<DadosPessoaisJogadores> getDadosPessoaisJogadores()
    {
        return dadosPessoaisJogadores;
    }

    public int getNumeroJogadores()
    {
        int num = 0;
        for (DadosPessoaisJogadores jogador : dadosPessoaisJogadores)
        {
            num++;
        }
        return num;
    }

    public void addTabelaDadosListener(ListaDadosPessoaisJogadoresListener list) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(list);
    }

    public void atualizarDados()
    {
        notifyRecordesActualizados();
    }

    private void notifyRecordesActualizados() {
        if (listeners != null) {
            for (ListaDadosPessoaisJogadoresListener list : listeners)
                list.dadosActualizados(this);
        }
    }
}
