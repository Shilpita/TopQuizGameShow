package roy.assign3.pkg.TopQuizQs;
/**
 * This is the class for the one word questions extends parent class Question
 * @author Shilpita_roy
 *
 */

public class OneWordQuestion extends Question{
	/**
	 * Default Constructor
	 */
       public OneWordQuestion(){
    	   super();
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
        */
       
       public OneWordQuestion (String question,String type, String topic,String solution, String img,int givenTime,int points){
    	   super( question, type, topic, solution,  img, givenTime, points);
       }
}
