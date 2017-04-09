package roy.assign3.pkg.TopQuizUI;
/**
 * The GUI for Top Quiz
 * @author shilpita roy
 */

import roy.assign3.pkg.TopQuizQs.*;

import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class TopQuizGUI {
	//Data members for Swing components
	private JFrame frame;
	private JPanel panelTitle , panelTopic , panelQuestionDisplay , panelTimer , panelEnd ,panelGif ,panelScore,barPanel;;
	private JLabel question ,timeLabel ,timeIcon;	
	private JTextField textField;	
	private JButton nextQueBtn, endQuizBtn;

	private JRadioButton rdbtnTopic1 ,rdbtnTopic2 ,rdbtnTopic3;
	private JRadioButton rdbtnChoice1 ,rdbtnChoice2 ,rdbtnChoice3;
	private ButtonGroup choiceGrp, topicsGrp;
	
	//Data Members for Questions
	private Question currentQuestion ;
	private QuestionBank qb;
	
	//Data member for Timer Inner class instance  
	private CountTimer cntTimer;
	
	//Data Member for Scores 
	private int totalAttempted, totalCorrect, totalPoints;
	private int worldPoints ,sciencePoints ,moviesPoints;
	private JLabel totalAttemptedLabel ,totalCorrectLabel , totalPointsLabel;
	
//	//animation urls
	private final String bestLuck ="http://www.sherv.net/cm/emoticons/yes/giving-thumbs-up-winking-smiley-emoticon.gif";
	private final String nervous ="http://www.sherv.net/cm/emoticons/nervous/nervous.gif";
	private final String timeup ="http://www.sherv.net/cm/emo/sad/unhappy-smiley-emoticon.gif";
//	private final String bestLuck ="thumbsup.gif";
//	private final String nervous ="nervous.gif";
//	private final String timeup ="lost.gif";

	/**
	 * Constructor
	 * Create the application.
	 */
	public TopQuizGUI() {
		initializeQuestionBank();
		initialize();
	}
	
	/**
	 * Initialization of question bank from input text file
	 */
	private void initializeQuestionBank() {
		//currentQuestion = new Question("Which of these is the main way that ocean tides are created?","OneWord", "AllScience","Moon","tide.jpg",1000,10);	
		qb = new QuestionBank("questions_bank");
		//qb = new QuestionBank("./FileArchives/questions_bank");
		currentQuestion = new Question();
		System.out.println(qb.showQuestion().size());
	}

	/***
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame("Quiz Bee");
		frame.setBounds(200, 200, 1000, 500);
		totalAttempted = 0; totalCorrect =0; totalPoints =0;
		worldPoints =0; sciencePoints =0; moviesPoints=0;
		
		initializeTitlePanel();
		initializeTopicPanel();
		initializeQuestionPanel();
		initializeTimerPanel();
		initializeEndPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
	}
	
	/**
	 * Initialize the Title panel to show the welcome note
	 */
	private void initializeTitlePanel(){
		panelTitle = new JPanel();
		panelTitle.setBackground(Color.PINK);
		frame.getContentPane().add(panelTitle, BorderLayout.NORTH);
		JLabel titleLabel = new JLabel("Welcome to Quiz Bee!");
		JLabel welcomeNote = new JLabel("Feeling Lucky? Choose a topic to start Quiz.");
		titleLabel.setFont(new Font("Comic Sans", Font.BOLD, 25));
		welcomeNote.setFont(new Font("Comic Sans", Font.BOLD, 25));
		panelTitle.add(titleLabel);
		panelTitle.add(welcomeNote);
	}
	
	/**
	 * Initialize the Topic panel to show the topics for question
	 * World,Science and movies
	 */
	
	private void initializeTopicPanel(){
		panelTopic = new JPanel();
		panelTopic.setLayout(new BoxLayout(panelTopic, BoxLayout.PAGE_AXIS));
		panelTopic.setBackground(Color.PINK);
		frame.getContentPane().add(panelTopic, BorderLayout.WEST);
		panelTopic.setPreferredSize(new Dimension(150,480));
		JLabel topicLabel = new JLabel(" SELECT TOPIC:");
		topicLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
		panelTopic.add(topicLabel);
		
		rdbtnTopic1 = new JRadioButton(QuestionTopic.WORLD.toString()+" ");
		rdbtnTopic1.setFont(new Font("Comic Sans",Font.ITALIC ,15));
		panelTopic.add(rdbtnTopic1);

		rdbtnTopic2 = new JRadioButton(QuestionTopic.SCIENCE.toString());
		rdbtnTopic2.setFont(new Font("Comic Sans",Font.ITALIC ,15));
		panelTopic.add(rdbtnTopic2);
		
		rdbtnTopic3 =new JRadioButton(QuestionTopic.MOVIES.toString()+" ");
		rdbtnTopic3.setFont(new Font("Comic Sans",Font.ITALIC ,15));
		panelTopic.add(rdbtnTopic3);
		
		topicsGrp = new ButtonGroup();
		topicsGrp.add(rdbtnTopic1);
		topicsGrp.add(rdbtnTopic2);
		topicsGrp.add(rdbtnTopic3);
		
		ButtonHandlerTopic handler = new ButtonHandlerTopic();
		rdbtnTopic1.addActionListener(handler);
		rdbtnTopic2.addActionListener(handler);
		rdbtnTopic3.addActionListener(handler);
	}
	
	/**
	 * Initialize the panel to display questions
	 */
	
	private void initializeQuestionPanel(){
		panelQuestionDisplay = new JPanel();
		panelQuestionDisplay.setPreferredSize(new Dimension(600,480));
		textField = new JTextField(30);
		//textField.setColumns(5);
		
		rdbtnChoice1 =new  JRadioButton() ;
		rdbtnChoice1.setFont(new Font("Comic Sans",Font.ITALIC ,16));
		rdbtnChoice1.setForeground(Color.BLUE);
		
		rdbtnChoice2 =new  JRadioButton() ;
		rdbtnChoice2.setFont(new Font("Comic Sans",Font.ITALIC ,16));
		rdbtnChoice2.setForeground(Color.BLUE);
		
		rdbtnChoice3 =new  JRadioButton() ;
		rdbtnChoice3.setFont(new Font("Comic Sans",Font.ITALIC ,16));
		rdbtnChoice3.setForeground(Color.BLUE);

		choiceGrp = new ButtonGroup();
		choiceGrp.add(rdbtnChoice1);
		choiceGrp.add(rdbtnChoice2);
		choiceGrp.add(rdbtnChoice3);
		
		panelQuestionDisplay.setBackground(Color.WHITE);
		panelQuestionDisplay.add(setDisplayPic("quiz.png",300,400));	
		//panelQuestionDisplay.setPreferredSize(new Dimension(400, 480));
		frame.getContentPane().add(panelQuestionDisplay, BorderLayout.CENTER);
	}
	
	/***
	 * Initialize the timer panel for count down
	 */
	
	private void initializeTimerPanel(){
		panelTimer = new JPanel();
		panelTimer.setLayout(new BoxLayout(panelTimer, BoxLayout.PAGE_AXIS));
		panelTimer.setBackground(Color.PINK);
		panelTimer.setPreferredSize(new Dimension(200,480));
		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Comic Sans",Font.BOLD ,18));
		timeLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelTimer.add(timeLabel);
		cntTimer = new CountTimer();
		
		panelGif = new JPanel();
		panelGif.setLayout(new BoxLayout(panelGif, BoxLayout.PAGE_AXIS));
		panelGif.setBackground(Color.PINK);
		timeIcon  = new JLabel();
		panelGif.add(timeIcon);
		panelTimer.add(panelGif);
		
		frame.getContentPane().add(panelTimer, BorderLayout.EAST);
	}
	
	/**
	 * Initialize Score Panel
	 */
	private void initializePanelScore(){
		   panelScore = new JPanel();
		   panelScore.setLayout(new GridLayout(0,2));
		   panelScore.setBackground(Color.PINK);
		   panelScore.setPreferredSize(new Dimension(300,150));
		   
		    totalAttemptedLabel = new JLabel("Total Attempted :" ); 
			totalCorrectLabel = new JLabel("Total Correct :" );
			totalPointsLabel= new JLabel("Total points :" );
			
			totalAttemptedLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
			totalCorrectLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
			totalPointsLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
			
			totalAttemptedLabel.setForeground(Color.BLACK);
			totalCorrectLabel.setForeground(Color.BLACK);
			totalPointsLabel.setForeground(Color.BLACK);
			
			JLabel resultTotalAttemptedLabel = new JLabel(" "+totalAttempted);
			JLabel resultTotalCorrectLabel = new JLabel(" "+ totalCorrect);
			JLabel resultTotalPointsLabel = new JLabel(" "+totalPoints);
			
			resultTotalAttemptedLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
			resultTotalCorrectLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
			resultTotalPointsLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
			
			resultTotalAttemptedLabel.setForeground(Color.RED);
			resultTotalCorrectLabel.setForeground(Color.RED);
			resultTotalPointsLabel.setForeground(Color.RED);
			
			panelScore.add(totalAttemptedLabel);
			panelScore.add(resultTotalAttemptedLabel);
			panelScore.add(totalCorrectLabel);
			panelScore.add(resultTotalCorrectLabel);
			panelScore.add(totalPointsLabel);
			panelScore.add(resultTotalPointsLabel);
		   
	
	}
	/***
	 * Initialize the End panel to display END QUIZ button.
	 * This initiates new window to display score and bar graph.
	 */
	
	private void initializeEndPanel(){
		panelEnd = new JPanel();
		panelEnd.setLayout(new BoxLayout(panelEnd, BoxLayout.PAGE_AXIS));
		panelEnd.setBackground(Color.PINK);
		panelEnd.setPreferredSize(new Dimension(200,100));
		initializePanelScore();
		panelEnd.add(panelScore);
		endQuizBtn = new JButton("EndQuiz");
		panelEnd.add(endQuizBtn);
		ButtonHandlerEnd endscore = new ButtonHandlerEnd();
		endQuizBtn.addActionListener(endscore);
		frame.getContentPane().add(panelEnd, BorderLayout.SOUTH);
	}
	
	/***
	 * This refreshes the panel to display question after each action
	 * @param displayQuestion
	 */
	
	private void displayQuestionPanel(Question displayQuestion){
		panelQuestionDisplay.removeAll();
		panelQuestionDisplay.setPreferredSize(new Dimension(600,480));
	    panelQuestionDisplay.setLayout(new BoxLayout(panelQuestionDisplay, BoxLayout.PAGE_AXIS));
		panelQuestionDisplay.setBackground(Color.WHITE);
		frame.getContentPane().add(panelQuestionDisplay, BorderLayout.CENTER);
		//System.out.println(displayQuestion.getQuestion());
		question = new JLabel(displayQuestion.getQuestion());
		question.setFont(new Font("Comic Sans",Font.BOLD ,18));	
		question.setForeground(Color.BLUE);
		question.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelQuestionDisplay.add(question);

		if(displayQuestion.getType().equalsIgnoreCase(QuestionType.ONEWORD.toString())){
			textField.setText("");
			textField.setMaximumSize( textField.getPreferredSize() );
			textField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			panelQuestionDisplay.add(textField);
		}else{
			choiceGrp.clearSelection();
			MultipleChoiceQuestion mcq = (MultipleChoiceQuestion)displayQuestion;
			rdbtnChoice1.setText(mcq.getChoices().get(0));
			rdbtnChoice2.setText(mcq.getChoices().get(1));
			rdbtnChoice3.setText(mcq.getChoices().get(2));

			panelQuestionDisplay.add(rdbtnChoice1);
			panelQuestionDisplay.add(rdbtnChoice2);
			panelQuestionDisplay.add(rdbtnChoice3);
		}
//		panelQuestionDisplay.setAlignmentX(JComponent.CENTER_ALIGNMENT-5);
		panelQuestionDisplay.add(setDisplayPic(displayQuestion.getImg(),200,200));	
		
		nextQueBtn = new JButton("NextQuestion");
		nextQueBtn.setBackground(Color.BLUE);
		nextQueBtn.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		ButtonHandlerNext next = new ButtonHandlerNext();
		nextQueBtn.addActionListener(next);
		panelQuestionDisplay.add(nextQueBtn);
		
		panelQuestionDisplay.revalidate();
		panelQuestionDisplay.repaint();
	}
	
	/**
	 * Displays the Timer for each question with a count down to total time to 0.
	 * This also includes the timer animation
	 * @param givenTime
	 * @param urlname
	 */
	private void displayQuestionTimer(int givenTime, String urlname){
		panelTimer.removeAll();
		
		panelTimer.setLayout(new BoxLayout(panelTimer, BoxLayout.PAGE_AXIS));
		panelTimer.setBackground(Color.PINK);
		
		cntTimer.start(currentQuestion.getGivenTime());
		timeLabel.setFont(new Font("Comic Sans",Font.BOLD ,18));
		timeLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		displayGif(urlname);
		panelTimer.add(timeLabel);
		panelTimer.add(panelGif);
		frame.getContentPane().add(panelTimer, BorderLayout.EAST);
		
		panelTimer.revalidate();
		panelTimer.repaint();
	}
	
	/**
	 * This method Displays the score
	 */
	
	private void displayPanelScore(int totalAttempted,int totalCorrect,int totalPoints){
			panelScore.removeAll();
			panelScore.setLayout(new GridLayout(0,2));
			
			totalAttemptedLabel = new JLabel("Total Attempted :" ); 
			totalCorrectLabel = new JLabel("Total Correct :" );
			totalPointsLabel= new JLabel("Total points :" );
			
			totalAttemptedLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
			totalCorrectLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
			totalPointsLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
			
			totalAttemptedLabel.setForeground(Color.BLACK);
			totalCorrectLabel.setForeground(Color.BLACK);
			totalPointsLabel.setForeground(Color.BLACK);
			
			JLabel resultTotalAttemptedLabel = new JLabel(" "+totalAttempted);
			JLabel resultTotalCorrectLabel = new JLabel(" "+ totalCorrect);
			JLabel resultTotalPointsLabel = new JLabel(" "+totalPoints);
			
			resultTotalAttemptedLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
			resultTotalCorrectLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
			resultTotalPointsLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
			
			resultTotalAttemptedLabel.setForeground(Color.RED);
			resultTotalCorrectLabel.setForeground(Color.RED);
			resultTotalPointsLabel.setForeground(Color.RED);
			
			panelScore.add(totalAttemptedLabel);
			panelScore.add(resultTotalAttemptedLabel);
			panelScore.add(totalCorrectLabel);
			panelScore.add(resultTotalCorrectLabel);
			panelScore.add(totalPointsLabel);
			panelScore.add(resultTotalPointsLabel);
			
			panelScore.revalidate();
			panelScore.repaint();
		
	}
	
	/**
	 * This displays the Gif animations as per the timing in timer.
	 * @param urlname
	 */
	private void displayGif(String urlname){
		panelGif.removeAll();
		
		panelGif.setLayout(new BoxLayout(panelGif, BoxLayout.PAGE_AXIS));
		panelGif.setBackground(Color.PINK);
		timeIcon = setAnimation(urlname, 200, 200);
		timeIcon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		panelGif.add(timeIcon);
		panelTimer.add(panelGif);
		
		panelGif.revalidate();
		panelGif.repaint();
	}	
	
	/***
	 * The method processes the answer submitted by the user as well as keeps track of score
	 * 
	 */
	private void processQuestion(){
		//Process the question and update the session statistics panel
		totalAttempted++;
		String answer="";
		//Get answer entered in text field/chosen depending on type of question
		if (currentQuestion.getType().equalsIgnoreCase(QuestionType.MULTIPLE.toString())){
			if (rdbtnChoice1.isSelected()==true) answer = rdbtnChoice1.getActionCommand();
			if (rdbtnChoice2.isSelected()==true) answer = rdbtnChoice2.getActionCommand();
			if (rdbtnChoice3.isSelected()==true) answer = rdbtnChoice3.getActionCommand();
			
			System.out.println("in process question multiple");
			System.out.println(answer);
		}else if (currentQuestion.getType().equalsIgnoreCase(QuestionType.ONEWORD.toString())){
			answer = textField.getText();
			System.out.println("in process question one word");
			System.out.println(answer);
		}
		if (questionIsCorrect(answer)==true){
			totalCorrect++;
			totalPoints+=currentQuestion.getPoints();
			if (currentQuestion.getTopic().equalsIgnoreCase(QuestionTopic.WORLD.toString())) worldPoints++;
			if (currentQuestion.getTopic().equalsIgnoreCase(QuestionTopic.SCIENCE.toString())) sciencePoints++;
			if (currentQuestion.getTopic().equalsIgnoreCase(QuestionTopic.MOVIES.toString())) moviesPoints++;
		}
		System.out.println(totalAttempted +" "+ totalCorrect +" "+totalPoints);
	}
	
	/**
	 * The method compares the submitted answer to the correct answer in the question bank.
	 * @param answer
	 * @return boolean flag isCorrectAnswer
	 */
	private boolean questionIsCorrect(String answer){
		if (currentQuestion.getSolution().equalsIgnoreCase(answer)){
			return true;
		}
		return false;
	}
	
	/**
	 * This method reads the image file from the question bank and resizes image
	 * @param image
	 * @param width
	 * @param height
	 * @return buffered image object
	 */
	public BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	/**
	 * The method uses the resized image and fits into a label for display.
	 * @param filename
	 * @param width
	 * @param height
	 * @exception IOException for the image filename 
	 * @return JLabel with the resized image 
	 */
	private JLabel setDisplayPic(String filename, int width , int height){
		BufferedImage displayPic;
		JLabel labelHint = null ;
		try {
				displayPic = ImageIO.read(new File(filename));
				displayPic = resize(displayPic,width,height);
				labelHint = new JLabel(new ImageIcon(displayPic));
			//	labelHint.setMaximumSize(new Dimension(100, 100)); 
				return labelHint;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return labelHint;
	}
	
	/**
	 * This method reads the url given in parameter and sets it to a label
	 * @param urlname
	 * @param width
	 * @param height
	 * @exception MalformedURLException for the parameter url
	 * @return JLabel with the animated gif
	 */
	
	private JLabel setAnimation(String urlname , int width, int height){
				URL url;
				JLabel label =new JLabel();
				try {
					url = new URL(urlname);
					Icon icon = new ImageIcon(url);
			        label = new JLabel(icon);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      return label;  
	}
	
	/**
	 *  Inner class for topic radio button event handling
	 * @author shilpita roy
	 *
	 */
	   private class ButtonHandlerTopic implements ActionListener {
		      // handle button event
		      public void actionPerformed( ActionEvent event ){
		    	  String topic = event.getActionCommand();
		    	  //	System.out.println(currentQuestion.printQuestion());
					currentQuestion = qb.getQuestion(topic.toUpperCase());
					if(currentQuestion == null){
						  JOptionPane.showMessageDialog(frame,
								    "Completed current Topic.Choose another topic",
								    "Inane warning",
								    JOptionPane.WARNING_MESSAGE);
					  }else{
							displayQuestionPanel(currentQuestion);	
							displayQuestionTimer(currentQuestion.getGivenTime(),bestLuck);
							displayPanelScore(totalAttempted, totalCorrect, totalPoints);
							qb.removeQuestionFromBank(currentQuestion);
					  }
		      }
		   } // end private inner class ButtonHandler

	  
		/**
		 * Inner class for next question button event handling
		 * @author shilpita roy
		 *
		 */
	   private class ButtonHandlerNext implements ActionListener {
		   // handle button event
			@Override
		      public void actionPerformed( ActionEvent event ){
				      String currentTopic = currentQuestion.getTopic().trim();
				      System.out.println(currentQuestion.getTopic());
		    	      cntTimer.stop();
		    	      processQuestion(); 
					  Question newCurrentQuestion = qb.getQuestion(currentTopic.toUpperCase());
					  if(newCurrentQuestion == null){
						  JOptionPane.showMessageDialog(frame,
								    "Complted current Topic.Choose another topic",
								    "Inane warning",
								    JOptionPane.WARNING_MESSAGE);
					  }else{
						  currentQuestion = newCurrentQuestion;
						  displayQuestionPanel(newCurrentQuestion);	
						  displayQuestionTimer(newCurrentQuestion.getGivenTime(),bestLuck);
						  displayPanelScore(totalAttempted, totalCorrect, totalPoints);
						  qb.removeQuestionFromBank(newCurrentQuestion);
					  }
		      } 
	   }
	   
		/**
		 * Inner class for end quiz button event handling
		 * @author shilpita roy
		 *
		 */
	   private class ButtonHandlerEnd implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				EndScoreCard score = new EndScoreCard();
			}
		   
	   }
	   
	   /**
		 * Inner class to display the Scores and Bargraph on end quiz
		 * @author shilpita roy
	    */
	   
	   private class EndScoreCard {
		   private JFrame scoreFrame;
		   private JPanel panelScoreEnd;
		   
		   public EndScoreCard (){
			   	scoreFrame = new JFrame("Your Score For Quiz Bee");
			    scoreFrame.setBounds(200, 200, 500, 500);
			    panelScoreEnd = new JPanel();
			    panelScoreEnd.setBackground(Color.WHITE);
			    //panelScoreEnd.setLayout(new GridLayout(0,2));
			    panelScoreEnd.setLayout(new BoxLayout(panelScoreEnd, BoxLayout.PAGE_AXIS));
			    scoreFrame.getContentPane().add(panelScoreEnd, BorderLayout.NORTH);
			    displayPanelScore(totalAttempted, totalCorrect, totalPoints);
			    panelScore.setBackground(Color.WHITE);
				panelScoreEnd.add(panelScore);
			    
				BarChart chart = new BarChart();
				chart.setBackground(Color.WHITE);
				chart.setPreferredSize(new Dimension(300,400));
				chart.addBar(Color.red, worldPoints );
				chart.addBar(Color.green, sciencePoints);
				chart.addBar(Color.blue, moviesPoints );
				scoreFrame.getContentPane().add(chart, BorderLayout.CENTER);
				
				JPanel panelMark = new JPanel();
				panelMark.setLayout(new BoxLayout(panelMark, BoxLayout.PAGE_AXIS));
				panelMark.setBackground(Color.WHITE);
				JLabel label1 = new JLabel("WORLD");
				JLabel label2 = new JLabel("SCIENCE");
				JLabel label3 =new JLabel("MOVIES");
				
				label1.setFont(new Font("Comic Sans", Font.BOLD, 20)) ;
				label2.setFont(new Font("Comic Sans", Font.BOLD, 20)) ;
				label3.setFont(new Font("Comic Sans", Font.BOLD, 20)) ;
				
				label1.setForeground(Color.RED);
				label2.setForeground(Color.GREEN);
				label3.setForeground(Color.BLUE);
				scoreFrame.getContentPane().add(panelMark, BorderLayout.WEST);
				
				panelMark.add(label1);
				panelMark.add(label2);
				panelMark.add(label3);
				scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				scoreFrame.setVisible(true);
				scoreFrame.pack();
		   }
	   }
	   
	/***
	 * Inner class for Timer
	 * @author shilpita roy
	 **/
	
	private class CountTimer implements ActionListener {

        private static final int ONE_SECOND = 1000;
        private int count = 0;
        private boolean isTimerActive = false;
        private Timer tmr = new Timer(ONE_SECOND, this);
        
        public CountTimer(){
        	count =0;
        	setTimerText(TimeFormat(count));
        }

        public void start(int givenTime) {
            count = givenTime; 
            isTimerActive = true;
            tmr.start();
        }
        public void stop() {
        	tmr.stop();
			
		}

        @Override
		public void actionPerformed(ActionEvent e) {
			if (isTimerActive) {
				int halfTime = 30;
                setTimerText(TimeFormat(count));
                setTimerColor(Color.BLUE.darker());
                count--;
                if(count <= halfTime){
                	setTimerColor(Color.RED);
                	displayGif(nervous);	
                }
                if(count == 0){
                	tmr.stop();
                	processQuestion();
                	displayGif(timeup);
					JOptionPane.showMessageDialog(frame,
							    "Oops!Times up.Don't Worry.Take Next question.",
							    "Inane warning",
							    JOptionPane.WARNING_MESSAGE);	
                }
                	
            }
        }
        
    }
	/**
	 * This method formats the timer text for display Hr:mm:ss
	 * @param count
	 * @return formated timer
	 */
    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count-hours*3600)/60;
        int seconds = count-minutes*60;

        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
    
    /**
     * Sets the text of the timer display
     * @param sTime
     */
    private void setTimerText(String sTime) {
        timeLabel.setText(sTime);
    }
    
    /**
     * This method changes the color of the timer text
     * @param sColor
     */
    private void setTimerColor(Color sColor) {
        timeLabel.setForeground(sColor);
    }
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopQuizGUI window = new TopQuizGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
