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

import java.util.*;
import java.util.stream.Collectors;

public class Grouping {

	enum CaloricLevel {
		DIET, NORMAL, FAT
	};

	public static void main(String... args) {
		System.out.println("Dishes grouped by type (old method) " + groupDishesByTypeOld());
		System.out.println("Dishes grouped by type: " + groupDishesByType());
		System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
		System.out.println("Count dishes in groups: " + countDishesInGroups());
		System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
		System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
		System.out.println("Sum calories by type: " + sumCaloriesByType());
	}

	private static Map<Dish.Type, List<Dish>> groupDishesByTypeOld() {
		Map<Dish.Type, List<Dish>> dishes = new HashMap<>();

		for (Dish dish : Dish.menu) {
			if (dishes.get(dish.getType()) == null) {
				dishes.put(dish.getType(), new ArrayList<>());
			}
			dishes.get(dish.getType()).add(dish);
		}
		return dishes;
	}

	private static Map<Dish.Type, List<Dish>> groupDishesByType() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
	}

	private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy((Dish dish) -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		})));
	}

	private static Map<Dish.Type, Long> countDishesInGroups() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
	}

	private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType,
				Collectors.reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
	}

	private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
		return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
				Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get)));
	}

	private static Map<Dish.Type, Integer> sumCaloriesByType() {
		return Dish.menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
	}

}
