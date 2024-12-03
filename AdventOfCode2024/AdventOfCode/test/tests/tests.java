package tests;

import references.references.NumberReference;

import static AdventOfCode.AdventOfCode.AdventOfCode.*;
import static references.references.references.CreateNumberReference;
import static testing.testing.testing.AssertEquals;
import static testing.testing.testing.AssertStringEquals;

public class tests {
    public static double test(){
        NumberReference failures;

        failures = CreateNumberReference(0d);

        char [] input = """
3   4
4   3
2   5
1   3
3   9
3   3
""".toCharArray();

        char [] output = ComputeDay1Part1(input);

        AssertStringEquals(output, "11".toCharArray(), failures);

        input = """
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".toCharArray();

        output = ComputeDay2Part1(input);

        AssertStringEquals(output, "2".toCharArray(), failures);

        input = """
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".toCharArray();

        output = ComputeDay2Part2(input);

        AssertStringEquals(output, "4".toCharArray(), failures);

        return failures.numberValue;
    }


}
