package pl.mpankows.blockchain.merkleroot;

import java.util.ArrayList;

public class MerkleTree {

    private ArrayList<String> leafNodesArray;
    private String merkleRootHash;

    public MerkleTree(ArrayList<String> leafNodesArray) {
        this.leafNodesArray = leafNodesArray;
        this.merkleRootHash = createMerkleRootHash(leafNodesArray);
    }

    private String createMerkleRootHash(ArrayList<String> leafNodesArray) {
        //need to use Merkle Tree implementation in Java
        return null;
    }

}
