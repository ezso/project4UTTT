package uttt.game.implementation;

import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class MarkInterfaceImplement implements MarkInterface {
    private int position;
    private Symbol symbol;

    public MarkInterfaceImplement(int position, Symbol symbol) {
        if (position > 8 || position < 0)
            throw new IllegalArgumentException();
        this.position = position;
        this.symbol = symbol;
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setSymbol(Symbol symbol) throws IllegalArgumentException {
        if (symbol == null)
            throw new IllegalArgumentException("setSymbol null ex");
        this.symbol = symbol;
    }

}
