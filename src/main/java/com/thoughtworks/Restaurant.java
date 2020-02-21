package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

  private List<Dish> dishes;
  private List<Promotion> promotions = new ArrayList<>();

  public Restaurant(List<Dish> dishes) {
    this.dishes = dishes;
    promotions.add(new NonPromotion());
  }

  public void addPromotion(Promotion promotion) {
    promotions.add(promotion);
  }

  public OrderResult order(List<OrderItem> orderItems) {
    PromotionResult promotionResult = selectPromotion(orderItems);
    return new OrderResult(orderItems, promotionResult);
  }

  private PromotionResult selectPromotion(List<OrderItem> orderItems) {
    PromotionResult result = null;
    for (Promotion promotion : promotions) {
      PromotionResult cur = promotion.settle(orderItems);
      if (result == null || result.getTotal() > cur.getTotal()) {
        result = cur;
      }
    }
    return result;
  }

  public OrderItem getOrderItem(String id, int count) {
    for (Dish dish: dishes) {
      if (dish.getId().equals(id)) {
        return new OrderItem(dish, count);
      }
    }
    return null;
  }
}
