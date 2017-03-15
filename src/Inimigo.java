
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Inimigo {
    
    private Image imagem;
    private int x, y;
    
    private int largura, altura;
    
    //definir se o inimigo está visivel ou não
    private boolean isVisivel;
    
    //constante para inimigo não extrapolar a tela do jogo
    private static final int LARGURA_TELAJOGO = 500;
    
    //constante definir a velocidade do inimigo
    private static final int VELOCIDADE = 1;
    
    //construtor da classe
    public Inimigo(int x, int y) { //coordenadas da nave, para o inimigo saber onde vai aparecer
        
        this.x = x;
        this.y = y;
        
        ImageIcon referencia = new ImageIcon("res\\inimigo.png");
      
        
        // um objeto imageicon para colocar a imagem do missel
        
        imagem = referencia.getImage();
        
        //definir altura e largura dos inimigos
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
        
        isVisivel = true;
        
    }
    
    //método para o inimigo andar pela tela até onde ele conseguir, caso ele chegue no final da tela, ele irá voltar para o para o seu estado inicial
    public void mexer() {
        
        if (this.x < 0) {
            this.x = LARGURA_TELAJOGO;
        } else {
            this.x -= VELOCIDADE;
        }
        
    }
    
    //getter e setters para sabermos quais as coordenadas da nave
    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //método para tratar a colisão (quando a nave colidir com o inimigo, a nave irá ser destruída) (cria um retangulo invisivel ao redor do inimigo, para sabermos se a colisão foi realizada)
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }
    
    
    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }     
    
}
