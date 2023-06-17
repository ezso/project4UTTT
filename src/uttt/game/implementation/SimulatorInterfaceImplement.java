package uttt.game.implementation;

import uttt.game.BoardInterface;
import uttt.game.PlayerInterface;
import uttt.game.SimulatorInterface;
import uttt.game.UserInterface;
import uttt.utils.Move;
import uttt.utils.Symbol;

public class SimulatorInterfaceImplement implements SimulatorInterface {
    private BoardInterface[] boards;
    private Symbol currentPlayerSymbol;
    private int indexNextBoard;
    private int[][] winCombinations = {
            { 0, 1, 2 },
            { 3, 4, 5 },
            { 6, 7, 8 },
            { 0, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 },
            { 0, 4, 8 },
            { 2, 4, 6 }
    };

    public SimulatorInterfaceImplement() {
        this.boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            this.boards[i] = new BoardInterfaceImplement();
        }
        this.currentPlayerSymbol = Symbol.CROSS;
        this.indexNextBoard = -1;
    }

    @Override
    public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui)
            throws IllegalArgumentException {
        Move nextMove;
        while (this.isGameOver()) {
            if (playerOne.getSymbol() == this.getCurrentPlayerSymbol()) {
                nextMove = playerOne.getPlayerMove(this, ui);
                if (this.indexNextBoard == nextMove.getBoardIndex() || this.indexNextBoard == -1) {
                    if (this.isMovePossible(nextMove.getBoardIndex(), nextMove.getMarkIndex())) {
                        this.indexNextBoard = nextMove.getMarkIndex();
                        this.setCurrentPlayerSymbol(playerTwo.getSymbol());
                        this.setMarkAt(playerOne.getSymbol(), nextMove.getBoardIndex(), nextMove.getMarkIndex());
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                nextMove = playerTwo.getPlayerMove(this, ui);
                if (this.indexNextBoard == nextMove.getBoardIndex() || this.indexNextBoard == -1) {
                    if (this.isMovePossible(nextMove.getBoardIndex(), nextMove.getMarkIndex())) {
                        this.indexNextBoard = nextMove.getMarkIndex();
                        this.setCurrentPlayerSymbol(playerOne.getSymbol());
                        this.setMarkAt(playerTwo.getSymbol(), nextMove.getBoardIndex(), nextMove.getMarkIndex());
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
    }

    @Override
    public BoardInterface[] getBoards() {
        return this.boards;
    }

    @Override
    public void setBoards(BoardInterface[] boards) throws IllegalArgumentException {
        if (boards == null)
            throw new IllegalArgumentException("setBoards null arg");
        for (int i = 0; i < this.boards.length; i++) {
            if (boards[i] == null)
                throw new IllegalArgumentException("setBoards null item in arg array");
            this.boards[i] = boards[i];
        }
    }

    @Override
    public Symbol getCurrentPlayerSymbol() {
        return this.currentPlayerSymbol;
    }

    @Override
    public void setCurrentPlayerSymbol(Symbol symbol) throws IllegalArgumentException {
        if (symbol == null || symbol == Symbol.EMPTY)
            throw new IllegalArgumentException("setCurrentPlayerSymbol null arg or value is EMPTY");
        this.currentPlayerSymbol = symbol;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int boardIndex, int markIndex) throws IllegalArgumentException {
        if (symbol == null || boardIndex > 8 || boardIndex < 0 || markIndex > 8 || markIndex < 0)
            throw new IllegalArgumentException("wrong arg in setMarkAt");
        if (symbol != this.currentPlayerSymbol)
            throw new IllegalArgumentException("the given symbol does not match the current player's symbol");
        if (!this.boards[boardIndex].isMovePossible(markIndex))
            return false;
        this.boards[boardIndex].getMarks()[markIndex].setSymbol(symbol);
        return true;
    }

    @Override
    public int getIndexNextBoard() {
        return this.indexNextBoard;
    }

    @Override
    public void setIndexNextBoard(int index) throws IllegalArgumentException {
        if (index > 8 || index < -1)
            throw new IllegalArgumentException("setIndexNextBoard index out of bounds");
        this.indexNextBoard = index;
    }

    @Override
    public boolean isGameOver() {
        if (getWinner() != Symbol.EMPTY)
            return true;
        for (int i = 0; i < this.boards.length; i++) {
            if (!this.boards[i].isClosed())
                return false;
        }
        return true;
    }

    @Override
    public boolean isMovePossible(int boardIndex) throws IllegalArgumentException {
        if (boardIndex > 8 || boardIndex < 0)
            throw new IllegalArgumentException("isMovePossible boardIndex out of bounds");
        return !this.boards[boardIndex].isClosed();
    }

    @Override
    public boolean isMovePossible(int boardIndex, int markIndex) throws IllegalArgumentException {
        if (boardIndex > 8 || boardIndex < 0 || markIndex > 8 || markIndex < 0)
            throw new IllegalArgumentException("isMovePossible boardIndex or markIndex out of bounds");
        if (this.boards[boardIndex].isClosed())
            return false;
        else if (this.boards[boardIndex].isMovePossible(markIndex))
            return true;
        else
            return false;
    }

    @Override
    public Symbol getWinner() {
        for (int i = 0; i < this.winCombinations.length; i++) {
            if (this.boards[this.winCombinations[i][0]].getWinner() == Symbol.CIRCLE
                    && this.boards[this.winCombinations[i][1]].getWinner() == Symbol.CIRCLE
                    && this.boards[this.winCombinations[i][2]].getWinner() == Symbol.CIRCLE) {
                return Symbol.CIRCLE;
            }
            if (this.boards[this.winCombinations[i][0]].getWinner() == Symbol.CROSS
                    && this.boards[this.winCombinations[i][1]].getWinner() == Symbol.CROSS
                    && this.boards[this.winCombinations[i][2]].getWinner() == Symbol.CROSS) {
                return Symbol.CROSS;
            }
        }
        return Symbol.EMPTY;
    }

}
