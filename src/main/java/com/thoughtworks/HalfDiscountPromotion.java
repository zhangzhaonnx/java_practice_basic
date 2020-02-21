package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class HalfDiscountPromotion implements Promotion {

  private List<String> halfDishIds;

  public HalfDiscountPromotion(List<String> halfDishIds) {
    this.halfDishIds = halfDishIds;
  }

  @Override
  public String getName() {
    return "指定菜品半价";
  }

  @Override
  public PromotionResult settle(List<OrderItem> orderItems) {
    double original = 0;
    double total = 0;
    List<Dish> relatedDishes = new ArrayList<>();
    for (OrderItem item : orderItems) {
      double temp = item.getCount() * item.getDish().getPrice();
      if (isHalfPrice(item.getDish())) {
        total += temp / 2;
        relatedDishes.add(item.getDish());
      } else {
        total += temp;
      }
      original += temp;
    }

    return new PromotionResult(getName(), total, original, relatedDishes);
  }

  private boolean isHalfPrice(Dish item) {
    for (String id : halfDishIds) {
      if (id.equals(item.getId())) {
        return true;
      }
    }
    return false;
  }
}
