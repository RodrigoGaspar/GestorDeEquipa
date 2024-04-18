package pt.ipleiria.estg.dei.ei.esoft;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main extends JFrame {
    private JPanel panel1;
    private JButton btnSair;
    private JButton btnDadosPessoais;
    private JButton dadosEstatisticosButton;
    private JButton contratosButton;
    private JPanel painelPrincipal;

    public Main(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        btnSair.addActionListener(this::btnSairActionPerformed);
        btnDadosPessoais.addActionListener(this::btnDadosPessoaisActionPerformed);
    }
    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnDadosPessoaisActionPerformed(ActionEvent e) {
        var janela = new JanelaDadosPessoais();
        janela.setVisible(true);
    }

    public static void main(String[] args) {
        new Main("Main Menu").setVisible(true);
    }
}