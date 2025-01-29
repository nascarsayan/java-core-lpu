package minesweeper;

abstract class Cell {
    protected boolean isMine;
    protected boolean isFlagged;
    protected boolean isRevealed;

    public abstract boolean isMine();
    public abstract boolean isFlagged();
    public abstract boolean isRevealed();
    public abstract void reveal();
    public abstract void flag();
}

abstract class Game {
    protected Cell[][] grid;
    protected int mines;
    protected int flags;
    protected int revealed;
    protected long startTime;

    public Game(int rows, int cols, int mines) {

    }

    public abstract void revealCell(int row, int col);
    public abstract void flagCell(int row, int col);
    public abstract boolean isGameOver();
    public abstract boolean isGameWon();
    public abstract long getElapsedTime();
}
