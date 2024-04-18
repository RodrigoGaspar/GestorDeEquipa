package pt.ipleiria.estg.dei.ei.esoft;
import javax.swing.*;
import java.awt.event.ActionEvent;


public class Main extends JFrame {
    private JPanel panel1;
    private JButton btnSair;
    private JButton btnDadosPessoais;
    private JButton btnDadosEstatisticos;
    private JButton contratosButton;
    private JPanel painelPrincipal;

    public Main(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        btnSair.addActionListener(this::btnSairActionPerformed);
        btnDadosPessoais.addActionListener(this::btnDadosPessoaisActionPerformed);
        btnDadosEstatisticos.addActionListener(this::btnDadosEstatisticosPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnDadosPessoaisActionPerformed(ActionEvent e) {
        var janela = new JanelaDadosPessoais();
        janela.setVisible(true);
    }

    private void btnDadosEstatisticosPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Main("Main Menu").setVisible(true);
    }
}