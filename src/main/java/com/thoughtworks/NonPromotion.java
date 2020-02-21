package com.thoughtworks;

import java.util.List;

public class NonPromotion implements Promotion {

  @Override
  public String getName() {
    return "无";
  }

  @Override
  public PromotionResult settle(List<OrderItem> orderItems) {
    double total = 0;
    for (OrderItem item : orderItems) {
      total += item.getCount() * item.getDish().getPrice();
    }
    return new PromotionResult(getName(), total, total);
  }
}
