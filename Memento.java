package com.design;

/**
 * 备忘录类，保存结果/状态
 *
 * @author likp2
 * @department 营销云_新零售产品
 * @date 2023/9/9
 */
public class Memento<T> {

    private T result;

    public T getResult() {
        return result;
    }

    public Memento(T result) {
        this.result = result;
    }
}
