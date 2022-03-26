from sys import stdin, stdout


class Node:
    def __init__(self, user_name, cartao, data):
        self.user_name = user_name
        self.card_dates = {cartao: data}
        self.left_node = None
        self.right_node = None

    def balance(self):
        left = 0

        if self.left_node:
            left = self.left_node.get_depth()
        right = 0

        if self.right_node:
            right = self.right_node.get_depth()

        return left - right

    def get_depth(self):
        left = 0

        if self.left_node:
            left = self.left_node.get_depth()

        right = 0

        if self.right_node:
            right = self.right_node.get_depth()

        return 1 + max(left, right)

    def rot_right_node(self):
        self.user_name, self.card_dates, self.left_node.user_name, self.left_node.card_dates = self.left_node.user_name, self.left_node.card_dates, self.user_name, self.card_dates
        old_right_node = self.right_node
        self.left_node, self.right_node = self.left_node.left_node, self.left_node
        self.right_node.left_node, self.right_node.right_node = self.right_node.right_node, old_right_node

    def rot_left_node(self):
        self.user_name, self.card_dates, self.right_node.user_name, self.right_node.card_dates = self.right_node.user_name, self.right_node.card_dates, self.user_name, self.card_dates
        old_left_node = self.left_node
        self.left_node, self.right_node = self.right_node, self.right_node.right_node
        self.left_node.left_node, self.left_node.right_node = old_left_node, self.left_node.left_node

    def rot_right_node_left_node(self):
        self.right_node.rot_right_node()
        self.rot_left_node()

    def rot_left_node_right_node(self):
        self.left_node.rot_left_node()
        self.rot_right_node()

    def check_balance(self):
        balance = self.balance()
        if balance > 1:
            if self.left_node and self.left_node.balance() > 0:
                self.rot_right_node()
            else:
                self.rot_left_node_right_node()
        elif balance < -1:
            if self.right_node and self.right_node.balance() < 0:
                self.rot_left_node()
            else:
                self.rot_right_node_left_node()

    def add(self, user_name, cartao, data):
        if user_name == self.user_name:
            if cartao not in self.card_dates.keys():
                self.card_dates[cartao] = data

                stdout.write("NOVO CARTAO INSERIDO\n")
            else:
                self.card_dates[cartao] = data

                stdout.write("CARTAO ATUALIZADO\n")

            return False

        elif user_name < self.user_name:
            if not self.left_node:
                self.left_node = Node(user_name, cartao, data)
            else:
                if not self.left_node.add(user_name, cartao, data):

                    return False
        else:
            if not self.right_node:
                self.right_node = Node(user_name, cartao, data)
            else:
                if not self.right_node.add(user_name, cartao, data):
                    
                    return False

        self.check_balance()

        return True

    def traverse_tree(self):
        if self.left_node:
            self.left_node.traverse_tree()

        stdout.write(self.__repr__() + "\n")

        if self.right_node:
            self.right_node.traverse_tree()

    def search(self, user_name):
        if user_name == self.user_name:
            for key in sorted(self.card_dates):
                stdout.write(key + " " + self.card_dates[key] + "\n")

                continue

            stdout.write("FIM\n")

            return True

        elif user_name < self.user_name:
            if self.left_node:
                if self.left_node.search(user_name):

                    return True
        else:
            if self.right_node:
                if self.right_node.search(user_name):

                    return True

        return False

    def delete(self):
        self = None

    def __repr__(self):
        cartoes = ""
        for key in sorted(self.card_dates):
            cartoes += key + " " + self.card_dates[key] + " "

        node_info = self.user_name + " " + cartoes

        return node_info.rstrip()


def read_input():
    input = []

    line = stdin.readline().rstrip().split()
    while line[0] != "FIM":
        input.append(line)
        line = stdin.readline().rstrip().split()

    return input


def main():
    lines = read_input()
    flag = True
    root = None

    for line in lines: 
        if flag and line[0] == "ACRESCENTA":
            root = Node(line[1], line[2], line[3])
            stdout.write("NOVO UTILIZADOR CRIADO\n")

            flag = False

        elif line[0] == "ACRESCENTA":
            if root.add(line[1], line[2], line[3]):
                stdout.write("NOVO UTILIZADOR CRIADO\n")

                continue

        elif line[0] == "CONSULTA":
            if not root.search(line[1]):
                stdout.write("NAO ENCONTRADO\n")

                continue

        elif line[0] == "LISTAGEM":
            root.traverse_tree()
            stdout.write("FIM\n")

        elif line[0] == "APAGA":
            root.delete()
            flag = True
            stdout.write("LISTAGEM APAGADA\n")


if __name__ == '__main__':
    main()
