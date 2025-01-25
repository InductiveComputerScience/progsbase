package AdventOfCode.AdventOfCode;

import DataStructures.Array.RedBlackTrees.RedBlackNodeReference;
import DataStructures.Array.Set.Set;
import DataStructures.Array.Set.SetIterator;
import DataStructures.Array.Structures.DataReference;
import DataStructures.Array.Structures.Structure;
import DataStructures.Array.Structures.Array;
import references.references.StringArrayReference;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.RedBlackTrees.RedBlackTrees.GetNextData;
import static DataStructures.Array.RedBlackTrees.RedBlackTrees.InitIterator;
import static DataStructures.Array.Set.Sets.*;
import static DataStructures.Array.Structures.Structures.*;
import static QuickSort.QuickSortStrings.QuickSortStrings.xQuickSortStrings;
import static aarrays.arrays.arrays.aStringsEqual;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.CreateStringArrayReferenceLengthValue;
import static strings.strings.strings.*;

public class AdventOfCodeDay23 {
    public static char[] ComputeDay23Part1(char[] input) {
        double n, i;
        char [] output;
        StringReference [] lines;
        Structure links;
        Set computers, triples, col, filtered;
        StringReference [] cs;
        char [] c1, c2;
        SetIterator it, it2;
        DataReference com, triple;

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
        com = new DataReference();
        InitSetIterator(computers, it);
        for(;SetIterate(it, com);){
            AddStringToSet(col, com.data.string);
            FindLinks(triples, links, com.data.string, com.data.string, col, 0);
            RemoveStringFromSet(col, com.data.string);
        }

        // Filter those with a t-computer
        filtered = CreateSet();
        triple = new DataReference();
        it2 = new SetIterator();
        InitSetIterator(triples, it);
        for(;SetIterate(it, triple);){
            InitSetIterator(triple.data.set, it2);
            boolean found = false;
            for(;SetIterate(it2, com);) {
                if (StartsWith(com.data.string, "t".toCharArray())) {
                    found = true;
                }
            }
            if(found){
                AddSetToSet(filtered, triple.data.set);
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
                if(!SetContains(col, CreateStringData(con))) {
                    AddStringToSet(col, con);
                    FindLinks(triples, links, con, goal, col, d + 1);
                    RemoveStringFromSet(col, con);
                }
            }
        }
    }

    public static char[] ComputeDay23Part2(char[] input) {
        double i, count;
        char [] output, c1, c2, con;
        StringReference [] lines, cs;
        Structure links;
        Set computers, set, next, prev;
        RedBlackNodeReference nodeRef;
        DataReference keyRef, valueRef, group, com, code;
        SetIterator it, it2;
        boolean done;
        Array cons;
        StringArrayReference arr;

        nodeRef = new RedBlackNodeReference();
        keyRef = new DataReference();
        valueRef = new DataReference();
        it = new SetIterator();
        com = new DataReference();

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
        InitIterator(links.tree, nodeRef);
        for(;GetNextData(links.tree, nodeRef, keyRef, valueRef);){
            for(i = 0d; i < ArrayLength(valueRef.data.array); i = i + 1d){
                con = ArrayIndexString(valueRef.data.array, i);
                set = CreateSet();
                AddStringToSet(set, keyRef.data.string);
                AddStringToSet(set, con);
                AddSetToSet(prev, set);
            }
        }

        group = new DataReference();
        it2 = new SetIterator();

        done = false;
        for(;!done;) {
            // n + 1
            next = CreateSet();
            InitSetIterator(prev, it);
            for (;SetIterate(it, group);) {
                // Find all computers linked to all
                InitSetIterator(computers, it2);
                for (;SetIterate(it2, com);) {
                    count = 0d;
                    cons = GetArrayFromStruct(links, com.data.string);
                    // String con : cons
                    for (i = 0d; i < ArrayLength(cons); i = i + 1d) {
                        con = ArrayIndexString(cons, i);
                        if (SetContains(group.data.set, CreateStringData(con))) {
                            count = count + 1;
                        }
                    }

                    // Check if linked to the same as the group size, if so, add
                    if (count == SetSize(group.data.set)) {
                        set = CreateSet();
                        AddAllToSet(set, group.data.set);
                        AddStringToSet(set, com.data.string);
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
        code = new DataReference();
        InitSetIterator(prev, it);
        SetIterate(it, code);

        // Sort
        arr = CreateStringArrayReferenceLengthValue(SetSize(code.data.set), "".toCharArray());

        InitSetIterator(code.data.set, it);
        for (i = 0d;SetIterate(it, com);i = i + 1d) {
            arr.stringArray[(int)i].string = com.data.string;
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
