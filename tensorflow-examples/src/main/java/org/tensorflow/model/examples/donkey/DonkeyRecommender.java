package org.tensorflow.model.examples.donkey;

import java.util.List;
import java.util.Random;

public class DonkeyRecommender {

  public static void main(String[] args) {
    // start the service
    // load a model (and reload in the background)
    String savedModelBundleLocation = "/tmp/donkey-recommender-model-v2021-08-11-0042";
    String graphOperation = "fancy/model:0";
    Predictor predictor = new Predictor(savedModelBundleLocation, graphOperation);
    Random random = new Random();

    // a few thousand concurrent requests per second per JVM
    for (int i = 0; i < 5 * 1000; i++) {
      List<Food> menu = Food.randomFood(random);
      Donkey donkey = Donkey.random(random);
      processRequest(donkey, menu, predictor);
    }
  }

  public static void processRequest(Donkey donkey, List<Food> menu, Predictor predictor) {
    List<Float> probabilityOfEating;
    // batch contains (and closes) input tensors
    try (Batch batch = new Batch(donkey, menu)) {
      // predict() contains (and closes) output tensors
      probabilityOfEating = predictor.predict(batch);
    }
    // rank, etc
  }
}

