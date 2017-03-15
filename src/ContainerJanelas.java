import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class ContainerJanelas extends JFrame {
    
    public ContainerJanelas() {
        //criando a barra do menu
        JMenuBar barMenu = new JMenuBar();
        
        JMenu menu = new JMenu("Menu");
        
        JMenuItem sobre = new JMenuItem("Sobre");
        
        JMenuItem exit = new JMenuItem("Sair");
        
        //exibir informações quando o jogador clicar em SOBRE, no menu do jogo
        sobre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Jogo desenvolvido por Fernando Juriolli, para a cadeira de Programação Orientada a objetos da faculdade Estácio do Recife", "Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});
                
                //fazer o jogo sair quando o jogador apertar o SAIR
                JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
                
                //adicionando os itens no menu
                menu.add(sobre);
		menu.add(sair);
		
		barMenu.add(menu);
		
		setJMenuBar(barMenu);
        
        //criando a fase e setando parâmetros
        add(new Fase());
        setTitle("Perdido no Espaço");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main (String [] args) {
        
        new ContainerJanelas();
    }
    
}
