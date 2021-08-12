package org.tensorflow.model.examples.donkey;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;

import java.util.ArrayList;
import java.util.List;

public class Predictor {

  private String graphOperation;
  private SavedModelBundle savedModelBundle;

  Predictor(String savedModelBundleLocation, String graphOperation) {
    this.savedModelBundle = SavedModelBundle.load(savedModelBundleLocation);
    this.graphOperation = graphOperation;
  }

  List<Float> predict(Batch batch) {
    Session.Runner runner = this.savedModelBundle.session().runner();
    batch.feed(runner);
    int batchSize = batch.getBatchSize();
    List<Float> predictions  = new ArrayList<>(batchSize);
    FloatDataBuffer floatDataBuffer;
    try (Tensor predTensor = runner.fetch(this.graphOperation).run().get(0)) {
      floatDataBuffer = predTensor.asRawTensor().data().asFloats();
      float[] arrayOfPredictions = new float[batchSize];
      floatDataBuffer.read(arrayOfPredictions);
      for (float onePrediction : arrayOfPredictions) {
        predictions.add(onePrediction);
      }
    }
    return predictions;
  }
}