package com.thoughtworks;

import java.util.Arrays;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  /**
   * 接收用户选择的菜品和数量，返回计算后的汇总信息
   *
   * @param selectedItems 选择的菜品信息
   */
  public static String bestCharge(String selectedItems) {
    String[] selectedIds = parseSelectedIds(selectedItems);
    int[] selectedCounts = parseSelectedCounts(selectedItems);

    int originalTotal = getOriginalTotal(selectedIds, selectedCounts);
    int halfDiscountTotal = getHalfDiscountTotal(selectedIds, selectedCounts);
    int fullReductionTotal = getFullReductionTotal(originalTotal);

    String orderList = buildOrderList(selectedIds, selectedCounts);
    String discountInfo = buildDiscountInfo(originalTotal, halfDiscountTotal, fullReductionTotal,
        selectedIds);
    String totalInfo = buildTotalInfo(originalTotal, halfDiscountTotal, fullReductionTotal);

    return orderList + discountInfo + totalInfo;
  }

  private static String buildTotalInfo(int originalTotal, int halfDiscountTotal,
      int fullReductionTotal) {
    StringBuilder totalInfo = new StringBuilder();

    int total = Math.min(originalTotal, Math.min(halfDiscountTotal, fullReductionTotal));
    totalInfo.append(String.format("总计：%s元\n", total));
    totalInfo.append("===================================");

    return totalInfo.toString();
  }

  private static String buildDiscountInfo(int originalTotal, int halfDiscountTotal,
      int fullReductionTotal, String[] selectedIds) {
    StringBuilder discountInfo = new StringBuilder();
    if (halfDiscountTotal < originalTotal && fullReductionTotal < originalTotal) {
      if (halfDiscountTotal <= fullReductionTotal) {
        discountInfo.append("使用优惠:\n");
        String halfNamesStr = getSelectedHalfNamesStr(selectedIds);
        int saveTotal = originalTotal - halfDiscountTotal;
        discountInfo.append(String.format("指定菜品半价(%s)，省%s元\n", halfNamesStr, saveTotal));
        discountInfo.append("-----------------------------------\n");
      } else {
        discountInfo.append("使用优惠:\n");
        int saveTotal = originalTotal - fullReductionTotal;
        discountInfo.append(String.format("满30减6元，省%s元\n", saveTotal));
        discountInfo.append("-----------------------------------\n");
      }
    }

    return discountInfo.toString();
  }

  private static String getSelectedHalfNamesStr(String[] selectedIds) {
    StringBuilder builder = new StringBuilder();
    for (String selectedId : selectedIds) {
      int menuIndex = Arrays.binarySearch(getItemIds(), selectedId);
      if (isHalfPrice(selectedId)) {
        builder.append("，").append(getItemNames()[menuIndex]);
      }
    }
    return builder.toString().substring(1);
  }

  private static int getFullReductionTotal(int originalTotal) {
    return originalTotal >= 30 ? originalTotal - 6 : originalTotal;
  }

  private static int getHalfDiscountTotal(String[] selectedIds, int[] selectedCounts) {
    int halfDiscountTotal = 0;
    for (String selectedId : selectedIds) {
      int selectedIndex = Arrays.binarySearch(selectedIds, selectedId);
      int menuIndex = Arrays.binarySearch(getItemIds(), selectedId);
      int count = selectedCounts[selectedIndex];
      int product = (int) getItemPrices()[menuIndex] * count;
      halfDiscountTotal += isHalfPrice(selectedId) ? product / 2 : product;
    }
    return halfDiscountTotal;
  }

  private static int getOriginalTotal(String[] selectedIds, int[] selectedCounts) {
    int originalTotal = 0;
    for (String selectedId : selectedIds) {
      int selectedIndex = Arrays.binarySearch(selectedIds, selectedId);
      int menuIndex = Arrays.binarySearch(getItemIds(), selectedId);
      int count = selectedCounts[selectedIndex];
      int product = (int) getItemPrices()[menuIndex] * count;
      originalTotal += product;
    }
    return originalTotal;
  }

  private static boolean isHalfPrice(String selectedId) {
    return Arrays.binarySearch(getHalfPriceIds(), selectedId) >= 0;
  }

  private static String buildOrderList(String[] selectedIds, int[] selectedCounts) {
    StringBuilder builder = new StringBuilder();

    builder.append("============= 订餐明细 =============\n");
    for (String selectedId : selectedIds) {
      int selectedIndex = Arrays.binarySearch(selectedIds, selectedId);
      int menuIndex = Arrays.binarySearch(getItemIds(), selectedId);
      int count = selectedCounts[selectedIndex];
      int product = (int) getItemPrices()[menuIndex] * count;
      builder.append(String.format("%s x %s = %s元\n", getItemNames()[menuIndex], count, product));
    }
    builder.append("-----------------------------------\n");

    return builder.toString();
  }

  private static int[] parseSelectedCounts(String selectedItems) {
    String[] items = selectedItems.split(",");
    int[] counts = new int[items.length];
    for (int i = 0; i < items.length; i ++) {
      counts[i] = Integer.parseInt(items[i].split(" x ")[1]);
    }
    return counts;
  }

  private static String[] parseSelectedIds(String selectedItems) {
    String[] items = selectedItems.split(",");
    String[] ids = new String[items.length];
    for (int i = 0; i < items.length; i ++) {
      ids[i] = items[i].split(" x ")[0];
    }
    return ids;
  }

  /**
   * 获取每个菜品依次的编号
   */
  public static String[] getItemIds() {
    return new String[]{"ITEM0001", "ITEM0013", "ITEM0022", "ITEM0030"};
  }

  /**
   * 获取每个菜品依次的名称
   */
  public static String[] getItemNames() {
    return new String[]{"黄焖鸡", "肉夹馍", "凉皮", "冰粉"};
  }

  /**
   * 获取每个菜品依次的价格
   */
  public static double[] getItemPrices() {
    return new double[]{18.00, 6.00, 8.00, 2.00};
  }

  /**
   * 获取半价菜品的编号
   */
  public static String[] getHalfPriceIds() {
    return new String[]{"ITEM0001", "ITEM0022"};
  }
}
