package lab_5.task_4;

public class Main {
	
	/*
	 * Прошу вас!!! 
	 * Позвольте представить метод Гаусса, который состоит из 2 ходов: прямой и обратный.
	 * А в остальном. Код повторяет метод Гаусса, и я не вижу смысла что-либо здесь
	 * комментировать. Ибо тогда придется расписывать алгоритм, взятый с сайта по решение
	 * матрицы методом Гаусса.
	 */
	public static void printMatrix(double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) System.out.printf("%.5f\t", matrix[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printResult(double[] result) {
		for(int i = 0; i < result.length; i++) System.out.printf("x%d = %f\n", i + 1, result[i]);
		System.out.print("----------------");
	}
	
	public static double[][] firstStepGauss(double[][] matrix) {
		for(int line = 0; line < matrix.length - 1; line++) {
			for(int i = line + 1; i < matrix.length; i++) {
				double factor = matrix[i][line] / matrix[line][line];
				for(int j = line; j < matrix[0].length; j++) matrix[i][j] -= matrix[line][j] * factor;
			}
		}
		return matrix;
	}
	
	public static double[] lastStepGauss(double[][] matrix) {
		double[] result = new double[matrix.length];
		for(int i = matrix.length - 1; i >= 0; i--) {
			for(int j = i + 1; j < matrix.length; j++) result[i] -= matrix[i][j] * result[j];
			result[i] = (result[i] + matrix[i][matrix.length]) / matrix[i][i];
		}
		return result;
	}
	
	private static void task_4() {
		double[][] matrix = new double[][] {{1, -1, 1, -4, -2}, {2, 1, -5, 1, 3}, {8, -1, -1, 2, 11}, {1, 6, -2, -2, -7}};
		printMatrix(matrix);
		firstStepGauss(matrix);
		printMatrix(matrix);
		printResult(lastStepGauss(matrix));
	}

	public static void main(String[] args) {

		task_4();

	}

}
