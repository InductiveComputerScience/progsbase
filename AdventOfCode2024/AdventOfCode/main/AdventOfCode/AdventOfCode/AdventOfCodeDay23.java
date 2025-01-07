package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.Data;
import QuickSort.QuickSortStrings.QuickSortStrings;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;
import references.references.StringArrayReference;
import references.references.StringReference;

import java.util.*;

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
        double i;
        char [] output;
        StringReference [] lines;
        //RedBlackTree computersSet, links;
        //Array computers, triples;
        Map<String, List<String>> links;
        Set<String> computers;
        List<Set<Set<String>>> joined;

        joined = new ArrayList<>();

        // Read initial secrets
        lines = SplitByCharacter(input, '\n');

        links = new HashMap<>();
        computers = new HashSet<>();

        // Find computers and links
        for(i = 0; i < lines.length; i = i + 1d){
            StringReference[] cs = SplitByCharacter(lines[(int) i].string, '-');
            String c1 = new String(cs[0].string);
            String c2 = new String(cs[1].string);
            computers.add(c1);
            computers.add(c2);

            if(!links.containsKey(c1)){
                links.put(c1, new ArrayList<>());
            }
            links.get(c1).add(c2);

            if(!links.containsKey(c2)){
                links.put(c2, new ArrayList<>());
            }
            links.get(c2).add(c1);
        }

        //System.out.println(computers);
        //System.out.println(links);

        // 0
        joined.add(new HashSet<>());

        // 1
        joined.add(new HashSet<>());

        // 2
        Set<Set<String>> twos = new HashSet<>();
        for(Map.Entry<String, List<String>> x : links.entrySet()){
            for(String con : x.getValue()){
                Set<String> set = new HashSet<>();
                set.add(x.getKey());
                set.add(con);
                twos.add(set);
            }
            joined.add(twos);
        }

        Set<Set<String>> prev = twos;

        boolean done = false;
        for(;!done;) {
            // n + 1
            Set<Set<String>> next = new HashSet<>();
            for (Set<String> group : prev) {
                // Find all computers linked to all
                for (String com : computers) {
                    int count = 0;
                    List<String> cons = links.get(com);
                    for (String con : cons) {
                        if (group.contains(con)) {
                            count = count + 1;
                        }
                    }

                    // Check if linked to the same as the group size, if so, add
                    if (count == group.size()) {
                        Set<String> set = new HashSet<>();
                        set.addAll(group);
                        set.add(com);
                        next.add(set);
                    }
                }
            }
            if(next.size() > 0) {
                joined.add(next);
                prev = next;
            }else{
                done = true;
            }
        }

        //System.out.println(joined);

        Set<String> last = joined.get(joined.size() - 1).stream().toList().get(0);

        List<String> code = new ArrayList<>();
        code.addAll(last);
        code.sort(String::compareTo);

        StringBuilder sb = new StringBuilder();
        for(i = 0; i < code.size(); i++){
            sb.append(code.get((int)i));
            if(i + 1 < code.size()){
                sb.append(",");
            }
        }
        System.out.println(sb);


        // Produce output
        output = sb.toString().toCharArray();

        return output;
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


























