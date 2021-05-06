
public class MyVector{

    private int vector;
    private int length;

    public MyVector (int vector) {
        this.vector = vector;
        length = String.valueOf(vector).length();
    }

    public int getVector() {
        return vector;
    }

    public void setVector(int vector) {
        this.vector = vector;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int addVectors(MyVector v) throws VectorsNotSameLengthException {
        if (this.length == v.getLength()) {
            int result = this.vector + v.getVector();
            return result;
        } else {
            throw new VectorsNotSameLengthException(this.length, v.getLength());
        }
    }
}
