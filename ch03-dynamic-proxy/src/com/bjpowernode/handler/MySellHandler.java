package com.bjpowernode.handler;

import com.bjpowernode.factory.UsbKingFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
//必须实现InvocationHandler接口，完成代理类要做的功能（1.调用目标方法2.功能增强）

public class MySellHandler implements InvocationHandler {
    private Object target = null;
    //动态代理:目标对象是活动的,不是固定的,需要传入进来.
    //传入是谁,就给谁创建代理.
    public MySellHandler(Object target) {
        //给目标对象赋值
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = null;
        //向厂家发送订单,告诉厂家,我买了U盘,厂家发货
        res = method.invoke(target,args);//执行的是UsbKingFactory中的方法
        //(功能增强)商家需要加价,也就是代理需要增加价格
        if(res != null){
            float yuanjia;
            Float price = (Float)res;
            yuanjia = price/85;
            price = price + (25*yuanjia);
            res = price;
        }
        //增加后的价格
        return res;
    }
}
