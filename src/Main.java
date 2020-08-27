import java.util.*;

public class Main {
    public static void main(String[] args) {

        String input = "2H 3D 5S 9C KD 2C 3h 4S 8C AH"; // high card
//        String input = "3H 3D 3S 9C KD 2C 3C 4S 5C 6H"; //straight
//        String input = "3H 3D 3S 9C KD 2C 2H 4S 4C 6H"; // three of kind
//        String input = "3H 3D 4S 9C KD 2C 2H 4H 4C 6H"; //two pair
//        String input = "3H 3D 4S 9C KD 2C 3C 9S 5C 6H"; //pair
//        String input = "3C 3D 4S 9C KD 2H 3H 9H 5H 6H"; //flush
//        String input = "3C 3D 4S 9C KD 5C 5H 9S 9C 9H"; //full house
//        String input = "3H 3D 4S 9C KD 9C 3S 9S 9C 9H"; //four of kind
//        String input = "3H 3D 4S 9C KD 5H 6H 7H 8H 9H"; // straight flush


//        String sameRankInput = "3H 4H 5H 6H 7H 9H TH JH QH KH"; // straight flush
//        String sameRankInput = "3H 3S 3D 3C 7H 9S 9H 9D 9C KH"; // four of kind
//        String sameRankInput = "3H 3S 3D 5C 5H 9S 9H 9D 2C 2H"; // full house
//        String sameRankInput = "2H 3H 5H 7H KH 4H 8H 9H TH JH"; // flush
//        String sameRankInput = "2H 3H 4D 5S 6H 7D 8H 9H TH JH"; // straight
//        String sameRankInput = "7H 7C 7D 5S 6H 2D 2H 2C TH JH"; // three of a kind
//        String sameRankInput = "7H 7C KD KS 6H 2D 2H 3C JC JH"; // two pairs

        PokerCards blackPokerCards = new PokerCards();
        PokerCards whitePokerCards = new PokerCards();

        handleInput(input, blackPokerCards, whitePokerCards);
//        handleInput(sameRankInput, blackPokerCards, whitePokerCards);

        int blackRank = getRank(blackPokerCards);
        int whiteRank = getRank(whitePokerCards);
        if (blackRank == whiteRank) {
            System.out.println(handleEqualRank(blackRank, blackPokerCards, whitePokerCards));
        } else if (blackRank > whiteRank) {
            String str = handleNoEqualRank(blackRank, blackPokerCards);
            System.out.println("black win. - with " + str);
        } else {
            String str = handleNoEqualRank(whiteRank, whitePokerCards);
            System.out.println("white win. - with " + str);
        }

//        handleInput(input, blackCards, whiteCards);
//
//        handleHighCard(blackCards, whiteCards);

//        System.out.println(blackCards);
//        System.out.println(whiteCards);
    }

    public static String handleNoEqualRank(int rank, PokerCards pokerCards) {
        int[] cardNumber = pokerCards.getCardNumber();
        char[] cardChar = pokerCards.getCardChar();
        if (rank == 9) {
            return "straight flush: " +  cardNumber[0] + " to " + cardNumber[4];
        }
        if (rank == 8) {
            return "four of a kind: " + cardNumber[2];
        }
        if (rank == 7) {
            int first = cardNumber[2];
            int second;
            if (cardNumber[0] != first) {
                second = cardNumber[0];
            } else {
                second = cardNumber[4];
            }
            return "full house: " + first + " over " + second;
        }
        if (rank == 6) {
            return "flush: " + cardChar[0];
        }
        if (rank == 5) {
            return "straight: " + cardNumber[0] + " to " + cardNumber[4];
        }
        if (rank == 4) {
            return "three of a kind: " + cardNumber[2];
        }
        if (rank == 3) {
            int first = -1;
            int second = -1;
            boolean mark = false;
            for (int i = 0; i < cardNumber.length - 1; i++) {
                if (cardNumber[i] == cardNumber[i + 1] && !mark) {
                    first = cardNumber[i];
                    mark = true;
                } else if (cardNumber[i] == cardNumber[i + 1]) {
                    second = cardNumber[i];
                }
            }
            return "two pairs: " + first + " and " + second;
        }
        if (rank == 2) {
            int number = -1;
            for (int i = 0; i < cardNumber.length - 1; i++) {
                if (cardNumber[i] == cardNumber[i + 1]) {
                    number = cardNumber[i];
                }
            }
            return "pair: " + number;
        }
        return null;
    }


    private static String handleEqualRank(int rank, PokerCards blackPokerCards, PokerCards whitePokerCards) {
        int[] blackNumbers = blackPokerCards.getCardNumber();
        int[] whiteNumbers = whitePokerCards.getCardNumber();
        switch (rank) {
            case 9:
                return blackNumbers[4] > whiteNumbers[4] ? "Black win. - with straight flush: " + convertNumberToString(blackNumbers[4]) :
                        "White win. - with straight flush: " + convertNumberToString(whiteNumbers[4]);

            case 8:
                return blackNumbers[2] > whiteNumbers[2] ? "Black win. - with four of a kind: " + convertNumberToString(blackNumbers[2]) :
                        "White win. - with four of a kind: " + convertNumberToString(whiteNumbers[2]);

            case 7:
                return blackNumbers[2] > whiteNumbers[2] ? "Black win. - with full house: " + convertNumberToString(blackNumbers[2]) :
                        "White win. - with full house: " + convertNumberToString(whiteNumbers[2]);

            case 6:
                return blackNumbers[4] > whiteNumbers[4] ? "Black win. - with flush: " + convertNumberToString(blackNumbers[4]) :
                        "White win. - with flush: " + convertNumberToString(whiteNumbers[4]);

            case 5:
                return blackNumbers[4] > whiteNumbers[4] ? "Black win. - with straight: " + convertNumberToString(blackNumbers[4]) :
                        "White win. - with straight: " + convertNumberToString(whiteNumbers[4]);

            case 4:
                return blackNumbers[2] > whiteNumbers[2] ? "Black win. - with three of a kind: " + convertNumberToString(blackNumbers[2]) :
                        "White win. - with three of a kind: " + convertNumberToString(whiteNumbers[2]);

            case 3:
                return "a little annoying"; //need to consider two pair all equal, compare the single one.

            case 2:
                return "a little annoying too"; // reason like the former

            case 1:
                return blackNumbers[4] > whiteNumbers[4] ? "Black win. - with high card: " + convertNumberToString(blackNumbers[4]) :
                        "White win. - with high card: " + convertNumberToString(whiteNumbers[4]);

            default:
                return null;
        }
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
