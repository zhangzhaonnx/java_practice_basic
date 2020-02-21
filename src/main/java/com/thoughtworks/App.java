package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) {

  }

  public static String bestCharge(String selectedItems) {
    Restaurant restaurant = new Restaurant(DataProvider.getDishes());
    restaurant.addPromotion(new FullReductionPromotion(DataProvider.getFullReductionLimit(),
        DataProvider.getFullReduction()));
    restaurant.addPromotion(new HalfDiscountPromotion(DataProvider.getHalfDishIds()));

    List<OrderItem> orderItems = parseOrderItems(selectedItems, restaurant);

    OrderResult orderResult = restaurant.order(orderItems);

    return renderResult(orderResult);
  }

  private static List<OrderItem> parseOrderItems(String selectedItems, Restaurant restaurant) {
    String[] itemsArray = selectedItems.split(",");
    List<OrderItem> orderItems = new ArrayList<>();
    for (String inputItem : itemsArray) {
      String[] idAndCount = inputItem.split(" x ");
      String id = idAndCount[0];
      int count = Integer.parseInt(idAndCount[1]);
      orderItems.add(restaurant.getOrderItem(id, count));
    }
    return orderItems;
  }

  private static String renderResult(OrderResult orderResult) {
    return renderOrderItems(orderResult.getOrderItems()) + renderPromotion(
        orderResult.getPromotionResult());
  }

  private static String renderPromotion(PromotionResult result) {
    StringBuilder builder = new StringBuilder();
    builder.append("-----------------------------------\n");
    if (result.getType().equals("无")) {
      builder.append("使用优惠:\n");
      double save = result.getOriginal() - result.getTotal();
      String dishStr = result.getRelatedDishes().stream().map(Dish::getName)
          .collect(Collectors.joining("，"));
      String dishInfo = dishStr.isEmpty() ? "" : String.format("(%s)", dishStr);
      builder.append(String.format("%s%s，省%s元\n", result.getType(), dishInfo, (int) save));
      builder.append("-----------------------------------\n");
    }
    builder.append(String.format("总计：%s元\n", (int) result.getTotal()));
    builder.append("===================================");
    return builder.toString();
  }

  private static String renderOrderItems(List<OrderItem> orderItems) {
    StringBuilder builder = new StringBuilder();
    builder.append("============= 订餐明细 =============\n");
    for (OrderItem item : orderItems) {
      Dish dish = item.getDish();
      int total = (int) dish.getPrice() * item.getCount();
      builder.append(String.format("%s x %s = %s元\n", dish.getName(), item.getCount(), total));
    }
    return builder.toString();
  }

}
