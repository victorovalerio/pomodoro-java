import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Cronometro extends JPanel implements ActionListener{
	private Timer timer;
	private JLabel texto = new JLabel("25:00");
	long tempoInicial = 0;
	long tempoCorrido = 0;
	long tempoRestante = 0;
	long tempoRestanteMinutos = 0;
	long tempoRestanteSegundos = 0;
	String tempoString = "Teste";
	boolean pause = false;
	boolean pauseBreak = false;

	Cronometro(){		
		timer = new Timer(1000, this);
		texto.setForeground(Color.BLACK);
		texto.setFont(new Font("courier", Font.PLAIN, 30));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10,25));
		add(texto);
		
	}
	
	Cronometro getThis(){
		return this;
	}
	
	public void comando(JButton botao) {
		switch(botao.getText()){
			case "Pomo":
				System.out.println("INICIO");
				if(!pause) {
					tempoInicial = 1500010 + System.currentTimeMillis(); //25 min (25 * 60 * 1000);
					tempoString = "25:00";
					texto.setText("25:00");
				}
				else 
				tempoInicial += System.currentTimeMillis();
				pause = false;
				pauseBreak = false;
				timer.start();
				botao.setText("Pause");
				break;
			case "Pause":
				System.out.println("PAUSE!");
				pause = true;
				pauseBreak = false;
				tempoInicial = tempoRestante;
				timer.stop();
				botao.setText("Pomo");
				break;
			case "Break":
				System.out.println("BREAK");
				if(!pauseBreak) {
					tempoInicial = System.currentTimeMillis() + 300010; //5 min (25 * 60 * 1000);
					tempoString = "05:00";
					texto.setText("05:00");
				}else
					tempoInicial += System.currentTimeMillis();
				pause = false;
				pauseBreak = false;
				timer.start();
				tempoCorrido = System.currentTimeMillis();
				botao.setText("Relax");
				break;
			case "Relax":
				System.out.println("RELAXA!");
				pause = false;
				pauseBreak = true;
				tempoInicial = tempoRestante;
				timer.stop();
				botao.setText("Break");
				break;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tempoCorrido = System.currentTimeMillis();
		tempoRestante = tempoInicial-tempoCorrido;
		tempoRestanteMinutos = ((tempoRestante/1000)/60);
		tempoRestanteSegundos = ((tempoRestante/1000)%60);
		boolean zero = tempoRestanteSegundos < 10;
		boolean zeroMin = tempoRestanteMinutos < 10;
		
				tempoString = (zeroMin ? "0" + String.valueOf(tempoRestanteMinutos)
					: String.valueOf(tempoRestanteMinutos))
				+ ":" +
				(zero ? "0" + String.valueOf(tempoRestanteSegundos)
					: String.valueOf(tempoRestanteSegundos));
				
		texto.setText(tempoString);
		
		if(tempoRestante<= 0) {
			timer.stop();
			System.out.println("DRRRRRRRRRRRRRIM!");
		}
	}
}
