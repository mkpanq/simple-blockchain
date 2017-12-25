package pl.mpankows.blockchain;

import pl.mpankows.blockchain.simulation.Simulation;

import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) {
        final int INITIAL_BLOCK_NUMBER = 1;
        final int DIFFICULTY = 3;
        final int MAX_BLOCKS_AMOUNT = 10;

        List<String> transactionList = Arrays.asList(
                "First transaction",
                "Second transaction",
                "Third transaction",
                "Fourth transaction");

        Simulation.generateBlockchain(
                INITIAL_BLOCK_NUMBER,
                DIFFICULTY,
                transactionList,
                MAX_BLOCKS_AMOUNT);
    }
}
