package questionP;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.CardLayout;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.UIManager.*;

public class Quiz extends JFrame{
	
	JPanel panel = new JPanel();
	CardLayout cardLayout = new CardLayout();
	int numberofQuestions;
	static int total = 0, wrongs = 0;
	
	String[][] answers = { //Answer Array
			
		{"public static int main(String args[])","public void main(String args) ","public static void main(arg[]) ","public static void main(String args[])"},
		{"#","~","!","`"},
		{"Reader","File","ObjectInputStream","ObjectReader"},
		{"method(void) {};","void method() {};","void method {};","void method(void) {};"},
		{"True","False"},
		{"True","False"},
		{"True","False"},
		{"Int","Char","Float","Short"},
	};
	
	Question questions[] = { //Question Array with Answers (Question.java)
		
		new Question(
			"Which of the following are legal definitions of the main method that can be used to execute a class?",
			answers[0],
			3,this
		),
		new Question(
			"Which operator is used to perform bitwise inversion in Java?",
			answers[1],
			1,this
		),
		new Question(
			"By which class we can read object from stream?",
			answers[2],
			2,this
		),
		new Question(
			"Which of the following are legal declaration and definition of a method?",
			answers[3],
			1,this
		),
		new Question(
			"Arrays can also be created and initialize as in above statement.",
			answers[4],
			0,this
		),
		new Question(
			"In an instance method or a constructor, \"this\" is a reference to the current object.",
			answers[5],
			0,this
		),
		new Question(
			"Constructor overloading is not possible in Java.",
			answers[6],
			1,this
		),
		new Question(
			"In Java, which of the following is not a numerical type?",
			answers[7],
			1,this
		)
	};

	public static void main(String args[]){ //Nimbus View Code Part
		
		try {
			
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	
		        if ("Nimbus".equals(info.getName())) {
		        	
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		            
		        }
		    }
		} catch (Exception e) {
			
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		new Quiz();
	}
	
	public Quiz(){
		
		super("Questions"); //Title
		setResizable(true);
		setSize(600,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(cardLayout);
		numberofQuestions = questions.length;
		
		for(int i  = 0; i < numberofQuestions; i++){
			
			panel.add(questions[i] , "q" + i);

		}
		
		Random random = new Random();
		int i = random.nextInt(numberofQuestions);
		cardLayout.show(panel , "q" + i);
		add(panel);
		setVisible(true);
		
	}
	
	public void nextQuestion(){ //Random next question and finish part
		
		if((total - wrongs) == numberofQuestions){
			
			showResultwithPoint();
			
		}else{
			
			boolean found = false;
			Random random = new Random();
			int i = 0;
			
			while(!found){
				
				i = random.nextInt(numberofQuestions);
				
				if(!questions[i].used){
					
					found = true;
					
				}
			}
			cardLayout.show(panel , "q" + i);
		}
	}
	
	public static void showResultwithPoint(){
		
		JOptionPane.showMessageDialog(null , "\nCorrects: " + (total-wrongs) + "\nWrongs: " + wrongs + "\nPoint: \t\t" + (((total-wrongs)-wrongs)*10) , "Result" , JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
		
	}
}