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

    
}
