import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.Calculator;

import java.util.stream.Stream;


public class CalculatorTests {
    Calculator sut;

    @BeforeEach
    public void beforeEach() {
        //System.out.println("beforeEach");
        sut = new Calculator();
    }

    @AfterEach
    public void afterEach() {
        //System.out.println("afterEach");
        sut = null;
    }

    @Test
    public void testAdd() {
        //given
        int a = 8, b = 2, expected = 10;
        //when
        var result = sut.add(a, b);
        //then
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @MethodSource ("addSource")
    public void testMultiplyWithParameters(int a, int b, int expected) {
        //System.out.println("WithParams");
        //when
        var result = sut.multiply(a, b);
        //then
        Assertions.assertEquals(result, expected);
    }

    public static Stream<Arguments> addSource() {
        //given
        return Stream.of(
                Arguments.of(1,2,2),
                Arguments.of(2,2,4),
                Arguments.of(3,2,6)
        );
    }

    @Test
    public void testDivide() {
        //given
        int a = 3, b = 0;
        Class<RuntimeException> expectedType = RuntimeException.class;
        //when
        Executable executable = () -> sut.divide(a, b);
        //then
        Assertions.assertThrows(expectedType, executable);
    }

}
