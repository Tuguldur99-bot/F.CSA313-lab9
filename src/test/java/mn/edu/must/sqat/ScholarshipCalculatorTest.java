package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // Part A: happy-path tests only

    @Test
    void letterGrade_95_isA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void gpa_95and85_is3point5() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.001);
    }

    @Test
    void scholarship_highGpaEnoughCredits_is100() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship_gpa3point5Credits12_is70() {
        assertEquals(70, calc.scholarship(3.5, 12, false));
    }

    @Test
    void scholarship_gpa3point1_is50() {
        assertEquals(50, calc.scholarship(3.1, 10, false));
    }
}
