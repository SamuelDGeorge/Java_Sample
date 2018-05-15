package grail.interfaces;

public interface ArrayStore {
	public void setScannedString(String scannedString);
	public String getScannedString();
	public StoreToken[] getTokens();
	public String[] getErrors();
}
