import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Pomodoro extends JFrame implements ActionListener{
	List<JButton> botoes = new ArrayList<>();
	Cronometro cronometro = new Cronometro();
	private JButton pomo = new JButton("Pomo");
	private JButton breaker = new JButton("Break");
	private JPanel painel = new JPanel();
	
	public Pomodoro(){
		organizarLayout();
		setSize(300,150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pomo.addActionListener(this);
		breaker.addActionListener(this);
		
	}
	void organizarLayout(){
		setTitle("POMOBEER");
		setAlwaysOnTop(true);
		setLayout(new BorderLayout());
		painel.add(pomo); 
		painel.add(breaker);
		
		botoes.add(pomo); 
		botoes.add(breaker);

		cronometro.setSize(232,50);
		
		add(painel, BorderLayout.SOUTH);
		add(cronometro, BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			
			if(botao.getText().equals("Pomo")) {
				botoes.stream()
				.filter(o -> o.getText().equals("Relax"))
				.forEach(o -> o.setText("Break"));
			}
			
			if(botao.getText().equals("Break")) {
				botoes.stream()
				.filter(o -> o.getText().equals("Pause"))
				.forEach(o -> o.setText("Pomo"));
			}
			
			cronometro.comando(botao);
			
		}
	}
}