package hr.pejak.processing;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessTest {

    @Test
    public void sumInput() {
        Process process = new Process();
        int result = process.sumInputSet();
        assertEquals(6, result);
    }

}