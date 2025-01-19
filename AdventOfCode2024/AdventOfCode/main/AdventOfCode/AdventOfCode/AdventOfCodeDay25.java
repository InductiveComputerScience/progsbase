package AdventOfCode.AdventOfCode;

import references.references.NumberArrayReference;
import references.references.StringReference;

import static aarrays.arrays.arrays.aStringsEqual;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static references.references.references.CreateNumberArrayReferenceLengthValue;
import static references.references.references.CreateStringArrayReferenceLengthValue;
import static strings.strings.strings.*;

public class AdventOfCodeDay25 {
    public static char[] ComputeDay25Part1(char[] input) {
        double n, i, j, k, count;
        char [] output, line;
        StringReference [] lines;
        double keysLen, locksLen, keyNr, lockNr;
        NumberArrayReference [] keys;
        NumberArrayReference [] locks;
        StringReference [] field;
        NumberArrayReference lock, key;
        boolean fit;

        // Get keys and locks
        lines = SplitByCharacter(input, '\n');

        keysLen = 0d;
        locksLen = 0d;
        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;

            if(aStringsEqual(line, "#####".toCharArray())){
                locksLen = locksLen + 1d;
            }

            if(aStringsEqual(line, ".....".toCharArray())){
                keysLen = keysLen + 1d;
            }

            i = i + 7d;
        }

        keys = new NumberArrayReference[(int)keysLen];
        locks = new NumberArrayReference[(int)locksLen];

        field = CreateStringArrayReferenceLengthValue(7d, "     ".toCharArray()).stringArray;

        keyNr = 0d;
        lockNr = 0d;
        for(i = 0d; i < lines.length; i = i + 1d){
            line = lines[(int)i].string;

            if(aStringsEqual(line, "#####".toCharArray())){
                field[0].string = lines[(int)(i+0)].string;
                field[1].string = lines[(int)(i+1)].string;
                field[2].string = lines[(int)(i+2)].string;
                field[3].string = lines[(int)(i+3)].string;
                field[4].string = lines[(int)(i+4)].string;
                field[5].string = lines[(int)(i+5)].string;
                field[6].string = lines[(int)(i+6)].string;

                locks[(int)lockNr] = CreateNumberArrayReferenceLengthValue(5, 0);

                for(k = 0; k < 5; k = k + 1) {
                    count = 0d;

                    // Count set
                    for (j = 1; j < 7; j = j + 1) {
                        if(AdventOfCodeLib.GetCharacter(field, k, j) == '#'){
                            count = count + 1d;
                        }
                    }

                    locks[(int)lockNr].numberArray[(int)k] = count;
                }

                lockNr = lockNr + 1d;
            }

            if(aStringsEqual(line, ".....".toCharArray())){
                field[0].string = lines[(int)(i+0)].string;
                field[1].string = lines[(int)(i+1)].string;
                field[2].string = lines[(int)(i+2)].string;
                field[3].string = lines[(int)(i+3)].string;
                field[4].string = lines[(int)(i+4)].string;
                field[5].string = lines[(int)(i+5)].string;
                field[6].string = lines[(int)(i+6)].string;

                keys[(int)keyNr] = CreateNumberArrayReferenceLengthValue(5, 0);

                for(k = 0; k < 5; k = k + 1) {
                    count = 0d;

                    // Count set
                    for (j = 1; j < 7; j = j + 1) {
                        if(AdventOfCodeLib.GetCharacter(field, k, 7 - j - 1) == '#'){
                            count = count + 1d;
                        }
                    }

                    keys[(int)keyNr].numberArray[(int)k] = count;
                }

                keyNr = keyNr + 1d;
            }

            i = i + 7d;
        }

        // Compute fits
        n = 0d;
        for(i = 0; i < lockNr; i = i + 1d){
            lock = locks[(int)i];
            for(j = 0; j < keyNr; j = j + 1d){
                key = keys[(int)j];

                fit = true;

                for(k = 0; k < 5 && fit; k = k + 1d){
                    if(key.numberArray[(int)k] + lock.numberArray[(int)k] <= 5d){
                    }else{
                        fit = false;
                    }
                }

                if(fit){
                    n = n + 1d;
                }
            }
        }

        // Write output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }
}


