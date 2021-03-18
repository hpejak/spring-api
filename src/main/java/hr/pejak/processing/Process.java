package hr.pejak.processing;

import java.util.ArrayList;
import java.util.List;

public class Process {

    private final List<Integer> inputSet = new ArrayList<>();

    public Integer sumInputSet(){
        inputSet.add(6);

        int sumNum = 0;
        for (int i : inputSet) {
            sumNum += i;
        }
        return sumNum;
    }

    public String converterToInt(String input) {
        if (!input.equals("test")) return null;
        return input;
    }

}
