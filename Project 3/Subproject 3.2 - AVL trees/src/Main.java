import java.io.*;
import java.util.StringTokenizer;

class Node {
    String user;
    String cardNo;
    int date;
    int height;
    Node left, right;

    Node(int height) {
        this.height = height;
    }

    Node(String user, String cardNo, int date) {
        this.user = user;
        this.cardNo = cardNo;
        this.date = date;
        this.height = 1;
    }
}

class AVLTree {
    Node root;

    int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalanceFactor(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node insertNode(Node node, String user, String cardNo, int date) {
        if (node == null) {
            return (new Node(user, cardNo, date));
        }

        if (node.user.compareTo(user) < 0) {
            node.left = insertNode(node.left, user, cardNo, date);
        } else if (node.user.compareTo(user) > 0) {
            node.right = insertNode(node.right, user, cardNo, date);
        } else {
            if (!node.cardNo.equals(cardNo)) {
                node.cardNo = cardNo;

                return new Node(-1);
            } else {
                node.date = date;

                return new Node(-2);
            }
        }

        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (node.left.user.compareTo(user) < 0) {
                return rightRotate(node);
            } else if (node.left.user.compareTo(user) > 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balanceFactor < -1) {
            if (node.right.user.compareTo(user) > 0) {
                return leftRotate(node);
            } else if (node.right.user.compareTo(user) < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    Node search(Node node, String user) {
        if (node == null) {
            return null;
        }

        if (node.user.compareTo(user) < 0) {
            return search(node.right, user);
        } else if (node.user.compareTo(user) > 0) {
            return search(node.left, user);
        } else {
            return node;
        }
    }

    void printTree(Node node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(node.user + " " + node.cardNo + " " + node.date);
            printTree(node.right);
        }
    }

    public static void main(String[] args) throws IOException {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        AVLTree tree = new AVLTree();

        while (!(line = in.readLine()).equals("FIM")) {
            st = new StringTokenizer(line);
            String op = st.nextToken();

            switch (op) {
                case "ACRESCENTA" -> {
                    String user = st.nextToken();
                    String cardNo = st.nextToken();
                    int date = Integer.parseInt(st.nextToken());
                    Node node = tree.insertNode(tree.root, user, cardNo, date);

                    if (node.height == -1) {
                        System.out.println("NOVO CARTAO INSERIDO");
                    } else if (node.height == -2) {
                        System.out.println("CARTAO ATUALIZADO");
                    } else {
                        tree.root = node;
                        System.out.println("NOVO UTILIZADOR CRIADO");
                    }
                }
                case "CONSULTA" -> {
                    Node result = tree.search(tree.root, st.nextToken());

                    if (result == null) {
                        System.out.println("NAO ENCONTRADO");
                    } else {
                        System.out.println("");
                    }
                }
                case "LISTAGEM" -> {
                    tree.printTree(tree.root);
                    System.out.println("FIM");
                }
                case "APAGA" -> {
                    tree.root = null;
                    System.out.println("LISTAGEM APAGADA");
                }
            }
        }
    }
}
