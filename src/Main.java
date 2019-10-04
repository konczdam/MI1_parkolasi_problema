public class Main {
	
	
	public static void main(String[] args) {
		Game game = new Game(System.in);
//		
//		for(Vehicle v: game.getVehiclesToPlace())
//			System.out.println(v.getLength() +" " + v.getWidth());
		game.solve();
		game.printresults();
	}
}
