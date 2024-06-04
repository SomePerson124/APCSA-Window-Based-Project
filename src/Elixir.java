public class Elixir {

    private boolean[] elixirBar;
    private int elixirAmt;

    public Elixir() {
        elixirBar = new boolean[10];
        elixirAmt = 0;
    }

    public boolean[] getElixirBar() {
        return elixirBar;
    }

    public int getElixirAmt() {
        return elixirAmt;
    }

    public void addElixir() {
        elixirAmt++;
    }

    public void useElixir(int elixirUsed) {
        for (int i = 0; i < elixirBar.length; i++) {
            if (i >= elixirAmt - elixirUsed) {
                elixirBar[i] = false;
            }
        }
        elixirAmt -= elixirUsed;
    }

}
