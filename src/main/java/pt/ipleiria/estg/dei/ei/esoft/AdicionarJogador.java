package pt.ipleiria.estg.dei.ei.esoft;

import javax.swing.*;
import java.awt.*;
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
    private JTextField textNacionalidade;
    private JTextField textDia;
    private JTextField textAltura;
    private JTextField textPeso;
    private JTextField textPosicaoPreferida;
    private JPanel painelContratar;
    private JButton btnCancelar;
    private JButton btnContratar;
    private JTextField textMes;
    private JTextField textAno;
    private JButton esquerdoButton;
    private JButton direitoButton;
    private String peDominante;

    private ListaDadosPessoaisJogadores dados;


    public AdicionarJogador(ListaDadosPessoaisJogadores dados) {
        peDominante = "";
        this.dados = dados;

        btnContratar.addActionListener(this::btnContratarActionPerformed);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        direitoButton.addActionListener(this::btnDireitoActionPerformed);
        esquerdoButton.addActionListener(this::btnEsquerdoActionPerformed);

        setContentPane(painelContratar);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        pack();
        setVisible(true);
    }


    private void btnContratarActionPerformed(ActionEvent e) {
        if (dadosErrados())
        {
            return;
        }
        dados.adicionarJogador(new DadosPessoaisJogadores());
        dados.getNovoJogador().setJogador(txtNome.getText(), textNacionalidade.getText(), new Date(Integer.parseInt(textAno.getText()) - 1900, Integer.parseInt(textMes.getText()) - 1, Integer.parseInt(textDia.getText())), Double.parseDouble(textAltura.getText()), Double.parseDouble(textPeso.getText()), "", textPosicaoPreferida.getText());
        dados.atualizarDados();
        guardarDadosDisco();
    }

    private void btnCancelarActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void btnDireitoActionPerformed(ActionEvent e)
    {
        peDominante = "Direito";
        direitoButton.setBackground(Color.gray);
        esquerdoButton.setBackground(Color.white);
    }

    private void btnEsquerdoActionPerformed(ActionEvent e)
    {
        peDominante = "Esquerdo";
        direitoButton.setBackground(Color.white);
        esquerdoButton.setBackground(Color.gray);
    }

    private boolean dadosErrados()
    {
        boolean erroNome = validarString(txtNome);
        boolean erroNacionalidade = validarString(textNacionalidade);
        boolean erroPos = validarString(textPosicaoPreferida);
        boolean erroPeso = validarDouble(textPeso);
        boolean erroAltura = validarDouble(textAltura);
        boolean erroData = false;
        boolean erroPe = false;
        if (peDominante.compareTo("") == 0)
        {
            erroPe = true;
            esquerdoButton.setBackground(Color.red);
            direitoButton.setBackground(Color.red);
        }


        Date date = new Date();
        int ano = Integer.parseInt(textAno.getText());
        int mes = Integer.parseInt(textMes.getText());
        int dia = Integer.parseInt(textDia.getText());
        if (date.getYear() < (ano - 1900))
        {
            erroData = true;
            textAno.setBackground(Color.RED);
        }
        else
        {
            textAno.setBackground(Color.white);
            if (0 < mes && mes <= 12)
            {
                textMes.setBackground(Color.white);
                switch (Integer.parseInt(textMes.getText()))
                {
                    case 2:
                        if ((ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0))
                        {
                            if (!(0 < dia && dia <= 29))
                            {
                                erroData = true;
                                textDia.setBackground(Color.red);
                            }
                            else
                            {
                                textDia.setBackground(Color.white);
                            }
                        }
                        else
                        {
                            if (!(0 < dia && dia <= 28))
                            {
                                erroData = true;
                                textDia.setBackground(Color.red);
                            }
                            else
                            {
                                textDia.setBackground(Color.white);
                            }
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (!(0 < dia && dia <= 30))
                        {
                            erroData = true;
                            textDia.setBackground(Color.red);
                        }
                        else
                        {
                            textDia.setBackground(Color.white);
                        }
                        break;
                    default:
                        if (!(0 < dia && dia <= 31))
                        {
                            erroData = true;
                            textDia.setBackground(Color.red);
                        }
                        else
                        {
                            textDia.setBackground(Color.white);
                        }
                }
            }
            else
            {
                erroData = true;
                textMes.setBackground(Color.red);
            }
        }


        return erroNome || erroNacionalidade || erroPos || erroPeso || erroAltura || erroData || erroPe;
    }

    private boolean validarString(JTextField textField)
    {
        if(textField.getText().compareTo("") == 0)
        {
            textField.setBackground(Color.RED);
            return true;
        }
        textField.setBackground(Color.white);
        return false;
    }

    private boolean validarDouble(JTextField textField)
    {
        if(Double.parseDouble(textField.getText())  <= 0)
        {
            textField.setBackground(Color.RED);
            return true;
        }
        textField.setBackground(Color.white);
        return false;
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
