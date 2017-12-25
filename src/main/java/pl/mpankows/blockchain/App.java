package pl.mpankows.blockchain;

import pl.mpankows.blockchain.simulation.Simulation;

import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) {

        final int GENESIS_BLOCK_NUMBER = 1; //number of first block
        final int DIFFICULTY = 3; //required amount of zeros at the beginning of valid hash
        final int MAX_BLOCKS_AMOUNT = 10; //amount of blocks in chain

        //Transaction list - here are four, example strings
        List<String> transactionList = Arrays.asList(
                "First transaction",
                "Second transaction",
                "Third transaction",
                "Fourth transaction");

        //Generate blockchain, starting from creating genesis block
        Simulation.generateBlockchain(
                GENESIS_BLOCK_NUMBER,
                DIFFICULTY,
                transactionList,
                MAX_BLOCKS_AMOUNT);
    }
}
