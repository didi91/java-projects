package HW5;
import HW5.Tank;
import HW5.TerminationFactory;;

public class Simulation {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		int T = Integer.parseInt(args[2]);

		int totalEstimator = 0;
		for (int i = 0; i < 1; i++) {
			TerminationFactory tank = new TerminationFactory();
			Tank [] a = new Tank [500];
			for (int j = 0; j < k; j++) {
				String newString = randomString(N, "");
				Tank t = new Tank(newString);
				if(tank.T1.search(t)== null && tank.T2.search(t)== null) tank.insert(t);
				else j--;
			}
		
		if (tank.T1.root == null)
			System.out.println("empty puta");
		else
			print(tank.T1.root);
		if (tank.T2.root == null)
			System.out.println("ahora es empty puta");
		tank.dismantleTanks();
		if (tank.T2.root == null)
			System.out.println("todavia empty puta...");
		else{
			System.out.println("goooood");
		
			print(tank.T2.root);
		
			if (tank.T1.root == null && tank.T1.size==0)
			System.out.println("tannn buenooo");
			else
				System.out.println("fuckkkkkkkkkkkkkkkkkkkkk");
			for (int j = 0; j < k; j++) {
				String newString = randomString(N, "");
				Tank t = new Tank(newString);
				if(tank.T1.search(t)== null && tank.T2.search(t)== null) tank.insert(t);
				else j--;
			}
			if (tank.T1.root == null)
				System.out.println("empty puta2");
			else
System.out.println("size of t1: " + tank.T1.size);		
			if (tank.T2.root == null)
				System.out.println("ahora es empty puta2");
			tank.dismantleTanks();
			if (tank.T2.root == null)
				System.out.println("todavia empty puta...2");
			else{
				System.out.println("goooood2");
			
				print(tank.T2.root);
				if (tank.T1.root == null)
				System.out.println("tannn buenooo2");
				System.out.println(tank.T2.size);
				System.out.println(tank.T1.size);
	}}}
		
		}
	private static void print(AVLNode root) {
		if(root== null)
			return;
	print(root.left);
	System.out.println(root.t.serialNumber());
	print(root.right);

	}
	/**
	 * private function to randomize numbers and build strings
	 * 
	 * @param n
	 * @param x
	 * @return
	 */
	private static String randomString(int n, String x) {
		int randomNum = (int) (Math.random() * (n + 1));
		char a = (char) (randomNum % 26 + 65);
		randomNum = randomNum / 26;
		if (n == 0)
			return x;
		x = "" + a + x;
		return randomString(randomNum, x);
	}

}
