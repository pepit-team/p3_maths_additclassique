package org.pepit.p3.maths.additclassique;

import java.util.Random;

public class ExerciseModelWithoutCarry extends ExerciseModel {

	public ExerciseModelWithoutCarry(int nbNumbers, Operator operator,
			int nbCols, int maxNumber, int maxNbAnswer) {
		super(nbNumbers, operator, nbCols, maxNumber, maxNbAnswer);
	}

	/**
	 * Return the optional carry line
	 */
	protected int[] getCarry() {
		return null;
	}

	/**
	 * Build the array of random numbers for the operation
	 */
	protected void rndNumbers() {
		Operator operator = this._operator;
		Random generator = new Random();
		int maxRnd = 9;
		int max = 0;
		int rnd = 0;
		int sizex = this._nbCols - 1;
		int sizey = this._nbNumbers;
		int[][] matrix = new int[sizex][sizey];
		int[] sumCol = new int[sizex];
		/*
		 * Get random numbers by columns with the constraint: sum in column <= 9
		 * and >= 0
		 */
		for (int x = 0; x < sizex; x++) {
			max = maxRnd;
			for (int y = 0; y < sizey; y++) {
				max = maxRnd - sumCol[x];
				if (max > 0) {
					rnd = generator.nextInt(max) + 1;
				} else {
					rnd = 0;
				}
				matrix[x][y] = rnd;
				if (operator.equals(Operator.PLUS)) {
					sumCol[x] += rnd;
				}
			}
		}
		String s = "";
		int nb = 0;
		int answer = 0;
		/*
		 * Extract the numbers from the matrix (extract the lines)
		 */
		for (int y = 0; y < sizey; y++) {
			s = "";
			for (int x = 0; x < sizex; x++) {
				s += matrix[x][y];
			}
			nb = Integer.parseInt(s);
			if (operator.equals(Operator.PLUS)) {
				answer += nb;
			}
			this._numbers[y] = nb;
		}
		// update the current right answer
		this._rightAnswer = answer;
	}
}
