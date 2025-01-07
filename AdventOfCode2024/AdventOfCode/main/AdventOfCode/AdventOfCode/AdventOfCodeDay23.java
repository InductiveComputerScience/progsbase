package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.Data;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;
import references.references.StringReference;

import java.util.*;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Structures.Structures.*;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.CreateBooleanReference;
import static strings.strings.strings.*;

public class AdventOfCodeDay23 {
    public static char[] ComputeDay23Part1(char[] input) {
        double n, i, j, secret;
        char [] output, secretStr;
        StringReference [] lines;
        //RedBlackTree computersSet, links;
        //Array computers, triples;
        Map<String, List<String>> links;
        Set<String> computers;

        // Read initial secrets
        lines = SplitByCharacter(input, '\n');

        //computersSet = CreateRedBlackTree();
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

        // Find all triples
        Set<Set<String>> triples = new HashSet<>();
        Set<String> col = new HashSet<>();
        for(String com : computers){
            col.add(com);
            FindLinks(triples, links, com, com, col, 0);
            col.remove(com);
        }

        //System.out.println(triples);

        // Filter those with a t-computer
        Set<String> filtered = new HashSet<>();
        for(Set<String> triple : triples){
            if(triple.toString().contains(", t") || triple.toString().contains("[t")){
                filtered.add(triple.toString());
            }
        }

        //System.out.println(filtered);

        // Produce output
        n = filtered.size();
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double NameToNum(char [] name){
        double n;

        n = name[0]*100 + name[1];

        return n;
    }

    public static void FindLinks(Set<Set<String>> triples, Map<String, List<String>> links, String com, String goal, Set<String> col, double d) {

        if(d == 2){
            List<String> cons = links.get(com);
            for(String con : cons) {
                if (con.equals(goal)) {
                    triples.add(new HashSet<>(col));
                }
            }
        }else{
            List<String> cons = links.get(com);
            for(String con : cons) {
                if(!col.contains(con)) {
                    col.add(con);
                    FindLinks(triples, links, con, goal, col, d + 1);
                    col.remove(con);
                }
            }
        }
    }

    public static boolean SetContains(RedBlackTree computersSet, char[] key) {
        BooleanReference found = new BooleanReference();
        Search(computersSet, key, found);
        return found.booleanValue;
    }


    public static char[] ComputeDay23Part2(char[] input) {
        double n, i, j, secret;
        char [] output, secretStr;
        StringReference [] lines;
        //RedBlackTree computersSet, links;
        //Array computers, triples;
        Map<String, List<String>> links;
        Set<String> computers;
        List<Set<Set<String>>> joined;

        joined = new ArrayList<>();

        // Read initial secrets
        lines = SplitByCharacter(input, '\n');

        //computersSet = CreateRedBlackTree();
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

}


























