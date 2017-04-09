package roy.assign3.pkg.TopQuizQs;
/**
 * @author shilpita_roy
 */
import java.util.*;

public class Question {
	protected String question;
	protected String topic;
	protected String solution;
	protected String type;
	protected int points;
	protected int givenTime; 
	protected String img;
	
	/**
	 * Default Constructor
	 */
	public Question(){
		this.question = "";
		this.topic    = "";
		this.solution = "";
		this.img	  = "";
		this.givenTime= 0;
		this.points   = 0;
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
	public Question(String question,String type, String topic,String solution, String img,int givenTime,int points){
		this.question = question;
		this.type     = type;
		this.topic    = topic;
		this.solution = solution;
		this.img	  = img;
		this.givenTime= givenTime;
		this.points   = points;
	}
	
	/**
	 * Getter method for String Question
	 * @return String question 
	 */
	public String getQuestion(){
		return this.question;
	}
	
	public void setQuestion(String question){
		this.question = question;
	}
	
	/**
	 * Getter method for String Question type
	 * @return String question type
	 */
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.topic = type;
	}
	
	/**
	 * Getter method for String Question Topic
	 * @return String question topic 
	 */
	public String getTopic(){
		return this.topic;
	}
	
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	/**
	 * Getter method for String Question solution
	 * @return String question solution
	 */
	public String getSolution(){
		return this.solution;
	}
	
	public void setSolution(String solution){
		this.solution = solution;
	}
	
	/**
	 * Getter method for String Image file name
	 * @return String Image file name
	 */
	
	public String getImg(){
		return this.img;
	}
	
	public void setImg(String img){
		this.img = img;
	}
	/**
	 * Getter method for int Question time
	 * @return int question time
	 */
	public int getGivenTime(){
		return this.givenTime;
	}
	
	public void setGivenTime(int givenTime){
		this.givenTime = givenTime;
	}
	
	/**
	 * Getter method for int Question points
	 * @return int question points
	 */
	public int getPoints(){
		return this.points;
	}
	
	public void setPoints(int points){
		this.points = points;
	}
	
	/**
	 * Returns a string with description of question object and its member values.
	 * @return String question object description
	 */
	
	public String printQuestion(){
		return getQuestion()+"\n"+getTopic()
		+"\n"+getType()
		+"\n"+getSolution();
		
	}
	
}

