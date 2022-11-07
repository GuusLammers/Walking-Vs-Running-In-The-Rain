package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    final int SIMULATION_WIDTH = 500;
    final int SIMULATION_HEIGHT = 30;

    final int PERSON_WIDTH = 10;
    final int PERSON_HEIGHT = 50;

    final double WALKER_VELOCITY = 1;
    double RUNNER_VELOCITY;

    final double RAIN_VELOCITY = 3;
    final double RAIN_FACTOR = 0.1;

    Person walker;
    Person runner;

    List<RainDroplet> rainDroplets;

    boolean simulationRunning;

    public Model(double velocity) {
        RUNNER_VELOCITY = velocity;
        walker = new Person(PERSON_WIDTH, PERSON_HEIGHT, 0, 0, WALKER_VELOCITY);
        runner = new Person(PERSON_WIDTH, PERSON_HEIGHT, 0, 0, RUNNER_VELOCITY);
        generateInitialRain();
        simulationRunning = true;
    }

    public void runSimulation() {
        while(!isSimulationOver()) {
            movePeople();
            checkIfPeopleMadeItToDestination();
            
            createNewLayerOfRain();
            moveRain();

            removeRainThatHitGround();

            updatePersonRainCollisions();
        }
    }

    public int getWalkerResults() {
        return walker.getNumberOfRainDropsCollidedWith();
    }

    public int getRunnerResults() {
        return runner.getNumberOfRainDropsCollidedWith();
    }

    private void generateInitialRain() {
        rainDroplets = new ArrayList<RainDroplet>();
        for(int i = 0; i < SIMULATION_HEIGHT; i++) {
            createNewLayerOfRain();
            moveRain();
        }
    }

    private void createNewLayerOfRain() {
        int droplets = (int) (RAIN_FACTOR * SIMULATION_WIDTH);
        Random rand = new Random();
        for(int i = 0; i < droplets; i++) {     
            int x = rand.nextInt(SIMULATION_WIDTH);
            RainDroplet rainDroplet = new RainDroplet(1, 1, x, SIMULATION_HEIGHT, RAIN_VELOCITY);
            rainDroplets.add(rainDroplet);
        }
    }

    private void moveRain() {
        for(RainDroplet droplet : rainDroplets) { 
            droplet.move();
        }
    }

    private void movePeople() {
        walker.move();
        runner.move();
    }

    private void updatePersonRainCollisions() {
        for(RainDroplet droplet : rainDroplets) {
            if(walker.collidesWith(droplet)) walker.hitByRainDroplet();
            if(runner.collidesWith(droplet)) runner.hitByRainDroplet();
        }
    }

    private void removeRainThatHitGround() {
        List<RainDroplet> newRainDropletsList = new ArrayList<RainDroplet>();

        for(RainDroplet droplet : rainDroplets) {
            if(droplet.getBottomLeftY() <= 0) continue;

            newRainDropletsList.add(droplet);
        }

        rainDroplets = newRainDropletsList;
    }

    private boolean isSimulationOver() {
        if(walker.atDestination() && runner.atDestination()) return true;

        return false;
    }

    private void checkIfPeopleMadeItToDestination() {
        if(walker.getBottomLeftX() >= SIMULATION_WIDTH - PERSON_WIDTH) walker.madeItToDestination();
        if(runner.getBottomLeftX() >= SIMULATION_WIDTH - PERSON_WIDTH) runner.madeItToDestination();
    }
}
