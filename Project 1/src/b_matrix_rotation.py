import input as inp


def read_matrix(n_rows, n_cols):
    matrix = list()

    for i in range(n_rows):
        row = list()
        line_splitted_row = inp.readln().split()

        for j in range(n_cols):
            row.append(line_splitted_row[j])

        matrix.append(row)

    return matrix


def print_matrix(matrix):
    for i in range(len(matrix)):
        row = matrix[i]
        l = len(row)

        for j in range(l):
            inp.out(row[j])

            if j != l - 1:
                inp.out(" ")

        inp.out("\n")


def rotate_matrix(matrix, angle):
    if angle % 90 != 0:
        raise ValueError("Angle must be a multiple of 90!")

    angle = angle % 360

    if angle == 0:
        return matrix

    n_rows = len(matrix)
    n_cols = len(matrix[0])
    rotated_matrix = list()

    if angle == 90:
        rotated_matrix = [[0 for j in range(n_rows)] for i in range(n_cols)]
        for i in range(n_cols):
            for j in range(n_rows):
                rotated_matrix[i][n_rows - j - 1] = matrix[j][i]

    elif angle == 180:
        rotated_matrix = [[0 for j in range(n_cols)] for i in range(n_rows)]

        for i in range(n_rows):
            for j in range(n_cols):
                rotated_matrix[n_rows - i - 1][n_cols - j - 1] = matrix[i][j]
    else:
        rotated_matrix = [[0 for j in range(n_rows)] for i in range(n_cols)]

        for i in range(n_cols):
            for j in range(n_rows):
                rotated_matrix[n_cols - i - 1][j] = matrix[j][i]
    return rotated_matrix


def main():
    angles = [90, 180, 270]
    n_rows = int()
    n_cols = int()
    matrix = list()
    rotated_matrix = list()
    line = str()

    line = inp.readln()
    line_splitted = line.split()

    n_rows = int(line_splitted[0])
    n_cols = int(line_splitted[1])

    matrix = read_matrix(n_rows, n_cols)

    for angle in angles:
        inp.outln(str(angle))
        rotated_matrix = rotate_matrix(matrix, angle)
        print_matrix(rotated_matrix)


if __name__ == '__main__':
    main()
