package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;

public class JanelaDadosPessoais extends JFrame {
    private JPanel panel1;
    private JButton btnContratar;
    private JButton btnDespedir;
    private JPanel painelDadosPessoais;

    public JanelaDadosPessoais()
    {

        setContentPane(painelDadosPessoais);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();
        setVisible(true);
    }
}
