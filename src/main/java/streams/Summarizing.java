/*
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package streams;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class Summarizing {
	public static void main(String... args) {
		System.out.println("Nr. of dishes: " + howManyDishes());
		System.out.println("The most caloric dish is: " + findMostCaloricDish());
		System.out.println("Total calories in menu: " + calculateTotalCalories());
		System.out.println("Average calories in menu: " + calculateAverageCalories());
		System.out.println("Menu statistics: " + calculateMenuStatistics());
		System.out.println("Short menu: " + getShortMenu());
		System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
	}

	private static long howManyDishes() {
		return Dish.menu.stream().collect(Collectors.counting());
	}

	private static Dish findMostCaloricDish() {
		return Dish.menu.stream()
				.collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
	}

	private static int calculateTotalCalories() {
		return Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
	}

	private static Double calculateAverageCalories() {
		return Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
	}

	private static IntSummaryStatistics calculateMenuStatistics() {
		return Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
	}

	private static String getShortMenu() {
		return Dish.menu.stream().map(Dish::getName).collect(Collectors.joining());
	}

	private static String getShortMenuCommaSeparated() {
		return Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
	}
}
