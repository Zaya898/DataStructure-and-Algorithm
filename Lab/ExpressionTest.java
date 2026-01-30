import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTest {

    @Test
    void testPostfixBuildAndEvaluate() {
        Expression e = Expression.fromPostfix("2 3 4 * +");
        double result = e.evaluate();
        assertEquals(14.0, result);
    }

    @Test
    void testPrefixBuildAndEvaluate() {
        Expression e = Expression.fromPrefix("+ 2 * 3 4");
        double result = e.evaluate();
        assertEquals(14.0, result);
    }

    @Test
    void testInfixBuildAndEvaluate() {
        Expression e = Expression.fromInfix("(2+(3*4))");
        double result = e.evaluate();
        assertEquals(14.0, result);
    }

    @Test
    void testSimpleAdd() {
        Expression e = Expression.fromInfix("(5+6)");
        assertEquals(11.0, e.evaluate());
    }

    @Test
    void testComplexExpression() {
        Expression e = Expression.fromPostfix("5 1 2 + 4 * + 3 -");
        assertEquals(14.0, e.evaluate());
    }
}
