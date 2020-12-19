
public class Contestant {
	
	// Variaveis
	String name;
	int points;

	// Construtor
	public Contestant(String a) {
		name = a;
		points = 0;
	}

	// Devolve o nome
	public String returnName() {
		return name;
	}

	// Devolve os pontos
	public int returnPoints() {
		return points;
	}
	
	// Atualiza os pontos
	public void updatePoints(int a) {
		points = points + a;
	}

}
