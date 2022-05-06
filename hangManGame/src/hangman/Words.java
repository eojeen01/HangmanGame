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
