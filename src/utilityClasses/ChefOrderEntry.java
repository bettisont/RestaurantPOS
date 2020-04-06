/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityClasses;

/**
 *
 * @author timbettison
 */
public class ChefOrderEntry {

    private int tableNumber;
    private String mealName;
    private String preperationTime;
    private Boolean isAllergen;

    public ChefOrderEntry(int tableNumber, String mealName, String preperationTime, Boolean isAllergen) {

        this.tableNumber = tableNumber;
        this.mealName = mealName;
        this.preperationTime = preperationTime;
        this.isAllergen = isAllergen;

    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getPreperationTime() {
        return preperationTime;
    }

    public void setPreperationTime(String preperationTime) {
        this.preperationTime = preperationTime;
    }

    public Boolean getIsAllergen() {
        return isAllergen;
    }

    public void setIsAllergen(Boolean isAllergen) {
        this.isAllergen = isAllergen;
    }

}
