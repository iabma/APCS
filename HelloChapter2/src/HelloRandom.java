import java.util.Random;

public class HelloRandom {

	public static void main(String[] args) throws InterruptedException {
		Random gen = new Random();
		
		//Generates 10 integers
		for(int i=0; i<10; i++) {
			System.out.println(50+gen.nextInt(50));
			Thread.sleep(75); //Delay added to make a "waterfall effect"
		}
	}
}
