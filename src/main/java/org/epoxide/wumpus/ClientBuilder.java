package org.epoxide.wumpus;

public class ClientBuilder {
    private String token;

    private int[] shardList = null;
    private int shardCount = -1;

    /**
     * The discord bot token of the bot
     *
     * @param token The token of the bot
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * The list of shards the bot instance is going to use
     *
     * @param shardList
     */
    public void setShardList(int[] shardList) {
        this.shardList = shardList;
    }

    /**
     * The amount of shards there are.
     *
     * @param shardCount The shard count for the bot to use, this needs be more then the shard list length
     */
    public void setShardCount(int shardCount) {
        this.shardCount = shardCount;
    }

    public Wumpus login() {

        return new Wumpus(token, shardList, shardCount);
    }
}
