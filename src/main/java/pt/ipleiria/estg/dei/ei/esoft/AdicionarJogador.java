package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdicionarJogador extends JFrame {
    private JTextField txtNome;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JPanel painelContratar;
    private JButton btnCancelar;
    private JButton btnContratar;

    private ListaDadosPessoaisJogadores dados;


    public AdicionarJogador(ListaDadosPessoaisJogadores dados) {
        this.dados = dados;

        btnContratar.addActionListener(this::btnContratarActionPerformed);

        setContentPane(painelContratar);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();
        setVisible(true);
    }


    private void btnContratarActionPerformed(ActionEvent e) {
        dados.adicionarJogador(new DadosPessoaisJogadores());
        dados.getNovoJogador().setJogador(txtNome.getText(), "", new Date(2004, 04, 19), 0.0, 0.0, "", "");
        dados.atualizarDados();
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
}
