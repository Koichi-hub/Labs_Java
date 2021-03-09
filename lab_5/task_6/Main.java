package lab_5.task_6;

import java.util.Arrays;

public class Main {
	
	// Метод Крамера
	// Поехали!..
	
	// Нахождение определителя матрицы
	// ПО ФАКТУ! мы можем просто перемножить диагональные элементы решеной матрицы и получить ее определитель
	// РРРРррраунд!
	private static double findDeterminant(double[][] matrix) {
		double determinant = 1;
		int count = 0;
		for(double[] line : lab_5.task_4.Main.firstStepGauss(matrix)) determinant *= line[count++];
		return determinant;
	}
	
	// Потом по старой схеме с шаблоном матрицы
	private static double[] methodCramer(double[][] matrix) {
		// шаблон 0_0
		double[][] matrixPattern = new double[matrix.length][matrix.length];
		
		// результат 0_0
		double[] result = new double[matrix.length];
		
		// Заполняем шаблон 0_0
		for(int i = 0; i < matrix.length; i++) matrixPattern[i] = Arrays.copyOf(matrix[i], matrix.length); 
		
		// Находим определитель по шаблону(шаблон здесь изменяется мы должны помнить об этом) -_-
		double determinant = findDeterminant(matrixPattern);
		
		// Проходимся по значениям неизвестных
		for(int i = 0; i < matrix.length; i++) {
			
			// Заполняем шаблон только теперь меняем столбцы текущей неизвестной со столбцом свободных членов *снова закадровый смех*
			// Чтобы это реализовать берем индекс текущей переменной, если он равен текущему столбцу при заполнении, то обращаемся
			// к последнему элементу(свободному члену) если нет, то просто заполняем этот столбец как в матрице (не фильм *закадровый смех*)
			for(int k = 0; k < matrix.length; k++) 
				for(int j = 0; j < matrix.length; j++) matrixPattern[k][j] = matrix[k][j == i ? matrix.length : j];
			
			// Ну вот и усе
			// Финишная прямая высчитываем значение неизвестной по формуле определитель делить на определитель от неизвестной и все
			// Не сложнее адронного коллайдера..
			result[i] = findDeterminant(matrixPattern) / determinant;
		}
		return result;
	}
	
	private static void task_6() {
		double[][] matrix = new double[][] {{1, -1, 1, -4, -2}, {2, 1, -5, 1, 3}, {8, -1, -1, 2, 11}, {1, 6, -2, -2, -7}};
		lab_5.task_4.Main.printMatrix(matrix);
		double[] result = methodCramer(matrix);
		lab_5.task_4.Main.printResult(result);
	}

	public static void main(String[] args) {

		task_6();
		
	}

}
