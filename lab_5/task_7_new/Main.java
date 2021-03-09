package lab_5.task_7_new;

import java.util.ArrayList;

interface Function {
	double func(double x, int factor);
}

public class Main {
	
	private static double[][] a = new double[3][7];
	private static double[] errors = new double[3];
	
	private static double elem_func_1(double x, int factor) {
		return Math.log(x + factor);
	}
	
	private static double elem_func_2(double x, int factor) {
		return Math.pow(x, factor);
	}
	
	private static double elem_func_3(double x, int factor) {
		return Math.pow(x, -factor) ;
	}
	
	private static double func_1(double x, int i) {
		return a[i][0] + a[i][1]*Math.log(x + 1) + a[i][2]*Math.log(x + 2) + a[i][3]*Math.log(x + 3) + a[i][4]*Math.log(x + 4);
	}
	
	private static double func_2(double x, int i) {
		return a[i][0] + a[i][1]*x + a[i][2]*Math.pow(x, 2) + a[i][3]*Math.pow(x, 3) + a[i][4]*Math.pow(x, 4) + a[i][5]*Math.pow(x, 5) + a[i][6]*Math.pow(x, 6);
	}
	
	private static double func_3(double x, int i) {
		return a[i][0] + a[i][1]*Math.pow(x, -1) + a[i][2]*Math.pow(x, -2) + a[i][3]*Math.pow(x, -3) + a[i][4]*Math.pow(x, -4) + a[i][5]*Math.pow(x, -5) + a[i][6]*Math.pow(x, -6);
	}
	
	private static double sum(int n, int factor, int j, double[] x, double[] y, char mode, Function func) {
		double sum = 0;
		if (mode == 'y') {
			if(factor == 0) {
				for (int i = 0; i < n; i++) {
					sum += y[i];
				}
			}else {
				for (int i = 0; i < n; i++) {
					sum += y[i] * func.func(x[i], factor);
				}
			}
		}else {
			if(factor == 0 || j == 0) {
				factor = Math.max(factor, j);
				for (int i = 0; i < n; i++) {
					sum += func.func(x[i], factor);
				}
			}else {
				for (int i = 0; i < n; i++) {
					sum += func.func(x[i], j) * func.func(x[i], factor);
				}
			}
		}
		
		return sum;
	}
	
	private static void task_7() {
		double[][] points = new double[][] {
			{5.401, 3.427, 5.97, 2.67, 1.287, 3.87, 4.721, 1.781, 4.974, 1.599}, 
			{24.1, 18.35, 19.33, 20.18, 45.56, 20.98, 16.17, 22.87, 22.79, 35.99}
		};
		ArrayList<double[][]> Matrix = new ArrayList<double[][]>(); 
		Matrix.add(new double[5][6]);
		Matrix.add(new double[7][8]);
		Matrix.add(new double[7][8]);
		
		int rows, columns, n = points[0].length;
		
		Function functions[] = new Function[] {Main::elem_func_1, Main::elem_func_2, Main::elem_func_3};
		
		for(int matrix = 0; matrix < Matrix.size(); matrix++) {
			rows = Matrix.get(matrix).length;
			columns = Matrix.get(matrix)[0].length;
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					if(i == 0 && j == 0) {
						Matrix.get(matrix)[0][0] = n;
					}else if(i == 0) {
						if(j == columns - 1 || (matrix == 0 && j == 5)) {
							Matrix.get(matrix)[i][j] = sum(n, 0, 0, points[0], points[1], 'y', functions[matrix]);
						}else {
							Matrix.get(matrix)[i][j] = sum(n, 0, j, points[0], points[1], 'x', functions[matrix]);
						}
					}else {
						if(j == columns - 1 || (matrix == 0 && j == 5)) {
							Matrix.get(matrix)[i][j] = sum(n, i, 0, points[0], points[1], 'y', functions[matrix]);
						}
						else {
							Matrix.get(matrix)[i][j] = sum(n, i, j, points[0], points[1], 'x', functions[matrix]);
						}
					}
				}
			}
		}
		
		for(int matrix = 0; matrix < Matrix.size(); matrix++) {
			int spaces = 0;
			for(int i = 0; i < Matrix.get(matrix).length; i++) {
				for(int j = 0; j < Matrix.get(matrix)[0].length; j++) {
					spaces = (int)Math.max(spaces, Matrix.get(matrix)[i][j]);
				}
			}
			spaces = Integer.toString(spaces).length() + 7;
			printMatrix(Matrix.get(matrix), spaces);
			double[] result = lab_5.task_4.Main.lastStepGauss(lab_5.task_4.Main.firstStepGauss(Matrix.get(matrix)));
			a[matrix] = result;
			printMatrix(Matrix.get(matrix), spaces);
			for(int j = 0; j < result.length; j++) {
				System.out.printf("a%d = %f\n", j, result[j]);
			}System.out.print("----------------\n\n");
		}
		
		functions = new Function[] {Main::func_1, Main::func_2, Main::func_3};
		
		System.out.print("func_1\t\tfunc_2\t\tfunc_3\n");
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < n; j++) {
				errors[i] += Math.pow(points[1][j] - functions[i].func(points[0][j], i), 2);
			}
			System.out.printf("%f\t", errors[i]);
		}
		System.out.println();
		
	}
	
	private static void printMatrix(double[][] matrix, int spaces) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.printf("%.5f%s", matrix[i][j], " ".repeat(spaces - String.format("%.5f", matrix[i][j]).length()));
			}
			System.out.println();
		}System.out.println();
	}
	
	

	public static void main(String[] args) {
		
		task_7();

	}

}
