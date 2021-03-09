package lab_5.task_3;

public class Main {
	
	private static double e;
	
	/*
	 * Уровень абстракции: >~>~>Божественный<~<~<
	 * Начнем с метода Ньютона, которому нужна производная функции, в данном случае производная вычисляется численным методом.
	 * Значит мы обращаемься к методам класса для 2-го задания(методам вычисления производной функции численным методом) 
	 * и делаем для каждого обертку, чтобы можно было этим методам передать еще и метод функции, ибо это также позволяет применить 
	 * эти методы для данной задачи, ибо здесь у нас другая функция, не та, что во 2-ом задании..
	 * 
	 * И на этом все, в цикле проходимся по погрешностям.
	 * Вызываем метод Ньютона куда в качестве лямбда-выражения(метод для вычисления производной) передаем соответсвующий 
	 * метод обертку, а в методе обертке мы просто вызываем методы вычисления производной(из 2-го задания) и передаем
	 * туда:
	 * x
	 * погрешность e - здесь ето шаг(посмотрите методы 2-го задания) который в 10 раз меньше погрещности дабы была сходимость
	 * и лямбда-выражение(метод функции)
	 * И Voila.. мы имеем решение, которое просто переиспользует написанный ранее код, дабы не копировать одно и тоже 
	 * из класса в класс. 0_0 Думаю, не сложнее сложного..
	 */
	static double dfuncLeftWrapper(double x) {
		return lab_5.task_2.Main.dfuncLeft(x, e / 10, lab_5.task_1.Main::func);
	}
	
	static double dfuncRightWrapper(double x) {
		return lab_5.task_2.Main.dfuncRight(x, e / 10, lab_5.task_1.Main::func);
	}

	static double dfuncCenterWrapper(double x) {
		return lab_5.task_2.Main.dfuncCenter(x, e / 10, lab_5.task_1.Main::func);
	}
	
	static void task_3() {
		double a = 0, b = 1000, solve, res;
		System.out.println("Точность\tНьютон\t\tLeft\t    Погрешность\tRight\t    Погрешность\tCenter\t    Погрешность");
		for(e = 1e-1; e > 1e-7; e /= 10) {
			System.out.printf("%.9f\t%.9f\t%.9f %.7f%%\t%.9f %.7f%%\t%.9f %.7f%%\n", e, solve = lab_5.task_1.Main.methodNewton(a, b, e, lab_5.task_1.Main::dfunc)[0],
					res = lab_5.task_1.Main.methodNewton(a, b, e, Main::dfuncLeftWrapper)[0], lab_5.task_1.Main.compareResults(solve, res),
					res = lab_5.task_1.Main.methodNewton(a, b, e, Main::dfuncRightWrapper)[0], lab_5.task_1.Main.compareResults(solve, res),
					res = lab_5.task_1.Main.methodNewton(a, b, e, Main::dfuncCenterWrapper)[0], lab_5.task_1.Main.compareResults(solve, res)); 
		}
	}

	public static void main(String[] args) {

		task_3();

	}

}
