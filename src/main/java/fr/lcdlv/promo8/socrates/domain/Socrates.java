package fr.lcdlv.promo8.socrates.domain;

import java.util.ArrayList;
import java.util.List;

import static fr.lcdlv.promo8.socrates.domain.SocratesDay.*;

class Socrates {
    private final ParticipantsRepository participantsRepo;
    private CheckIn checkInHotMealLimit;
    private CheckOut checkOutLunchLimit;

    Socrates(List<Participant> participants, CheckIn checkInHotMealLimit, CheckOut checkOutLunchLimit) {
        this.checkInHotMealLimit = checkInHotMealLimit;
        this.checkOutLunchLimit = checkOutLunchLimit;
        this.participantsRepo = new ParticipantsRepository(participants);
    }

    ColdMealChecker giveColdMealsChecker() {
        List<String> emails = new ArrayList<>();
        for (Participant participant : participantsRepo.getParticipants()) {
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
        NumberOfMealsByDiet numberOfMealsByDiet = generateNumberOfMealsByDiet(meal, participantsRepo.getParticipants());
        return new MealReport(meal, numberOfMealsByDiet);
    }

    private NumberOfMealsByDiet generateNumberOfMealsByDiet(Meal meal, List<Participant> participants) {
        NumberOfMealsByDiet numberOfMealsByDiet = new NumberOfMealsByDiet();
        for (Participant participant : participants) {
            if (participant.hasMealOnThursday(checkInHotMealLimit, meal) && participant.hasMealOnSunday(checkOutLunchLimit, meal)) {
                MealType mealType = participant.hasColdMeal(checkInHotMealLimit, meal)
                        ? MealType.COLDMEAL
                        : participant.getMealType();
                numberOfMealsByDiet.incrementCoverNumberByMealType(mealType);
            }
        }
        return numberOfMealsByDiet;
    }

}
