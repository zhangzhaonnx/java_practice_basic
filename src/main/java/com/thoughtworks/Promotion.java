package com.thoughtworks;

import java.util.List;

public interface Promotion {

  String getName();

  PromotionResult settle(List<OrderItem> orderItems);
}
