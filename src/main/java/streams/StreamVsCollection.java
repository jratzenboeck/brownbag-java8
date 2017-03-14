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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamVsCollection {

	public static void main(String[] args) {
		//getLowCaloricDishesNames(Dish.menu).forEach(System.out::println);
		getLowCaloricDishesNamesFunctional(Dish.menu).forEach(System.out::println);
	}

	private static List<String> getLowCaloricDishesNames(List<Dish> dishes) {
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish d : dishes) {
			if (d.getCalories() < 400) {
				lowCaloricDishes.add(d);
			}
		}
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			public int compare(Dish d1, Dish d2) {
				return Integer.compare(d1.getCalories(), d2.getCalories());
			}
		});
		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish d : lowCaloricDishes) {
			lowCaloricDishesName.add(d.getName());
		}
		return lowCaloricDishesName;
	}

	private static List<String> getLowCaloricDishesNamesFunctional(List<Dish> dishes) {
		return dishes.stream().filter(d -> d.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories))
				.map(Dish::getName).collect(Collectors.toList());
	}

}
