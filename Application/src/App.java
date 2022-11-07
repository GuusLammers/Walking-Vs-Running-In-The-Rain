import Model.Model;

public class App {
    public static void main(String[] args) throws Exception {
        for(double d = 1; d <= 7.5; d += 0.25) {
            int walkerSum = 0;
            int runnerSum = 0;
            for(int i = 0; i < 50; i++) {
                Model model = new Model(d);
                model.runSimulation();
                walkerSum += model.getWalkerResults();
                runnerSum += model.getRunnerResults();
            }

            int walkerResult = walkerSum / 50;
            int runnerResult = runnerSum / 50;

            System.out.println(walkerResult + "," + runnerResult + "," + d + ",");
        }
    }
}
