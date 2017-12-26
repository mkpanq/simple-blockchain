package pl.mpankows.blockchain.block;

import lombok.Getter;
import pl.mpankows.blockchain.merkleroot.MerkleTree;
import pl.mpankows.blockchain.sha256hash.ShaHash;

import java.sql.Timestamp;
import java.util.List;

/**
 * Block class representation. I simplify storing difficulty for each block -
 * there is only simply integer which tells us, how many (at least) should be zeros at
 * the beginning of the hash.
 * Also, I'm not sure about hashing operation - in this example I'm hashing every element of the block
 * (block number, timestamp, difficulty, merkle root hash), putting them together into one string and hash it with
 * nonce
 */
@Getter
public class Block {

    int blockNumber;
    Timestamp timestamp;
    String prevHashString;
    String validHashString;
    int difficulty; //no byte representation - only required amount of zeros at the beginning of the hash
    int nonce = 0;
    List<String> transactionsList;

    /**
     * Before getting into miningHash method, we hash all block elements and create ane big string from them.
     * Then all mining operations are begin made in miningHash method.
     */
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

    /**
     * This method tries to find validate hash for block. Starting from nonce = 0,
     * hashes together baseHashString (all hashed block elements) and nonce.
     * Depending difficulty variable, it decides is this hash is valid or not -
     * how many zeros is at the beginning of the possibleHash. If hash is wrong -
     * nonce is increased by one and all validations operations are performed again.
     * Hash validation is checking at validateHash function.
     */
    private void miningHash(int nonce, String baseHashString, int difficulty) {
        ShaHash possibleHash;
        ShaHash validHash;
        System.out.println("Mining in progress...\n");
        while (true) {
            possibleHash = new ShaHash(baseHashString + String.valueOf(nonce));
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
