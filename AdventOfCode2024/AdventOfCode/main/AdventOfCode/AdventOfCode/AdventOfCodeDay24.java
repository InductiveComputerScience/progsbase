package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.Data;
import DataStructures.Array.Structures.Structure;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;
import references.references.StringArrayReference;
import references.references.StringReference;

import java.util.HashSet;
import java.util.Set;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Structures.Structures.*;
import static QuickSort.QuickSortStrings.QuickSortStrings.xQuickSortStrings;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static aarrays.arrays.arrays.aCopyString;
import static aarrays.arrays.arrays.aStringsEqual;
import static java.lang.Math.pow;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static references.references.references.CreateBooleanReference;
import static references.references.references.CreateStringArrayReferenceLengthValue;
import static strings.strings.strings.*;

public class AdventOfCodeDay24 {
    public static char[] ComputeDay24Part1(char[] input) {
        double n, i, op;
        char [] output, line;
        StringReference [] lines, parts;
        RedBlackTree links;
        StringReference[] cs;
        char [] c1, c2, com, triple;
        Array filtered, array;
        Structure values;
        Operation [] operations;
        boolean done;
        Operation operation;

        // Read initial values
        lines = SplitByCharacter(input, '\n');

        // Read initial values
        values = CreateStructure();
        done = false;
        for(i = 0; i < lines.length && !done; i = i + 1d){
            line = lines[(int)i].string;

            if(line.length != 0){
                parts = SplitByCharacter(line, ':');

                n = CreateNumberFromDecimalString(Trim(parts[1].string));

                AddNumberToStruct(values, parts[0].string, n);

                System.out.println(new String(parts[0].string) + ":" + n);
            }else{
                done = true;
            }
        }

        // Read operations
        operations = new Operation[(int)(lines.length - i)];
        op = 0;
        for(; i < lines.length; i = i + 1d){
            operation = new Operation();
            line = lines[(int)i].string;

            parts = SplitByString(line, "->".toCharArray());

            operation.output = Trim(parts[1].string);

            if(ContainsString(parts[0].string, "AND".toCharArray())){
                operation.operation = "AND".toCharArray();
                parts = SplitByString(parts[0].string, "AND".toCharArray());
            }else if(ContainsString(parts[0].string, "XOR".toCharArray())){
                operation.operation = "XOR".toCharArray();
                parts = SplitByString(parts[0].string, "XOR".toCharArray());
            }else if(ContainsString(parts[0].string, "OR".toCharArray())){
                operation.operation = "OR".toCharArray();
                parts = SplitByString(parts[0].string, "OR".toCharArray());
            }

            operation.input1 = Trim(parts[0].string);
            operation.input2 = Trim(parts[1].string);

            operations[(int)op] = operation;
            op = op + 1d;
        }

        // Find outputs
        done = false;
        boolean didop;
        for(; !done; ){
            didop = false;
            for(i = 0; i < operations.length; i = i + 1d){
                operation = operations[(int)i];

                // Check that value has not already been produced
                if(!StructHasKey(values, operation.output)) {
                    // Check if both inputs are available
                    if (StructHasKey(values, operation.input1) && StructHasKey(values, operation.input2)) {
                        didop = true;

                        double v1 = GetNumberFromStruct(values, operation.input1);
                        double v2 = GetNumberFromStruct(values, operation.input2);

                        if (aStringsEqual(operation.operation, "AND".toCharArray())) {
                            if (v1 == 1 && v2 == 1) {
                                AddNumberToStruct(values, operation.output, 1);
                                System.out.println(new String(operation.output) + " -> " + 1);
                            } else {
                                AddNumberToStruct(values, operation.output, 0);
                                System.out.println(new String(operation.output) + " -> " + 0);
                            }
                        }
                        if (aStringsEqual(operation.operation, "OR".toCharArray())) {
                            if (v1 == 1 || v2 == 1) {
                                AddNumberToStruct(values, operation.output, 1);
                                System.out.println(new String(operation.output) + " -> " + 1);
                            } else {
                                AddNumberToStruct(values, operation.output, 0);
                                System.out.println(new String(operation.output) + " -> " + 0);
                            }
                        }
                        if (aStringsEqual(operation.operation, "XOR".toCharArray())) {
                            if ((v1 == 1) && (v2 == 0) || (v1 == 0) && (v2 == 1)) {
                                AddNumberToStruct(values, operation.output, 1);
                                System.out.println(new String(operation.output) + " -> " + 1);
                            } else {
                                AddNumberToStruct(values, operation.output, 0);
                                System.out.println(new String(operation.output) + " -> " + 0);
                            }
                        }
                    }
                }
            }
            if(!didop){
                done = true;
            }
        }

        // Produce output

        n = 0d;
        done = false;
        for(i = 0d; !done; i = i + 1d){
            char [] z = "z00".toCharArray();
            char[] nstr = CreateStringDecimalFromNumber(i);
            if(nstr.length == 1){
                z[2] = nstr[0];
            }
            if(nstr.length == 2){
                z[1] = nstr[0];
                z[2] = nstr[1];
            }

            if(StructHasKey(values, z)) {

                double v = GetNumberFromStruct(values, z);
                n = n + v * pow(2, i);

                System.out.println(new String(z) + ": " + v);
            }else{
                done = true;
            }
        }

        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    static class Operation{
        public char [] input1;
        public char [] input2;
        public char [] operation;
        public char [] output;
    }
}


























