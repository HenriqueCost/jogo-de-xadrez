package xadrez;

public class ExcessaoDeXadrez extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExcessaoDeXadrez(String msg) {
		super(msg);
	}
}
