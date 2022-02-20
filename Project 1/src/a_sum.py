import input as inp


def main():
    line = inp.readln()
    line_splitted = line.split()
    num_1 = int(line_splitted[0])
    num_2 = int(line_splitted[1])
    num_sum = num_1 + num_2

    inp.outln(num_sum)


if __name__ == '__main__':
    main()
