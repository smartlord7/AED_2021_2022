

class Node:
    def __init__(self, user_name, card, data):
        self.left_node = None
        self.right_node = None
        self.user_name = user_name
        self.card_dates = dict()
        self.card_dates[card] = data

    def get_depth(self):
        left_node = int()

        if self.left_node:
            left_node = self.left_node.get_depth()

        right_node = int()

        if self.right_node:
            right_node = self.right_node.get_depth()

        return 1 + max(left_node, right_node)

    def get_balance(self):
        left_node = int()

        if self.left_node:
            left_node = self.left_node.get_depth()
        right_node = int()

        if self.right_node:
            right_node = self.right_node.get_depth()

        return left_node - right_node

    def rotate_left(self):
        self.user_name, self.card_dates, self.right_node.user_name, self.right_node.card_dates = self.right_node.user_name, self.right_node.card_dates, self.user_name, self.card_dates
        old_left_node = self.left_node
        self.left_node, self.right_node = self.right_node, self.right_node.right_node
        self.left_node.left_node, self.left_node.right_node = old_left_node, self.left_node.left_node

    def rotate_right(self):
        self.user_name, self.card_dates, self.left_node.user_name, self.left_node.card_dates = self.left_node.user_name, self.left_node.card_dates, self.user_name, self.card_dates
        old_right_node = self.right_node
        self.left_node, self.right_node = self.left_node.left_node, self.left_node
        self.right_node.left_node, self.right_node.right_node = self.right_node.right_node, old_right_node

    def rotate_left_right(self):
        self.left_node.rotate_left()
        self.rotate_right()

    def rotate_right_left(self):
        self.right_node.rotate_right()
        self.rotate_left()

    def check_balance(self):
        get_balance = self.get_balance()
        if get_balance > 1:
            if self.left_node and self.left_node.get_balance() > 0:
                self.rotate_right()
            else:
                self.rotate_left_right()
        elif get_balance < -1:
            if self.right_node and self.right_node.get_balance() < 0:
                self.rotate_left()
            else:
                self.rotate_right_left()

    def print_tree(self):
        if self.left_node:
            self.left_node.print_tree()

        print(self.__repr__() + "")

        if self.right_node:
            self.right_node.print_tree()

    def add(self, user_name, card, data):
        if user_name == self.user_name:
            if card not in self.card_dates.keys():
                self.card_dates[card] = data

                print("NOVO CARTAO INSERIDO")
            else:
                self.card_dates[card] = data

                print("CARTAO ATUALIZADO")

            return False

        elif user_name < self.user_name:
            if not self.left_node:
                self.left_node = Node(user_name, card, data)
            else:
                if not self.left_node.add(user_name, card, data):

                    return False
        else:
            if not self.right_node:
                self.right_node = Node(user_name, card, data)
            else:
                if not self.right_node.add(user_name, card, data):
                    
                    return False

        self.check_balance()

        return True

    def find_by_user_name(self, user_name):
        if user_name == self.user_name:
            for key in sorted(self.card_dates):
                print(key + " " + self.card_dates[key] + "")

                continue

            print("FIM")

            return True

        elif user_name < self.user_name:
            if self.left_node:
                if self.left_node.find_by_user_name(user_name):

                    return True
        else:
            if self.right_node:
                if self.right_node.find_by_user_name(user_name):

                    return True

        return False

    def erase(self):
        self = None

    def __repr__(self):
        cards = ""
        for key in sorted(self.card_dates):
            cards += key + " " + self.card_dates[key] + " "

        node_info = self.user_name + " " + cards

        return node_info.rstrip()


def read_input():
    inp = []

    lines = input().rstrip().split()
    while lines[0] != "FIM":
        inp.append(lines)
        lines = input().rstrip().split()

    return inp


def main():
    input_lines = read_input()
    flag = True
    tree_root = None

    for line in input_lines: 
        if flag and line[0] == "ACRESCENTA":
            tree_root = Node(line[1], line[2], line[3])
            print("NOVO UTILIZADOR CRIADO")

            flag = False

        elif line[0] == "ACRESCENTA":
            if tree_root.add(line[1], line[2], line[3]):
                print("NOVO UTILIZADOR CRIADO")

                continue

        elif line[0] == "CONSULTA":
            if not tree_root.find_by_user_name(line[1]):
                print("NAO ENCONTRADO")

                continue

        elif line[0] == "LISTAGEM":
            tree_root.print_tree()
            print("FIM")

        elif line[0] == "APAGA":
            tree_root.erase()
            flag = True
            print("LISTAGEM APAGADA")


if __name__ == '__main__':
    main()
