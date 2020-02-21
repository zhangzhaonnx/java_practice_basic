package com.thoughtworks;

import java.util.List;

public class FullReductionPromotion implements Promotion {

  private double limit;

  private double reduction;

  public FullReductionPromotion(double limit, double reduction) {
    this.limit = limit;
    this.reduction = reduction;
  }


  @Override
  public String getName() {
    return String.format("满%s减%s元", (int) limit, (int) reduction);
  }

  @Override
  public PromotionResult settle(List<OrderItem> orderItems) {
    double original = 0;
    for (OrderItem item : orderItems) {
      original += item.getCount() * item.getDish().getPrice();
    }

    double total = original > limit ? original - reduction : original;
    return new PromotionResult(getName(), total, original);
  }
}
