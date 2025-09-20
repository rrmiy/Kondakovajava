import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecipeProcessor {

    public static class Recipe {
        String dishName;
        int calories;
        int cookingTime;


    public Recipe(String dishName, int calories, int cookingTime) {
        this.dishName = dishName;
        this.calories = calories;
        this.cookingTime = cookingTime;
    }

    public String getDishName() {
        return dishName;
    }

    public int getCalories() {
        return calories;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public String toString() {
        return "Рецепт {" +
                "Название: '" + dishName + '\'' +
                ", калории: " + calories +
                ", время готовки: " + cookingTime +
                '}';
        }
    }

    @FunctionalInterface
    interface Processor<T> {
        void process(T obj);
    }


    public static void main(String[] args) {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Паста Карбонара", 600, 25));
        recipes.add(new Recipe("Салат Цезарь", 350, 15));
        recipes.add(new Recipe("Борщ", 450, 60));
        recipes.add(new Recipe("Омлет", 200, 10));
        recipes.add(new Recipe("Куриный суп", 300, 45));

        List<Recipe> filteredRecipe = recipes.stream()
                .filter(recipe -> recipe.getCalories() < 500 && recipe.getCookingTime() < 30)
                .sorted(Comparator.comparingInt(Recipe::getCookingTime))
                .toList();

        Processor<Recipe> recipePrinter = recipe -> System.out.println("Название: " + recipe.getDishName() +
                ", калории: " + recipe.getCalories() +
                ", время готовки: " + recipe.getCookingTime());

    }
}

