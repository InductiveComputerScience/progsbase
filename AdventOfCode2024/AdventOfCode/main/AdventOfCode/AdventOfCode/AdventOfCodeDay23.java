package AdventOfCode.AdventOfCode;

import DataStructures.Array.RedBlackTrees.RedBlackNodeReference;
import DataStructures.Array.Set.Set;
import DataStructures.Array.Set.SetIterator;
import DataStructures.Array.Set.SetReference;
import DataStructures.Array.Structures.DataReference;
import DataStructures.Array.Structures.StructIterator;
import DataStructures.Array.Structures.Structure;
import DataStructures.Array.Structures.Array;
import references.references.StringArrayReference;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Set.Sets.*;
import static DataStructures.Array.Structures.Structures.*;
import static QuickSort.QuickSortStrings.QuickSortStrings.xQuickSortStrings;
import static aarrays.arrays.arrays.aStringsEqual;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.CreateStringArrayReferenceLengthValue;
import static strings.strings.strings.*;

public class AdventOfCodeDay23 {
    public static char[] ComputeDay23Part1(char[] input) {
        boolean found;
        double n, i;
        char [] output, c1, c2;
        StringReference [] lines, cs;
        Structure links;
        Set computers, triples, col, filtered;
        SetIterator it, tripleIterator;
        SetReference triple;
        StringReference computer;

        // Read initial secrets
        lines = SplitByCharacter(input, '\n');

        links = CreateStructure();
        computers = CreateSet();

        // Find computers and links
        for(i = 0; i < lines.length; i = i + 1d){
            cs = SplitByCharacter(lines[(int) i].string, '-');
            c1 = cs[0].string;
            c2 = cs[1].string;

            AddStringToSet(computers, c1);
            AddStringToSet(computers, c2);

            if(!StructHasKey(links, c1)){
                AddArrayToStruct(links, c1, CreateArray());
            }
            AddStringToArray(GetArrayFromStruct(links, c1), c2);

            if(!StructHasKey(links, c2)){
                AddArrayToStruct(links, c2, CreateArray());
            }
            AddStringToArray(GetArrayFromStruct(links, c2), c1);
        }

        // Find all triples
        triples = CreateSet();
        col = CreateSet();
        it = new SetIterator();
        computer = new StringReference();

        for(InitSetIterator(computers, it); SetIterateStrings(it, computer);){
            AddStringToSet(col, computer.string);
            FindLinks(triples, links, computer.string, computer.string, col, 0);
            RemoveStringFromSet(col, computer.string);
        }

        // Filter those with a t-computer
        filtered = CreateSet();
        triple = new SetReference();
        tripleIterator = new SetIterator();

        for(InitSetIterator(triples, it); SetIterateSets(it, triple);){
            found = false;
            for(InitSetIterator(triple.set, tripleIterator); SetIterateStrings(tripleIterator, computer);) {
                if (StartsWith(computer.string, "t".toCharArray())) {
                    found = true;
                }
            }

            if(found){
                AddSetToSet(filtered, triple.set);
            }
        }

        // Produce output
        n = SetSize(filtered);
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static void FindLinks(Set triples, Structure links, char [] com, char [] goal, Set col, double d) {
        Array cons;
        char [] con;
        double i;
        Set set;

        cons = GetArrayFromStruct(links, com);

        if(d == 2){
            for(i = 0d; i < ArrayLength(cons); i = i + 1d) {
                con = ArrayIndexString(cons, i);
                if (aStringsEqual(con, goal)) {
                    set = CreateSet();
                    AddAllToSet(set, col);
                    AddSetToSet(triples, set);
                }
            }
        }else{
            for(i = 0d; i < ArrayLength(cons); i = i + 1d) {
                con = ArrayIndexString(cons, i);
                if(!SetContainsString(col, con)) {
                    AddStringToSet(col, con);
                    FindLinks(triples, links, con, goal, col, d + 1);
                    RemoveStringFromSet(col, con);
                }
            }
        }
    }

    public static char [] ComputeDay23Part2(char[] input) {
        double i, count;
        char [] output, c1, c2, con;
        boolean done;
        StringReference [] lines, cs;
        Structure links;
        Set computers, set, next, prev;
        StructIterator structIt;
        DataReference valueRef;
        StringReference computer, keyRef;
        SetReference group, code;
        SetIterator it, it2;
        Array cons;
        StringArrayReference arr;

        structIt = new StructIterator();
        keyRef = new StringReference();
        valueRef = new DataReference();
        it = new SetIterator();
        computer = new StringReference();

        // Read initial secrets
        lines = SplitByCharacter(input, '\n');

        links = CreateStructure();
        computers = CreateSet();

        // Find computers and links
        for(i = 0; i < lines.length; i = i + 1d){
            cs = SplitByCharacter(lines[(int) i].string, '-');
            c1 = cs[0].string;
            c2 = cs[1].string;

            AddStringToSet(computers, c1);
            AddStringToSet(computers, c2);

            if(!StructHasKey(links, c1)){
                AddArrayToStruct(links, c1, CreateArray());
            }
            AddStringToArray(GetArrayFromStruct(links, c1), c2);

            if(!StructHasKey(links, c2)){
                AddArrayToStruct(links, c2, CreateArray());
            }
            AddStringToArray(GetArrayFromStruct(links, c2), c1);
        }

        // 2
        prev = CreateSet();

        for(InitStructIterator(links, structIt); IterateStruct(structIt, keyRef, valueRef);){
            for(i = 0d; i < ArrayLength(valueRef.data.array); i = i + 1d){
                con = ArrayIndexString(valueRef.data.array, i);
                set = CreateSet();
                AddStringToSet(set, keyRef.string);
                AddStringToSet(set, con);
                AddSetToSet(prev, set);
            }
        }

        group = new SetReference();
        it2 = new SetIterator();

        done = false;
        for(;!done;) {
            // n + 1
            next = CreateSet();

            for (InitSetIterator(prev, it); SetIterateSets(it, group);) {
                // Find all computers linked to all
                for (InitSetIterator(computers, it2); SetIterateStrings(it2, computer);) {
                    count = 0d;
                    cons = GetArrayFromStruct(links, computer.string);

                    for (i = 0d; i < ArrayLength(cons); i = i + 1d) {
                        con = ArrayIndexString(cons, i);
                        if (SetContainsString(group.set, con)) {
                            count = count + 1;
                        }
                    }

                    // Check if linked to the same as the group size, if so, add
                    if (count == SetSize(group.set)) {
                        set = CreateSet();
                        AddAllToSet(set, group.set);
                        AddStringToSet(set, computer.string);
                        AddSetToSet(next, set);
                    }
                }
            }

            if(SetSize(next) > 0d) {
                prev = next;
            }else{
                done = true;
            }
        }

        // Get only element left
        code = new SetReference();
        InitSetIterator(prev, it);
        SetIterateSets(it, code);

        // Sort
        arr = CreateStringArrayReferenceLengthValue(SetSize(code.set), "".toCharArray());

        InitSetIterator(code.set, it);
        for (i = 0d; SetIterateStrings(it, computer); i = i + 1d) {
            arr.stringArray[(int)i].string = computer.string;
        }

        xQuickSortStrings(arr);

        // Produce output
        output = ArrayToString(arr);

        return output;
    }

    public static char [] ArrayToString(StringArrayReference strArr) {
        double i;
        char [] str;

        str = "".toCharArray();

        for(i = 0; i < strArr.stringArray.length; i = i + 1d) {
            str = AppendString(str, strArr.stringArray[(int)i].string);
            if(i + 1 < strArr.stringArray.length) {
                str = AppendCharacter(str, ',');
            }
        }
        return str;
    }
}
