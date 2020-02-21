package com.thoughtworks;

import java.util.List;

public class OrderResult {
  private List<OrderItem> orderItems;
  private PromotionResult promotionResult;

  public OrderResult(List<OrderItem> orderItems, PromotionResult promotionResult) {
    this.orderItems = orderItems;
    this.promotionResult = promotionResult;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public PromotionResult getPromotionResult() {
    return promotionResult;
  }
}
