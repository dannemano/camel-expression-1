package se.supportix.camelreboot.messages;

public class ABeanMessage {

	private long id;
	private int number;
	private String message;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ABeanMessage [id=" + id + ", number=" + number + "]";
	}
	
	
	
}
