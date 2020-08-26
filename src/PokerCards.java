import java.util.Arrays;

public class PokerCards {
    private int[] cardNumber;
    private char[] cardChar;

    public PokerCards(int[] cardNumber, char[] cardChar) {
        this.cardNumber = cardNumber;
        this.cardChar = cardChar;
    }

    public PokerCards() {
    }

    public int[] getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int[] cardNumber) {
        this.cardNumber = cardNumber;
    }

    public char[] getCardChar() {
        return cardChar;
    }

    public void setCardChar(char[] cardChar) {
        this.cardChar = cardChar;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cardNumber=" + Arrays.toString(cardNumber) +
                ", cardChar=" + Arrays.toString(cardChar) +
                '}';
    }
}
