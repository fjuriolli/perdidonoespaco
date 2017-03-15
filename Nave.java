import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Nave {
    
    private int x, y;
    private int dx, dy;
    private Image imagem;
    //estrutura de dados para manipular os misseis
    private List<Missel> misseis;
    //definir a altura e largura da nave
    private int altura, largura;
    //defirnir uma variável para setarmos se a nave está ou não visível (morta ou vida)
    private boolean isVisivel;
    
    public Nave() {
        
        ImageIcon referencia = new ImageIcon("res\\nave2.gif");
        imagem = referencia.getImage();
        
        //setando a altura e largura da nave
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        
        //instanciar a lista de misseis
        misseis = new ArrayList<Missel>();
        
        this.x = 100;
        this.y = 100;
         
    }    
    
    public void mexerNaTela () {
        
        x += dx;
        y += dy;
        
        //tratar para que a nave não exceda as bordas da tela do jogo
        if (this.x < 1) {
            x = 1;
        }
        
        if (this.x > 462) {
            x = 462;
        }
        
        if (this.y < 1) {
            y = 1;
        }
        
        if (this.y > 340) {
            y = 340;
        }
        
    }
    
    //metodo para pegar a lista de misseis
    public List<Missel> getMisseis() {
        return misseis;
    }
                       
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
    
    //getters e setters para o booleano isVisive l
    public boolean isVisivel() {
        return isVisivel;
    }
    
    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
    
    //método para tratar como a nave irá atirar, toda vez que clicarmos na tecla para atirar, o método irá criar um novo missel para a nave
    public void naveAtiraMissel() {
        // somar o x com a largura da nave, para o missel aparecer um pouco a frente da nave
        //somar o y com a altura dividido por 2, para o missel não sair muito pra cima da nave
        this.misseis.add(new Missel(x + largura, y + altura/2));
        
    }
    //método para tratar a colisão (quando a nave colidir com o inimigo, a nave irá ser destruída (cria um retangulo invisivel ao redor da nave, para sabermos se a colisão foi realizada)    
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }
    
    
    public void keyPresses(KeyEvent tecla) {
        //métodos para quando o jogador PRESSIONAR a tecla no teclado (espaço, pra cima, pra baixo, direita e esquerda)
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_SPACE) {
            naveAtiraMissel();
        }
        
        if (codigo == KeyEvent.VK_UP) {
            dy = -1;
        }
        
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 1;
        }
        
         if (codigo == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        
    }
    
    public void keyRelease(KeyEvent tecla) {
        //métodos para quando o jogador SOLTAR a tecla no teclado
        int codigo = tecla.getKeyCode();
        
        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }
        
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        
         if (codigo == KeyEvent.VK_LEFT) {
            dx = -0;
        }
        
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
    
}
