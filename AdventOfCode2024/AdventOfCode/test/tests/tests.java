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

        // Day 1 Part 1
        char [] input = """
3   4
4   3
2   5
1   3
3   9
3   3""".toCharArray();

        char [] output = ComputeDay1Part1(input);

        AssertStringEquals(output, "11".toCharArray(), failures);

        // Day 1 Part 2
        output = ComputeDay1Part2(input);

        AssertStringEquals(output, "31".toCharArray(), failures);

        // Day 2 Part 1
        input = """
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".toCharArray();

        output = ComputeDay2Part1(input);

        AssertStringEquals(output, "2".toCharArray(), failures);

        // Day 2 Part 2
        input = """
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".toCharArray();

        output = ComputeDay2Part2(input);

        AssertStringEquals(output, "4".toCharArray(), failures);

        // Day 3 Part 1
        input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))".toCharArray();

        output = ComputeDay3Part1(input);

        AssertStringEquals(output, "161".toCharArray(), failures);

        // Day 3 Part 2
        input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))".toCharArray();

        output = ComputeDay3Part2(input);

        AssertStringEquals(output, "48".toCharArray(), failures);

        // Day 4 Part 1
        input = """
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX""".toCharArray();

        output = ComputeDay4Part1(input);

        AssertStringEquals(output, "18".toCharArray(), failures);

        // Day 4 Part 2
        input = """
.M.S......
..A..MSMS.
.M.S.MAA..
..A.ASMSM.
.M.S.M....
..........
S.S.S.S.S.
.A.A.A.A..
M.M.M.M.M.
..........""".toCharArray();

        output = ComputeDay4Part2(input);

        AssertStringEquals(output, "9".toCharArray(), failures);

        // Day 5 Part 1:
        input = """
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47""".toCharArray();

        output = ComputeDay5Part1(input);

        AssertStringEquals(output, "143".toCharArray(), failures);

        // Day 5 Part 2

        output = ComputeDay5Part2(input);

        AssertStringEquals(output, "123".toCharArray(), failures);

        // Day 6 Part 1

        input = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...""".toCharArray();

        output = ComputeDay6Part1(input);

        AssertStringEquals(output, "41".toCharArray(), failures);

        // Day 6 part 2

        output = ComputeDay6Part2(input);

        AssertStringEquals(output, "6".toCharArray(), failures);

        // Day 7 Part 1

        input = """
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20""".toCharArray();

        output = ComputeDay7Part1(input);

        AssertStringEquals(output, "3749".toCharArray(), failures);

        // Day 7 Part 2

        output = ComputeDay7Part2(input);

        AssertStringEquals(output, "11387".toCharArray(), failures);

        // Day 8, Part 1

        input = """
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............""".toCharArray();

        output = ComputeDay8Part1(input);

        AssertStringEquals(output, "14".toCharArray(), failures);

        // Day 8, Part 2
        output = ComputeDay8Part2(input);

        AssertStringEquals(output, "34".toCharArray(), failures);

        // Day 9, Part 1

        input = "2333133121414131402".toCharArray();

        output = ComputeDay9Part1(input);

        AssertStringEquals(output, "1928".toCharArray(), failures);

        // Day 9, Part 2

        output = ComputeDay9Part2(input);

        AssertStringEquals(output, "2858".toCharArray(), failures);

        // Day 10, Part 1

        input = """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732""".toCharArray();

        output = ComputeDay10Part1(input);

        AssertStringEquals(output, "36".toCharArray(), failures);

        // Day 10, part 2

        output = ComputeDay10Part2(input);

        AssertStringEquals(output, "81".toCharArray(), failures);

        // Day 11, Part 1

        input = "125 17".toCharArray();

        output = ComputeDay11Part1(input);

        AssertStringEquals(output, "55312".toCharArray(), failures);

        // Day 11, Part 2

        output = ComputeDay11Part2(input);

        AssertStringEquals(output, "65601038650482".toCharArray(), failures);

        // Day 12, Part 1

        input = """
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE""".toCharArray();

        output = ComputeDay12Part1(input);

        AssertStringEquals(output, "1930".toCharArray(), failures);

        // Day 12, Part 2

        output = ComputeDay12Part2(input);

        AssertStringEquals(output, "1206".toCharArray(), failures);

        // Done

        return failures.numberValue;
    }
}

