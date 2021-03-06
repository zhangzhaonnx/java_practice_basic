# 基础大练习：黄焖鸡

## 需求描述

某快餐品牌推出了它独家的外卖应用，用户可以在手机上直接下单。该应用会根据用户选择的菜品(Item)、数量(Count)和优惠方式(Sales Promotion)进行计算，告诉用户需要支付的金额(Charge)。

优惠活动有多种形式。假设用户一次只能使用一种优惠，那么使用哪种优惠省钱最多就会是一个让用户头疼的问题。所以该外卖应用为了方便用户，在用户下单时，会自动选择最优惠的方式并计算出最终金额让用户确认。

我们需要实现一个名为`bestCharge`的函数，它能够接收用户选择的菜品和数量（以特定格式呈现）作为输入，然后返回计算后的汇总信息。

已知：

- 该店的菜品每一个都有一个唯一的id
- 当前的优惠方式有:
  - 满30减6元
  - 指定菜品半价
- 除菜品外没有其它收费（如送餐费、餐盒费等）
- 如果两种优惠方式省钱一样多，则使用前一种优惠方式

输入样例
-------

```
ITEM0001 x 1,ITEM0013 x 2,ITEM0022 x 1
```

输出样例
-------

```
============= 订餐明细 =============
黄焖鸡 x 1 = 18元
肉夹馍 x 2 = 12元
凉皮 x 1 = 8元
-----------------------------------
使用优惠:
指定菜品半价(黄焖鸡，凉皮)，省13元
-----------------------------------
总计：25元
===================================
```

使用另一种优惠的样例
------------------

输入：

```
ITEM0013 x 4,ITEM0022 x 1
```


输出：

```
============= 订餐明细 =============
肉夹馍 x 4 = 24元
凉皮 x 1 = 8元
-----------------------------------
使用优惠:
满30减6元，省6元
-----------------------------------
总计：26元
===================================
```

如果没有优惠可享受
---------------

输入：

```
ITEM0013 x 4
```

输出：

```
============= 订餐明细 =============
肉夹馍 x 4 = 24元
-----------------------------------
总计：24元
===================================
```

## 练习要求

- 克隆模版库
- 在项目中`src/main/java/com/thoughtworks`目录下`App.java`类下的`bestCharge`方法中编写代码
- 编写完成后的代码要能通过`src/test`目录下对应的测试验收

## 使用简介
1. 如何导入gradle项目(现在不要求掌握什么是gradle)
- https://www.youtube.com/watch?v=0s7YYjQEsfU
- https://www.jetbrains.com/help/idea/gradle.html

2.运行所有测试  
```
./gradlew clean test
```
3. 运行单个测试可以直接点击对应测试右边的绿色三角形
