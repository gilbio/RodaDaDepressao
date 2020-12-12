public class SecretIterator {
	private Secret[] secrets;
	private int counter;
	private int nextSecret;
	
	public SecretIterator(Secret[] secrets, int counter) {
		this.secrets = secrets;
		this.counter = counter;
		nextSecret = 0;
	}
	
	public boolean hasNext() {
		return nextSecret < counter;
	}
	
	public Secret next() {
		return secrets[nextSecret++];
	}
}

