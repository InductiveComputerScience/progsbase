package AdventOfCode.AdventOfCode;

import DataStructures.Array.Structures.Array;
import Trees.RedBlackTrees.RedBlackNode;
import Trees.RedBlackTrees.RedBlackTree;
import references.references.BooleanReference;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Structures.Structures.*;
import static Trees.RedBlackTrees.RedBlackTrees.*;
import static java.lang.Math.floor;
import static numbers.NumberToString.NumberToString.CreateStringDecimalFromNumber;
import static numbers.StringToNumber.StringToNumber.CreateNumberFromDecimalString;
import static references.references.references.CreateBooleanReference;
import static strings.strings.strings.*;

public class AdventOfCodeDay22 {
    public static char[] ComputeDay22Part1(char[] input) {
        double n, i, j, secret;
        char [] output;
        StringReference [] secretsStr;

        // Read initial secrets
        secretsStr = SplitByCharacter(input, '\n');

        n = 0d;
        for(i = 0d; i < secretsStr.length; i = i + 1d){
            char[] secretStr = secretsStr[(int) i].string;
            secret = CreateNumberFromDecimalString(secretStr);

            //System.out.println(secret);

            for(j = 0d; j < 2000; j = j + 1d){
                secret = Next(secret);
                //System.out.println(secret);
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
        long A = (long)a;
        long B = (long)b;

        return A ^ B;
    }

    public static double Prune(double a) {

        return (long)(a % 16777216);
    }

    public static char[] ComputeDay22Part2(char[] input) {
        double n, i, j, secret, prev, price, diff;
        char [] output;
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

        n = 0d;
        for(i = 0d; i < secretsStr.length; i = i + 1d){
            bananas[(int)i] = CreateRedBlackTree();
            seq = CreateArray();

            char[] secretStr = secretsStr[(int) i].string;
            secret = CreateNumberFromDecimalString(secretStr);

            //System.out.println(secret);

            price = floor(secret % 10);
            //System.out.println((int)secret + ": " + (int)price);

            prev = price;

            for(j = 0d; j < 2000; j = j + 1d){
                secret = Next(secret);

                price = floor(secret % 10);
                diff = price - prev;

                AddNumberToArray(seq, diff);

                //System.out.println((int)secret + ": " + (int)price + " ("+(int)diff+")");

                double len = ArrayLength(seq);
                if(len >= 4) {
                    char [] key;
                    key = CreateStringDecimalFromNumber(ArrayIndex(seq, len - 4).number);
                    key = AppendCharacter(key, ',');
                    key = AppendString(key, CreateStringDecimalFromNumber(ArrayIndex(seq, len - 3).number));
                    key = AppendCharacter(key, ',');
                    key = AppendString(key, CreateStringDecimalFromNumber(ArrayIndex(seq, len - 2).number));
                    key = AppendCharacter(key, ',');
                    key = AppendString(key, CreateStringDecimalFromNumber(ArrayIndex(seq, len - 1).number));

                    //System.out.println(key);

                    found = CreateBooleanReference(false);
                    Search(bananas[(int)i], key, found);
                    if(!found.booleanValue){
                        InsertData(bananas[(int)i], key, CreateNumberData(price));
                        //bananas[(int)i].put(key, price);
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

            System.out.println("finding " + i + "/" + secretsStr.length);
        }

        double sum = 0;
        double maximum = 0;
        for(i = 0d; i < ArrayLength(keys); i = i + 1d){
            System.out.println(i + "/" + ArrayLength(keys));
            char [] key = ArrayIndex(keys, i).string;
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

                //System.out.println("Found better:");
                //System.out.println(bestkey);
                //System.out.println(maximum);
            }
        }

        //System.out.println(bestkey);
        //System.out.println(maximum);
        n = maximum;

        // Produce output
        output = CreateStringDecimalFromNumber(n);

        return output;
    }
}































