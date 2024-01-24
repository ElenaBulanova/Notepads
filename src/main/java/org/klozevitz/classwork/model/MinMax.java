package org.klozevitz.classwork.model;

import java.util.Map;

public class MinMax {
    private Map<String, Integer> max;
    private Map<String, Integer> min;

    public MinMax(Map<String, Integer> max, Map<String, Integer> min) {
        this.max = max;
        this.min = min;
    }

    public Map<String, Integer> getMax() {
        return max;
    }

    public void setMax(Map<String, Integer> max) {
        this.max = max;
    }

    public Map<String, Integer> getMin() {
        return min;
    }

    public void setMin(Map<String, Integer> min) {
        this.min = min;
    }
}
