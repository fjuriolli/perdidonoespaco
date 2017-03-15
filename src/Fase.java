import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
    
    private Image fundo;
    private Nave nave;
    private Timer timer;
    //variável para saber se o jogador está no jogo ou não
    private boolean emJogo;
    
    //criar uma lista de inimigos
    private List<Inimigo> inimigos;
    
    //matriz para criarmos as posições de cada inimigo (onde cada inimigo vai nascer, no caso, x e y)
    private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },
            { 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
            { 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
            { 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
            { 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
            { 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
            { 920, 300 }, { 856, 328 }, { 456, 320 } 
        };
    
    //construtor da fase
    public Fase () {
        
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(new TecladoAdapter());
        
        ImageIcon referencia = new ImageIcon("res\\fundouniverso.png");
        fundo = referencia.getImage();
        
        nave = new Nave();
        
        //setamos que o jogador está jogando (em jogo)
        emJogo = true;
        
        //inicializando os inimigos
        criarInimigos();
        
        timer = new Timer(5, this);
        timer.start();
    }
    
    //método para iniciar os inimigos
    public void criarInimigos() {
        
        //instanciar a lista de inimigos
        inimigos = new ArrayList<Inimigo>();
        
        //for para adicionar todos os elementos que queremos na tela (no caso aqui, os inimigos)
        for (int i = 0; i < coordenadas.length; i++) {
        //adicionar os inimigos pela matriz que criamos (linha a linha)
           inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1])); 
        }
        
    }
    
    //método para checar as colisões
    public void checaColisao() {
        
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;
        
        //for para testar as colisões da nave com os inimigos
        for (int i = 0; i < inimigos.size(); i++){
            
            //criar inimigos temporarios e pegar a forma de colisão deles
            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();
            
            //if para dizermos o que irá acontecer caso a nave se colida(intersects) com um inimigo
            if(formaNave.intersects(formaInimigo)) {
                //setando a visibilidade da nave para falso (nave destruída)
                nave.setVisivel(false);
                //setando a visibilidade do inimigo em falso também (inimigo morto por se colidir com a nave do jogador)
                tempInimigo.setIsVisivel(false);
                //como o jogador tem somente uma vida, caso haja a colisão com o inimigo, o jogo irá terminar
                emJogo = false;
                
            }
            
        }
        
        //criar uma lista que irá receber os elementos da nave, no caso, os elementos da nave são os misseis
        List<Missel> misseis = nave.getMisseis();
        
        //for para testar os misseis
        for (int i = 0; i < misseis.size(); i++) {
            
            Missel tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();
            
            //testar cada elemento da lista de inimigos
            for (int k = 0; k < inimigos.size(); k++) {
                
                Inimigo tempInimigo = inimigos.get(k);
                formaInimigo = tempInimigo.getBounds();
                
                //se a forma do missel interceptar a forma do inimigo, então o missel irá acertar o inimigo e o mesmo irá ser destruido
                if (formaMissel.intersects(formaInimigo)) {
                    
                    //o inimigo irá morrer
                    tempInimigo.setIsVisivel(false);
                    //o missel também não ficará mais visivel, pois 1 missel mata somente 1 inimigo
                    tempMissel.setIsVisivel(false);
                }
                
            }
        }
    }
            
    
    public void paint (Graphics g) {
        
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        
        //testar se o jogo está em execução
            if (emJogo) {
            
            //pintar a nave na tela
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

            List<Missel> misseis = nave.getMisseis();

            //for para iterar todos os elementos da lista de misseis e mostrar ao usuario os misseis que a nave possui
            for(int i = 0; i < misseis.size(); i++) {

                Missel m = (Missel) misseis.get(i);
                //fazer a "pintura" do missel na tela
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);

            }

             //for para iterar todos os elementos da lista de inimigos e mostrar ao usuario os inimigos na tela
            for(int i = 0; i < inimigos.size(); i++) {

                Inimigo n = inimigos.get(i);
                //fazer a "pintura" dos inimigos na tela
                graficos.drawImage(n.getImagem(), n.getX(), n.getY(), this);

            }
            
            //colocando uma mensagem na tela de quantos inimigos ainda estão faltando para o jogador destruir
            graficos.setColor(Color.WHITE);
            graficos.drawString("Inimigos:" + inimigos.size(), 5, 15);
            
    //se não estiver em jogo, então...        
    } else {
        //colocar a imagem de game over na tela
        ImageIcon fimDeJogo = new ImageIcon("res//gameover2.gif");
        
        //pegando a imagem e colocando na tela toda
        graficos.drawImage(fimDeJogo.getImage(), 0, 0, null);
        
            }
        
        g.dispose();
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        //testar se o jogador ganhou ou perdeu o jogo (se todos os inimigos morreram)
        if(inimigos.size() == 0) {
            emJogo = false;
        }
        
        //printar os misseis que serão possíveis aparecer na tela e remover os que não serão mais necessários
        List<Missel> misseis = nave.getMisseis();
        
        for (int i = 0; i < misseis.size(); i++) {
            Missel m  = (Missel) misseis.get(i);
            //saber se o missel está visível ou não
            if(m.isIsVisivel()) {
                m.mexer();
            } else {
                misseis.remove(i);
            }
        }
        
         for (int i = 0; i < inimigos.size(); i++) {
            Inimigo n  = inimigos.get(i);
            //saber se o inimigo está visível ou não
            if(n.isIsVisivel()) {
                n.mexer();
            } else {
                inimigos.remove(i);
            }
        }
        
        //chamar o método para fazer a nave andar pela tela
        nave.mexerNaTela();
        //chamar o método para testar as colisões
        checaColisao();
        //repintar a tela
        repaint();
    }
    
    private class TecladoAdapter extends KeyAdapter {

        
        @Override
        public void keyPressed(KeyEvent e) {
            //tratando quando o usuario clica enter na tela do gameover
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                emJogo = true;
                nave = new Nave();
                criarInimigos();
            }
            
            nave.keyPresses(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            nave.keyRelease(e);
        }
                
    }
    
    
    
}
