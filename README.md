# HangmanGame

객체지향 프로그래밍
[Hang Man Game]
202021066 박어진
I.	전체 소스코드
i.	CountPanel 클래스
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
ii.	DrawPanel 클래스
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

iii.	HangMan 클래스
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


iv.	MissPanel 클래스
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

v.	Music 클래스
package hangman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Hangman.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

vi.	WordPanel 클래스
package hangman;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

class WordPanel extends JPanel{
	private JTextField tf;
	private String word;
	private String mean;
	private int len;
	private char[] buf;
	private int a;
	WordPanel(){
		add(new JLabel("Word : "));
		add(tf = new JTextField(12));
		tf.setEditable(false);
		tf.setHorizontalAlignment(JTextField.CENTER);
	}
	void setWord(String word) {
		this.word = word;
		len = word.length();
		buf = new char[len];
		for (int i = 0; i<len; i++) {
			buf[i]='_';
		}
		tf.setText(new String(buf));
	}
	String getWord() {
		return word;
	}
	
	
	int getWordLength() {
		return len;
	}
	boolean match(char c) {
		boolean found = false;
		for(int i = 0; i<len; i++) {
			if(word.charAt(i)==c) {
				buf[i]=c;
				found = true;
			}
		}
		tf.setText(new String(buf));
		return found;
		
	}
	boolean isAllMatched() {
		for(int i = 0; i<len; i++) {
			if(buf[i]=='_') {
				return false;
			}
		}
		return true;
	}
}

vii.	Words 클래스
package hangman;

import java.util.Random;

class Words {
	private Random rand;
	private static int a;
Words(){
	rand = new Random();
}
String getWord() {
	a = rand.nextInt(words.length);
	String to = Integer.toString(a);
	return words[a];
}
static String getMean() {
	return meanings[a];
}
static String getEx() {
	return examples[a];
}




String[] words = {
	"advice", "progress", "audit", "similar", "prior", "upcoming", "contribute",
	"original", "loan", "career", "accompany","volunteer", "operation", "mechanic",
	"photograph", "relation", "highly", "fix", "behind", "acquire", "maintain",
	"opening", "analysis", "deal", "amount", "exercicse","agent", "manual", "entrance",
	"consult"
};
static String[] meanings = {
		"조언", "진전", "회계 감사", "비슷한", "~전의", "다가오는", "기여하다", "원래의", "대출",
		"경력", "동반하다", "지원하다", "업무", "정비사", "사진", "관계", "대단히", "고치다", "뒤에",
		"얻다", "유지하다", "시작", "분석", "계약", "총액", "운동하다", "상담원", "수동식", "입구", "상담하다"
};

static String[] examples = {"They had been primed with good advice.\n그들은 좋은 충고들을 듣고 대비가 되어 있는 상태였다.",
	"Progress has been made on all fronts.\n모든 영역에서 진전이 이루어져 왔다."	,
	"There will be a company-wide audit by our holding company from March 1 to 23.\n3월 1일부터 23일까지 당사 지주회사가 진행하는 전사적인 감사가 실시됩니다.",
	"To the untrained eye, the products look remarkably similar.\n훈련되어 있지 않은 눈에는 그 상품들이 대단히 비슷해 보인다.",
	"Students should have prior experience of veterinary practice.\n학생들은 가축병원에서 이미 실습한 경험이 있어야 한다.",
	"a single from the band’s upcoming album\n곧 발매될 그 밴드의 앨범에서 뽑은 싱글곡",
	"Would you like to contribute to our collection?\n저희 모금에 기부를 하시겠습니까?",
	"He never deviated from his original plan.\n그는 원래 계획에서 결코 벗어나지 않았다.",
	"The loan was denominated in US dollars.\n그 융자금은 미국 달러화로 액수가 매겨졌다.",
	"His career has been blighted by injuries.\n그의 경력은 잦은 부상으로 엉망이 되었다.",
	"He will accompany you.\n그는 너와 같이 갈 것이다.",
	"Volunteer work gives her life (a sense of) purpose.\n자원 봉사 활동은 그녀의 삶에 목적의식을 부여해 준다.",
	"The police operation was encumbered by crowds of reporters.\n경찰의 작전은 몰려든 기자들 때문에 지장을 받았다.",
	"The mechanic was wearing a pair of blue overalls.\n그 기술자는 푸른색 작업복을 입고 있었다.",
	"The delegates posed for a group photograph.\n대표들이 단체 사진 촬영을 위해 포즈를 취했다.",
	"the relation between rainfall and crop yields\n강우량과 작물 수확량 사이의 관련성",
	"The dolphin has evolved a highly developed jaw.\n돌고래는 대단히 발달된 턱을 진화시키게 되었다.",
	"Don’t worry—I’ll fix him.\n걱정 마. 내가 그를 손을 좀 보겠어.",
	"The bride’s dress trailed behind her.\n신부의 드레스가 뒤로 끌렸다.",
	"One can acquire everything in solitude - except character.\n사람은 혼자서 모든 것을 습득할 수 있다. 성격은 빼고.",
	"The house is large and difficult to maintain.\n그 집은 커서 유지하기가 힘들다.",
	"The movie has an exciting opening.\n그 영화는 시작 부분이 신난다.",
	"In analysis the individual resolves difficult emotional conflicts.\n정신분석을 통해 그 개인은 힘든 정서적 갈등을 푼다.",
	"The deal must be agreeable to both sides.\n거래는 양측 모두에게 알맞아야 한다.",
	"He earns an obscene amount of money.\n그는 돈을 터무니없이 많이 번다.",
	"How often do you exercise?\n운동은 얼마나 자주 하세요?",
	"Our agent in New York deals with all US sales.\n뉴욕에 있는 저희 대리점이 모든 미국 판매를 담당합니다.",
	"Leave the controls on manual.\n제어 장치를 비자동으로 놔두어라.",
	"A lighthouse marks the entrance to the harbour.\n등대 하나가 그 항구로 들어가는 입구를 보여준다.",
	"If the pain continues, consult your doctor.\n통증이 계속되면 의사와 상담하세요."
		
};
}
II.	객체지향 개선
i.	기존 프로그램에 비해 개선된 부분
단어를 맞추는데 걸리는 시간을 측정하고, 걸린 시간에 따라 등급을 A+, A, B, C로 나누어 출력한다. 철자를 모두 맞추거나, 틀려서 게임이 끝났을 경우 단어의 뜻과 예문을 보여주어서 교육적인 프로그램으로 개선시켰다. 사용자가 프로그램을 실행하며 지루함을 느끼지 않도록 배경음악을 재생시켰으며, 단어를 맞췄을 때와 틀렸을 때 효과음을 재생할 수 있게 했다.
ii.	완성된 프로그램에서 더 개선할 수 있는 부분
걸린 시간에 따라 등급을 출력하는 것과 함께 순위를 나타내는 기능을 추가하여 개선할 수 있다. 사용자의 닉네임을 입력 받고 게임 진행 시간이 더 적은 사용자부터 정렬하여 출력하면 더 완성도 높은 프로그램이 될 것이라고 예상한다. 그리고 현재 프로그램은 철자를 틀렸을 경우 그려지는 사람을 도형으로 출력시켰기 때문에 창의 크기를 키울 경우 사람의 비율이 무너지는 단점이 있다. 따라서 사람을 도형이 아닌 png 파일로 나타내면 이러한 부분을 개선할 수 있을 것이라고 생각한다.
