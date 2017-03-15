
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Missel {
    
    private Image imagem;
    private int x, y;
    private int largura, altura;
    
    //definir se o missel está visivel ou não
    private boolean isVisivel;
    
    //constante para o missvel não extrapolar a tela do jogo
    private static final int LARGURA_TELAJOGO = 500;
    
    //constante definir a velocidade do missel
    private static final int VELOCIDADE_MISSEL = 2;
    
    //construtor da classe
    public Missel(int x, int y) { //coordenadas da nave, para o misesl saber onde vai aparecer
        
        this.x = x;
        this.y = y;
        
        // um objeto imageicon para colocar a imagem do missel
        ImageIcon referencia = new ImageIcon("res\\misselnave.png");
        imagem = referencia.getImage();
        
        //definir altura e largura do missel
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        isVisivel = true;
        
    }
    
    //método para o missel "andar" pela tela
    public void mexer() {
        
        this.x += VELOCIDADE_MISSEL;
        
        //fazer o missel sumir quando ele sair da tela
        if (this.x > LARGURA_TELAJOGO) {
            isVisivel = false;
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

    public boolean isIsVisivel() {
        return isVisivel;
    }

    public void setIsVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }
    
    //método para tratar a colisão (quando a nave colidir com o inimigo, a nave irá ser destruída) (cria um retangulo invisivel ao redor do missel, para sabermos se a colisão foi realizada)
    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }   
    
}
