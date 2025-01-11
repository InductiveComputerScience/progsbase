package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.Data;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;
import references.references.StringArrayReference;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Structures.Structures.*;
import static QuickSort.QuickSortStrings.QuickSortStrings.xQuickSortStrings;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static aarrays.arrays.arrays.aCopyString;
import static aarrays.arrays.arrays.aStringsEqual;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.*;
import static strings.strings.strings.*;

public class AdventOfCodeDay23 {
    public static char[] ComputeDay23Part1(char[] input) {
        double n, i;
        char [] output;
        StringReference [] lines;
        RedBlackTree links;
        StringSet computers, col, triples;
        StringReference[] cs;
        char [] c1, c2, com, triple;
        Array filtered, array;

        // Read initial secrets
        lines = SplitByCharacter(input, '\n');

        links = CreateRedBlackTree();
        computers = CreateStringSet();

        // Find computers and links
        for(i = 0; i < lines.length; i = i + 1d){
            cs = SplitByCharacter(lines[(int) i].string, '-');

            c1 = cs[0].string;
            c2 = cs[1].string;

            AddToSet(computers, c1);
            AddToSet(computers, c2);

            if(!TreeHasKey(links, c1)){
                InsertData(links, c1, CreateArrayData());
            }
            array = GetTreeData(links, c1).array;
            AddStringToArray(array, c2);

            if(!TreeHasKey(links, c2)){
                InsertData(links, c2, CreateArrayData());
            }
            array = GetTreeData(links, c2).array;
            AddStringToArray(array, c1);
        }

        // Find all triples
        triples = CreateStringSet();
        col = CreateStringSet();
        for(i = 0; i < ArrayLength(computers.array); i = i + 1){
            com = ArrayIndex(computers.array, i).string;
            AddToSet(col, com);
            FindLinks(triples, links, com, com, col, 0);
            RemoveFromSet(col, com);
        }

        // Filter those with a t-computer
        filtered = CreateArray();
        for(i = 0d; i < ArrayLength(triples.array); i = i + 1d){
            triple = ArrayIndex(triples.array, i).string;
            if(ContainsString(triple, ",t".toCharArray()) || StartsWith(triple, "t".toCharArray())){
                AddStringToArray(filtered, triple);
            }
        }

        // Produce output
        n = ArrayLength(filtered);
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static void FindLinks(StringSet triples, RedBlackTree links, char [] com, char [] goal, StringSet col, double d) {
        Array cons;
        char [] con, str;
        double i, c;
        StringArrayReference strArr;

        cons = GetTreeData(links, com).array;

        if(d == 2){

            for(c = 0d; c < ArrayLength(cons); c = c + 1) {
                con = ArrayIndex(cons, c).string;
                if (aStringsEqual(con, goal)) {
                    str = "".toCharArray();

                    strArr = ToStaticStringArray(col.array);
                    xQuickSortStrings(strArr);

                    for(i = 0; i < strArr.stringArray.length; i = i + 1d) {
                        str = AppendString(str, strArr.stringArray[(int)i].string);
                        if(i + i < ArrayLength(col.array)) {
                            str = AppendCharacter(str, ',');
                        }
                    }

                    AddToSet(triples, str);
                }
            }
        }else{
            for(i = 0; i < ArrayLength(cons); i = i + 1d) {
                con = ArrayIndex(cons, i).string;
                if(!SetContains(col, con)) {
                    AddToSet(col, con);
                    FindLinks(triples, links, con, goal, col, d + 1);
                    RemoveFromSet(col, con);
                }
            }
        }
    }

    public static StringArrayReference ToStaticStringArray(Array array) {
        StringArrayReference arr;
        double i;

        arr = new StringArrayReference();
        arr.stringArray = new StringReference[(int)ArrayLength(array)];

        for(i = 0; i < ArrayLength(array); i = i + 1d){
            arr.stringArray[(int)i] = new StringReference();
            arr.stringArray[(int)i].string = aCopyString(ArrayIndex(array, i).string);
        }

        return arr;
    }

    public static char[] ComputeDay23Part2(char[] input) {
        double i, j, k, count, prevLen, nextLen;
        boolean done;
        char [] output, c1, c2, con, com, str;
        StringReference [] lines, cs;
        RedBlackTree links;
        StringSet computers;
        Array array;
        StringSet ai, last, set, group;
        StringSet [] prev, next;
        StringArrayReference arr;

        // Read initial secrets
        lines = SplitByCharacter(input, '\n');

        links = CreateRedBlackTree();
        computers = CreateStringSet();
        ai = CreateStringSet();

        // Find computers and links
        for(i = 0; i < lines.length; i = i + 1d){
            cs = SplitByCharacter(lines[(int) i].string, '-');

            c1 = cs[0].string;
            c2 = cs[1].string;

            AddToSet(computers, c1);
            AddToSet(computers, c2);

            if(!TreeHasKey(links, c1)){
                InsertData(links, c1, CreateArrayData());
            }
            array = GetTreeData(links, c1).array;
            AddStringToArray(array, c2);

            if(!TreeHasKey(links, c2)){
                InsertData(links, c2, CreateArrayData());
            }
            array = GetTreeData(links, c2).array;
            AddStringToArray(array, c1);
        }

        // 2
        prev = new StringSet[100000];
        prevLen = 0;
        for(i = 0d; i < ArrayLength(computers.array); i = i + 1d){
            com = ArrayIndex(computers.array, i).string;
            array = GetTreeData(links, com).array;

            for(j = 0d; j < ArrayLength(array); j = j + 1d){
                con = ArrayIndex(array, j).string;
                set = CreateStringSet();
                AddToSet(set, com);
                AddToSet(set, con);
                prev[(int)prevLen] = set;
                prevLen = prevLen + 1d;
            }
        }

        done = false;
        //int n = 2;
        for(;!done;) {
            // n + 1
            //System.out.println(n+":" + prevLen);
            //System.out.println(n++);
            next = new StringSet[100000];
            nextLen = 0d;
            for (k = 0d; k < prevLen; k = k + 1d) {
                group = prev[(int)k];
                // Find all computers linked to all
                for (i = 0d; i < ArrayLength(computers.array); i = i + 1d) {
                    com = ArrayIndex(computers.array, i).string;
                    count = 0d;
                    array = GetTreeData(links, com).array;

                    for(j = 0d; j < ArrayLength(array); j = j + 1d){
                        con = ArrayIndex(array, j).string;
                        if (SetContains(group, con)) {
                            count = count + 1d;
                        }
                    }

                    // Check if linked to the same as the group size, if so, add
                    if (count == group.array.length) {
                        set = CreateStringSet();
                        AddAllToSet(set, group);
                        AddToSet(set, com);

                        // Add if not already added
                        str = ToSortedString(set);
                        if(!SetContains(ai, str)){
                            next[(int)nextLen] = set;
                            nextLen = nextLen + 1d;
                            AddToSet(ai, str);
                        }
                    }
                }
            }

            if(nextLen > 0) {
                prev = next;
                prevLen = nextLen;
            }else{
                done = true;
            }
        }

        // Produce output
        last = prev[0];

        arr = CreateStringArrayReferenceLengthValue(last.array.length, "".toCharArray());

        for(i = 0; i < last.array.length; i = i + 1d){
            str = ArrayIndex(last.array, i).string;
            arr.stringArray[(int)i].string = str;
        }

        xQuickSortStrings(arr);

        output = ArrayToString(arr);

        return output;
    }

    public static char[] ToSortedString(StringSet set) {
        double i;
        char [] str, output;
        StringArrayReference arr;

        arr = CreateStringArrayReferenceLengthValue(set.array.length, "".toCharArray());

        for(i = 0; i < set.array.length; i = i + 1d){
            str = ArrayIndex(set.array, i).string;
            arr.stringArray[(int)i].string = str;
        }

        xQuickSortStrings(arr);

        output = ArrayToString(arr);

        return output;
    }

    public static char[] ArrayToString(StringArrayReference strArr) {
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

    public static class StringSet{
        public RedBlackTree rbtree;
        public Array array;
    }

    public static StringSet CreateStringSet(){
        StringSet set;

        set = new StringSet();
        set.rbtree = CreateRedBlackTree();
        set.array = CreateArray();

        return set;
    }

    public static void AddToSet(StringSet set, char [] str){
        BooleanReference foundRef;
        double p;

        foundRef = CreateBooleanReference(false);

        Search(set.rbtree, str, foundRef);

        if(!foundRef.booleanValue){
            AddStringToArray(set.array, str);
            p = ArrayLength(set.array) - 1d;
            InsertData(set.rbtree, str, CreateNumberData(p));
        }
    }

    public static void AddAllToSet(StringSet set, StringSet group) {
        double i;
        char[] str;

        for(i = 0d; i < ArrayLength(group.array); i = i + 1d){
            str = ArrayIndex(group.array, i).string;
            AddToSet(set, str);
        }
    }

    public static void RemoveFromSet(StringSet set, char [] str){
        BooleanReference foundRef;
        RedBlackNode node;
        double p;

        foundRef = CreateBooleanReference(false);

        node = Search(set.rbtree, str, foundRef);

        if(foundRef.booleanValue){
            p = node.value.number;

            Remove(set.rbtree, node);
            ArrayRemove(set.array, p);
        }
    }

    public static boolean SetContains(StringSet set, char [] str){
        BooleanReference foundRef;

        foundRef = CreateBooleanReference(false);

        Search(set.rbtree, str, foundRef);

        return foundRef.booleanValue;
    }

    public static boolean TreeHasKey(RedBlackTree tree, char [] str){
        BooleanReference foundRef;

        foundRef = CreateBooleanReference(false);

        Search(tree, str, foundRef);

        return foundRef.booleanValue;
    }

    public static Data GetTreeData(RedBlackTree tree, char [] str){
        BooleanReference foundRef;

        foundRef = CreateBooleanReference(false);

        RedBlackNode node = Search(tree, str, foundRef);

        return node.value;
    }
}