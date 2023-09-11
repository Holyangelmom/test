package com.design;

/**
 * 计算器类
 *
 * @author likp2
 * @department 营销云_新零售产品
 * @date 2023/9/9
 */
public class Calculator {

    private static Caretaker<Memento<Number>> caretaker = new Caretaker<>();

    public static void main(String[] args) {
        System.out.println("连续累加5次==============");
        Number result = Calculator.calculate(0, OperateType.ADD, 1);
        System.out.println(result);
        result = Calculator.calculate(result.intValue(), OperateType.ADD, 1);
        System.out.println(result);
        result = Calculator.calculate(result.intValue(), OperateType.ADD, 1);
        System.out.println(result);
        result = Calculator.calculate(result.intValue(), OperateType.ADD, 1);
        System.out.println(result);
        result = Calculator.calculate(result.intValue(), OperateType.ADD, 1);
        System.out.println(result);

        System.out.println("撤销三次==============");
        System.out.println(Calculator.undo());
        System.out.println(Calculator.undo());
        System.out.println(Calculator.undo());

        System.out.println("重做三次==============");
        System.out.println(Calculator.redo());
        System.out.println(Calculator.redo());
        System.out.println(Calculator.redo());

        System.out.println("乘以10==============");
        result = Calculator.calculate(result.intValue(), OperateType.MULTI, 10);
        System.out.println(result);

        System.out.println("归零==============");
        System.out.println(Calculator.clear());

    }

    public static Number calculate(int a, OperateType operateType, int b) {
        if (operateType == null) {
            throw new RuntimeException("请输入计算方式");
        }
        Number result = null;
        switch (operateType) {
            case ADD:
                result = add(a, b);
                break;
            case SUB:
                result = sub(a, b);
                break;
            case MULTI:
                result = multi(a, b);
                break;
            case DIV:
                result = div(a, b);
                break;
            default:
                break;
        }
        //在备忘录中记住当前结果
        caretaker.add(new Memento<>(result));
        return result;
    }

    private static int add(int a, int b) {
        return a + b;
        /*//转为二进制相加
        //二进制相加结果，相当于异或（相同为0，不同为1）
        //计算进位值，相当于与，再左移一位
        int jinwei = 0;
        int tmp = 0;
        do {
            tmp = a ^ b;
            jinwei = (a & b) << 1;
            a = tmp;
            b = jinwei;
        } while (jinwei != 0);
        return tmp;*/
    }

    private static int sub(int a, int b) {
        return a - b;
    }

    private static int multi(int a, int b) {
        return a * b;
    }

    private static double div(int a, int b) {
        return (double) a / b;
    }

    //回退
    public static Number undo() {
        Memento<Number> memento = caretaker.undo();
        return memento == null ? 0 : memento.getResult();
    }

    //重做
    public static Number redo() {
        Memento<Number> memento = caretaker.redo();
        return memento == null ? 0 : memento.getResult();
    }

    //归零
    public static int clear() {
        caretaker.clear();
        return 0;
    }




}
