package lab_5.task_5;

import java.util.Arrays;

public class Main {
	
	/*
	 * Вот здесь конечно поинтересней.
	 * Пройдемся по строкам
	 */
	private static double[][] findReverseMatrix(double[][] reverseMatrix) {
		// Инициализируем массив матрицы
		double[][] matrix = new double[reverseMatrix.length][reverseMatrix.length + 1];
		// Инициализируем массив шаблона матрицы
		double[][] patternMatrix = new double[reverseMatrix.length][reverseMatrix.length + 1];
		
		// Заполняем построчно матрицу и шаблон строками исходной матрицы
		for(int i = 0; i < matrix.length; i++) {
			matrix[i] = Arrays.copyOf(reverseMatrix[i], reverseMatrix.length + 1); 
			patternMatrix[i] = Arrays.copyOf(reverseMatrix[i], reverseMatrix.length + 1); 
		}
		
		// Здесь в цикле мы проходимся по столбцам обратной матрицы АБСТРАКТНО!!!	 
		for(int i = 0; i < matrix.length; i++) {
			// По алгоритму нахождения ОБР МАТР присваеваем последнему столбцу(он пустой да, ибо исходная матрицы квадратная) 1
			matrix[i][matrix.length] = 1;
			
			// Потом строку с 1 на конце *закадровый смех*
			// Мы опускаем *смех усилился* вниз, ну или меняем местами 1 и последнюю строки
			// Это нужно для нахождения корня последней неизвестной(ну в методе Гаусса, чтобы там строки местами не менять)
			double[] arr = matrix[i];
			matrix[i] = matrix[matrix.length - 1];
			matrix[matrix.length - 1] = arr;
			
			// Переиспользуем переменную arr теперь она ссылается на массив с решением такой матрицы
			arr = lab_5.task_4.Main.lastStepGauss(lab_5.task_4.Main.firstStepGauss(matrix));
			
			// Записываем решение матрицы (это же столбец обратной матрицы) в обратную матрицу
			for(int line = 0; line < reverseMatrix.length; line++) reverseMatrix[line][i] = arr[line];
			
			// Ну а потом "восстанавливаем исходную матрицу по шаблону" ибо в функцию мы все-таки ссылку на массив передаем, 
			// а не значение
			for(int k = 0; k < patternMatrix.length; k++) matrix[k] = Arrays.copyOf(patternMatrix[k], matrix.length + 1);
		}
		
		// Viola обратная матрица готова к употреблению
		return reverseMatrix;
	}
	
	private static void task_5() {
		double[][] matrix = new double[][] {{1, -1, 1, -4}, {2, 1, -5, 1}, {8, -1, -1, 2}, {1, 6, -2, -2}};
		lab_5.task_4.Main.printMatrix(matrix);
		findReverseMatrix(matrix);
		lab_5.task_4.Main.printMatrix(matrix);
	}

	public static void main(String[] args) {

		task_5();

	}

}
