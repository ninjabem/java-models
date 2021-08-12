package org.tensorflow.model.examples.donkey;

import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Batch implements AutoCloseable {

  private final int batchSize;
  private final Map<String, Tensor> tensors;

  Batch(Donkey donkey, List<Food> menu) {
    this.batchSize = menu.size();
    this.tensors = new HashMap<>();

    Map<String, NdArray<String>> stringFeatures = new HashMap<>();
    Map<String, float[][]> floatFeatures = new HashMap<>();
    Map<String, long[][]> longFeatures = new HashMap<>();

    for (Food food : menu) {
      // pack the features densely into the above HashMaps
      // this section is not important
    }

    stringFeatures.forEach(
        (placeholder, value) -> tensors.put(placeholder, TString.tensorOf(value)));
    floatFeatures.forEach(
        (placeholder, value) ->
            tensors.put(placeholder, TFloat32.tensorOf(StdArrays.ndCopyOf(value))));
    longFeatures.forEach(
        (placeholder, value) ->
            tensors.put(placeholder, TInt64.tensorOf(StdArrays.ndCopyOf(value))));
  }

  void feed(Session.Runner runner) {
    tensors.forEach(runner::feed);
  }

  @Override
  public void close() {
    tensors.values().forEach(Tensor::close);
  }

  public int getBatchSize() {
    return batchSize;
  }
}
