package lab_5.task_2;

// Тут все понятно..
import java.util.Scanner;
import lab_5.Function;

public class Main {
	
	private static Scanner in = new Scanner(System.in);
	
	/*
	 * Производная вычисляется численным методом, но чтобы понять производную какой функции я хочу найти
	 * я также передаю сюда ссылку на метод(для вычисления функции), который и станет лямбда-выражением.
	 * Можно сказать, просто передаю метод функции. 
	 * И так в трех методах для вычисления производной с шагом
	 */
	public static double dfuncLeft(double x, double h, Function func) {
		return (func.func(x + h) - func.func(x)) / h;
	}

	public static double dfuncCenter(double x, double h, Function func) {
		return (func.func(x + h) - func.func(x - h)) / (2 * h);
	}
	
	public static double dfuncRight(double x, double h, Function func) {
		return (func.func(x) - func.func(x - h)) / h;
	}
	
	// Метод фунции
	private static double func(double x) {
		return (3 + Math.pow(Math.E, x-1)) / (1 + Math.pow(x, 2)*(x+Math.tan(x)));
	}
	
	// Производная, вычисленная аналитически
	private static double dfunc(double x) {
		return Math.pow(Math.E, x-1)/(Math.pow(x, 2)*(x+Math.tan(x))+1) +
				(-Math.pow(x, 2)*(Math.pow(Math.tan(x), 2)+2)-2*x*(x+Math.tan(x)))*(3+Math.pow(Math.E, x-1))/Math.pow(Math.pow(x, 2)*(x+Math.tan(x))+1, 2);
	}
	
	// Здесь все понятно
	private static void task_2() {
		System.out.print("x:");
		double x = in.nextDouble();
		System.out.println("<x>\t\t<h>\t\tfunc(x-h)\tfunc(x)\t\tfunc(x+h)\tdfunc(x-h)\tdfunc(x)\tdfunc(x+h)\tanalitic solve");
		for(double h = 1; h >= 1e-7; h /= 10) {
			System.out.printf("%f\t%.7f\t%.7f\t%.7f\t%.7f\t%.7f\t%.7f\t%.7f\t%.7f\n", x, h, 
					func(x - h), func(x), func(x + h),
					dfuncLeft(x, h, Main::func), dfuncCenter(x, h, Main::func), dfuncRight(x, h, Main::func), dfunc(x));
		}
	}

	public static void main(String[] args) {

		task_2();

	}

}
