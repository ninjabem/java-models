package org.tensorflow.model.examples.donkey;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Food {

  private String name;
  private String color;
  private int spiciness;

  Food(String name, String color, int spiciness) {
    this.name = name;
    this.color = color;
    this.spiciness = spiciness;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public int getSpiciness() {
    return spiciness;
  }

  public static List<Food> randomFood(Random random) {
    List<Food> food = new LinkedList<>();
    food.add(new Food("wheat straw", "gold", random.nextInt(1000)));
    food.add(new Food("barley straw", "yellow", random.nextInt(1000)));
    food.add(new Food("oat straw", "green", random.nextInt(1000)));
    return food;
  }
}
