import java.util.Scanner;

/**
 * 
 * @authors Ricardo e Vladyslav
 *
 */
public class Main {

	private static final String ROLETA = "roleta";
	private static final String PUZZLE = "puzzle";
	private static final String PAINEL = "painel";
	private static final String PONTOS = "pontos";
	private static final String SAIR = "sair";
	private static final String NAO_ACABOU = "O jogo ainda nao tinha terminado";
	private static final String PARABENS = "Parabens!";
	private static final String MOEDA = "euros";
	private static final String GANHOU = "Ganhou";
	private static final String PERDEU = "Infelizmente nao ganhou dinheiro";
	private static final String VALOR_INVALIDO = "Valor invalido";
	private static final String LETRA_INVALIDA = "Letra invalida";
	private static final String JOGO_JA_TERMINOU = "O jogo terminou";
	private static final String COMANDO_INVALIDO = "Comando invalido";

	// verifica as possibilidades no fim do jogo
	private static void checkQuitOutcome(SystemIDK game) {
		if (!game.isCompleted()) {
			System.out.println(NAO_ACABOU);
		} else if (game.isCompleted() && game.getTotalPoints() > 0) {
			System.out.println(PARABENS + " " + GANHOU + " " + game.getTotalPoints() + " " + MOEDA);
		} else if (game.isCompleted() && game.getTotalPoints() <= 0) {
			System.out.println(PARABENS + " " + PERDEU);
		}
	}

	/**
	 *  verifica as possibilidades da opção puzzle
	 *  @pre: guess != null && 0 < guess.length() < 100
	 */
	
	private static void checkPuzzle(Scanner input, SystemIDK game) {
		String guess = input.nextLine();
		guess = guess.trim();
		if (game.isCompleted() && game.getCurrentRound() == game.getMaxRounds() ) { // neste caso se o segredo já estiver revelado
			System.out.println("O jogo terminou");
		} else if (game.isGuessCorrect(guess)) {
			game.sucess();
			game.nextRound();
		} else {
			game.fail();
			game.nextContestant();

		}
	}
	
	private static void nextContestant(SystemIDK game) {
		ContestantIterator c1 = game.iteratorOfContestants();
		while(c1.hasNext()) {
			Contestant c11 = c1.next();
			if(!c1.hasNext()) {
				c1.resetContestant();
			}
		}
	}
	// j a t
	
	private static void lastPlay(SystemIDK game) {
		if (game.isCompleted()) {
			game.nextRound();
		}
	}

	// verifica se a pessoa acertou na letra ou errou e adiciona ou remove pontos
	private static void verification(int points, String letter, SystemIDK game) {
		letter = letter.trim();
		if (!game.isTheLetterRepeated(letter.charAt(0)) && game.isTheLetterInTheSecret(letter)) {
			//game.isLetter(letter.charAt(0)) && letter.length() == 1 ^ 
			game.pointsAdd(letter, points);
			lastPlay(game);
		} else {
			game.pointsPenalize(points);
			game.nextContestant();
		}
	}
	// ------ -a-a-a ba-a-a batata = batata // round = 5

	/**
	 * 
	 * @pre: rouletPoints > 0
	 * @pre: 0 < letter.length() < 40 && letter != null
	 * 
	 */
	private static void roletaOutcomes(Scanner input, SystemIDK game) {
		int rouletPoints = input.nextInt();
		String letter = input.nextLine();
		letter = letter.trim();
		if (rouletPoints <= 0) {
			System.out.println(VALOR_INVALIDO);
		} else if (!game.isLetter(letter.charAt(0)) || letter.length() != 1) {
			System.out.println(LETRA_INVALIDA);
		} else if (game.isCompleted() && game.isLetter(letter.charAt(0)) || game.getCurrentRound() == game.getMaxRounds()){
			System.out.println(JOGO_JA_TERMINOU);
		} else {
			verification(rouletPoints, letter, game);
		}

	}

	// irá executar uma das opções escolhidas pelo utilizador
	private static void executeOption(Scanner input, String option, SystemIDK game) {
		switch (option) {

		case ROLETA:
			roletaOutcomes(input, game);
			break;
		case PUZZLE:
			checkPuzzle(input, game);
			break;
		case PAINEL:
			System.out.println(game.getThePanel());
			break;
		case PONTOS:
			System.out.println(game.getName() + " tem " + game.getTotalPoints() + " pontos");
			break;
		case SAIR:
			checkQuitOutcome(game);
			break;
		default:
			System.out.println(COMANDO_INVALIDO);
			input.nextLine();
			break;
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int maxRounds = input.nextInt();
		int maxContestants = input.nextInt();
		SystemIDK game = new SystemIDK(maxRounds, maxContestants);
		
		for(int i = 0; i < maxContestants; i++) {
			game.registerContestant(input.nextLine());
			input.next();
		}
		
		String option;
		

		do {
			option = input.next();
			executeOption(input, option, game);

		} while (!option.equals(SAIR));
		input.close();
	}

}

//Feito
//Rondas, Contestants em arrays. Trocamos de rondas e de contestants.
//Temos iteradores para o registo de concorrentes e segredos. E para trocar de segredo e de concorrente

//Falta
//Função que inicializa um nova ronda (quando painel.equals(segredo)) : Criar um painel com o novo segredo. Coloca os pontos de toda a gente a 0. Guarda o dinheiro do vencedor.
//E passa para o proximo concorrente