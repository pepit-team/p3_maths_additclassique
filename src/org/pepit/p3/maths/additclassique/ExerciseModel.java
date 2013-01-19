package org.pepit.p3.maths.additclassique;

abstract public class ExerciseModel {

	public enum Operator {
		PLUS // , MIN, MULT, DIV
	}

	// Current answer
	private String _currentAnswer = "";
	// Current number of right answers
	private int _nbRightAnswer = 0;
	// Maximum number of right answer before exiting the game
	private int _maxNbAnswer = 0;

	// Mathematical operation for the exercise
	protected Operator _operator = null;
	// Number of lines in operation
	protected int _nbNumbers = 0;
	// Size of numbers (number of characters) for answer/numbers
	protected int _nbCols = 0;
	// Maximum number in operation line
	protected int _maxNumber = 0;
	// Array of numbers for the operation
	protected int[] _numbers = null;
	// Right answer for the current operation
	protected int _rightAnswer = 0;

	public ExerciseModel(int nbNumbers, Operator operator, int nbCols,
			int maxNumber, int maxNbAnswer) {
		this._nbNumbers = nbNumbers;
		this._operator = operator;
		this._nbCols = nbCols;
		this._maxNumber = maxNumber;
		this._maxNbAnswer = maxNbAnswer;
		this._numbers = new int[nbNumbers];
	}

	public int[] getNumbers() {
		return (this._numbers);
	}

	public String getOperator() {
		String s = "";
		if (this._operator.equals(Operator.PLUS)) {
			s = "+";
		}
		return (s);
	}

	public String getCurrentAnswer() {
		return (this._currentAnswer);
	}

	public boolean currentAnswerIsEmpty() {
		String s = this._currentAnswer.replaceAll(" ", "");
		if (s.length() == 0) {
			return (true);
		}
		return (false);
	}

	/**
	 * Update the current answer at one position.
	 * 
	 * @param x
	 *            Position in the answer.
	 * @param s
	 *            The string to set at the position.
	 */
	public void updateCurrentAnswer(int x, String s) {
		String answer = this._currentAnswer;
		char[] chars = answer.toCharArray();
		chars[x] = s.charAt(0);
		this._currentAnswer = String.valueOf(chars);
	}

	public int getNbRightAnswer() {
		return (this._nbRightAnswer);
	}

	public void incNbRightAnswer() {
		if (this._nbRightAnswer < this._maxNbAnswer) {
			this._nbRightAnswer++;
		}
	}

	public int getMaxNbAnswer() {
		return (this._maxNbAnswer);
	}

	/**
	 * Check if the current answer is right.
	 * 
	 * @param s
	 *            Answer to check.
	 * @return TRUE if ok.
	 */
	public boolean checkAnswerComplete() {
		Integer ok = this._rightAnswer;
		String currentAnswer = this._currentAnswer.replaceAll(" ", "");
		if (currentAnswer.length() > 0) {
			int intAnswer = Integer.parseInt(currentAnswer);
			if (ok.equals(intAnswer)) {
				return (true);
			}
		}
		return (false);
	}

	/**
	 * Check if the Nth number is right.
	 * 
	 * @param s
	 *            Number to check.
	 * @param pos
	 *            Number position in answer.
	 * @return TRUE if ok.
	 */
	public boolean checkAnswerPart(String s, int pos) {
		Integer ok = this._rightAnswer;
		String f = "%0" + this._nbCols + "d";
		String sok = String.format(f, ok);
		if (pos >= 0 && (pos < sok.length())) {
			if (s.equals(Character.toString(sok.charAt(pos)))) {
				return (true);
			}
		}
		return (false);
	}

	/**
	 * Build a new game: Generate N random numbers for the game. Set the right
	 * answer. Initialize the current answer to empty.
	 */
	public void game() {
		int nbCols = this._nbCols;
		// initialize the current answer
		this._currentAnswer = "";
		for (int i = 0; i < nbCols; i++) {
			this._currentAnswer += " ";
		}
		// random numbers for the exercise
		this.rndNumbers();
	}

	/**
	 * Build the array of random numbers for the operation.
	 */
	abstract protected void rndNumbers();

	/**
	 * Return the optional carry line
	 */
	abstract protected int[] getCarry();
}
