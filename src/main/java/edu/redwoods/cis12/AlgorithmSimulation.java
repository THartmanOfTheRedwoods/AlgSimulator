package edu.redwoods.cis12;

public abstract class AlgorithmSimulation {
    private String title;
    private AlgSimulatorController asc;

    public AlgorithmSimulation(String title, AlgSimulatorController asc) {
        this.title = title;
        this.asc = asc;
    }
    public AlgorithmSimulation(String title) {
        this.title = title;
    }
    public abstract void loadControls();
    public abstract void simulate();

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() { return this.getTitle();}

    public AlgSimulatorController getAsc() {
        return asc;
    }

    public void setAsc(AlgSimulatorController asc) {
        this.asc = asc;
    }
}
