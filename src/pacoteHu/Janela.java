package pacoteHu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Janela extends JFrame{
    /*
    * @author Jhonata Flores Sande
    */
    public static final long serialVersionUID = 1L;

    private static Point point = new Point();

    // Barra da Frame
    JPanel barraFrame = new JPanel();
    /*BarraFrame*/
        Font BarraFrameFont = new Font("",Font.PLAIN,12);

        JLabel nomeApp;

        JButton abrir;
        JButton salvar;  

        JLabel tagTema;
        JButton botaoTema;
        

        JButton sair;
        JButton minimizar;     
    /*//Barra Frame*/

    JTextPane areaDeTexto;
    JScrollPane scroll;
    JTextArea caixaDeTexto;
        Font caixaFont = new Font("",Font.PLAIN,14);
    
    public Janela(){
        /*Configuração da Frame*/
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(CorDeFundoFrame());
        this.setUndecorated(true);
        this.setSize(700,600);
        this.setTitle("Escreva.txt");
        this.setResizable(false);        
        this.setLocationRelativeTo(null);

        /*Barra da Frame*/ 
        barraFrame.setLayout(null);
        barraFrame.setLocation(0,0);
        barraFrame.setSize(700,30);
        barraFrame.setBackground(CorDeFundoBarraFrame());
        /*Reposicionamento da Frame*/
        barraFrame.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
                }
            });

        barraFrame.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
                }
            });
        /*//Reposicioamento da Frame*/
        add(barraFrame);

            nomeApp = new JLabel(new ImageIcon(CaminhoIcone()));
            nomeApp.setForeground(CorDoTexto());
            nomeApp.setBorder(null);
            nomeApp.setSize(32,32);
            nomeApp.setLocation(5,0);
            barraFrame.add(nomeApp);

            /*Barra de Opções e Ferramentas*/

            abrir = new JButton("Abrir");
            abrir.setFont(BarraFrameFont);
            abrir.setForeground(CorDoTexto());
            abrir.setBackground(CorDeFundoBarraFrame());
            abrir.setBorder(null);
            abrir.setSize(50,32);
            abrir.setLocation(250,0);
            /*Ação*/
            abrir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String x = AbrirArquivo();
                    caixaDeTexto.setText(LerArquivo(x));
                }
            });
            /*//Ação*/
            abrir.setFocusPainted(false);
            barraFrame.add(abrir);

            salvar = new JButton("Salvar");
            salvar.setFont(BarraFrameFont);
            salvar.setForeground(CorDoTexto());
            salvar.setBackground(CorDeFundoBarraFrame());
            salvar.setBorder(null);
            salvar.setSize(50,32);
            salvar.setLocation(300,0);
            /*Ação*/
            salvar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String x = DiretorioArquivo();
                    String y = caixaDeTexto.getText();
                    SalvarArquivo(y, x);
                }
            });
            /*//Ação*/
            salvar.setFocusPainted(false);
            barraFrame.add(salvar);

        /*//Barra de opções e Ferramentas*/

            tagTema = new JLabel("Escuro:");
            tagTema.setFont(BarraFrameFont);
            tagTema.setForeground(CorDoTexto());
            tagTema.setBorder(null);
            tagTema.setSize(50,30);
            tagTema.setLocation(530,0);
            barraFrame.add(tagTema);

            botaoTema = new JButton(OnOrOff());
            botaoTema.setFont(new Font("",Font.HANGING_BASELINE,12));
            botaoTema.setBackground(CorDeFundoBarraFrame());
            botaoTema.setBorder(null);
            botaoTema.setSize(30,30);
            botaoTema.setLocation(570,0);
            try{
                if(LerTema().equals("t")){
                    botaoTema.setText("On");
                    botaoTema.setForeground(new Color(46,139,87));
                } else {
                    botaoTema.setText("Off");
                    botaoTema.setForeground(new Color(178,34,34));
                }
            } catch(Exception erro){
                
            }
            /*Ação*/
            botaoTema.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                        if(LerTema().equals("t")){
                            SetarTema("f");
                            dispose();
                            new Janela();
                        } else{
                            SetarTema("t");
                            dispose();
                            new Janela();
                        }
                    } catch(Exception erro){

                    }
                }
            });
            /*//Ação*/
            botaoTema.setFocusPainted(false);
            barraFrame.add(botaoTema);

            minimizar = new JButton(new ImageIcon(CaminhoMinimizar()));
            minimizar.setFont(BarraFrameFont);
            minimizar.setBackground(CorDeFundoBarraFrame());
            minimizar.setBorder(null);
            minimizar.setSize(32,32);
            minimizar.setLocation(636,0);
            /*Ação*/
            minimizar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setState(Janela.ICONIFIED);
                }
            });
            /*//Ação*/
            minimizar.setFocusPainted(false);
            barraFrame.add(minimizar);

            sair = new JButton(new ImageIcon(CaminhoSair()));
            sair.setFont(BarraFrameFont);
            sair.setBackground(CorDeFundoBarraFrame());
            sair.setBorder(null);
            sair.setSize(32,32);
            sair.setLocation(668,0);
            /*Ação*/
            sair.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            /*//Ação*/
            sair.setFocusPainted(false);
            barraFrame.add(sair);
            
        /*//Barra da Frame*/
        /*//Configuração da Frame*/

        /*Area do Texto*/
        caixaDeTexto = new JTextArea();
        caixaDeTexto.setLineWrap(true);
        caixaDeTexto.setWrapStyleWord(true);
        caixaDeTexto.setBackground(CorDeFundoTexto());
        caixaDeTexto.setForeground(CorDoTexto());
        caixaDeTexto.setFont(caixaFont);
        caixaDeTexto.setBorder(null);
        caixaDeTexto.setText("");
        
        /*Caso Abrir seja ativado*/

        scroll = new JScrollPane(caixaDeTexto);
        scroll.setBorder(null);
        scroll.setSize(690,558);
        scroll.setLocation(5,37);
        getContentPane().add(scroll);
        

        /*//Area do Texto*/

        /*Configurações Finais*/
        this.setVisible(true);
        /*//Configurações Finais*/
    }

    /*Seletor do Tema*/
    public Color CorDeFundoFrame(){
        Color x;
        try{
            if(LerTema().equals("t")){
                x = new Color(31,31,31);
                return x;
            } else {
                x = new Color(255,255,255);
                return x;
            }
            
        } catch (Exception erro){
            return  x = new Color(31,31,31);
        }
    }

    public Color CorDeFundoTexto(){
        Color x;
        try{
            if(LerTema().equals("t")){
                x = new Color(32,32,32);
                return x;
            } else {
                x = new Color(254,254,254);
                return x;
            }
            
        } catch (Exception erro){
            return  x = new Color(32,32,32);
        }
    }

    public Color CorDeFundoBarraFrame(){
        Color x;
        try{
            if(LerTema().equals("t")){
                x = new Color(23,23,23);
                return x;
            } else {
                x = new Color(248,248,248);
                return x;
            }
            
        } catch (Exception erro){
            return x = new Color(23,23,23);
        }
    }

    public Color CorDoTexto(){
        Color x;
        try{
            if(LerTema().equals("t")){
                x = new Color(230,230,230);
                return x;
            } else {
                x = new Color(6,6,6);
                return x;
            }
            
        } catch (Exception erro){
            return x = new Color(230,230,230);
        }
        
    }

    public String OnOrOff(){
        String x;
        try{
            if(LerTema().equals("t")){
                x = "On";
                return x;
            } else {
                x = "Off";
                return x;
            }
                
        } catch (Exception erro){
            return  x = "On";
        }
    }

    /*Criar config.txt*/
    public void SetarTema(String x){
        Path caminho = Paths.get("C:\\Users\\Public\\HuEscreva\\config.txt");
        String texto = x;
        byte[] textoEmByte = texto.getBytes();
        try{
            Files.write(caminho, textoEmByte);
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    /**/

    /*Salvar Arquivo*/
    public void SalvarArquivo(String y, String x){
        Path caminho = Paths.get(x);
        String texto = y;
        byte[] textoEmByte = texto.getBytes();
        try{
            Files.write(caminho, textoEmByte);
        }catch(Exception erro){
            erro.printStackTrace();
        }
    }
    /**/

    /*Abrir Arquivo*/
    public String AbrirArquivo(){
        String x;
        try{
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.showOpenDialog(getComponent(0));
            File f = fc.getSelectedFile();
            x =  f.getPath();            
        } catch (Exception erro){
            erro.printStackTrace();
            x = "";
        }
        return x;
    }
    /***/

    /*Ler Arquivo*/
    public String LerArquivo(String x){
        Path caminho = Paths.get(x);
        try{
            byte[] texto = Files.readAllBytes(caminho);
            String leitura = new String(texto);
            return leitura;
        } catch(Exception erro){
            erro.printStackTrace();
            return "";
        }
    }
    /**/

    /*Ler config.txt*/
    public String LerTema(){
        Path caminho = Paths.get("C:\\Users\\Public\\HuEscreva\\config.txt");
        try{
            byte[] texto = Files.readAllBytes(caminho);
            String leitura = new String(texto);
            return leitura;
        } catch(Exception erro){
            erro.printStackTrace();
            return "t";
        }
    }
    /**/

    /*Caminho Icone*/
    public String CaminhoIcone(){
        String x;
        try{
            if(LerTema().equals("t")){
                x = "C:\\Users\\Public\\HuEscreva\\icon\\icone2.png";
                return x;
            } else{
                x = "C:\\Users\\Public\\HuEscreva\\icon\\icone.png";
                return x;
            }
        } catch(Exception erro){
            return "HU";
        }

    }
    /***/

    /*Caminho Icone*/
    public String CaminhoMinimizar(){
        String x;
        try{
            if(LerTema().equals("t")){
                x = "C:\\Users\\Public\\HuEscreva\\icon\\icoMinimizar2.png";
                return x;
            } else{
                x = "C:\\Users\\Public\\HuEscreva\\icon\\icoMinimizar.png";
                return x;
            }
        } catch(Exception erro){
            return "_";
        }
    }
    /***/

    /*Caminho Icone*/
    public String CaminhoSair(){
        String x;
        try{
            if(LerTema().equals("t")){
                x = "C:\\Users\\Public\\HuEscreva\\icon\\icoSair2.png";
                return x;
            } else{
                x = "C:\\Users\\Public\\HuEscreva\\icon\\icoSair.png";
                return x;
            }
        } catch(Exception erro){
            return "HU";
        }
    }
    /***/

    /*Caminho Salvar Arquivo*/
    public String DiretorioArquivo(){
        String x;
        try{
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.showSaveDialog(getComponent(0));
            File f = fc.getSelectedFile();
            x = f.getPath();
        } catch (Exception erro){
            erro.printStackTrace();
            x = "";
        }
        return x;
    }
    /***/

    /*//Seleção do Tema*/

}