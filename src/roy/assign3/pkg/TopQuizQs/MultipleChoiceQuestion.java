package roy.assign3.pkg.TopQuizQs;

import java.util.ArrayList;
/**
 * This is the class for the multiple choice questions extends parent class Question
 * @author Shilpita_roy
 *
 */
public class MultipleChoiceQuestion extends Question{
		protected ArrayList<String> choices ;
		
		/**
		 * Default Constructor
		 */
		
		public MultipleChoiceQuestion (){
			super();
			this.choices = new ArrayList<String>();	
		}
		/**
		 * Parameterized Constructor
		 * @param question
		 * @param type
		 * @param topic
		 * @param solution
		 * @param img
		 * @param givenTime
		 * @param points
		 * @param choice
		 */
		public MultipleChoiceQuestion (String question, String type, String topic,String solution, String img,int givenTime,int points ,String choice){
			super( question, type, topic, solution,  img, givenTime, points);
			this.choices = new ArrayList<String>();
			if(choice != null)	{
				//System.out.println(choice);
				String[] tokens = choice.split(" ");
				for(int i = 0 ; i< tokens.length ; i++)
					this.choices.add(tokens[i]);
			}
		}
		
		/**
		 * Returns the ArrayList of String of question choices
		 * @return ArrayList of String Choices
		 */
		
		public ArrayList<String> getChoices(){
			return this.choices;
		}
		
		/**
		 * This method returns a String of the Description of question and the member values.
		 * @return String with the description of question and data members.
		 */
		public String printQuestion(){
			return getQuestion()+"\n"+getTopic()
			+"\n"+getType()
			+"\n"+getSolution() 
			+"\n"+getChoices();
		}
}
