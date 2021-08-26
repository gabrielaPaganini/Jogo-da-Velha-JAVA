package jogoDaVelha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JogoDaVelha extends JFrame{
	
	ImageIcon iconCirculo = new ImageIcon(getClass().getResource("circulo.png"));
	ImageIcon iconX = new ImageIcon(getClass().getResource("x.png"));

	JPanel pTela = new JPanel(new GridLayout(3,3,10,10));
	
	Bloco[] blocos = new Bloco[9];
	
	int rodadas = 0;
	
	final int jogador_1 = 1;
	final int jogador_2 = 2;
	
	int jogadorVez = jogador_1;

	JLabel lInformacao = new JLabel("Jogador "+jogador_1);
	
	public JogoDaVelha() {
		configurarJanela();
		configurarTela();
	}
	
	public void configurarTela() {
		add(BorderLayout.CENTER,pTela);
		add(BorderLayout.NORTH,lInformacao);
		pTela.setBackground(Color.BLACK);
		lInformacao.setFont(new Font("Arial", Font.BOLD, 35));
		lInformacao.setForeground(new Color(50,200,50));
		lInformacao.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i=0;i<9;i++) {
			Bloco bloco = new Bloco();
			blocos[i] = bloco;
			pTela.add(bloco);
		}
	}
	
	public void mudarVez() {
		if(jogadorVez == 1) {
			jogadorVez = 2;
			lInformacao.setText("jogador 2");
		}else {
			jogadorVez = 1;
			lInformacao.setText("jogador 1");

		}
	}
	
	public boolean testarVitoria(int jog) {
		if(blocos[0].quem==jog && blocos[1].quem==jog && blocos[2].quem==jog) {
			return true;
		}
		if(blocos[3].quem==jog && blocos[4].quem==jog && blocos[5].quem==jog) {
			return true;
		}
		if(blocos[6].quem==jog && blocos[7].quem==jog && blocos[8].quem==jog) {
			return true;
		}
		if(blocos[0].quem==jog && blocos[3].quem==jog && blocos[6].quem==jog) {
			return true;
		}
		if(blocos[1].quem==jog && blocos[4].quem==jog && blocos[7].quem==jog) {
			return true;
		}
		if(blocos[2].quem==jog && blocos[5].quem==jog && blocos[8].quem==jog) {
			return true;
		}
		if(blocos[0].quem==jog && blocos[4].quem==jog && blocos[8].quem==jog) {
			return true;
		}
		if(blocos[6].quem==jog && blocos[4].quem==jog && blocos[2].quem==jog) {
			return true;
		}
		return false;
	}
	
	public void configurarJanela() {
		setTitle("Jogo da Velha");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JogoDaVelha();
	}
	
	public class Bloco extends JButton{
		int quem = 0;
		public Bloco() {
			setBackground(Color.WHITE);
			addActionListener(e->{
				if(quem==0) {
					if(jogadorVez==jogador_1) {
						setIcon(iconCirculo);
						quem = jogador_1;
					}else {
						setIcon(iconX);
						quem = jogador_2;
					}
					if(testarVitoria(quem)) {
						JOptionPane.showMessageDialog(null,"jogador " +quem+" venceu!");
						System.exit(0);
					}
					rodadas++;
					if(rodadas == 9) {
						JOptionPane.showMessageDialog(null,"Deu velha!");
						System.exit(0);
					}
					mudarVez();
				}
			});
		}
	}
}
