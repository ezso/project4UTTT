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
import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

public class SimulatorTests {
    BoardInterface[] allEmptyBoards;
    BoardInterface[] allTieBoards;
    BoardInterface[] allWinCrossBoards;
    BoardInterface[] allWinCirleBoards;
    BoardInterface[] circle5Cross4Boards;
    BoardInterface[] circle4Cross4Tie1Boards;
    MarkInterface[] easyWinCross;
    MarkInterface[] easyWinCircle;
    MarkInterface[] emptyB;
    MarkInterface[] simpleTieB;
    SimulatorInterface simulator;

    private boolean compareBoard(MarkInterface[] x, MarkInterface[] y) {
        for (int i = 0; i < x.length; i++) {
            if (x[i].getSymbol() != y[i].getSymbol())
                return false;
        }
        return true;
    }

    private boolean compareBoards(BoardInterface[] x, BoardInterface[] y) {
        for (int i = 0; i < x.length; i++) {
            if (!compareBoard(x[i].getMarks(), y[i].getMarks()))
                return false;
        }
        return true;
    }

    @Before
    public void setUp() {
        this.simulator = UTTTFactory.createSimulator();
        this.emptyB = new MarkInterface[9];
        for (int i = 0; i < 9; i++) {
            this.emptyB[i] = UTTTFactory.createMark(Symbol.EMPTY, i);
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

        this.allEmptyBoards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            this.allEmptyBoards[i] = UTTTFactory.createBoard();
            this.allEmptyBoards[i].setMarks(this.emptyB);
        }

        this.allWinCrossBoards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            this.allWinCrossBoards[i] = UTTTFactory.createBoard();
            this.allWinCrossBoards[i].setMarks(this.easyWinCross);
        }

