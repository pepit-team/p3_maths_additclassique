package org.pepit.p3.maths.additclassique;

import java.util.Random;

public class ExerciseModelWithCarry extends ExerciseModel {

	// Carry for each column
	private int[] _carry = null;

	public ExerciseModelWithCarry(int nbNumbers, Operator operator, int nbCols,
			int maxNumber, int maxNbAnswer) {
		super(nbNumbers, operator, nbCols, maxNumber, maxNbAnswer);
		this._carry = new int[this._nbCols];
	}

	/**
	 * Return the optional carry line
	 */
	protected int[] getCarry() {
		return (this._carry);
	}

	/**
	 * Calculate the carry for each column
	 */
	private void _calcCarry() {
		Operator operator = this._operator;
		int sizex = this._nbCols;
		int sizey = this._nbNumbers;
		int[][] matrix = new int[sizex][sizey];
		int[] sumCol = new int[sizex];
		String f = "%0" + this._nbCols + "d";
		// Build a matrix with the numbers
		int nb = 0;
		for (int y = 0; y < sizey; y++) {
			nb = this._numbers[y];
			String snb = String.format(f, nb);
			for (int x = 0; x < sizex; x++) {
				char c = snb.charAt(x);
				matrix[x][y] = Integer.parseInt(String.valueOf(c));
			}
		}
		// Calculate the sum of each column
		int sum = 0;
		for (int x = 0; x < sizex; x++) {
			sum = 0;
			for (int y = 0; y < sizey; y++) {
				if (operator.equals(Operator.PLUS)) {
					sum += matrix[x][y];
				}
			}
			sumCol[x] = sum;
		}
		// Set the carry
		for (int x = sizex - 1; x > 0; x--) {
			this._carry[x - 1] = (sumCol[x] + this._carry[x]) / 10;
		}
	}

	/**
	 * Build the array of random numbers for the operation.
	 */
	protected void rndNumbers() {
		int nbNumbers = this._nbNumbers;
		Operator operator = this._operator;
		int maxNumber = this._maxNumber;
		Random generator = new Random();
		int answer = 0;
		int rnd = 0;
		for (int i = 0; i < nbNumbers; i++) {
			rnd = generator.nextInt(maxNumber) + 1;
			if (operator.equals(Operator.PLUS)) {
				answer += rnd;
			}
			this._numbers[i] = rnd;
		}
		// update the current right answer
		this._rightAnswer = answer;
		// update the carry line
		this._calcCarry();
	}
}
