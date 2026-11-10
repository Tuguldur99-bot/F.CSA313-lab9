package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // ---------- Part A: happy path ----------

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

    // ---------- Part B: letterGrade ----------

    @Test
    void letterGrade_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    void letterGrade_above100_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    void letterGrade_85_isB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    void letterGrade_75_isC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    void letterGrade_65_isD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    void letterGrade_30_isF() {
        assertEquals("F", calc.letterGrade(30));
    }

    // ---------- Part B: gpa ----------

    @Test
    void gpa_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpa_emptyArray_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    @Test
    void gpa_singleC_is2() {
        assertEquals(2.0, calc.gpa(new double[]{75}), 0.001);
    }

    @Test
    void gpa_singleD_is1() {
        assertEquals(1.0, calc.gpa(new double[]{65}), 0.001);
    }

    @Test
    void gpa_singleF_is0() {
        assertEquals(0.0, calc.gpa(new double[]{30}), 0.001);
    }

    // ---------- Part B: scholarship ----------

    @Test
    void scholarship_negativeGpa_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false));
    }

    @Test
    void scholarship_gpaAbove4_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false));
    }

    @Test
    void scholarship_negativeCredits_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false));
    }

    @Test
    void scholarship_violation_is0() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }

    @Test
    void scholarship_highGpaButCredits14_is70() {
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    @Test
    void scholarship_gpa3point5Credits11_is50() {
        assertEquals(50, calc.scholarship(3.5, 11, false));
    }

    @Test
    void scholarship_lowGpa_is0() {
        assertEquals(0, calc.scholarship(2.9, 16, false));
    }

    // ---------- Part C: loop boundary tests for the for-loop in gpa() ----------
    // 0 iterations: gpa_null_throws and gpa_emptyArray_throws (above)

    // 1 iteration
    @Test
    void gpa_loopOneIteration_95_is4() {
        assertEquals(4.0, calc.gpa(new double[]{95}), 0.001);
    }

    // 2+ iterations
    @Test
    void gpa_loopManyIterations_fourGrades_is2point5() {
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001);
    }
}
