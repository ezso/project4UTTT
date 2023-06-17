package uttt.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class BoardTests {
    MarkInterface[] emptyB;
    MarkInterface[] simpleCrossB;
    MarkInterface[] simpleCircleB;
    MarkInterface[] simpleTieB;
    MarkInterface[] nullB;
    MarkInterface[] invalidB;
    MarkInterface[] easyWinCross;
    MarkInterface[] easyWinCircle;
    BoardInterface board;

    private boolean compare(MarkInterface[] x, MarkInterface[] y) {
        for (int i = 0; i < x.length; i++) {
            if (x[i].getSymbol() != y[i].getSymbol())
                return false;
        }
        return true;
    }

    @Before
    public void setUp() {
        this.board = UTTTFactory.createBoard();

        this.emptyB = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            this.emptyB[i] = UTTTFactory.createMark(Symbol.EMPTY, i);
        }

        this.simpleCircleB = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            this.simpleCircleB[i] = UTTTFactory.createMark(Symbol.CIRCLE, i);
        }

        this.simpleCrossB = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            this.simpleCrossB[i] = UTTTFactory.createMark(Symbol.CROSS, i);
        }

        this.simpleTieB = new MarkInterface[9];
        this.simpleTieB[0] = UTTTFactory.createMark(Symbol.CIRCLE, 0);
        this.simpleTieB[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        this.simpleTieB[2] = UTTTFactory.createMark(Symbol.CIRCLE, 2);
        this.simpleTieB[3] = UTTTFactory.createMark(Symbol.CROSS, 3);
        this.simpleTieB[4] = UTTTFactory.createMark(Symbol.CIRCLE, 4);
        this.simpleTieB[5] = UTTTFactory.createMark(Symbol.CROSS, 5);
        this.simpleTieB[6] = UTTTFactory.createMark(Symbol.CROSS, 6);
        this.simpleTieB[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        this.simpleTieB[8] = UTTTFactory.createMark(Symbol.CROSS, 8);

        this.invalidB = new MarkInterface[9];
        this.invalidB[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        this.invalidB[4] = UTTTFactory.createMark(Symbol.CIRCLE, 4);
        this.invalidB[8] = UTTTFactory.createMark(Symbol.EMPTY, 8);

        this.easyWinCross = new MarkInterface[9];
        this.easyWinCross[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        this.easyWinCross[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        this.easyWinCross[2] = UTTTFactory.createMark(Symbol.CROSS, 2);
        this.easyWinCross[3] = UTTTFactory.createMark(Symbol.CIRCLE, 3);
        this.easyWinCross[4] = UTTTFactory.createMark(Symbol.CIRCLE, 4);
        this.easyWinCross[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        this.easyWinCross[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        this.easyWinCross[7] = UTTTFactory.createMark(Symbol.EMPTY, 7);
        this.easyWinCross[8] = UTTTFactory.createMark(Symbol.EMPTY, 8);

        this.easyWinCircle = new MarkInterface[9];
        this.easyWinCircle[0] = UTTTFactory.createMark(Symbol.CIRCLE, 0);
        this.easyWinCircle[1] = UTTTFactory.createMark(Symbol.CIRCLE, 1);
        this.easyWinCircle[2] = UTTTFactory.createMark(Symbol.CIRCLE, 2);
        this.easyWinCircle[3] = UTTTFactory.createMark(Symbol.CROSS, 3);
        this.easyWinCircle[4] = UTTTFactory.createMark(Symbol.CROSS, 4);
        this.easyWinCircle[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        this.easyWinCircle[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        this.easyWinCircle[7] = UTTTFactory.createMark(Symbol.EMPTY, 7);
        this.easyWinCircle[8] = UTTTFactory.createMark(Symbol.EMPTY, 8);
    }

    @Test
    public void setMarksExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.setMarks(this.nullB);
        });
    }

    @Test
    public void setMarksExceptionTest2() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.setMarks(null);
        });
    }

    @Test
    public void setMarksExceptionTest3() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.setMarks(this.invalidB);
        });
    }

    @Test
    public void getMarksNotNullTest1() {
        this.board.setMarks(this.emptyB);
        assertNotNull(this.board.getMarks());
    }

    @Test
    public void getMarksNotNullTest2() {
        this.board.setMarks(this.simpleCircleB);
        assertNotNull(this.board.getMarks());
    }

    @Test
    public void getMarksNotNullTest3() {
        this.board.setMarks(this.simpleCrossB);
        assertNotNull(this.board.getMarks());
    }

    @Test
    public void setGetMarksTest1() {
        this.board.setMarks(this.emptyB);
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res.length == this.emptyB.length && compare(res, this.emptyB));
    }

    @Test
    public void setGetMarksTest2() {
        this.board.setMarks(this.simpleCrossB);
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res.length == this.simpleCrossB.length && compare(res, this.simpleCrossB));
    }

    @Test
    public void setGetMarksTest3() {
        this.board.setMarks(this.simpleCircleB);
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res.length == this.simpleCircleB.length && compare(res, this.simpleCircleB));
    }

    @Test
    public void setGetMarksTest4() {
        this.board.setMarks(this.simpleTieB);
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res.length == this.simpleTieB.length && compare(res, this.simpleTieB));
    }

    @Test
    public void setMarkAtExceptionTest1() {
        this.board.setMarks(this.emptyB);
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.setMarkAt(null, 0);
        });
    }

    @Test
    public void setMarkAtExceptionTest2() {
        this.board.setMarks(this.emptyB);
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.setMarkAt(Symbol.CROSS, -1);
        });
    }

    @Test
    public void setMarkAtExceptionTest3() {
        this.board.setMarks(this.emptyB);
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.setMarkAt(Symbol.CROSS, 9);
        });
    }

    @Test
    public void setMarkAtTest1() {
        this.board.setMarks(this.emptyB);
        assertTrue(this.board.setMarkAt(Symbol.CROSS, 0));
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res[0].getSymbol() == Symbol.CROSS);
    }

    @Test
    public void setMarkAtTest2() {
        this.board.setMarks(this.emptyB);
        assertTrue(this.board.setMarkAt(Symbol.CIRCLE, 0));
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res[0].getSymbol() == Symbol.CIRCLE);
    }

    @Test
    public void setMarkAtTest3() {
        this.board.setMarks(this.simpleCrossB);
        assertFalse(this.board.setMarkAt(Symbol.CIRCLE, 0));
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res[0].getSymbol() == Symbol.CROSS);
    }

    @Test
    public void setMarkAtTest4() {
        this.board.setMarks(this.simpleCircleB);
        assertFalse(this.board.setMarkAt(Symbol.CROSS, 0));
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res[0].getSymbol() == Symbol.CIRCLE);
    }

    @Test
    public void setMarkAtTest5() {
        this.board.setMarks(this.simpleCircleB);
        assertFalse(this.board.setMarkAt(Symbol.EMPTY, 0));
        MarkInterface[] res = this.board.getMarks();
        assertTrue(res[0].getSymbol() == Symbol.CIRCLE);
    }

    @Test
    public void setMarkAtTest6() {
        this.board.setMarks(this.simpleCircleB);
        assertFalse(this.board.setMarkAt(Symbol.CIRCLE, 0));
    }

    @Test
    public void setMarkAtTest7() {
        this.board.setMarks(this.simpleCrossB);
        assertFalse(this.board.setMarkAt(Symbol.CROSS, 0));
    }

    @Test
    public void setMarkAtTest8() {
        assertTrue(this.board.setMarkAt(Symbol.EMPTY, 0));
    }

    @Test
    public void isClosedTest1() {
        this.board.setMarks(this.emptyB);
        assertFalse(this.board.isClosed());
    }

    @Test
    public void isClosedTest2() {
        this.board.setMarks(this.simpleTieB);
        assertTrue(this.board.isClosed());
    }

    @Test
    public void isClosedTest3() {
        this.board.setMarks(this.easyWinCross);
        assertTrue(this.board.isClosed());
    }

    @Test
    public void isClosedTest4() {
        this.board.setMarks(this.emptyB);
        this.board.setMarkAt(Symbol.CROSS, 0);
        this.board.setMarkAt(Symbol.CIRCLE, 1);
        this.board.setMarkAt(Symbol.CROSS, 2);

        assertFalse(this.board.isClosed());
    }

    @Test
    public void isClosedTest5() {
        this.board.setMarks(this.easyWinCircle);
        assertTrue(this.board.isClosed());
    }

    @Test
    public void isMovePossibleExceptionTest1() {
        this.board.setMarks(this.emptyB);
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.isMovePossible(-1);
        });
    }

    @Test
    public void isMovePossibleExceptionTest2() {
        this.board.setMarks(this.emptyB);
        assertThrows(IllegalArgumentException.class, () -> {
            this.board.isMovePossible(9);
        });
    }

    @Test
    public void isMovePossibleTest1() {
        this.board.setMarks(this.emptyB);
        assertTrue(this.board.isMovePossible(0));
    }

    @Test
    public void isMovePossibleTest2() {
        this.board.setMarks(this.emptyB);
        this.board.setMarkAt(Symbol.CROSS, 4);
        assertFalse(this.board.isMovePossible(4));
    }

    @Test
    public void getWinnerTest1() {
        this.board.setMarks(this.emptyB);
        assertEquals(Symbol.EMPTY, this.board.getWinner());
    }

    @Test
    public void getWinnerTest0() {
        this.board.setMarks(this.emptyB);
        this.board.setMarkAt(Symbol.CROSS, 0);
        this.board.setMarkAt(Symbol.CIRCLE, 1);
        this.board.setMarkAt(Symbol.CROSS, 2);

        assertEquals(Symbol.EMPTY, this.board.getWinner());
    }

    @Test
    public void getWinnerTest2() {
        this.board.setMarks(this.easyWinCross);
        assertEquals(Symbol.CROSS, this.board.getWinner());
    }

    @Test
    public void getWinnerTest3() {
        this.board.setMarks(this.easyWinCircle);
        assertEquals(Symbol.CIRCLE, this.board.getWinner());
    }

    @Test
    public void getWinnerTest4() {
        this.board.setMarks(this.simpleTieB);
        assertEquals(Symbol.EMPTY, this.board.getWinner());
    }

    @Test
    public void getWinnerTest5() {
        this.board.setMarks(this.emptyB);
        this.board.setMarkAt(Symbol.CROSS, 0);
        this.board.setMarkAt(Symbol.CIRCLE, 1);
        this.board.setMarkAt(Symbol.CIRCLE, 2);
        this.board.setMarkAt(Symbol.CIRCLE, 3);
        this.board.setMarkAt(Symbol.CROSS, 4);
        this.board.setMarkAt(Symbol.EMPTY, 5);
        this.board.setMarkAt(Symbol.EMPTY, 6);
        this.board.setMarkAt(Symbol.EMPTY, 7);
        this.board.setMarkAt(Symbol.CROSS, 8);
        assertEquals(Symbol.CROSS, this.board.getWinner());
    }

    @Test
    public void getWinnerTest6() {
        this.board.setMarks(this.emptyB);
        this.board.setMarkAt(Symbol.CIRCLE, 0);
        this.board.setMarkAt(Symbol.CROSS, 1);
        this.board.setMarkAt(Symbol.CROSS, 2);
        this.board.setMarkAt(Symbol.CROSS, 3);
        this.board.setMarkAt(Symbol.CIRCLE, 4);
        this.board.setMarkAt(Symbol.EMPTY, 5);
        this.board.setMarkAt(Symbol.EMPTY, 6);
        this.board.setMarkAt(Symbol.EMPTY, 7);
        this.board.setMarkAt(Symbol.CIRCLE, 8);
        assertEquals(Symbol.CIRCLE, this.board.getWinner());
    }

    @Test
    public void getWinnerTest7() {
        this.board.setMarks(this.emptyB);
        this.board.setMarkAt(Symbol.CIRCLE, 0);
        this.board.setMarkAt(Symbol.CROSS, 1);
        this.board.setMarkAt(Symbol.CIRCLE, 2);
        this.board.setMarkAt(Symbol.CIRCLE, 3);
        this.board.setMarkAt(Symbol.CROSS, 4);
        this.board.setMarkAt(Symbol.EMPTY, 5);
        this.board.setMarkAt(Symbol.EMPTY, 6);
        this.board.setMarkAt(Symbol.CROSS, 7);
        this.board.setMarkAt(Symbol.EMPTY, 8);
        assertEquals(Symbol.CROSS, this.board.getWinner());
    }

    @Test
    public void getWinnerTest8() {
        this.board.setMarks(this.emptyB);
        this.board.setMarkAt(Symbol.CROSS, 0);
        this.board.setMarkAt(Symbol.CIRCLE, 1);
        this.board.setMarkAt(Symbol.CROSS, 2);
        this.board.setMarkAt(Symbol.CROSS, 3);
        this.board.setMarkAt(Symbol.CIRCLE, 4);
        this.board.setMarkAt(Symbol.EMPTY, 5);
        this.board.setMarkAt(Symbol.EMPTY, 6);
        this.board.setMarkAt(Symbol.CIRCLE, 7);
        this.board.setMarkAt(Symbol.EMPTY, 8);
        assertEquals(Symbol.CIRCLE, this.board.getWinner());
    }
}
