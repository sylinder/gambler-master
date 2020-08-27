public enum Rank {
    STRAIGHT_FLUSH(9),
    FOUR_KIND(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_KIND(4),
    TWO_PAIR(3),
    PAIR(2),
    HIGH_CARD(1);

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
