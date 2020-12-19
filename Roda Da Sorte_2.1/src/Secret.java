/**
 * @authors Ricardo e Vladyslav
 * 
 *          esta classe ir� lidar com as opera��es relacionadas ao painel
 *          cria��o do mesmo, verifica��o de letras , substitui��o etc...
 * 
 */
public class Secret {
	// ** Vari�veis de inst�ncia **/
	private String panel; // esta vari�vel representa o painel
	private String secret; // esta vari�vel � o segredo
	private int counter; // servir� para contar n�meros de letras presentes na palavra

	/**
	 * Construtor
	 * 
	 * @param ---> segredo do jogo
	 * @pre: secret != null && 1 < secret.length() < 100
	 */

	public Secret(String secret) {
		this.secret = secret;
		counter = 0;
	}

	/**
	 * cria��o do painel da seguinte forma: ------
	 * 
	 * @param word ---> � o segredo
	 * @return ---> cria��o do painel para o jogo
	 * @pre: secret != null && 1 < secret.length() < 100
	 */

	public String createPanel(String secret) {
		char[] temppanel = secret.toCharArray();
		for (int i = 0; i < secret.length(); i++) {
			if (temppanel[i] >= 'a' && temppanel[i] <= 'z') {
				temppanel[i] = '-';
			}
			temppanel.toString();
			panel = String.valueOf(temppanel);
		}
		return panel;
	}


	/**
	 * procura da letra introduzida pelo utilizador e substituir no painel pela
	 * letra, caso ela exista no segredo
	 * 
	 * @param letter ---> letra introduzida pelo utilizador
	 * @return painel ---> devolve o painel modificado caso a letra esteja presente
	 *         no segredo
	 * @pre: letter != null && 0 < letter.length() < 40
	 */

	public void searchChar(char letter) {
		counter = 0;
		char[] temppanel = panel.toCharArray();
		for (int j = 0; j < secret.length(); j++) {
			if (secret.charAt(j) == letter) {
				temppanel[j] = letter;
				counter++;
			}
			temppanel.toString();
			panel = String.valueOf(temppanel);
		}
		//return panel;
	}

	/**
	 * detecta a letra introduzida e vai contar o n�mero de vezes que ela se
	 * encontra no painel
	 * 
	 * @param letter ---> letra introduzida pelo utilizador
	 * @return counter ---> n�mero de vezes que a letra est� presente no segredo
	 * @pre: letter != null && 0 < letter.length() < 40
	 */

	/*public int detectCharacter(char letter) {
		counter = 0;
		char[] temppanel = panel.toCharArray();
		for (int u = 0; u < panel.length(); u++) {
			if (temppanel[u] == letter) {
				counter++;

			}
		}

		return counter;
	}
	*/

	/**
	 * detecta se uma letra ja foi introduzida e registada no painel
	 * 
	 * @param letter ---> letra introduzida pelo utilizador
	 * @return true ---> quando se detecta uma repeti��o o contar n�o fica nulo e
	 *         serve de alerta para saber quando h� repeti��o
	 * @pre: letter != null && 0 < letter.length() < 40
	 * 
	 */

	public boolean RepeatedLetter(char letter) {
		char[] temppanel = panel.toCharArray();
		int count = 0;
		for (int u = 0; u < panel.length(); u++) {
			if (temppanel[u] == letter) {
				count++;

			}
		}
		return (count > 0);
	}
	/**
	 * verificar se a letra introduzida pertence ao segredo
	 * 
	 * @param letter ---> letra introduzida pelo utilizador
	 * @return true ---> caso a letra perten�a ao segredo
	 * @pre: letter != null && 0 < letter.length() < 40
	 */

	public boolean belongs(String letter) {
		return secret.contains(letter);
	}

	/**
	 * verifica se o palpite foi correto
	 * 
	 * se for, atualiza o painel para ser igual ao segredo
	 * 
	 * @param guess ---> palpite introduzida pelo utilizador para adivinhar o
	 *              segredo por completo
	 * @return true ---> se o palpite foi correto
	 * @pre: guess != null && 0 < guess.length() < 100
	 */
	public boolean isGuessRigth(String guess) {
		panel = secret;
		return (guess.equals(secret));

	}

	/**
	 * verificar se o segredo j� foi descoberto por completo
	 * 
	 * @return true ---> caso o segredo foi totalmente adivinhado
	 */
	public boolean completed() {
		if (!panel.equals(secret)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * devolver a vari�vel counter
	 * 
	 * @return counter ---> n�mero de vezes que a letra � repetida no segredo
	 */
	public int getCounter() {
		return counter;
	}

	// devolve o segredo original
	public String getSecret() {
		return secret;
	}

	/**
	 * @return panel ---> devolver o painel
	 */
	public String puzzle() {
		return panel;
	}
	
}