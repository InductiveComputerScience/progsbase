package AdventOfCode.AdventOfCode;

import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import lists.LinkedListNumbers.Structures.LinkedListNumbers;
import references.references.NumberReference;
import references.references.StringReference;

import static aarrays.arrays.arrays.aCopyNumberArray;
import static java.lang.Math.*;
import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;
import static lists.LinkedListNumbers.LinkedListNumbersFunctions.LinkedListNumbersFunctions.*;
import static math.math.math.Truncate;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static numbers.StringToNumber.StringToNumber.StringToNumberArray;
import static references.references.references.CreateNumberArrayReferenceLengthValue;
import static strings.strings.strings.SplitByCharacter;
import static strings.strings.strings.SplitByString;

public class AdventOfCodeDay17 {
    public static char[] ComputeDay17Part1(char[] input) {
        char [] output;
        StringReference [] lines;
        double A, B, C, i;
        double [] program;

        // Read field
        lines = SplitByCharacter(input, '\n');

        StringReference[] parts = SplitByString(lines[0].string, ": ".toCharArray());
        A = CreateNumberFromDecimalString(parts[1].string);

        parts = SplitByString(lines[1].string, ": ".toCharArray());
        B = CreateNumberFromDecimalString(parts[1].string);

        parts = SplitByString(lines[2].string, ": ".toCharArray());
        C = CreateNumberFromDecimalString(parts[1].string);

        parts = SplitByString(lines[4].string, ": ".toCharArray());
        program = StringToNumberArray(parts[1].string);


        //System.out.println("A: " + A + ", B: " + B + ", C: " + C);
        //System.out.println(Arrays.toString(program));

        double [] outputNumbers = RunProgram(A, B, C, program);

        LinkedListCharacters ll = CreateLinkedListCharacter();

        for(i = 0d; i < outputNumbers.length; i = i + 1d){
            char [] nstr = CreateStringDecimalFromNumber(outputNumbers[(int)i]);
            LinkedListCharactersAddString(ll, nstr);
            if(i + 1d != outputNumbers.length){
                LinkedListAddCharacter(ll, ',');
            }
        }

        output = LinkedListCharactersToArray(ll);

        return output;
    }

    public static double [] RunProgram(double rA, double rB, double rC, double[] program) {
        double ip, opcode, operand, a, b, x, i;
        LinkedListNumbers output;
        boolean done;

        output = CreateLinkedListNumbers();

        ip = 0d;

        done = false;
        for(i = 0d; !done; i = i + 1d){
            if(ip >= program.length){
                done = true;
            }

            if(!done) {
                opcode = program[(int) ip];
                operand = program[(int) (ip + 1d)];

                if (opcode == 0d) { // adv
                    a = rA;
                    b = GetCombo(rA, rB, rC, operand);

                    x = a / pow(2d, b);

                    x = Truncate(x);
                    rA = x;

                    ip = ip + 2d;
                }else if (opcode == 1d) { // bxl
                    a = rB;
                    b = operand;

                    //x = Xor4Byte(a, b);
                    x = (long)a ^ (long)b;

                    x = Truncate(x);
                    rB = x;

                    ip = ip + 2d;
                }else if (opcode == 2d) { // bst
                    a = GetCombo(rA, rB, rC, operand);

                    //x = a % 8d;
                    x = (long)a & 7;

                    x = Truncate(x);
                    rB = x;

                    ip = ip + 2d;
                }else if (opcode == 3d) { // jnz
                    if (rA == 0d) {
                        // Do nothing.
                        ip = ip + 2d;
                    } else {
                        a = operand;

                        ip = a;
                    }
                }else if (opcode == 4d) { // bxc
                    a = rB;
                    b = rC;

                    //x = Xor4Byte(a, b);
                    x = (long)a ^ (long)b;

                    x = Truncate(x);
                    rB = x;

                    ip = ip + 2d;
                }else if (opcode == 5d) { // out
                    a = GetCombo(rA, rB, rC, operand);

                    //x = a % 8d;
                    x = (long)a & 7;

                    LinkedListAddNumber(output, x);

                    ip = ip + 2d;
                }else if (opcode == 6d) { // bdv
                    a = rA;
                    b = GetCombo(rA, rB, rC, operand);

                    //x = a / pow(2d, b);
                    x = (long)a >> (long)b;

                    x = Truncate(x);
                    rB = x;

                    ip = ip + 2d;
                }else if (opcode == 7d) { // cdv
                    a = rA;
                    b = GetCombo(rA, rB, rC, operand);

                    //x = a / pow(2d, b);
                    x = (long)a >> (long)b;

                    x = Truncate(x);
                    rC = x;

                    ip = ip + 2d;
                }
            }
        }

        return LinkedListNumbersToArray(output);
    }

    public static double GetCombo(double rA, double rB, double rC, double operand) {
        double r;

        r = 0d;

        if(operand == 0d || operand == 1d || operand == 2d || operand == 3d){
            r = operand;
        }else if(operand == 4d){
            r = rA;
        }else if(operand == 5d){
            r = rB;
        }else if(operand == 6d) {
            r = rC;
        }

        return r;
    }

    public static char[] ComputeDay17Part2(char[] input) {
        char [] output;
        StringReference [] lines, parts;
        double B, C;
        double [] program, blocks;
        NumberReference aRef;

        // Read field
        lines = SplitByCharacter(input, '\n');

        parts = SplitByString(lines[1].string, ": ".toCharArray());
        B = CreateNumberFromDecimalString(parts[1].string);

        parts = SplitByString(lines[2].string, ": ".toCharArray());
        C = CreateNumberFromDecimalString(parts[1].string);

        parts = SplitByString(lines[4].string, ": ".toCharArray());
        program = StringToNumberArray(parts[1].string);

        blocks = CreateNumberArrayReferenceLengthValue(program.length, 0d).numberArray;
        aRef = new NumberReference();
        CheckA(B, C, program, blocks.length - 1d, blocks, aRef);

        output = CreateStringDecimalFromNumber(aRef.numberValue);

        return output;
    }

    public static boolean CheckA(double B, double C, double [] program, double p, double[] blocks_, NumberReference aRef){
        boolean done, matchDone;
        double [] outputNumbers, blocks;
        double i, j, match, A;

        blocks = aCopyNumberArray(blocks_);

        done = false;
        for(i = 0d; i <= 8 && !done; i = i + 1d) {
            blocks[(int)p] = i;

            A = MakeA(blocks);

            outputNumbers = RunProgram(A, B, C, program);

            if(outputNumbers.length == blocks.length) {

                matchDone = false;
                match = 0d;
                for (j = 0; j < outputNumbers.length && !matchDone; j = j + 1d) {
                    if (program[(int) (blocks.length - 1d - j)] == outputNumbers[(int) (blocks.length - 1d - j)]) {
                        match = match + 1d;
                    } else {
                        matchDone = true;
                    }
                }

                if(match == blocks.length){
                    done = true;
                    aRef.numberValue = A;
                }

                if(!done) {
                    if (match >= blocks.length - p) {
                        done = CheckA(B, C, program, p - 1d, blocks, aRef);
                    }
                }
            }
        }

        return done;
    }

    public static double MakeA(double[] blocks) {
        double i, A;

        A = 0d;

        for(i = 0; i < blocks.length; i = i + 1d){
            A = A + blocks[(int)(blocks.length - i - 1d)] * pow(8, blocks.length - i - 1d);
        }

        return A;
    }
}






























