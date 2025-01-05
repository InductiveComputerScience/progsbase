package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Array;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;
import references.references.StringReference;

import static Bits.Bitwise.Bitwise.XorBytes;
import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Structures.Structures.*;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static java.lang.Math.floor;
import static math.math.math.Truncate;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static references.references.references.CreateBooleanReference;
import static strings.strings.strings.*;

public class AdventOfCodeDay22 {
    public static char[] ComputeDay22Part1(char[] input) {
        double n, i, j, secret;
        char [] output, secretStr;
        StringReference [] secretsStr;

        // Read initial secrets
        secretsStr = SplitByCharacter(input, '\n');

        n = 0d;
        for(i = 0d; i < secretsStr.length; i = i + 1d){
            secretStr = secretsStr[(int) i].string;
            secret = CreateNumberFromDecimalString(secretStr);

            for(j = 0d; j < 2000; j = j + 1d){
                secret = Next(secret);
            }

            n = n + secret;
        }

        // Produce output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }

    public static double Next(double secret) {
        double r;

        // Step 1
        r = secret * 64;
        secret = Mix(secret, r);
        secret = Prune(secret);

        // Step 2:
        r = floor(secret / 32);
        secret = Mix(secret, r);
        secret = Prune(secret);

        // Step 3:
        r = secret * 2048;
        r = Mix(secret, r);
        secret = Prune(r);

        return secret;
    }

    public static double Mix(double a, double b) {
        double A, B;

        A = Truncate(a);
        B = Truncate(b);

        return XorBytes(A, B, 6d);
    }

    public static double Prune(double a) {
        return Truncate(a % 16777216d);
    }

    public static char[] ComputeDay22Part2(char[] input) {
        double n, i, j, secret, prev, price, diff, len, sum, maximum;
        char [] output, secretStr, key;
        StringReference [] secretsStr;
        RedBlackTree [] bananas;
        Array seq;
        BooleanReference found;
        RedBlackNode node;
        Array keys;
        RedBlackTree keyCache;

        keyCache = CreateRedBlackTree();
        keys = CreateArray();

        // Read initial secrets
        secretsStr = SplitByCharacter(input, '\n');

        bananas = new RedBlackTree [secretsStr.length];

        for(i = 0d; i < secretsStr.length; i = i + 1d){
            bananas[(int)i] = CreateRedBlackTree();
            seq = CreateArray();

            secretStr = secretsStr[(int) i].string;
            secret = CreateNumberFromDecimalString(secretStr);

            price = floor(secret % 10);

            prev = price;

            for(j = 0d; j < 2000; j = j + 1d){
                secret = Next(secret);

                price = floor(secret % 10);
                diff = price - prev;

                AddNumberToArray(seq, diff);

                len = ArrayLength(seq);
                if(len >= 4) {
                    key = CreateStringDecimalFromNumber(ArrayIndex(seq, len - 4).number);
                    key = AppendCharacter(key, ',');
                    key = AppendString(key, CreateStringDecimalFromNumber(ArrayIndex(seq, len - 3).number));
                    key = AppendCharacter(key, ',');
                    key = AppendString(key, CreateStringDecimalFromNumber(ArrayIndex(seq, len - 2).number));
                    key = AppendCharacter(key, ',');
                    key = AppendString(key, CreateStringDecimalFromNumber(ArrayIndex(seq, len - 1).number));

                    found = CreateBooleanReference(false);
                    Search(bananas[(int)i], key, found);
                    if(!found.booleanValue){
                        InsertData(bananas[(int)i], key, CreateNumberData(price));
                    }

                    found = CreateBooleanReference(false);
                    Search(keyCache, key, found);
                    if(!found.booleanValue){
                        AddStringToArray(keys, key);
                        InsertData(keyCache, key, CreateNumberData(price));
                    }
                }

                prev = price;
            }
        }

        maximum = 0;
        for(i = 0d; i < ArrayLength(keys); i = i + 1d){
            key = ArrayIndex(keys, i).string;
            sum = 0;
            // Find key price
            for(j = 0d; j < secretsStr.length; j = j + 1d){
                found = CreateBooleanReference(false);
                node = Search(bananas[(int) j], key, found);
                if(found.booleanValue){
                    sum = sum + node.value.number;
                }
            }

            // Check
            if(sum > maximum){
                maximum = sum;
            }
        }

        n = maximum;

        // Produce output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }
}