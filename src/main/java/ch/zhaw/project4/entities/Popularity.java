package ch.zhaw.project4.entities;

public enum Popularity {

    HIGH(100),
    MEDIUM(50),
    LOW(20);

    private int levelOfPop;

    Popularity(int level) {
        this.levelOfPop = level;
    }

    public int getLevelOfPop() {
        return levelOfPop;
    }
}
