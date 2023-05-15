
package turingmachine;

import java.util.*;

public class TuringMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input string no numbers: ");
        String input = scanner.nextLine();
        Set<Character> symbols = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                symbols.add(Character.toLowerCase(c));
            }
        }
        symbols.add('#');
        Character[] symbolArray = symbols.toArray(new Character[0]);
        System.out.print("Your symbols are: ");
        for(int i=0 ; i<symbolArray.length ; i++){
            System.out.print(symbolArray[i]);
        }
        System.out.println("");
        System.out.print("Enter the number of states: ");
        int numStates = scanner.nextInt();
        scanner.nextLine();

        String[][] transitions = new String[numStates][symbolArray.length];
        System.out.println("Enter the transition states as [symbol change, action, new state]");

        for (int i = 0; i < numStates; i++) {
            for (int j = 0; j < symbolArray.length; j++) {
                System.out.print("Enter the transition for state " + i + ", read " + symbolArray[j] + ": ");
                transitions[i][j] = scanner.nextLine();
            }
        }

        int currentState = 0;
        int currentSymbolIndex = 0;

        while (true) {
            System.out.println(input);
            for (int i = 0; i < currentSymbolIndex; i++) {
                System.out.print(" ");
            }
            System.out.println("^");

            if (currentState == numStates) {
                System.out.println("Rejected");
                break;
            }

            if (currentSymbolIndex == input.length()) {
                System.out.println("Accepted");
                break;
            }

            char currentSymbol = input.charAt(currentSymbolIndex);

            String transition = transitions[currentState][indexOf(symbolArray, currentSymbol)];
            String[] parts = transition.split(",");

            char newSymbol = parts[0].charAt(0);
            String direction = parts[1];
            int newState = Integer.parseInt(parts[2]);

            StringBuilder sb = new StringBuilder(input);
            sb.setCharAt(currentSymbolIndex, newSymbol);
            input = sb.toString();

            if (direction.equals("R") || direction.equals("r")) {
                currentSymbolIndex++;
            } else if (direction.equals("L") || direction.equals("l")) {
                currentSymbolIndex--;
            }

            currentState = newState;
        }
    }

    private static int indexOf(Character[] arr, char c) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                return i;
            }
        }
        return -1;
    }
}


