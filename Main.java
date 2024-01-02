package brick_bracker;


import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame obj=new JFrame();
		playGame playg=new  playGame();
	    obj.setBounds(10,10,700,600);
	    obj.setTitle("RoofBreak Ball");
	    obj.setResizable(false);
	    obj.setVisible(true);
	    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    obj.add(playg);
	}

}
