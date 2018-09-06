package columbo;

public enum TokenType {
	JOB_TITLE(0);
	
	private int i;
	
	private TokenType(int i) {
		this.i = i;
	}
	
	public int getInt() {
		return i;
	}
	
	public static TokenType getTokenType(int i) {
		return TokenType.values()[i];
	}
}
