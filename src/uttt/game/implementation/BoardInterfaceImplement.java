package uttt.game.implementation;

import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class BoardInterfaceImplement implements BoardInterface {
    private MarkInterface[] marks;
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

    public BoardInterfaceImplement() {
        this.marks = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            this.marks[i] = new MarkInterfaceImplement(i, Symbol.EMPTY);
        }
    }

    @Override
    public MarkInterface[] getMarks() {
        return this.marks;
    }

    @Override
    public void setMarks(MarkInterface[] marks) throws IllegalArgumentException {
        if (marks == null)
            throw new IllegalArgumentException("setMarks null arg");
        for (int i = 0; i < 9; i++) {
            if (marks[i] == null)
                throw new IllegalArgumentException();
            this.marks[i] = marks[i];
        }
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int markIndex) throws IllegalArgumentException {
        if (markIndex > 8 || markIndex < 0)
            throw new IllegalArgumentException("markIndex out of bounds");
        if (symbol == null)
            throw new IllegalArgumentException("null symbol arg");
        if (this.marks[markIndex].getSymbol() == Symbol.EMPTY) {
            this.marks[markIndex] = new MarkInterfaceImplement(markIndex, symbol);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isClosed() {
        if (getWinner() != Symbol.EMPTY)
            return true;
        for (int i = 0; i < this.marks.length; i++) {
            if (this.marks[i].getSymbol() == Symbol.EMPTY)
                return false;
        }
        return true;
    }

    @Override
    public boolean isMovePossible(int markIndex) throws IllegalArgumentException {
        if (markIndex > 8 || markIndex < 0)
            throw new IllegalArgumentException("isMovePossible markIndex out of bounds");
        if (this.marks[markIndex].getSymbol() == Symbol.EMPTY && !isClosed())
            return true;
        else
            return false;
    }

    @Override
    public Symbol getWinner() {
        for (int i = 0; i < this.winCombinations.length; i++) {
            if (this.marks[this.winCombinations[i][0]].getSymbol() == Symbol.CIRCLE
                    && this.marks[this.winCombinations[i][1]].getSymbol() == Symbol.CIRCLE
                    && this.marks[this.winCombinations[i][2]].getSymbol() == Symbol.CIRCLE) {
                return Symbol.CIRCLE;
            }
            if (this.marks[this.winCombinations[i][0]].getSymbol() == Symbol.CROSS
                    && this.marks[this.winCombinations[i][1]].getSymbol() == Symbol.CROSS
                    && this.marks[this.winCombinations[i][2]].getSymbol() == Symbol.CROSS) {
                return Symbol.CROSS;
            }
        }
        return Symbol.EMPTY;
    }

}
