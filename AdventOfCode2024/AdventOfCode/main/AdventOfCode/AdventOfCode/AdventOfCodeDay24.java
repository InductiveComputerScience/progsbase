package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Structure;
import references.references.StringReference;

import static DataStructures.Array.Structures.Structures.*;
import static aarrays.arrays.arrays.aStringsEqual;
import static java.lang.Math.floor;
import static java.lang.Math.pow;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static strings.strings.strings.*;

public class AdventOfCodeDay24 {
    public static char[] ComputeDay24Part1(char[] input) {
        double n, i, op;
        char [] output, line;
        StringReference [] lines, parts;
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

                //System.out.println(new String(parts[0].string) + ":" + n);
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

        // Compute Value
        n = ComputeValue(values, operations);

        // Write output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    private static double ComputeValue(Structure values, Operation[] operations) {
        boolean done;
        Operation operation;
        double n;
        double i;
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
                                //System.out.println(new String(operation.output) + " -> " + 1);
                            } else {
                                AddNumberToStruct(values, operation.output, 0);
                                //System.out.println(new String(operation.output) + " -> " + 0);
                            }
                        }
                        if (aStringsEqual(operation.operation, "OR".toCharArray())) {
                            if (v1 == 1 || v2 == 1) {
                                AddNumberToStruct(values, operation.output, 1);
                                //System.out.println(new String(operation.output) + " -> " + 1);
                            } else {
                                AddNumberToStruct(values, operation.output, 0);
                                //System.out.println(new String(operation.output) + " -> " + 0);
                            }
                        }
                        if (aStringsEqual(operation.operation, "XOR".toCharArray())) {
                            if ((v1 == 1) && (v2 == 0) || (v1 == 0) && (v2 == 1)) {
                                AddNumberToStruct(values, operation.output, 1);
                                //System.out.println(new String(operation.output) + " -> " + 1);
                            } else {
                                AddNumberToStruct(values, operation.output, 0);
                                //System.out.println(new String(operation.output) + " -> " + 0);
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

        n = ComputeVarValue(values, 'z');
        /*double x = ComputeVarValue(values, 'x');
        double y = ComputeVarValue(values, 'y');

        System.out.println("x: " + (long)x);
        System.out.println("y: " + (long)y);
        System.out.println("z: " + (long)n);*/

        return n;
    }

    private static double ComputeVarValue(Structure values, char var) {
        boolean done;
        double i;
        double n;
        n = 0d;
        done = false;
        for(i = 0d; !done; i = i + 1d){
            char[] z = ComputeVarName(var, i);

            if(StructHasKey(values, z)) {

                double v = GetNumberFromStruct(values, z);
                n = n + v * pow(2, i);

                //System.out.println(new String(z) + ": " + v);
            }else{
                done = true;
            }
        }
        return n;
    }

    private static char[] ComputeVarName(char prefix, double i) {
        char [] v = "_00".toCharArray();
        v[0] = prefix;
        char[] nstr = CreateStringDecimalFromNumber(i);
        if(nstr.length == 1){
            v[2] = nstr[0];
        }
        if(nstr.length == 2){
            v[1] = nstr[0];
            v[2] = nstr[1];
        }
        return v;
    }

    static class Operation{
        public char [] input1;
        public char [] input2;
        public char [] operation;
        public char [] output;
    }

    public static char[] ComputeDay24Part2(char[] input) {
        double n, i, j, op;
        char [] output, line;
        StringReference [] lines, parts;
        Structure values;
        Operation [] operations;
        boolean done;
        Operation operation;

        // Read initial values
        lines = SplitByCharacter(input, '\n');

        // Read operations
        operations = new Operation[(int)(lines.length)];
        op = 0;
        for(i = 0d; i < lines.length; i = i + 1d){
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

        // Set input values
        double x, y;

        x = 30626032511001d;
        y = 28716883075641d;

        n = ComputeSum(operations, x, y);

        // Write output
        output = CreateStringDecimalFromNumber(n);

        System.out.println((long)x + " + " + (long)y + " = " + (long)n);
        System.out.println((long)x + " + " + (long)y + " = " + (long)(x+y));

        // Compute var roles
        char [] carry, int1, int2, int3, xvar, yvar, zvar;
        carry = "skt".toCharArray();
        int1 = "".toCharArray();
        int2 = "".toCharArray();
        int3 = "".toCharArray();
        for(i = 1; i <= 44; i = i + 1){
            xvar = ComputeVarName('x', i);
            yvar = ComputeVarName('y', i);
            zvar = ComputeVarName('z', i);

            System.out.println((int)i);

            // Find int1
            for(j = 0; j < operations.length; j = j + 1){
                operation = operations[(int)j];
                if(aStringsEqual(operation.operation, "XOR".toCharArray())){
                    if(aStringsEqual(operation.input1, xvar) && aStringsEqual(operation.input2, yvar)){
                        int1 = operation.output;
                    }else if(aStringsEqual(operation.input1, yvar) && aStringsEqual(operation.input2, xvar)){
                        int1 = operation.output;
                    }
                }
            }

            System.out.println("  int1 : " + new String(int1));

            // Find valop
            boolean foundValop = false;
            for(j = 0; j < operations.length; j = j + 1){
                operation = operations[(int)j];
                if(aStringsEqual(operation.operation, "XOR".toCharArray()) && aStringsEqual(operation.output, zvar)){
                    if(aStringsEqual(operation.input1, carry) && aStringsEqual(operation.input2, int1)){
                        System.out.println("  Found valop");
                        foundValop = true;
                    }else if(aStringsEqual(operation.input1, int1) && aStringsEqual(operation.input2, carry)){
                        System.out.println("  Found valop");
                        foundValop = true;
                    }
                }
            }

            if(!foundValop){
                System.out.println("  Expected valop: " + new String(carry) + " XOR " + new String(int1) + " -> " + new String(zvar));
            }

            // Find int2
            for(j = 0; j < operations.length; j = j + 1){
                operation = operations[(int)j];
                if(aStringsEqual(operation.operation, "AND".toCharArray())){
                    if(aStringsEqual(operation.input1, xvar) && aStringsEqual(operation.input2, yvar)){
                        int2 = operation.output;
                    }else if(aStringsEqual(operation.input1, yvar) && aStringsEqual(operation.input2, xvar)){
                        int2 = operation.output;
                    }
                }
            }

            System.out.println("  int2 : " + new String(int2));

            // Find int3
            for(j = 0; j < operations.length; j = j + 1){
                operation = operations[(int)j];
                if(aStringsEqual(operation.operation, "AND".toCharArray())){
                    if(aStringsEqual(operation.input1, int1) && aStringsEqual(operation.input2, carry)){
                        int3 = operation.output;
                    }else if(aStringsEqual(operation.input1, carry) && aStringsEqual(operation.input2, int1)){
                        int3 = operation.output;
                    }
                }
            }

            System.out.println("  int3 : " + new String(int3));

            // Find next carry
            for(j = 0; j < operations.length; j = j + 1){
                operation = operations[(int)j];
                if(aStringsEqual(operation.operation, "OR".toCharArray())){
                    if(aStringsEqual(operation.input1, int2) && aStringsEqual(operation.input2, int3)){
                        carry = operation.output;
                    }else if(aStringsEqual(operation.input1, int3) && aStringsEqual(operation.input2, int2)){
                        carry = operation.output;
                    }
                }
            }

            System.out.println("  new carry : " + new String(carry));
        }

        // Correct operations
        boolean success = false;

        n = ComputeSum(operations, x, y);
        if(n == 59342915586642d){
            success = true;
        }

        System.out.println(success);

        return output;
    }

    private static double ComputeSum(Operation[] operations, double x, double y) {
        Structure values;
        double i;
        double n;
        values = CreateStructure();

        double wx = x;
        for(i = 0; wx > 0; i = i + 1d){
            char [] vn = ComputeVarName('x', i);
            double d = wx % 2;
            wx = floor(wx / 2);
            AddNumberToStruct(values, vn, d);
        }

        double wy = y;
        for(i = 0; wy > 0; i = i + 1d){
            char [] vn = ComputeVarName('y', i);
            double d = wy % 2;
            wy = floor(wy / 2);
            AddNumberToStruct(values, vn, d);
        }

        // Compute Value
        n = ComputeValue(values, operations);
        return n;
    }
}











/*
x00 XOR y00 -> z00 -- val
y00 AND x00 -> skt -- carry

(
	input: x00, y00
	inter:
	outpu: skt
)

y01 XOR x01 -> kjn -- int1
skt XOR kjn -> z01 -- val
y01 AND x01 -> hth -- int2
skt AND kjn -> hjc -- int3
hjc OR hth -> pgc  -- carry

(
	input: skt, x01, y01
	inter: kjn, hth, hjc
	outp.: pgc
)

x02 XOR y02 -> fvj -- int1
pgc XOR fvj -> z02 -- val
x02 AND y02 -> nss -- int1
pgc AND fvj -> qrm -- int2
qrm OR nss -> wdm  -- carry

(
	input: pgc, x02, y02
	inter: fvj, nss, qrm
	outp.: wdm
)

y03 XOR x03 -> psv -- int
psv XOR wdm -> z03 -- val
x03 AND y03 -> nbc -- carry
wdm AND psv -> fwc -- int 2
fwc OR nbc -> rkm  -- int 3

(
	input: x03, y03
	inter:
	outp.:
)






*/














