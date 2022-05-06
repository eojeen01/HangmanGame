package hangman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



class Hangman extends JFrame implements ActionListener{
	
	Music introMusic = new Music("Cemetery.mp3",true);
	
	public static void main (String[]args) {
	
		new Hangman();
		
	}
	Hangman(){
		setSize(380,170);
		setTitle("Hangman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeUI();
		setVisible(true);
		
		
		introMusic.start();
	}
	static final int COUNT = 6;
	private WordPanel wp;
	private CountPanel cp;
	private JTextField tf;
	private DrawPanel dp;
	private MissPanel mp;
	private Words words;
	private Timer T;
	

	private void makeUI(){
		words = new Words();
		wp = new WordPanel();
		wp.setWord(words.getWord());
		cp = new CountPanel();
		cp.setCount(COUNT);
		
		
		
		JPanel gp = new JPanel();
		gp.add(new JLabel("Guess: "));
		gp.add(tf = new JTextField(1));
		tf.addActionListener(this);
		
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(4,1));
		right.add(wp);
		right.add(gp);
		right.add(mp = new MissPanel());
		right.add(cp);

		
	
		
		setLayout(new BoxLayout(getContentPane(),
				BoxLayout.X_AXIS));
		add(dp = new DrawPanel());
		add(right);

		
	}
	long start = System.currentTimeMillis();
	@Override
	public void actionPerformed(ActionEvent e) {
		char c = tf.getText().charAt(0);
		tf.setText("");
		if(wp.match(c)==false) {
			mp.add(c);
			dp.drawNext();
			if(cp.decrement()==0) {
				long end = System.currentTimeMillis();
				System.out.println((end-start)/1000+" milliseconds");
				Music overMusic = new Music("lose.mp3",true);
				overMusic.start();
				JOptionPane.showMessageDialog(this, "Mission Failed\n"+wp.getWord()+"\n"+"["+Words.getMean()+
							"]\n"+Words.getEx());
			reset();
			overMusic.close();
			}
		}
		else if(wp.isAllMatched()){			
			long end = System.currentTimeMillis();
			System.out.println((end-start)/1000+" milliseconds");
			Music winMusic = new Music("win.mp3",true);
			winMusic.start();
			if(end-start<=30000) {
				JOptionPane.showMessageDialog(this, "Mission Success\n"+"A+\n"+wp.getWord()+"\n"+"["+Words.getMean()+
						"]\n"+Words.getEx());
			}
			else if(end-start>40000) {
			JOptionPane.showMessageDialog(this, "Mission Success\n"+"A\n"+wp.getWord()+"\n"+"["+Words.getMean()+
					"]\n"+Words.getEx());
			}
			else if(end-start>50000) {
				JOptionPane.showMessageDialog(this, "Mission Success\n"+"B\n"+wp.getWord()+"\n"+"["+Words.getMean()+
						"]\n"+Words.getEx());
				}
			else if(end-start>80000){
				JOptionPane.showMessageDialog(this, "Mission Success\n"+"C\n"+wp.getWord()+"\n"+"["+Words.getMean()+
						"]\n"+Words.getEx());
				}	
			reset();
			winMusic.close();
		}
	
        
	}
	
	
	void reset() {
		long start = System.currentTimeMillis();
		wp.setWord(words.getWord());
		cp.setCount(6);
		dp.reset();
		mp.reset();
	}
	
}
