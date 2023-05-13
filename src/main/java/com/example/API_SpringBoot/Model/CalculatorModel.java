package com.example.API_SpringBoot.Model;

import redis.clients.jedis.Jedis;

public class CalculatorModel {
    private int num1;
    private int num2;
    private String operation;
    private Jedis jedis;

    public CalculatorModel() {
        this.jedis = new Jedis("localhost", 6379);
    }

    public CalculatorModel(int num1, int num2, String operation) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
        this.jedis = new Jedis("localhost", 6379);
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getResult() {
        String key = num1 + ":" + num2 + ":" + operation;
        String value = jedis.get(key);
        if (value != null) {
            return Integer.parseInt(value);
        } else {
            int result = calculateResult();
            jedis.set(key, Integer.toString(result));
            return result;
        }
    }

    private int calculateResult() {
        int result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                result = num1 / num2;
                break;
        }
        return result;
    }

    public void clearCache() {
        String key = num1 + ":" + num2 + ":" + operation;
        jedis.del(key);
    }
}
