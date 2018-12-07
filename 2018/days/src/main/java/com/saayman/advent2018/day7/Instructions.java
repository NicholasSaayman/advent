package com.saayman.advent2018.day7;

import java.util.*;

public class Instructions {
    HashMap<String, Step> steps = new HashMap<>();
    List<String> orderSteps = new ArrayList<>();
    List<String> orderedSimulationSteps = new ArrayList<>();
    List<String> doneSimulationSteps = new ArrayList<>();

    private int baseStepTime;

    public void load(List<String> inputs) {
        for (String input : inputs) {
            parseInput(input);
        }
    }

    public int totalStepsBefore(String stepName) {
        Step step = steps.get(stepName);
        return step.stepsBefore.size();
    }

    public int totalStepsAfter(String stepName) {
        Step step = steps.get(stepName);
        return step.stepsAfter.size();
    }

    public int totalSteps() {
        return steps.size();
    }

    private void parseInput(String input) {
        String stepNameBefore = input.substring(input.indexOf("Step ") + 5, input.indexOf(" must"));
        String stepNameAfter = input.substring(input.indexOf("step ") + 5, input.indexOf(" can"));

        Step before = steps.get(stepNameBefore);
        if (before == null) {
            Step toAdd = new Step(stepNameBefore);
            steps.put(stepNameBefore, toAdd);
            before = toAdd;
        }
        before.stepsAfter.add(stepNameAfter);

        Step after = steps.get(stepNameAfter);
        if (after == null) {
            Step toAdd = new Step(stepNameAfter);
            steps.put(stepNameAfter, toAdd);
            after = toAdd;
        }
        after.stepsBefore.add(stepNameBefore);
    }

    public String orderedSteps() {
        if (orderSteps.isEmpty()) {
            orderTheSteps();
        }
        String ordering = "";
        for (String step : orderSteps) {
            ordering = ordering + step;
        }
        return ordering;
    }

    private void orderTheSteps() {
        do {
            List<String> readySteps = nextReadySteps();
            Collections.sort(readySteps);
            orderSteps.add(readySteps.get(0));
        } while (nextReadySteps().size() > 0);
    }

    public List<String> nextReadySteps() {
        List<String> ready = new ArrayList<>();
        for (Step step : steps.values()) {
            if (step.isReady()) {
                ready.add(step.name);
            }
        }
        return ready;
    }

    public String orderedSimulationSteps() {
        if (orderedSimulationSteps.isEmpty()) {
            orderTheSimulationSteps();
        }
        String ordering = "";
        for (String step : orderedSimulationSteps) {
            ordering = ordering + step;
        }
        return ordering;
    }

    private void orderTheSimulationSteps() {
        do {
            List<String> readySteps = nextReadySimulationSteps();
            Collections.sort(readySteps);
            orderedSimulationSteps.addAll(readySteps);
        } while (nextReadySimulationSteps().size() > 0);
    }

    private List<String> nextReadySimulationSteps() {
        List<String> ready = new ArrayList<>();
        for (Step step : steps.values()) {
            if (step.isSimulationReady()) {
                ready.add(step.name);
            }
        }
        return ready;
    }

    public void setStepTimeBase(int baseStepTime) {
        this.baseStepTime = baseStepTime;
    }

    public int getStepTime(String step) {
        return steps.get(step).getTime();
    }

    public int simulateTotalTime(int workers) {
        int clock = 0;
        List<Step> workingOn = new ArrayList<>();
        while (doneSimulationSteps.size() != steps.size()) {
            Iterator<Step> i = workingOn.iterator();
            while (i.hasNext()) {
                Step step = i.next();
                if(clock == step.finishTime) {
                    doneSimulationSteps.add(step.name);
                    step.inProgress=false;
                    i.remove();
                }
            }

            List<Step> available = new ArrayList<>();
            for (Step step : steps.values()) {
                if(step.isNextStep()) {
                    available.add(step);
                }
            }

            for(int x = workingOn.size(); x < workers; x++) {
                Iterator<Step> avail = available.iterator();
                while(avail.hasNext()) {
                    Step step = avail.next();
                    step.inProgress = true;
                    step.finishTime = clock + step.getTime();
                    workingOn.add(step);
                    avail.remove();
                    break;
                }
            }
            System.out.println("clock"+clock);

            System.out.println("workingon"+workingOn);
            System.out.println("done"+doneSimulationSteps);

            clock++;
        }
        return clock-1;
    }

    class Step {
        public String name;
        public int finishTime;
        public boolean inProgress;
        public Set<String> stepsBefore = new HashSet<>();
        public Set<String> stepsAfter = new HashSet<>();

        @Override
        public String toString() {
            return "Step{" +
                    "name='" + name + '\'' +
                    ", finishTime=" + finishTime +
                    ", inProgress=" + inProgress +
                    ", stepsBefore=" + stepsBefore +
                    ", stepsAfter=" + stepsAfter +
                    '}';
        }

        public Step(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Step step = (Step) o;
            return Objects.equals(name, step.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public boolean isReady() {
            if (!orderSteps.contains(name) && (stepsBefore.isEmpty() || orderSteps.containsAll(stepsBefore))) {
                return true;
            }
            return false;
        }

        public boolean isSimulationReady() {
            if (!orderedSimulationSteps.contains(name) && (stepsBefore.isEmpty() || orderedSimulationSteps.containsAll(stepsBefore))) {
                return true;
            }
            return false;
        }

        public boolean isNextStep() {
            if (!doneSimulationSteps.contains(name) && (stepsBefore.isEmpty() || doneSimulationSteps.containsAll(stepsBefore)) && !inProgress) {
                return true;
            }
            return false;
        }


        public int getTime() {
            return baseStepTime + (name.toCharArray()[0] - 'A' + 1);
        }
    }

}

