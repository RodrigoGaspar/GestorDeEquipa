package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JanelaDadosPessoais extends JFrame {
    private JPanel panel1;
    private JButton btnContratar;
    private JButton btnDespedir;
    private JPanel painelDadosPessoais;
    private JPanel jpDados;
    private DadosPessoaisJogadores dados;
    private int numeroJogadores;


    public JanelaDadosPessoais()
    {
        dados = new DadosPessoaisJogadores();
        numeroJogadores = 1;

        lerDados();

        jpDados.setLayout(new GridLayout(numeroJogadores, 7));

        btnContratar.addActionListener(this::btnContratarActionPerformed);

        setContentPane(painelDadosPessoais);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();
        setVisible(true);

        dados.addTabelaRecordesListener(new DadosPessoaisJogadoresListener() {
            @Override
            public void dadosActualizados(DadosPessoaisJogadores dadosPessoais) {
                dadosPessoaisActualizados(dados);
            }
        });
    }


    private void btnContratarActionPerformed(ActionEvent e) {
        var janela = new AdicionarJogador(dados);
        janela.setVisible(true);
    }

    private void dadosPessoaisActualizados(DadosPessoaisJogadores dadosPessoais) {
        JLabel aux = new JLabel(dadosPessoais.getNome());
        jpDados.add(aux);
        aux = new JLabel(dadosPessoais.getNacionalidade());
        jpDados.add(aux);
        aux = new JLabel(dadosPessoais.getDataNascimento().toString());
        jpDados.add(aux);
        aux = new JLabel(dadosPessoais.getAltura().toString());
        jpDados.add(aux);
        aux = new JLabel(dadosPessoais.getPeso().toString());
        jpDados.add(aux);
        aux = new JLabel(dadosPessoais.getPosicaoPreferida());
        jpDados.add(aux);
        aux = new JLabel(dadosPessoais.getPeDominante());
        jpDados.add(aux);
        guardarDadosDisco();
    }

    private void guardarDadosDisco() {
        ObjectOutputStream oos = null;
        try {
            File f =new
                    File(System.getProperty("user.home")+File.separator+"GestorEquipas.dadosPessoais");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(dados);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(JanelaDadosPessoais.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    private void lerDados() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"GestorEquipas.dadosPessoais");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                dados=(DadosPessoaisJogadores) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(JanelaDadosPessoais.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JanelaDadosPessoais.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

}
