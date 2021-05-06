
public class NrTelefoniczny implements Comparable<NrTelefoniczny> {

    private int nrkierunkowy;
    private int nrTelefonu;

    public NrTelefoniczny (int nrkierunkowy, int nrTelefonu) {
        this.nrkierunkowy = nrkierunkowy;
        this.nrTelefonu = nrTelefonu;
    }

    public int getNrkierunkowy() {
        return nrkierunkowy;
    }

    public void setNrkierunkowy(int nrkierunkowy) {
        this.nrkierunkowy = nrkierunkowy;
    }

    public int getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(int nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    @Override
    public int compareTo(NrTelefoniczny n) {
        int result = Integer.compare(nrkierunkowy, n.nrkierunkowy);
        if (result == 0) {
            result = Integer.compare(nrTelefonu, n.nrTelefonu );
        }
        return result;
    }

    @Override
    public String toString() {
        return "+" + nrkierunkowy + " " + nrTelefonu;
    }
}
