
public class VectorsNotSameLengthException extends Exception{
	private int firstLength;
	private int secondLength;

    public VectorsNotSameLengthException(int firstLength, int secondLength, String message) {
        super(message);
        this.firstLength = firstLength;
        this.secondLength = secondLength;
    }

    public int getFirstLength() {
    	return firstLength;
    }

    public int getSecondLength() {
    	return secondLength;
    }
}
