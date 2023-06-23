package ModifiedExpenseReport;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    private ExpenseType type;
    private int amount;

    public Expense(ExpenseType type, int amount){
        this.type = type;
        this.amount = amount;
    }

    public ExpenseType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public boolean DinnerOverThreshold(){
        boolean IsOverThreshold = type == ExpenseType.DINNER && amount > 5000 ? true : false;
        return IsOverThreshold;
    }
    public boolean BreakfastOverThreshold(){
        boolean IsOverThreshold = type == ExpenseType.BREAKFAST && amount > 1000 ? true : false;
        return IsOverThreshold;
    }

}

public class ModifiedExpenseReport {
    public void printReport(List<Expense> expenses) {
        int totalExpenses = 0;
        int totalMealExpenses = 0;

        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            if (isMealExpense(expense.getType())) {
                totalMealExpenses += expense.getAmount();
            }

            String expenseName = getExpenseName(expense.getType());

            String mealOverExpensesMarker = getMealOverExpensesMarker(expense);

            System.out.println(expenseName + "\t" + expense.getAmount() + "\t" + mealOverExpensesMarker);

            totalExpenses += expense.getAmount();
        }

        System.out.println("Meal expenses: " + totalMealExpenses);
        System.out.println("Total expenses: " + totalExpenses);
    }

    public boolean isMealExpense(ExpenseType expenseType) {
        return expenseType == ExpenseType.DINNER || expenseType == ExpenseType.BREAKFAST;
    }

    public String getExpenseName(ExpenseType expenseType) {
        switch (expenseType) {
            case DINNER:
                return "Dinner";
            case BREAKFAST:
                return "Breakfast";
            case CAR_RENTAL:
                return "Car Rental";
            default:
                return "";
        }
    }

    public String getMealOverExpensesMarker(Expense expense) {
       // boolean isDinner = expense.getType() == ExpenseType.DINNER;
       // boolean isBreakfast = expense.getType() == ExpenseType.BREAKFAST;
        boolean isOverThreshold = (expense.DinnerOverThreshold()) || (expense.BreakfastOverThreshold());
        return isOverThreshold ? "X" : " ";
    }

//    public static void main(String[] args) {
//        List<Expense> expenses = new ArrayList<>();
//
//        Expense dinnerExpense = new Expense();
//        dinnerExpense.setType(ExpenseType.DINNER);
//        dinnerExpense.setAmount(7000);
//        expenses.add(dinnerExpense);
//
//        Expense breakfastExpense = new Expense();
//        breakfastExpense.setType(ExpenseType.BREAKFAST);
//        breakfastExpense.setAmount(1000);
//        expenses.add(breakfastExpense);
//
//        Expense carRentalExpense = new Expense();
//        carRentalExpense.setType(ExpenseType.CAR_RENTAL);
//        carRentalExpense.setAmount(4500);
//        expenses.add(carRentalExpense);

//        new ModifiedExpenseReport().printReport(expenses);
//    }
}
