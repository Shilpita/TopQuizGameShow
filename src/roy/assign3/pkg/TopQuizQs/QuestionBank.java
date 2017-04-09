package roy.assign3.pkg.TopQuizQs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class QuestionBank {
        ArrayList<Question> questionList;
        
        /**
         * Default Constructor
         */
        public QuestionBank(){}
        
        public QuestionBank(String filename){
        	this.questionList = new ArrayList<Question>();
        	loadTopicQuestions(filename);
        }
        /**
         * Constructor takes the text filename from where the questions are read
         * @param topic
         */
         
        protected  void loadTopicQuestions (String filename){
        		FileInputStream fstream = null;
        		BufferedReader br		= null;
        		String strLine;
        		String delimiter = ":";
        		try {
        				fstream  = new FileInputStream(filename+".txt");
        				br 		 = new BufferedReader(new InputStreamReader(fstream));
        				//Read File Line By Line
        				while ((strLine = br.readLine()) != null)   {
        						String[] tokens = strLine.split(delimiter);
        						switch(QuestionType.valueOf(tokens[0].toUpperCase())){
	        						case ONEWORD :
	        							questionList.add(new OneWordQuestion(tokens[1], tokens[0].toUpperCase(), tokens[2], tokens[3],  tokens[4], 
	        									Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6])));
	        							break;
	        						case MULTIPLE :
	        							questionList.add(new MultipleChoiceQuestion(tokens[1], tokens[0].toUpperCase(), tokens[2], tokens[3],  tokens[4], 
	        									Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]));
	        							break;
        						}
        				}
        			} catch (FileNotFoundException e) {
        						e.printStackTrace();
        			} catch (IOException e) {
        						e.printStackTrace();
        			}finally{
        				//Close the input stream
        				if (br != null )
        						try {
        								br.close();
        						} catch (IOException e) {
        								e.printStackTrace();
        						}
        				if(fstream != null)
        						try {
        								fstream.close();
        						} catch (IOException e) {
        								e.printStackTrace();
        						}
        			}
        	}
        
 /**
  * Displays the ArrayList of questions
  * @return ArrayList of object questions
  */
    	public ArrayList<Question> showQuestion() {
    		return questionList;	
    	}    
   
/**
 * This method selects a question of a specific topic from ArrayList of Questions
 * @param topic
 * @return selected Question
 */
    	
    	public Question getQuestion(String topic) {
    		for(Question q : questionList){
    			if(q.getTopic().equalsIgnoreCase(topic.trim())){
    				return q;
    			}
    		}
			return null;
    	}
  
  /**
   * This method removes the asked question from the ArrayList of question to avoid repetition.  	
   * @param askedQuestion
   */
    	
    	public void removeQuestionFromBank(Question askedQuestion) {
    		showQuestion().remove(askedQuestion);
    	}
        	
}
