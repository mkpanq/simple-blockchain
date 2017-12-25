package pl.mpankows.blockchain.simulation;

import pl.mpankows.blockchain.block.Block;

import java.util.List;

/**
 * Simple "simulation" class with static methods, to generate blockchain.
 * All needed arguments are in Main method as final variables.
 * Right here we generate whole blockchain core with genesis block,
 * in Main function we decide about difficulty, amount of blocks and genesis block number.
 */
public class Simulation {

    static final String genesisPrevHashString = "4e17811011ec217ac84a9e037a82758a7de318342886a88a37ebabf90f52af73";
    //Hash from text - "Genesis block"

    /**
     * Method that creates every block with genesis block using generateGenesisBlock function
     */
    static public void generateBlockchain(
            int blockNumber,
            int difficulty,
            List<String> transactionList,
            int maxBlocksAmount) {

        System.out.println("SIMPLE BLOCKCHAIN:\n" +
                "Difficulty: " + difficulty + "\n" +
                "List of transactions is constant for every block\n");

        Block previousBlock = generateGenesisBlock(blockNumber, difficulty, transactionList);

        for (blockNumber = 2; blockNumber <= maxBlocksAmount; blockNumber++) {
            Block block = new Block(blockNumber,
                    previousBlock.getValidHashString(),
                    difficulty,
                    transactionList);

            previousBlock = block;

            System.out.println(
                    "Generating next Block...\n" +
                            "Block number: " + blockNumber + "\n" +
                            "Timestamp: " + block.getTimestamp() + "\n" +
                            "Previous hash: " + block.getPrevHashString() + "\n" +
                            "Nonce: " + block.getNonce() + "\n" +
                            "Valid block hash: " + block.getValidHashString() + "\n");
        }
    }

    /**
     * Special function that creates genesis block - we are using final string genesisPrevHashString as a previous
     * valid hash to make a block.
     * Genesis block initialize whole chain.
     */
    static public Block generateGenesisBlock(int blockNumber, int difficulty, List<String> transactionList) {

        Block genesisBlock = new Block(
                blockNumber,
                genesisPrevHashString,
                difficulty,
                transactionList);

        System.out.println(
                "Generating Genesis Block...\n" +
                        "Block number: " + blockNumber + "\n" +
                        "Timestamp: " + genesisBlock.getTimestamp() + "\n" +
                        "Previous hash: " + genesisPrevHashString + "\n" +
                        "Nonce: " + genesisBlock.getNonce() + "\n" +
                        "Valid block hash: " + genesisBlock.getValidHashString() + "\n");

        return genesisBlock;
    }


}
