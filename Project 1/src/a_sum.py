from sys import stdin, stdout


def readln():
    return stdin.readline().rstrip()


def outln(n):
    stdout.write(str(n))
    stdout.write("\n")


def main():
    line = readln()
    line_splitted = line.split()
    num_1 = int(line_splitted[0])
    num_2 = int(line_splitted[1])
    num_sum = num_1 + num_2

    outln(num_sum)


if __name__ == '__main__':
    main()
