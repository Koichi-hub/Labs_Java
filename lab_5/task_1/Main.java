package lab_5.task_1;

/* Подключаем интерфейс Function
 * В нем только один метод, который будет определен через ссылку на нужный нам метод.
 * Это также называется лямбда-выражение, т.е функция или блок кода, который мы можем использовать повторно.
 * В данном случае чтобы использовать метод Ньютона дважды я использовал лямбда-выражение для подсчета производной. 
 */
import lab_5.Function;

public class Main {
	
	// Исходная функция
	public static double func(double x) {
		return Math.sqrt(x) - Math.cos(x);
	}
	
	// Производная функции
	public static double dfunc(double x) {
		return 1f / (2*Math.sqrt(x)) + Math.sin(x);
	}
	// Функция Фи
	private static double phifunc(double x) {
		return x + Math.cos(x) - Math.sqrt(x);
	}
	
	// Сравнение результатов по формуле
	public static double compareResults(double solve, double method) {
		return Math.abs(solve - method) / solve * 100;
	}
	
	/* Метод половинного деления
	 * Находим произведение функций от точек a и b чтобы удостовериться, что в этом интервале
	 * знак меняется. Потом пока интервал > заданной погрешности вычисляем функцию от середины
	 * и также проверяем на каком из отрезком меняется знак, в соответствии с отрезком меняется
	 * значение одной из переменных границ (a или b) на значение середины интервала
	 */
	private static double[] halfDivisionMethod(double a, double b, double error) {
		double c, countIterations = 0;
		if(func(a) * func(b) < 0) {
			while(Math.abs(a - b) > error) {
				countIterations++;
				if(func(c = (a + b) / 2) == 0) return new double[] {c, countIterations};
				else {
					if(func(a) * func(c) < 0) b = c;
					else a = c;
				}
			}
			return new double[] {(a + b) / 2, countIterations};
		}
		return null;
	}
	
	/* Метод простых итераций
	 * Условие цикла то же, только мы предыдущему аргументу присваиваем значение функции от текущего по формуле
	 */
	private static double[] simpleIterationMethod(double a, double b, double error) {
		double previousX = a, currentX = phifunc(previousX), countIterations = 0;
		while(Math.abs(currentX - previousX) >= error) {
			previousX = currentX;
			currentX = phifunc(previousX);
			countIterations++;
		}
		return new double[] {currentX, countIterations};
	}
	
	/*
	 *  Метод Ньютона
	 *  То же условие цикла, та же логика нахождения приближенного значения аргумента
	 *  только здесь чтобы использовать метод 		 Ньютона с производной, вычисленной аналитически или численным методом,
	 *  применяется лямбда-выражение. При вызове метода ^ и передаче ссылки на нужный метод      ^   или  ^ лямбда-выражение
	 *  func.func(previousX) - будет просто методом объекта интерфейса, а метод это и есть методы анал. или численного
	 *  вычисления производной, ибо мы передаем ссылку на метод как реализацию, думаю вообще не сложно..
	 */
	public static double[] methodNewton(double a, double b, double error, Function func) {
		double previousX = 1, currentX = previousX - func(previousX) / func.func(previousX), countIterations = 0;
		while(Math.abs(previousX - currentX) >= error) {
			previousX = currentX;
			currentX = previousX - func(previousX) / func.func(previousX);
			countIterations++;
		}
		return new double[] {currentX, countIterations};
	}
	
	/*
	 * Проходимся по массиву с точностями и вызываем все методы, и  для сравнения используем метод compareResults
	 * В остальном же тут также ничего сложного..
	 */
	private static void task_1() {
		double[] errors = {1e-2, 1e-3, 1e-5, 1e-7}, res;
		double a = 0, b = 1000, solve = 0.641714370872883;
		System.out.println("Точность\tАнал.решение\tПоловинного деления\tПогрешность\tПростых итераций\tПогрешность\tНьютона\t\tПогрешность");
		for(double error : errors) {
			System.out.printf("%.7f\t%.9f\t%.7f (%.0f)\t\t%.7f%%\t%.7f (%.0f)\t\t%.7f%%\t%.7f (%.0f)\t%.7f%%\n", 
					error, solve, (res = halfDivisionMethod(a, b, error))[0], res[1], compareResults(solve, res[0]),
					(res = simpleIterationMethod(a, b, error))[0], res[1], compareResults(solve, res[0]), 
					(res = methodNewton(a, b, error, Main::dfunc))[0], res[1], compareResults(solve, res[0]));
		}
	}

	public static void main(String[] args) {

		task_1();

	}

}
