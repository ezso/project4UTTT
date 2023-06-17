package uttt.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class MarkTests {
    MarkInterface mark;

    @Test
    public void simpleMarkTest1() {
        this.mark = UTTTFactory.createMark(Symbol.CIRCLE, 0);
        assertNotNull(this.mark);
    }

    @Test
    public void simpleMarkTest2() {
        this.mark = UTTTFactory.createMark(Symbol.CROSS, 0);
        assertNotNull(this.mark);
    }

    @Test
    public void simpleMarkTest3() {
        this.mark = UTTTFactory.createMark(Symbol.EMPTY, 0);
        assertNotNull(this.mark);
    }

    @Test
    public void setSymbolTest1() {
        this.mark = UTTTFactory.createMark(Symbol.EMPTY, 3);
        this.mark.setSymbol(Symbol.CIRCLE);
        assertEquals(Symbol.CIRCLE, this.mark.getSymbol());
    }

    @Test
    public void setSymbolTest2() {
        this.mark = UTTTFactory.createMark(Symbol.EMPTY, 3);
        this.mark.setSymbol(Symbol.CROSS);
        assertEquals(Symbol.CROSS, this.mark.getSymbol());
    }

    @Test
    public void setSymbolTest3() {
        this.mark = UTTTFactory.createMark(Symbol.EMPTY, 3);
        this.mark.setSymbol(Symbol.EMPTY);
        assertEquals(Symbol.EMPTY, this.mark.getSymbol());
    }

    @Test
    public void symbolNullTest() {
        this.mark = UTTTFactory.createMark(Symbol.CIRCLE, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            this.mark.setSymbol(null);
        });
    }

    @Test
    public void getSymbolTest1() {
        this.mark = UTTTFactory.createMark(Symbol.CIRCLE, 0);
        assertEquals(Symbol.CIRCLE, this.mark.getSymbol());
    }

    @Test
    public void getSymbolTest2() {
        this.mark = UTTTFactory.createMark(Symbol.CROSS, 0);
        assertEquals(Symbol.CROSS, this.mark.getSymbol());
    }

    @Test
    public void getSymbolTest3() {
        this.mark = UTTTFactory.createMark(Symbol.EMPTY, 0);
        assertEquals(Symbol.EMPTY, this.mark.getSymbol());
    }

    @Test
    public void getPosTest1() {
        this.mark = UTTTFactory.createMark(Symbol.CROSS, 0);
        assertEquals(0, this.mark.getPosition());
    }

    @Test
    public void getPosTest2() {
        this.mark = UTTTFactory.createMark(Symbol.CROSS, 7);
        assertEquals(7, this.mark.getPosition());
    }

    @Test
    public void getPosTestIllegalArg1() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.mark = UTTTFactory.createMark(Symbol.CROSS, -1);
        });
    }

    @Test
    public void getPosTestIllegalArg2() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.mark = UTTTFactory.createMark(Symbol.CROSS, 9);
        });
    }
}
