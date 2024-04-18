package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;
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
        numeroJogadores = 2;

        lerDados();

        for (int i = 0; i < numeroJogadores; i++)
        {
            JLabel o = new JLabel(dados.getNome());
            jpDados.add(o);
            new JLabel(dados.getNacionalidade());
        }

        setContentPane(painelDadosPessoais);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();
        setVisible(true);
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
