import java.util.Arrays;

public class Application {
    private InputDevice inputDevice;
    private OutputDevice outputDevice;

    public Application(InputDevice inputDevice, OutputDevice outputDevice) {
        this.inputDevice = inputDevice;
        this.outputDevice = outputDevice;
    }

    public void run(int maxScore) {
        outputDevice.writeMessage("The game has started...");
        playGame(maxScore);
    }

    public void playGame(int maxScore) {

        int Player1Score = 0;
        int Player2Score = 0;
        int round = 1;

        while(Player1Score < maxScore && Player2Score < maxScore){
            int Player1Choice = inputDevice.nextInt();
            int Player2Choice = inputDevice.nextInt();
            outputDevice.writeMessage("______Round_"+round+"_______");
            round = round + 1;

            outputDevice.writeMessage("Player 1 chose: " + Player1Choice);
            outputDevice.writeMessage("Player 2 chose: " + Player2Choice);
            outputDevice.writeMessage("Showdown!!");

            if (Player1Choice == Player2Choice) {

                Player1Score += 2;
                Player2Score += 2;

            } else if (Player1Choice > Player2Choice) {

                if (Player1Choice % Player2Choice == 0 && Player2Choice >= 10) {
                    Player2Score += 1;
                }
                else{
                    Player1Score += 1;
                }

            } else{

                if (Player2Choice % Player1Choice == 0 && Player1Choice >= 10) {
                    Player1Score += 1;
                }
                else{
                    Player2Score += 1;
                }

            }
            outputDevice.writeMessage("Player 1 score: " + Player1Score);
            outputDevice.writeMessage("Player 2 score: " + Player2Score);

            if (Player1Score >= maxScore){
                outputDevice.writeMessage("Player1 wins!");
            } else if (Player2Score >= maxScore) {
                outputDevice.writeMessage("Player2 wins!");
            }
        }


    }

    public int[] sortNumbers(int[] numbers) {
        Arrays.sort(numbers);
        return numbers;
    }

    public void randomArraySort() {
        int[] myTenNumbers = new int[10];
        int[] myHundredNumbers = new int[100];

        myTenNumbers = inputDevice.getNumbers(10);
        myHundredNumbers = inputDevice.getNumbers(100);

        outputDevice.writeMessage("Random Arrays chosen: "
                + Arrays.toString(myTenNumbers)
                + " and "
                + Arrays.toString(myHundredNumbers));

        myTenNumbers = sortNumbers(myTenNumbers);
        myHundredNumbers = sortNumbers(myHundredNumbers);

        outputDevice.writeMessage("Random Arrays sorted: "
                + Arrays.toString(myTenNumbers)
                + " and "
                + Arrays.toString(myHundredNumbers));
    }

    public int[] wordSizeHistogram(String sentence) {
        int[] histogram = new int[20];

        String[] words = sentence.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            histogram[words[i].length()]++;
        }
        return histogram;
    }

    public void exampleHistogram(){
        String myString = inputDevice.getLine();
        outputDevice.writeMessage(myString);

        int[] myHistogram = wordSizeHistogram(myString);
        outputDevice.writeMessage("Example histogram: " + Arrays.toString(myHistogram));
    }
}
