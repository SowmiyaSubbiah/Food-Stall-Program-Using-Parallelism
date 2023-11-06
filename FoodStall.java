import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FoodStall
{
    public void takeOrder(String custName, String foodItem)
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
        System.out.println();
    }
}
class custOrder implements Runnable
{
    private String custName, foodItem;
    private FoodStall foodStall;

public custOrder(String custName, String foodItem, FoodStall foodStall)
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
    FoodStall fs = new FoodStall();
    ExecutorService executor = Executors.newFixedThreadPool(5);
    String[] customers = {"customer 1", "customer 2", "customer 3", "customer 4", "customer 5"};
    String[] orders = {"Burger", "Pasta", "Noodles", "Briyani", "Fried Rice"};

    for(int i=0; i<5; i++)
    {
        Runnable order = new custOrder(customers[i], orders[i], fs);
        executor.execute(order);
    }
    executor.shutdown();
}
}