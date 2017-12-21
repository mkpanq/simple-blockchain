package pl.mpankows.blockchain;

import lombok.Getter;

@Getter
public class Block {

    private int blockNumber;
    private String[] transactions;

    public Block(int blockNumber, String[] transactions) {
        this.blockNumber = blockNumber;
        this.transactions = transactions;
    }
}
