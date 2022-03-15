import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public class Node {
        private String name;
        private int value;
        private int nChildren;
        private ArrayList<Node> children;

        public Node(String name, int value, int nChildren) {
            this.name = name;
            this.value = value;
            this.nChildren = nChildren;
            this.children = new ArrayList<Node>();
        }

        public boolean addChild(Node other) {
            if (nChildren == 0) {
                return false;
            }

            int l = children.size();

            if (l != 0) {
                if (children.get(l - 1).addChild(other)) {
                    value += other.getValue();
                    return true;
                }
            }

            if (nChildren == l) {
                if (children.get(l - 1).addChild(other)) {
                    value += other.value;

                    return true;
                }

                return false;
            }

            children.add(other);
            value += other.value;

            return true;
        }

        public void printGeneralTree() {
            ArrayList<Node> nodeList = new ArrayList<Node>();
            nodeList.add(this);

            while (!nodeList.isEmpty()) {
                for (int i = 0; i < nodeList.size(); i++) {
                    System.out.print(nodeList.get(i));

                    if (i != nodeList.size() - 1) {
                        System.out.print(" ");
                    }
                }

                System.out.println("");
                ArrayList<Node> children = new ArrayList<Node>();

                for (Node node : nodeList) {
                    children.addAll(node.children);
                }
                nodeList = children;
            }
        }

        @Override
        public String toString() {
            return name + "(" + value + ")";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getnChildren() {
            return nChildren;
        }

        public void setnChildren(int nChildren) {
            this.nChildren = nChildren;
        }

        public ArrayList<Node> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Node> children) {
            this.children = children;
        }
    }

    public Main() throws IOException {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        line = in.readLine();
        st = new StringTokenizer(line);
        Node root = new Node(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        while ((line = in.readLine()) != null && line.length() > 0) {
            st = new StringTokenizer(line);
            root.addChild(new Node(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        root.printGeneralTree();
    }

    public static void main(String[] args) throws IOException {
       new Main();
    }
}
