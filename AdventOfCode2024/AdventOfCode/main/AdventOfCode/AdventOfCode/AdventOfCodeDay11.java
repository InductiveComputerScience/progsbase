package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Data;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;

import static DataStructures.Array.Structures.Structures.*;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static java.lang.Math.*;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.*;
import static references.references.references.*;
import static strings.strings.strings.*;

public class AdventOfCodeDay11 {
    public static char[] ComputeDay11Part1(char[] input) {
        char [] output;
        double n, i;
        double [] numbers;
        RedBlackTree cache;

        // Read numbers into array
        ReplaceCharacter(input, ' ', ',');
        numbers = StringToNumberArray(input);

        // Compute with cache
        cache = CreateRedBlackTree();
        n = 0d;
        for(i = 0; i < numbers.length; i = i + 1d){
            n = n + ApplyRuleRecurse(numbers[(int) i], 25d, 0d, cache);
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static char[] ComputeDay11Part2(char[] input) {
        char [] output;
        double n, i;
        double [] numbers;
        RedBlackTree cache;

        // Read numbers into array
        ReplaceCharacter(input, ' ', ',');
        numbers = StringToNumberArray(input);

        // Compute with cache
        cache = CreateRedBlackTree();
        n = 0d;
        for(i = 0; i < numbers.length; i = i + 1d){
            n = n + ApplyRuleRecurse(numbers[(int) i], 75d, 0d, cache);
        }

        // Done
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double ApplyRuleRecurse(double n, double maxDepth, double depth, RedBlackTree cache) {
        double digits, p1, p2, count;
        RedBlackNode node;
        BooleanReference found;
        double key;
        char [] keyStr;
        Data cData;

        found = CreateBooleanReference(false);

        key = n * maxDepth + depth;
        keyStr = CreateStringDecimalFromNumber(key);

        if (depth == maxDepth) {
            count = 1d;
        }else {
            node = Search(cache, keyStr, found);

            if(found.booleanValue){
                count = node.value.number;
            } else  {
                digits = floor(log10(n)) + 1d;

                if (n == 0d) {
                    n = 1d;
                    count = ApplyRuleRecurse(n, maxDepth, depth + 1d, cache);
                } else if (digits % 2d == 0d) {
                    digits = digits / 2d;
                    p1 = floor(n / pow(10, digits));
                    p2 = n % pow(10, digits);

                    count = ApplyRuleRecurse(p1, maxDepth, depth + 1d, cache);
                    count = count + ApplyRuleRecurse(p2, maxDepth, depth + 1d, cache);
                } else {
                    n = n * 2024;
                    count = ApplyRuleRecurse(n, maxDepth, depth + 1d, cache);
                }

                cData = CreateNumberData(count);
                InsertData(cache, keyStr, cData);
            }
        }

        return count;
    }
}
