package fr.lcdlv.promo8.socrates.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static fr.lcdlv.promo8.socrates.domain.SocratesDay.*;

class Socrates {
    private final List<Participant> participants;
    private CheckIn checkInHotMealLimit;
    private CheckIn checkInColdMealLimit;

    Socrates(List<Participant> participants, CheckIn checkInHotMealLimit) {
        this.participants = participants;
        this.checkInHotMealLimit = checkInHotMealLimit;
    }


    ColdMealChecker giveColdMeals() {
        List<String> emails = new ArrayList<String>();
        for (Participant participant : participants) {
            if (participant.arriveAfter(checkInHotMealLimit) && participant.arriveSameDay(checkInHotMealLimit)) {
                emails.add(participant.getEmail());
            }
        }

        return new ColdMealChecker(emails);
    }

    List<MealReport> giveMealsReport() {
        EnumMap<Diet, Integer> numberOfMealsByDiet = initEnumMap();
        for (Participant participant : participants) {
            Integer tmpCount = numberOfMealsByDiet.get(participant.getDiet());
            numberOfMealsByDiet.put(participant.getDiet(), ++tmpCount);
        }
        List<MealReport> mealReports = new ArrayList<MealReport>();
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

    private EnumMap<Diet, Integer> initEnumMap() {
        EnumMap<Diet, Integer> numberOfMealsByDiet = new EnumMap<Diet, Integer>(Diet.class);
        for (Diet diet : Diet.values()) {
            numberOfMealsByDiet.put(diet, 0);
        }
        return numberOfMealsByDiet;
    }
}
