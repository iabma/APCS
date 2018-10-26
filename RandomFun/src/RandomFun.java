import java.util.Random;

public class RandomFun {

	public static void main(String[] args){
		Random randy = new Random();
		int superCoolNumber = randy.nextInt(11);

		System.out.println(superCoolNumber);
	}

}