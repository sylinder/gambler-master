import java.util.*;

public class Main {
    public static void main(String[] args) {

        String input = "3H 3D 3S 9C KD 2C 3H 4S 5C 6H";
        PokerCards blackPokerCards = new PokerCards();
        PokerCards whitePokerCards = new PokerCards();

        handleInput(input, blackPokerCards, whitePokerCards);

        int blackRank = getRank(blackPokerCards);
        int whiteRank = getRank(whitePokerCards);
        if (blackRank == whiteRank) {
            handleEqualRank(blackRank, blackPokerCards, whitePokerCards);
        } else if (blackRank > whiteRank) {
            System.out.println("black win...");
        } else {
            System.out.println("white win..." + whiteRank);
        }

//        handleInput(input, blackCards, whiteCards);
//
//        handleHighCard(blackCards, whiteCards);

//        System.out.println(blackCards);
//        System.out.println(whiteCards);
    }

    public static String getTips(int rank) {
        if (rank == 9) {

        }
        return null;
    }


    private static void handleEqualRank(int blackRank, PokerCards blackPokerCards, PokerCards whitePokerCards) {
        System.out.println("equal rank...");
    }

    public static int getRank(PokerCards pokerCards) {
        if (isStraightFlush(pokerCards)) {
            return Rank.STRAIGHT_FLUSH.getRank();
        }
        if (isFour(pokerCards)) {
            return Rank.FOUR_KIND.getRank();
        }
        if (isFullHouse(pokerCards)) {
            return Rank.FULL_HOUSE.getRank();
        }
        if (isFlush(pokerCards)) {
            return Rank.FLUSH.getRank();
        }
        if (isStraight(pokerCards)) {
            return Rank.STRAIGHT.getRank();
        }
        if (isThree(pokerCards)) {
            return Rank.THREE_KIND.getRank();
        }
        if (isTwoPair(pokerCards)) {
            return Rank.TWO_PAIR.getRank();
        }
        if (isPair(pokerCards)) {
            return Rank.PAIR.getRank();
        }
        return Rank.HIGH_CARD.getRank();
    }

    //handle high card
    public static void handleHighCard(PokerCards blackPokerCards, PokerCards whitePokerCards) {
        int[] blackCardNumbers = blackPokerCards.getCardNumber();
        int[] whiteCardNumbers = whitePokerCards.getCardNumber();
        for (int i = blackCardNumbers.length - 1; i >= 0; i--) {
            if (blackCardNumbers[i] > whiteCardNumbers[i]) {
                int num = blackCardNumbers[i];
                System.out.println("Black wins. - with high card: " + num);
                return ;
            } else if (blackCardNumbers[i] < whiteCardNumbers[i]) {
                int num = blackCardNumbers[i];
                System.out.println("White wins. - with high card: " + num);
                return ;
            }
        }
        System.out.println("Tie");
    }


    public static void handleInput(String input, PokerCards blackPokerCards, PokerCards whitePokerCards) {
        String[] black = new String[5];
        String[] white = new String[5];
        String[] strings = input.split(" ");
        for (int i = 0; i < 5; i++) {
            black[i] = strings[i];
        }
        for (int i = 5; i < strings.length; i++) {
            white[i - 5] = strings[i];
        }

        int[] blackNumbers = new int[5];
        char[] blackChars = new char[5];
        int[] whiteNumbers = new int[5];
        char[] whiteChars = new char[5];
        for (int i = 0; i < black.length; i++) {
            blackNumbers[i] = convertCharToNum(black[i].charAt(0));
            blackChars[i] = black[i].charAt(1);
        }
        for (int i = 0; i < black.length; i++) {
            whiteNumbers[i] = convertCharToNum(white[i].charAt(0));
            whiteChars[i] = white[i].charAt(1);
        }

        Arrays.sort(blackNumbers);
        Arrays.sort(whiteNumbers);
        blackPokerCards.setCardNumber(blackNumbers);
        blackPokerCards.setCardChar(blackChars);
        whitePokerCards.setCardNumber(whiteNumbers);
        whitePokerCards.setCardChar(whiteChars);
    }


    public static int convertCharToNum(char ch) {
        if (ch == 'T') {
            return 10;
        }
        if (ch == 'J') {
            return 11;
        }
        if (ch == 'Q') {
            return 12;
        }
        if (ch == 'K') {
            return 13;
        }
        if (ch == 'A') {
            return 14;
        }
        return ch - '0';
    }

    public static String convertNumberToString(int number) {
        if (number == 14) {
            return "Ace";
        }
        if (number == 13) {
            return "King";
        }
        if (number == 12) {
            return "Queen";
        }
        if (number == 11) {
            return "Jack";
        }
        return String.valueOf(number);
    }



    public static boolean isPair(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Set<Integer> set = new HashSet<>();
        for (Integer item : cardNumber) {
            set.add(item);
        }
        return set.size() == 4;
    }

    public static boolean isTwoPair(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : cardNumber) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        if (map.keySet().size() != 3) {
            return false;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Integer item : map.values()) {
            arrayList.add(item);
        }
        arrayList.sort((o1, o2) -> o1 - o2);
        if (arrayList.get(1) != 2 && arrayList.get(2) != 2) {
            return false;
        }
        return true;
    }

    public static boolean isThree(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : cardNumber) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        if (map.keySet().size() != 3) {
            return false;
        }
        int highest = 0;
        for (Integer item : map.values()) {
            if (item > highest) {
                highest = item;
            }
        }
        if (highest == 3) {
            return true;
        }
        return false;
    }

    public static boolean isStraight(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        for (int i = 0; i < cardNumber.length - 1; i++) {
            if (cardNumber[i + 1] - cardNumber[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFlush(PokerCards pokerCards) {
        char[] cardChar = pokerCards.getCardChar();
        Set<Character> set = new HashSet<>();
        for (Character ch : cardChar) {
            set.add(ch);
        }
        return set.size() == 1;
    }

    public static boolean isFullHouse(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : cardNumber) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        if (map.keySet().size() != 2) {
            return false;
        }
        Collection<Integer> values = map.values();
        if (values.contains(3) && values.contains(2)) {
            return true;
        }
        return false;
    }

    public static boolean isFour(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : cardNumber) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        for (Integer item : map.values()) {
            if (item == 4) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStraightFlush(PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        char[] cardChar = pokerCards.getCardChar();
        return isStraight(cardNumber) && isFlush(cardChar);
    }

    public static boolean isStraight(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i + 1] - numbers[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFlush(char[] cardChars) {
        Set<Character> set = new HashSet<>();
        for (char ch : cardChars) {
            set.add(ch);
        }
        return set.size() == 1;
    }

//    public static String getTips(PokerCards pokerCards) {
//        if (isStraightFlush(pokerCards)) {
//            return
//        }
//    }
}
