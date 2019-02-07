package fr.lcdlv.promo8.socrates.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static fr.lcdlv.promo8.socrates.domain.SocratesDay.*;

class Socrates {
    private final List<Participant> participants;
    private CheckIn checkInHotMealLimit;
    private CheckOut checkOutLunchLimit;

    Socrates(List<Participant> participants, CheckIn checkInHotMealLimit, CheckOut checkOutLunchLimit) {
        this.participants = participants;
        this.checkInHotMealLimit = checkInHotMealLimit;
        this.checkOutLunchLimit = checkOutLunchLimit;
    }

    ColdMealChecker giveColdMealsChecker() {
        List<String> emails = new ArrayList<>();
        for (Participant participant : participants) {
            if (participant.arriveAfterHotMealLimit(checkInHotMealLimit)) {
                emails.add(participant.getEmail());
            }
        }
        return new ColdMealChecker(emails);
    }

    List<MealReport> giveFullWeekMealsReport() {
        List<MealReport> mealReports = new ArrayList<>();

        for (SocratesDay socratesDay : values()) {
            Meal lunch = new Meal(socratesDay, MealTime.LUNCH);
            Meal dinner = new Meal(socratesDay, MealTime.DINNER);

            if (THURSDAY != socratesDay) {
                mealReports.add(giveMealReport(lunch));
            }
            if (SUNDAY != socratesDay) {
                mealReports.add(giveMealReport(dinner));
            }
        }
        return mealReports;
    }

    MealReport giveMealReport(Meal meal) {
        EnumMap<MealType, Integer> numberOfMealsByDiet = generateNumberOfMealsByDiet(meal, participants);
        return new MealReport(meal, numberOfMealsByDiet);
    }

    private EnumMap<MealType, Integer> initEnumMap() {
        EnumMap<MealType, Integer> numberOfMealsByDiet = new EnumMap<>(MealType.class);
        for (MealType mealType : MealType.values()) {
            numberOfMealsByDiet.put(mealType, 0);
        }
        return numberOfMealsByDiet;
    }

    private EnumMap<MealType, Integer> generateNumberOfMealsByDiet(Meal meal, List<Participant> participants) {
        EnumMap<MealType, Integer> numberOfMealsByDiet = initEnumMap();
        for (Participant participant : participants) {
            if (participant.hasNoMealOnThursday(checkInHotMealLimit, meal)) {
                continue;
            }
            if (participant.hasNoMealOnSunday(checkOutLunchLimit, meal)) {
                continue;
            }
            if (participant.hasColdMeal(checkInHotMealLimit, meal)) {
                incrementCoverNumberByMealType(numberOfMealsByDiet, MealType.COLDMEAL);
            } else {
                incrementCoverNumberByMealType(numberOfMealsByDiet, participant.getMealType());
            }
        }
        return numberOfMealsByDiet;
    }

    private void incrementCoverNumberByMealType(EnumMap<MealType, Integer> numberOfMealsByDiet, MealType participantMealType) {
        numberOfMealsByDiet.put(participantMealType, (numberOfMealsByDiet.get(participantMealType) + 1));
    }
}
