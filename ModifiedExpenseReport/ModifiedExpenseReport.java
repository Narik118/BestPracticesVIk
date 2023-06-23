package ModifiedExpenseReport;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    final ExpenseType type;
    final int amount;

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
        return type == ExpenseType.DINNER && amount > 5000 ? true : false;
    }
    public boolean BreakfastOverThreshold(){
        return type == ExpenseType.BREAKFAST && amount > 1000 ? true : false;
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
        return switch (expenseType) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
            default -> "";
        };
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
