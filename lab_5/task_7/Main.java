package lab_5.task_7;

public class Main {
	
	private static double[][] points;
	
	private static double sum(String mode, int... k) {
		double result = 0;
		if(mode.equals("xy")) for(int i = 0; i < points[0].length; i++) result += points[1][i] * Math.log(Math.abs(points[0][i] + k[0]));
		else if(mode.equals("xx")) for(int i = 0; i < points[0].length; i++) result += Math.log(Math.abs(points[0][i] + k[0])) * Math.log(Math.abs(points[0][i] + k[1]));
		else if(mode.equals("x")) for(int i = 0; i < points[0].length; i++) result += Math.log(Math.abs(points[0][i] + k[0]));
		else for(int i = 0; i < points[0].length; i++) result += points[1][i];
		return result;
	}
	
	private static void task_7() {
		points = new double[][] {
			{5.401, 3.427, 5.97, 2.67, 1.287, 3.87, 4.721, 1.781, 4.974, 1.599}, 
			{24.1, 18.35, 19.33, 20.18, 45.56, 20.98, 16.17, 22.87, 22.79, 35.99}
		};
		double[][] matrix = new double[5][6];
		matrix[0] = new double[] {points[0].length, sum("x", 1), sum("x", 2), sum("x", 3), sum("x", 4), sum("y")};
		matrix[1] = new double[] {sum("x", 1), sum("xx", 1, 1), sum("xx", 1, 2), sum("xx", 1, 3), sum("xx", 1, 4), sum("xy", 1)};
		matrix[2] = new double[] {sum("x", 2), sum("xx", 1, 2), sum("xx", 2, 2), sum("xx", 2, 3), sum("xx", 2, 4), sum("xy", 2)};
		matrix[3] = new double[] {sum("x", 3), sum("xx", 1, 3), sum("xx", 2, 3), sum("xx", 3, 3), sum("xx", 3, 4), sum("xy", 3)};
		matrix[4] = new double[] {sum("x", 4), sum("xx", 1, 4), sum("xx", 2, 4), sum("xx", 3, 4), sum("xx", 4, 4), sum("xy", 4)};
		lab_5.task_4.Main.printMatrix(matrix);
		double[] result = lab_5.task_4.Main.lastStepGauss(lab_5.task_4.Main.firstStepGauss(matrix));
		lab_5.task_4.Main.printMatrix(matrix);
		for(int i = 0; i < result.length; i++) System.out.printf("a%d = %f\n", i, result[i]);
 	}

	public static void main(String[] args) {
		
		task_7();

	}

}
