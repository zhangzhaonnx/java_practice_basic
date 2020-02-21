package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class PromotionResult {
  private String type;
  private double total;
  private double original;
  private List<Dish> relatedDishes;

  public PromotionResult(String type, double total, double original) {
    this(type, total, original, new ArrayList<>());
  }

  public PromotionResult(String type, double total, double original,
      List<Dish> relatedDishes) {
    this.type = type;
    this.total = total;
    this.original = original;
    this.relatedDishes = relatedDishes;
  }

  public String getType() {
    return type;
  }

  public double getTotal() {
    return total;
  }

  public double getOriginal() {
    return original;
  }

  public List<Dish> getRelatedDishes() {
    return relatedDishes;
  }
}
