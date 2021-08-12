package org.tensorflow.model.examples.donkey;

import java.util.Random;

public class Donkey {

  private String id;
  private String name;
  private int age;
  private float weight;
  private String breed;

  Donkey(String id, String name, int age, float weight, String breed) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.weight = weight;
    this.breed = breed;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public float getWeight() {
    return weight;
  }

  public String getBreed() {
    return breed;
  }

  public static Donkey random(Random random) {
    return new Donkey(
        String.valueOf(random.nextLong()),
        "don",
        random.nextInt(42),
        random.nextInt(1000),
        "Cardao");
  }
}
