package String;

import java.util.LinkedList;
import java.util.List;

public class StringPermutationI {
    /*
        Given a string with no duplicate characters,
        find all possible permutations with letters in the string.
     */

    List<String> allPermutation(String input) {
        if (input == null) {
            return new LinkedList<String>();
        }
        List<String> solution = new LinkedList<>();
        allPermutationHelper(input.toCharArray(), 0, solution);
        return solution;
    }
    void allPermutationHelper(char[] input, int index, List<String> solution) {
        if (index == input.length - 1) {
            solution.add(new String(input));
        }
        for (int i = index; i < input.length; i++) {
            swap(input, index, i);
            allPermutationHelper(input, index + 1, solution);
            // 过去错误：忘记swap back！！
            swap(input, index, i);
        }
    }

    void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        StringPermutationI solution = new StringPermutationI();
        List<String> result = solution.allPermutation("a");
        for (String s : result) {
            System.out.println(s);
        }
    }


}
