package fr.lcdlv.promo8.socrates.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static fr.lcdlv.promo8.socrates.domain.SocratesDay.*;

class Socrates {
    private final List<Participant> participants;
    private CheckIn checkInHotMealLimit;

    Socrates(List<Participant> participants, CheckIn checkInHotMealLimit) {
        this.participants = participants;
        this.checkInHotMealLimit = checkInHotMealLimit;
    }

    ColdMealChecker giveColdMealsChecker() {
        List<String> emails = new ArrayList<>();
        for (Participant participant : participants) {
            if (isAfterHotMealLimit(participant)) {
                emails.add(participant.getEmail());
            }
        }
        return new ColdMealChecker(emails);
    }

    private boolean isAfterHotMealLimit(Participant participant) {
        return participant.arriveAfter(checkInHotMealLimit) && participant.arriveSameDay(checkInHotMealLimit);
    }

    List<MealReport> giveMealsReport() {
        EnumMap<MealType, Integer> numberOfMealsByDiet = generateNumberOfMealsByDiet();
        List<MealReport> mealReports = new ArrayList<>();
        for (SocratesDay socratesDay : values()) {
            Meal lunch = new Meal(socratesDay, MealTime.LUNCH);
            Meal dinner = new Meal(socratesDay, MealTime.DINNER);

            if (socratesDay == FRIDAY || socratesDay == SATURDAY) {
                mealReports.add(new MealReport(lunch, numberOfMealsByDiet));
                mealReports.add(new MealReport(dinner, numberOfMealsByDiet));
            } else if (THURSDAY == socratesDay) {
                mealReports.add(new MealReport(dinner, numberOfMealsByDiet));
            } else {
                mealReports.add(new MealReport(lunch, numberOfMealsByDiet));
            }
        }
        return mealReports;
    }

    private EnumMap<MealType, Integer> generateNumberOfMealsByDiet() {
        EnumMap<MealType, Integer> numberOfMealsByDiet = initEnumMap();
        for (Participant participant : participants) {
            Integer tmpCount = numberOfMealsByDiet.get(participant.getMealType());
            numberOfMealsByDiet.put(participant.getMealType(), ++tmpCount);
        }
        return numberOfMealsByDiet;
    }

    private EnumMap<MealType, Integer> initEnumMap() {
        EnumMap<MealType, Integer> numberOfMealsByDiet = new EnumMap<>(MealType.class);
        for (MealType mealType : MealType.values()) {
            numberOfMealsByDiet.put(mealType, 0);
        }
        return numberOfMealsByDiet;
    }

    MealReport giveMealReportByMeal(Meal meal) {
        EnumMap<MealType, Integer> numberOfMealsByDiet = generateNumberOfMealsByDiet(meal);
        return new MealReport(meal, numberOfMealsByDiet);
    }

    private EnumMap<MealType, Integer> generateNumberOfMealsByDiet(Meal meal) {
        EnumMap<MealType, Integer> numberOfMealsByDiet = initEnumMap();
        for (Participant participant : participants) {
            if (meal.isSameDay(checkInHotMealLimit) && isAfterHotMealLimit(participant)) {
                incrementCoverNumberByMealType(numberOfMealsByDiet, MealType.COLDMEAL);
            } else if (!meal.isSameDay(checkInHotMealLimit) || participant.arriveSameDay(checkInHotMealLimit)) {
                incrementCoverNumberByMealType(numberOfMealsByDiet, participant.getMealType());
            }
        }
        return numberOfMealsByDiet;
    }

    private void incrementCoverNumberByMealType(EnumMap<MealType, Integer> numberOfMealsByDiet, MealType participantMealType) {
        numberOfMealsByDiet.put(participantMealType, (numberOfMealsByDiet.get(participantMealType) + 1));
    }
}
