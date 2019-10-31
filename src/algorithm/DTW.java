package algorithm;

import java.math.BigDecimal;

public class DTW {
//	double[][] trajectory1;
//	double[][] trajectory2;
	double[][] trajectory1 = { { 1, 1 }, { 1, 2 }, { 3, 2 }, { 4, 4 }, { 4, 5 }, { 5, 5 } };
	double[][] trajectory2 = { { 1, 1 }, { 4, 1 }, { 4, 3 }, { 4, 5 }, { 4, 6 }, { 5, 6 } };
	String minDist;

	public DTW() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DTW(double[][] trajectory1, double[][] trajectory2) {
		super();
		this.trajectory1 = trajectory1;
		this.trajectory2 = trajectory2;

	}

	public double[][] dist() {
		double x1;
		double y1;
		double x2, y2;
		double distance = 0;

		int n = trajectory2.length;
		int row_number = n;
		int column_number = n;
		double matrixA[][] = new double[row_number][column_number];
		System.out.println("-------------------Point to Point Distance-----------------------");
//		 System.out.println(trajectory1.length);
		if (trajectory1.length == trajectory2.length) {

			for (int i = 0; i < trajectory1.length; i++) {
				for (int j = 0; j < trajectory2.length; j++) {

					x1 = trajectory1[i][0];
					y1 = trajectory1[i][1];
					x2 = trajectory2[j][0];
					y2 = trajectory2[j][1];
					double temp1 = Math.pow((x2 - x1), 2);
					double temp2 = Math.pow(y2 - y1, 2);
					distance = temp1 + temp2;
					distance = Math.sqrt(distance);
					// System.out.println(distance);
					BigDecimal bigDecimal = new BigDecimal(distance);
					distance = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					matrixA[i][j] = distance;
					System.out.print(matrixA[i][j] + "\t\t");

//				 System.out.println("X1: " + x1 + "\t" + "Y1: " + y1);
//				 System.out.println("X2: " + x2 + "\t" + "Y2: " + y2);
				}
				System.out.println("\n");
			}

		}

		return matrixA;

	}

	public void dtw() {
		int n = trajectory2.length;
		int row_number = n;
		int column_number = n;
		double[][] matrixA = dist();
//		for (int i = 0; i < trajectory1.length; i++) {
//			for (int j = 0; j < trajectory2.length; j++) {
//				System.out.print(matrixA[i][j] + "\t\t\t");
//
//			}
//			System.out.println("\n");
//		}
		System.out.println("------------------------------DTW------------------------------------");
		double dtw[][] = new double[row_number][column_number];

		// System.out.println(dtw[0][0]);

		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[i].length; j++) {
				if (i == 0 && j == 0) {
					dtw[i][j] = matrixA[0][0];
				} else if (i == 0 && j != 0) {
					dtw[i][j] = dtw[i][j - 1] + matrixA[i][j];

				} else if (j == 0 && i != 0) {
					dtw[i][j] = dtw[i - 1][j] + matrixA[i][j];

				} else {
					if (dtw[i - 1][j - 1] < dtw[i - 1][j]) {
						if (dtw[i - 1][j - 1] < dtw[i][j - 1]) {
							dtw[i][j] = dtw[i - 1][j - 1] + matrixA[i][j];

						} else {
							dtw[i][j] = dtw[i][j - 1] + matrixA[i][j];

						}
					} else {
						if (dtw[i - 1][j] < dtw[i][j - 1]) {
							dtw[i][j] = dtw[i - 1][j] + matrixA[i][j];

						} else {
							dtw[i][j] = dtw[i][j - 1] + matrixA[i][j];

						}
					}
				}
				BigDecimal bigDecimal = new BigDecimal(dtw[i][j]);
				dtw[i][j] = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				System.out.print(dtw[i][j] + "\t\t");

			}
			System.out.println("\n");
		}

	}

}