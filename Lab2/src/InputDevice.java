import java.util.Random;


public class InputDevice {
    private Random random;

    public InputDevice() {
        random = new Random();
    }

    public String getType() {
        return "random";
    }

    public int nextInt() {
        return random.nextInt(100) + 1;
    }

    public int[] getNumbers(int N){
        int[] numbers = new int[N];
        for(int i = 0; i < N; i++){
            numbers[i] = nextInt();
        }
        return numbers;
    }

    public String getLine() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
    }
}
