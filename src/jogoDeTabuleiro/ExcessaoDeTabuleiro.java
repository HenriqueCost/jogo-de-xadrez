package jogoDeTabuleiro;

public class ExcessaoDeTabuleiro extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExcessaoDeTabuleiro(String msg) {
		super(msg);
	}
}
