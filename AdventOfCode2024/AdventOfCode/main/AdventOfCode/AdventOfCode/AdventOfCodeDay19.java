package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Data;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;
import references.references.StringReference;

import static DataStructures.Array.Structures.Structures.CreateNumberData;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static aarrays.arrays.arrays.aStringsEqual;
import static lists.StringList.StringList.RemoveString;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.*;
import static strings.strings.strings.*;

public class AdventOfCodeDay19 {
    public static char[] ComputeDay19Part1(char[] input) {
        StringReference [] lines, ts;
        double i, n;
        char [] output, combo;
        StringReference [] combos;
        boolean found, possible;

        // Read stripe types and expected combinations
        lines = SplitByCharacter(input, '\n');
        ts = SplitByString(lines[0].string, ", ".toCharArray());

        combos = CreateStringArrayReferenceLengthValue(lines.length - 2d, "".toCharArray()).stringArray;
        for(i = 2d; i < lines.length; i = i + 1d){
            combos[(int)(i - 2d)].string = lines[(int)i].string;
        }

        // Remove all towels that are combinations of others
        found = true;
        for(; found; ) {
            found = false;
            for (i = 0d; i < ts.length && !found; i = i + 1d) {
                combo = ts[(int) i].string;
                possible = IsPossible(combo, ts, "".toCharArray(), i);
                if (possible) {
                    // Remove and restart
                    found = true;
                    ts = RemoveString(ts, i);
                }
            }
        }

        // Compute if possible:
        n = 0d;
        for(i = 0d; i < combos.length; i = i + 1d){
            combo = combos[(int) i].string;

            if(IsPossible(combo, ts, "".toCharArray(), -1)){
               n = n + 1d;
            }
        }


        // Produce output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static boolean IsPossible(char[] combo, StringReference[] ts, char [] pat, double skip) {
        double i;
        boolean possible;
        char [] newpat;

        possible = false;

        for (i = 0d; i < ts.length && !possible; i = i + 1d) {
            if(i != skip) {
                newpat = ConcatenateString(pat, ts[(int) i].string);
                if (aStringsEqual(combo, newpat)) {
                    possible = true;
                } else if (StartsWith(combo, newpat)) {
                    possible = IsPossible(combo, ts, newpat, skip);
                }
            }
        }

        return possible;
    }

    public static char[] ComputeDay19Part2(char[] input) {
        StringReference [] lines, ts;
        double i, n;
        char [] output, combo;
        StringReference [] combos;
        RedBlackTree cache;

        cache = CreateRedBlackTree();

        // Read stripe types and expected combinations
        lines = SplitByCharacter(input, '\n');
        ts = SplitByString(lines[0].string, ", ".toCharArray());

        combos = CreateStringArrayReferenceLengthValue(lines.length - 2d, "".toCharArray()).stringArray;
        for(i = 2d; i < lines.length; i = i + 1d){
            combos[(int)(i - 2d)].string = lines[(int)i].string;
        }

        // Compute possibilities
        n = 0d;
        for(i = 0d; i < combos.length; i = i + 1d){
            combo = combos[(int) i].string;
            n = n + ComputeCombos(cache, combo, ts, "".toCharArray());
        }

        // Produce output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double ComputeCombos(RedBlackTree cache, char[] combo, StringReference[] ts, char [] pattern) {
        double i;
        double combosTotal, combos;
        char [] newPattern, remaining;
        BooleanReference foundRef;
        RedBlackNode node;
        Data data;

        combosTotal = 0d;

        for (i = 0d; i < ts.length; i = i + 1d) {
            newPattern = ConcatenateString(pattern, ts[(int) i].string);

            if (aStringsEqual(combo, newPattern)) {
                combosTotal = combosTotal + 1d;
            } else if (StartsWith(combo, newPattern)) {
                remaining = Substring(combo, newPattern.length, combo.length);

                foundRef = CreateBooleanReference(false);
                node = Search(cache, remaining, foundRef);

                if(foundRef.booleanValue){
                    combos = node.value.number;
                }else{
                    combos = ComputeCombos(cache, combo, ts, newPattern);
                    data = CreateNumberData(combos);
                    InsertData(cache, remaining, data);
                }

                combosTotal = combosTotal + combos;
            }
        }

        return combosTotal;
    }
}