        this.allWinCirleBoards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            this.allWinCirleBoards[i] = UTTTFactory.createBoard();
            this.allWinCirleBoards[i].setMarks(this.easyWinCircle);
        }

        this.allTieBoards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            this.allTieBoards[i] = UTTTFactory.createBoard();
            this.allTieBoards[i].setMarks(this.simpleTieB);
        }

        this.circle5Cross4Boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            this.circle5Cross4Boards[i] = UTTTFactory.createBoard();
            if (i < 5)
                this.circle5Cross4Boards[i].setMarks(this.easyWinCircle);
            else
                this.circle5Cross4Boards[i].setMarks(this.easyWinCross);
        }

        this.circle4Cross4Tie1Boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            this.circle4Cross4Tie1Boards[i] = UTTTFactory.createBoard();
            if (i < 4)
                this.circle4Cross4Tie1Boards[i].setMarks(this.easyWinCircle);
            else if (i >= 4 && i < 8)
                this.circle4Cross4Tie1Boards[i].setMarks(this.easyWinCross);
            else
                this.circle4Cross4Tie1Boards[i].setMarks(this.simpleTieB);
        }
    }

    @Test
    public void setBoardsExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setBoards(null);
        });
    }

    @Test
    public void setGetBoardsTest1() {
        this.simulator.setBoards(this.allEmptyBoards);
        BoardInterface[] res = this.simulator.getBoards();
        assertTrue(res.length == this.allEmptyBoards.length && compareBoards(res, this.allEmptyBoards));
    }

    @Test
    public void setGetBoardsTest2() {
        this.simulator.setBoards(this.allTieBoards);
        BoardInterface[] res = this.simulator.getBoards();
        assertTrue(res.length == this.allTieBoards.length && compareBoards(res, this.allTieBoards));
    }

    @Test
    public void setGetBoardsTest3() {
        this.simulator.setBoards(this.allWinCirleBoards);
        BoardInterface[] res = this.simulator.getBoards();
        assertTrue(res.length == this.allWinCirleBoards.length && compareBoards(res, this.allWinCirleBoards));
    }

    @Test
    public void setGetBoardsTest4() {
        this.simulator.setBoards(this.allWinCrossBoards);
        BoardInterface[] res = this.simulator.getBoards();
        assertTrue(res.length == this.allWinCrossBoards.length && compareBoards(res, this.allWinCrossBoards));
    }

    @Test
    public void setGetBoardsExceptionTest2() {
        BoardInterface[] notFull = new BoardInterface[9];
        notFull[0] = UTTTFactory.createBoard();
        notFull[3] = UTTTFactory.createBoard();
        notFull[6] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.emptyB);
        notFull[3].setMarks(this.simpleTieB);
        notFull[6].setMarks(this.easyWinCross);
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setBoards(notFull);
        });
    }

    @Test
    public void setCurrentPlayerSymbolNullTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setCurrentPlayerSymbol(null);
        });
    }

    @Test
    public void getCurrentPlayerSymbolTest1() {
        this.simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertEquals(Symbol.CIRCLE, this.simulator.getCurrentPlayerSymbol());
    }

    @Test
    public void getCurrentPlayerSymbolTest2() {
        this.simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertEquals(Symbol.CROSS, this.simulator.getCurrentPlayerSymbol());
    }

    // @Test
    // public void getCurrentPlayerSymbolTest3() {
    // assertThrows(IllegalArgumentException.class, () -> {
    // this.simulator.setCurrentPlayerSymbol(Symbol.EMPTY);
    // });
    // }

    @Test
    public void setMarkAtExceptionTest1() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setMarkAt(null, 0, 0);
        });
    }

    @Test
    public void setMarkAtExceptionTest2() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setMarkAt(Symbol.CROSS, -1, 0);
        });
    }

    @Test
    public void setMarkAtExceptionTest3() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setMarkAt(Symbol.CROSS, 9, 0);
        });
    }

    @Test
    public void setMarkAtExceptionTest4() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setMarkAt(Symbol.CROSS, 1, -1);
        });
    }

    @Test
    public void setMarkAtExceptionTest5() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setMarkAt(Symbol.CROSS, 1, 9);
        });
    }

    @Test
    public void setMarkAtTest1() {
        this.simulator.setBoards(this.allEmptyBoards);
        this.simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(this.simulator.setMarkAt(Symbol.CROSS, 4, 4));
        assertEquals(Symbol.CROSS, this.simulator.getBoards()[4].getMarks()[4].getSymbol());
    }

    @Test
    public void setMarkAtTest2() {
        this.simulator.setBoards(this.allEmptyBoards);
        this.simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertTrue(this.simulator.setMarkAt(Symbol.CIRCLE, 4, 4));
        assertEquals(Symbol.CIRCLE, this.simulator.getBoards()[4].getMarks()[4].getSymbol());
    }

    @Test
    public void setMarkAtTest4() {
        this.simulator.setBoards(this.allTieBoards);
        this.simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertFalse(this.simulator.setMarkAt(Symbol.CROSS, 4, 4));
    }

    @Test
    public void setMarkAtTest5() {
        this.simulator.setBoards(this.allTieBoards);
        this.simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertFalse(this.simulator.setMarkAt(Symbol.CIRCLE, 4, 4));
    }

    @Test
    public void setMarkAtTest6() {
        this.simulator.setBoards(this.allEmptyBoards);
        this.simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertTrue(this.simulator.setMarkAt(Symbol.CIRCLE, 4, 4));
        this.simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertFalse(this.simulator.setMarkAt(Symbol.CROSS, 4, 4));
    }

    @Test
    public void setMarkAtTest7() {
        this.simulator.setBoards(this.allEmptyBoards);
        this.simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertTrue(this.simulator.setMarkAt(Symbol.CROSS, 4, 4));
        this.simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertFalse(this.simulator.setMarkAt(Symbol.CIRCLE, 4, 4));
    }

    @Test
    public void setMarkAtExceptionTest8() {
        this.simulator.setBoards(this.allEmptyBoards);
        if (this.simulator.getCurrentPlayerSymbol() == Symbol.CROSS) {
            assertThrows(IllegalArgumentException.class, () -> {
                this.simulator.setMarkAt(Symbol.CIRCLE, 0, 0);
            });
        } else {
            assertThrows(IllegalArgumentException.class, () -> {
                this.simulator.setMarkAt(Symbol.CROSS, 0, 0);
            });
        }
    }

    @Test
    public void setIndexNextBoardExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setIndexNextBoard(-2);
        });
    }

    @Test
    public void setIndexNextBoardExceptionTest2() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.setIndexNextBoard(9);
        });
    }

    @Test
    public void setGetIndexNextBoardTest1() {
        this.simulator.setBoards(this.allEmptyBoards);
        this.simulator.setIndexNextBoard(1);
        assertEquals(1, this.simulator.getIndexNextBoard());
    }

    @Test
    public void setGetIndexNextBoardTest2() {
        this.simulator.setBoards(this.allEmptyBoards);
        this.simulator.setIndexNextBoard(-1);
        assertEquals(-1, this.simulator.getIndexNextBoard());
    }

    @Test
    public void isGameOverTest1() {
        assertNotNull(this.simulator.isGameOver());
    }

    @Test
    public void isGameOverTest2() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertFalse(this.simulator.isGameOver());
    }

    @Test
    public void isGameOverTest3() {
        this.simulator.setBoards(this.allTieBoards);
        assertTrue(this.simulator.isGameOver());
    }

    @Test
    public void isGameOverTest4() {
        this.simulator.setBoards(this.allWinCirleBoards);
        assertTrue(this.simulator.isGameOver());
    }

    @Test
    public void isGameOverTest5() {
        this.simulator.setBoards(this.allWinCrossBoards);
        assertTrue(this.simulator.isGameOver());
    }

    @Test
    public void isGameOverTest6() {
        this.simulator.setBoards(this.circle4Cross4Tie1Boards);
        assertTrue(this.simulator.isGameOver());
    }

    @Test
    public void isGameOverTest7() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.emptyB);
        notFull[1].setMarks(this.simpleTieB);
        notFull[2].setMarks(this.simpleTieB);
        notFull[3].setMarks(this.simpleTieB);
        notFull[4].setMarks(this.simpleTieB);
        notFull[5].setMarks(this.simpleTieB);
        notFull[6].setMarks(this.easyWinCross);
        notFull[7].setMarks(this.simpleTieB);
        notFull[8].setMarks(this.simpleTieB);
        this.simulator.setBoards(notFull);
        assertFalse(this.simulator.isGameOver());
    }

    @Test
    public void isGameOverTest8() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.simpleTieB);
        notFull[2].setMarks(this.simpleTieB);
        notFull[3].setMarks(this.simpleTieB);
        notFull[4].setMarks(this.simpleTieB);
        notFull[5].setMarks(this.simpleTieB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.simpleTieB);
        notFull[8].setMarks(this.simpleTieB);
        this.simulator.setBoards(notFull);
        assertFalse(this.simulator.isGameOver());
    }

    @Test
    public void isMovePossibleExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.isMovePossible(-1);
        });
    }

    @Test
    public void isMovePossibleExceptionTest2() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.isMovePossible(9);
        });
    }

    @Test
    public void isMovePossibleTest1() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertTrue(this.simulator.isMovePossible(0));
    }

    @Test
    public void isMovePossibleTest2() {
        this.simulator.setBoards(this.allTieBoards);
        assertFalse(this.simulator.isMovePossible(0));
    }

    @Test
    public void isMovePossibleTest3() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.simpleTieB);
        notFull[2].setMarks(this.simpleTieB);
        notFull[3].setMarks(this.simpleTieB);
        notFull[4].setMarks(this.simpleTieB);
        notFull[5].setMarks(this.simpleTieB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.simpleTieB);
        notFull[8].setMarks(this.simpleTieB);
        this.simulator.setBoards(notFull);
        assertTrue(this.simulator.isMovePossible(6));
    }

    @Test
    public void isMovePossibleTest4() {
        this.simulator.setBoards(this.allWinCrossBoards);
        assertFalse(this.simulator.isMovePossible(0));
    }

    @Test
    public void isMovePossible2ExceptionTest1() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.isMovePossible(-1, 0);
        });
    }

    @Test
    public void isMovePossible2ExceptionTest2() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.isMovePossible(9, 0);
        });
    }

    @Test
    public void isMovePossible2ExceptionTest3() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.isMovePossible(0, -1);
        });
    }

    @Test
    public void isMovePossible2ExceptionTest4() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.simulator.isMovePossible(1, 9);
        });
    }

    @Test
    public void isMovePossible2Test1() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertTrue(this.simulator.isMovePossible(0, 0));
    }

    @Test
    public void isMovePossible2Test2() {
        this.simulator.setBoards(this.allTieBoards);
        assertFalse(this.simulator.isMovePossible(0, 0));
    }

    @Test
    public void isMovePossible2Test3() {
        this.simulator.setBoards(this.allWinCrossBoards);
        assertFalse(this.simulator.isMovePossible(0, 8));
    }

    @Test
    public void isMovePossible2Test4() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.simpleTieB);
        notFull[2].setMarks(this.simpleTieB);
        notFull[3].setMarks(this.simpleTieB);
        notFull[4].setMarks(this.simpleTieB);
        notFull[5].setMarks(this.simpleTieB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.simpleTieB);
        notFull[8].setMarks(this.simpleTieB);
        this.simulator.setBoards(notFull);
        assertTrue(this.simulator.isMovePossible(6, 0));
    }

    @Test
    public void isMovePossible2Test5() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.emptyB);
        notFull[2].setMarks(this.emptyB);
        notFull[3].setMarks(this.emptyB);
        notFull[4].setMarks(this.emptyB);
        notFull[5].setMarks(this.emptyB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.emptyB);
        notFull[8].setMarks(this.emptyB);
        this.simulator.setBoards(notFull);
        assertFalse(this.simulator.isMovePossible(0, 8));
    }

    @Test
    public void getWinnerNotNull() {
        assertNotNull(this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest1() {
        this.simulator.setBoards(this.allEmptyBoards);
        assertEquals(Symbol.EMPTY, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest2() {
        this.simulator.setBoards(this.allWinCrossBoards);
        assertEquals(Symbol.CROSS, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest3() {
        this.simulator.setBoards(this.allWinCirleBoards);
        assertEquals(Symbol.CIRCLE, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest4() {
        this.simulator.setBoards(this.allTieBoards);
        assertEquals(Symbol.EMPTY, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest5() {
        this.simulator.setBoards(this.circle4Cross4Tie1Boards);
        assertEquals(Symbol.CIRCLE, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest7() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.easyWinCross);
        notFull[2].setMarks(this.easyWinCross);
        notFull[3].setMarks(this.easyWinCross);
        notFull[4].setMarks(this.easyWinCircle);
        notFull[5].setMarks(this.easyWinCircle);
        notFull[6].setMarks(this.easyWinCircle);
        notFull[7].setMarks(this.easyWinCircle);
        notFull[8].setMarks(this.emptyB);
        this.simulator.setBoards(notFull);
        assertEquals(Symbol.CROSS, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest8() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.easyWinCircle);
        notFull[2].setMarks(this.easyWinCircle);
        notFull[3].setMarks(this.easyWinCircle);
        notFull[4].setMarks(this.easyWinCross);
        notFull[5].setMarks(this.emptyB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.emptyB);
        notFull[8].setMarks(this.easyWinCross);
        this.simulator.setBoards(notFull);
        assertEquals(Symbol.CROSS, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest9() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCircle);
        notFull[1].setMarks(this.easyWinCross);
        notFull[2].setMarks(this.easyWinCross);
        notFull[3].setMarks(this.easyWinCross);
        notFull[4].setMarks(this.easyWinCircle);
        notFull[5].setMarks(this.emptyB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.emptyB);
        notFull[8].setMarks(this.easyWinCircle);
        this.simulator.setBoards(notFull);
        assertEquals(Symbol.CIRCLE, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest10() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCircle);
        notFull[1].setMarks(this.easyWinCross);
        notFull[2].setMarks(this.easyWinCircle);
        notFull[3].setMarks(this.easyWinCircle);
        notFull[4].setMarks(this.easyWinCross);
        notFull[5].setMarks(this.emptyB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.easyWinCross);
        notFull[8].setMarks(this.emptyB);
        this.simulator.setBoards(notFull);
        assertEquals(Symbol.CROSS, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest11() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.easyWinCircle);
        notFull[2].setMarks(this.easyWinCross);
        notFull[3].setMarks(this.easyWinCross);
        notFull[4].setMarks(this.easyWinCircle);
        notFull[5].setMarks(this.emptyB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.easyWinCircle);
        notFull[8].setMarks(this.emptyB);
        this.simulator.setBoards(notFull);
        assertEquals(Symbol.CIRCLE, this.simulator.getWinner());
    }

    @Test
    public void getWinnerTest12() {
        BoardInterface[] notFull = new BoardInterface[9];
        for (int i = 0; i < 9; i++)
            notFull[i] = UTTTFactory.createBoard();
        notFull[0].setMarks(this.easyWinCross);
        notFull[1].setMarks(this.emptyB);
        notFull[2].setMarks(this.emptyB);
        notFull[3].setMarks(this.emptyB);
        notFull[4].setMarks(this.emptyB);
        notFull[5].setMarks(this.emptyB);
        notFull[6].setMarks(this.emptyB);
        notFull[7].setMarks(this.emptyB);
        notFull[8].setMarks(this.emptyB);
        this.simulator.setBoards(notFull);
        assertEquals(Symbol.EMPTY, this.simulator.getWinner());
    }
}
