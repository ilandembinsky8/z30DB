package lib;

public class Question {
	private int questionID;
	private int addQuestionUserID;
	private String question;
	private String correctAnswer;
	private String wrongAnswer1;
	private String wrongAnswer2;
	private String wrongAnswer3;
	private String questionItemID;
	private String authorName;
	private String itemName;
	
	//constructor to insert new question into database
	public Question(int addQuestionUserID, String question, String correctAnswer, String wrongAnswer1,
			String wrongAnswer2, String wrongAnswer3, String questionItemID, String authorName, String itemName) {
		super();
		this.addQuestionUserID = addQuestionUserID;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.wrongAnswer1 = wrongAnswer1;
		this.wrongAnswer2 = wrongAnswer2;
		this.wrongAnswer3 = wrongAnswer3;
		this.questionItemID = questionItemID;
		this.authorName = authorName;
		this.itemName = itemName;
	}

	//constructor to fetch existing question from database
	public Question(int questionID, int addQuestionUserID, String question, String correctAnswer, String wrongAnswer1,
			String wrongAnswer2, String wrongAnswer3, String questionItemID, String authorName, String itemName) {
		super();
		this.questionID = questionID;
		this.addQuestionUserID = addQuestionUserID;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.wrongAnswer1 = wrongAnswer1;
		this.wrongAnswer2 = wrongAnswer2;
		this.wrongAnswer3 = wrongAnswer3;
		this.questionItemID = questionItemID;
		this.authorName = authorName;
		this.itemName = itemName;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getAddQuestionUserID() {
		return addQuestionUserID;
	}

	public void setAddQuestionUserID(int addQuestionUserID) {
		this.addQuestionUserID = addQuestionUserID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getWrongAnswer1() {
		return wrongAnswer1;
	}

	public void setWrongAnswer1(String wrongAnswer1) {
		this.wrongAnswer1 = wrongAnswer1;
	}

	public String getWrongAnswer2() {
		return wrongAnswer2;
	}

	public void setWrongAnswer2(String wrongAnswer2) {
		this.wrongAnswer2 = wrongAnswer2;
	}

	public String getWrongAnswer3() {
		return wrongAnswer3;
	}

	public void setWrongAnswer3(String wrongAnswer3) {
		this.wrongAnswer3 = wrongAnswer3;
	}

	public String getQuestionItemID() {
		return questionItemID;
	}

	public void setQuestionItemID(String questionItemID) {
		this.questionItemID = questionItemID;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void printme() {
		System.out.println("addQuestionUserID = " + this.addQuestionUserID);
		System.out.println("question = " + this.question);
		System.out.println("correctAnswer = " + this.correctAnswer);
		System.out.println("wrongAnswer1 = " + this.wrongAnswer1);
		System.out.println("wrongAnswer2 = " + this.wrongAnswer2);
		System.out.println("wrongAnswer3 = " + this.wrongAnswer3);
		System.out.println("questionItemID = " + this.questionItemID);
		System.out.println("authorName = " + this.authorName);
		System.out.println("itemName = " + this.itemName);
		System.out.println("-----------------------------------");
		} 
	
	
	
	
}
