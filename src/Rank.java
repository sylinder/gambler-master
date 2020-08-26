public enum Rank {
    STRAIGHT_FLUSH(9);


    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
