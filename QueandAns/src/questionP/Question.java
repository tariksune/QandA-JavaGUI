package questionP;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

public class Question extends JPanel implements ActionListener{
	
	boolean used; //We defined in Question class because we are using on nextQuestion() in Quiz.java
	int yourChoice, correctAnswer;
	Quiz quiz;	
	
	JPanel questionPanel = new JPanel(); //Question Panel
	JPanel answerPanel = new JPanel(); //Answer Panel
	
	JRadioButton[] answers;  //User Responses
	ButtonGroup group = new ButtonGroup();
	
	JPanel nextAndBreakPanel = new JPanel(); //Next and Break Panel
	JButton nextQuestionButton = new JButton("Next Question");
	JButton breakButton = new JButton("Break");
	
	public Question(String questionText, String[] options, int answer, Quiz quiz){ //in Quiz.java
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.quiz = quiz;
		correctAnswer = answer;
		setBackground(Color.BLACK);	
		questionPanel.setBackground(new Color(255, 255, 255));
		questionPanel.add(new JLabel(questionText)); //Add Label to Question Panel
		add(questionPanel);
		answers = new JRadioButton[options.length]; // Your Answers for Questions
		
		for(int i = 0; i < options.length; i++){
			
			answers[i] = new JRadioButton(options[i]);
			answers[i].addActionListener(this);
			group.add(answers[i]);
			answerPanel.add(answers[i]);
			
		}
		answerPanel.setBackground(new Color(245, 245, 245));
		
		add(answerPanel);
		nextQuestionButton.setBackground(SystemColor.activeCaption);
		nextQuestionButton.addActionListener(this); 
		breakButton.setBackground(new Color(250, 128, 114));
		breakButton.addActionListener(this);
		nextAndBreakPanel.setBackground(new Color(255, 255, 255));
		nextAndBreakPanel.add(nextQuestionButton);
		nextAndBreakPanel.add(breakButton);
		add(nextAndBreakPanel);
	}
	
	public void actionPerformed(ActionEvent ae){
		
		Object src = ae.getSource();
		
		if(src.equals(nextQuestionButton)){
			
			showAnswerResult(); //Show Correct or Wrong Message
			
			if(yourChoice == correctAnswer){
				
				used = true;
				
			}
			quiz.nextQuestion(); //If your answer is not correct then will pass the other question (randomly)

		}

		if(src.equals(breakButton)){
			
			quiz.showResultwithPoint(); //Corrects and Wrongs Info with Point
		}

		for(int i = 0; i < answers.length; i++){
			
			if(src == answers[i]){
				
				yourChoice = i;
				
			}
		}
	}
	
	public void showAnswerResult(){ //Result Message Window
		
		String text = answers[yourChoice].getText();
		quiz.total++;
		
		if(yourChoice == correctAnswer){

			JOptionPane.showMessageDialog(null,"Your Answer\n\n" + text + "\n\nIt is correct."); //Correct Selection Message
		
		}else{
			
			quiz.wrongs++;
			JOptionPane.showMessageDialog(null,"Your Answer\n\n" + text + "\n\nIt is wrong! Please think again."); //Wrong Selection Message
		
		}
	}
}
