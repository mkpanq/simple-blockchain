package pl.mpankows.blockchain.block;

import lombok.Getter;
import pl.mpankows.blockchain.merkleroot.MerkleTree;
import pl.mpankows.blockchain.sha256hash.ShaHash;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class Block {

    int blockNumber;
    Timestamp timestamp;
    String prevHashString;
    String validHashString;
    int difficulty;
    int nonce = 0;
    List<String> transactionsList;

    public Block(int blockNumber, String prevHashString, int difficulty, List<String> transactionsList) {
        this.blockNumber = blockNumber;
        this.prevHashString = prevHashString;
        this.difficulty = difficulty;
        this.transactionsList = transactionsList;
        timestamp = new Timestamp(System.currentTimeMillis());

        ShaHash blockNumberHash = new ShaHash(String.valueOf(blockNumber));
        ShaHash difficultyHash = new ShaHash(String.valueOf(difficulty));
        ShaHash timestampHash = new ShaHash(String.valueOf(timestamp));

        MerkleTree merkleTree = new MerkleTree(transactionsList);
        MerkleTree.Node rootNode = merkleTree.getRoot();
        ShaHash merkleRootHash = new ShaHash(String.valueOf(rootNode.getSig()));

        String baseHashString = blockNumberHash.getHashedString()
                + difficultyHash.getHashedString()
                + timestampHash.getHashedString()
                + merkleRootHash.getHashedString();

        miningHash(this.nonce, baseHashString, difficulty);
    }

    private void miningHash(int nonce, String baseHash, int difficulty) {
        ShaHash possibleHash;
        ShaHash validHash;

        while (true) {
            possibleHash = new ShaHash(baseHash + String.valueOf(nonce));
            if (validadeHash(possibleHash, difficulty) == true) {
                this.nonce = nonce;
                validHash = possibleHash;
                this.validHashString = validHash.getHashedString();
                break;
            } else
                nonce++;
        }
    }

    private boolean validadeHash(ShaHash possibleHash, int difficulty) {

        String possibleHashString = possibleHash.getHashedString();

        for (int i = 0; i <= difficulty; i++) {
            if (possibleHashString.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }

}
