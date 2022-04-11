package GameFunctions;

public class Leaderboard {

    private String playerName;
    private Leaderboard parent, leftChild, rightChild;
    private int playerScore;
    
    public Leaderboard (String playerName, Leaderboard parent, Leaderboard leftChild, Leaderboard rightChild, int playerScore) {

        this.playerName = playerName;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.playerScore = playerScore;

    }

    public String getPlayerName () {
        return playerName;
    }

    public int getPlayerScore () {
        return playerScore;
    }

    public Leaderboard searchScore (int searchKey, Leaderboard searchRoot) {

        int currentKey = searchRoot.getPlayerScore();

        if (currentKey == searchKey) {
            return searchRoot;
        }

        if (searchKey < currentKey) {
            return searchScore(searchKey, searchRoot.leftChild);
        }
        else {
            return searchScore(searchKey, searchRoot.rightChild);
        }
    }

    public void insertNode (Leaderboard currentNode, Leaderboard newNode) {

        int currentScore = currentNode.getPlayerScore();

        int newScore = newNode.getPlayerScore();

        if (newScore < currentScore) {
            if (currentNode.rightChild == null) {
                currentNode.rightChild = newNode;
                newNode.parent = currentNode;
            }
            else {
                insertNode(currentNode.rightChild, newNode);
            }
        }
        else {
            if (currentNode.leftChild == null) {
                currentNode.leftChild = newNode;
                newNode.parent = currentNode;
            }
            else {
                insertNode(currentNode.leftChild, newNode);
            }
        }
    }

    public Leaderboard maximum (Leaderboard root) {
        while (root.rightChild != null) {
            root = root.rightChild;
        }
        return root;
    }

    public Leaderboard minimum (Leaderboard root) {
        while (root.leftChild != null) {
            root = root.leftChild;
        }
        return root;
    }

    public Leaderboard predecessor (Leaderboard root) {
        if (root.rightChild != null) {
            return minimum(root);
        }
        Leaderboard y = root.parent;
        while (y != null && root == y.rightChild) {
            root = y;
            y = y.parent;
        }
        return y;
    }

    public Leaderboard revOrderWalk (Leaderboard currentNode) {
        if (currentNode == null) {
            return currentNode;
        }

        revOrderWalk(currentNode.leftChild);
        System.out.println(currentNode.playerName);
        revOrderWalk(currentNode.rightChild);

        return currentNode;
    }

    /*public static void main(String[] args) {
        Leaderboard root = new Leaderboard(null, null, null, null, -5);
        Leaderboard p1 = new Leaderboard ("Dom", null, null, null, 10);
        Leaderboard p2 = new Leaderboard ("Bill", null, null, null, 20);
        Leaderboard p3 = new Leaderboard ("John", null, null, null, 5);
        Leaderboard p4 = new Leaderboard ("Will", null, null, null, 40);
        Leaderboard p5 = new Leaderboard ("Mike", null, null, null, 2);

        root.insertNode(root, p1);
        root.insertNode(root, p2);
        root.insertNode(root, p3);
        root.insertNode(root, p4);
        root.insertNode(root, p5);

        root.revOrderWalk(root);

    }*/
    
}
