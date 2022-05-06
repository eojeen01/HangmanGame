package hangman;

import java.awt.*;
import javax.swing.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

class MissPanel extends JPanel{


	private JLabel misses;
	private boolean[] flag;
	static final int NUM_ALPHA = 26;
	MissPanel(){
		add(new JLabel("Misses: "));
		add(misses = new JLabel());
		flag = new boolean[NUM_ALPHA];
	}

	void add(char c) {
		flag[c-'a'] = true;
		misses.setText(scan());
	
	}
	void reset() {
		misses.setText("");
		for(int i = 0;i<NUM_ALPHA;i++) {
			flag[i]=false;
		}
	}
	private String scan() {
		int i, j, len = 0;
		
		for(i=0; i<NUM_ALPHA; i++) {
			if(flag[i] == true) {
				len++;
			}
		}
		char[] buf = new char[len*2];
		
		for(i=j=0; i<NUM_ALPHA;i++) {
			if (flag[i]==true) {
				
				buf[j++] = (char) ('a'+i);
				buf[j++] = ' ';
			}
		}
		return new String(buf);
	}



	
	
}
