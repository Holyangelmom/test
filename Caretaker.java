package com.design;

import java.util.LinkedList;

/**
 * 守护者类
 *
 * @author likp2
 * @department 营销云_新零售产品
 * @date 2023/9/9
 */
public class Caretaker<T> {

    //新增、删除多，链表+队列
    private LinkedList<T> undoMementoList = new LinkedList<>();
    private LinkedList<T> redoMementoList = new LinkedList<>();

    //备忘
    public void add(T memento) {
        //保存当前备忘时，undo队列新增，redo队列清空
        undoMementoList.addLast(memento);
        if (redoMementoList != null && redoMementoList.isEmpty()) {
            redoMementoList = null;
        }
    }

    //撤销
    public T undo() {
        if (undoMementoList.isEmpty()) {
            return null;
        }
        T memento = undoMementoList.removeLast();
        if (redoMementoList == null) {
            redoMementoList = new LinkedList<>();
        }
        redoMementoList.addFirst(memento);
        return memento;
    }

    //重做
    public T redo() {
        if (redoMementoList.isEmpty()) {
            return null;
        }
        T memento = redoMementoList.removeFirst();
        undoMementoList.addLast(memento);
        return memento;
    }

    //清空
    public void clear() {
        if (redoMementoList != null) {
            redoMementoList.clear();
        }
        if (undoMementoList != null) {
            undoMementoList.clear();
        }
    }




}
