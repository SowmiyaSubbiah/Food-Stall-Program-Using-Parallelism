import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FoodStall
{
    public void takeOrder(String custName, String foodItem) // Method Defined
    {
        System.out.println(custName + " is ordering " + foodItem);
        System.out.println();

        try // Simulate food preparation
        {
            Thread.sleep(3000);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(foodItem + " is ready for "+custName);
        System.out.println("");
    }
}
class custOrder implements Runnable
{
    private String custName, foodItem;
    private FoodStall foodStall; // Reference Variable for the Class FoodStall

public custOrder(String custName, String foodItem, FoodStall foodStall) // Constructor
{
    this.custName = custName;
    this.foodItem = foodItem;
    this.foodStall = foodStall;
}
@Override
public void run()
{
foodStall.takeOrder(custName, foodItem);
}

public static void main(String[] args)
{
    FoodStall fs = new FoodStall(); // Creating Object for the Class FoodStall
    ExecutorService executor = Executors.newFixedThreadPool(5); // Creating fixed Threads with Executor Framework
    String[] customers = {"customer 1", "customer 2", "customer 3", "customer 4", "customer 5"}; // Creating Array of Strings
    String[] orders = {"Burger", "Pasta", "Noodles", "Briyani", "Fried Rice"};

    for(int i=0; i<5; i++)
    {
        Runnable order = new custOrder(customers[i], orders[i], fs); // Creating Object for the Runnable Interface
        executor.execute(order);
    }
    executor.shutdown();
}
}