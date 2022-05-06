package hangman;

import java.awt.*;
import javax.swing.*;

public class CountPanel extends JPanel {
private JLabel lb_cnt;
private int count;
	CountPanel(){
		add(new JLabel("count Down: "));
		add(lb_cnt = new JLabel());
	}
	void setCount(int count) {
		lb_cnt.setText(String.valueOf(this.count = count));
	}
	int decrement() {//return -- count;
		lb_cnt.setText(String.valueOf(--count));
		return count;
	}
}
