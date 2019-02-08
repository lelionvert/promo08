package fr.lcdlv.promo8.socrates.domain.Billing;

import java.util.EnumMap;
import java.util.Objects;

public class NumberOfMealsByDiet {

    private final EnumMap<MealType, Integer> numberOfMealsByDiet;

    public NumberOfMealsByDiet() {
        this.numberOfMealsByDiet = initEnumMap();
    }

    public NumberOfMealsByDiet(EnumMap<MealType, Integer> numberOfCoversByDiet) {
        this.numberOfMealsByDiet = new EnumMap<>(numberOfCoversByDiet);
    }

    public void incrementCoverNumberByMealType(MealType participantMealType) {
        numberOfMealsByDiet.put(participantMealType, (numberOfMealsByDiet.get(participantMealType) + 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfMealsByDiet that = (NumberOfMealsByDiet) o;
        return Objects.equals(numberOfMealsByDiet, that.numberOfMealsByDiet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfMealsByDiet);
    }

    private EnumMap<MealType, Integer> initEnumMap() {
        EnumMap<MealType, Integer> numberOfMealsByDiet = new EnumMap<>(MealType.class);
        for (MealType mealType : MealType.values()) {
            numberOfMealsByDiet.put(mealType, 0);
        }
        return numberOfMealsByDiet;
    }
}
