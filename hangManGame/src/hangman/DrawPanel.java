package hangman;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class DrawPanel extends JPanel{
	private int stage = 0;
	DrawPanel(){
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(5,5,5,5),
				BorderFactory.createLineBorder(Color.RED)));
	}
	public Dimension getPreferredSize() {
		return new Dimension(90,120);
	}

boolean drawNext() {
	stage++;
	repaint();
	if(stage>6) {
		return true;
	}
	return false;
}
void reset() {
	stage = 0;
	repaint();
}
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	
	g.drawLine(20, 110, 100, 110);
	g.drawLine(70, 10, 70, 110);
	g.drawLine(30, 10, 70, 10);
	g.drawLine(30, 10, 30, 30);
	
	if(stage>0) {
		g.drawOval(20, 30, 20, 20); //head
	}
	if(stage>1) {
		g.drawLine(30, 50, 30, 80); //body
	}
	if(stage>2) {
		g.drawLine(30,58,10,50); //left arm
	}
	if(stage>3) {
		g.drawLine(30,58,50,50); //right arm
	}
	if(stage>4) {
		g.drawLine(30,80,10,100); //left leg
	}
	if(stage>5) {
		g.drawLine(30,80,50,100); //right leg
	}
	
}
}