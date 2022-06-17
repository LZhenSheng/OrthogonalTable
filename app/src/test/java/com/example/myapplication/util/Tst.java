package com.example.myapplication.util;

import com.example.myapplication.R;

import org.junit.Test;

public class ToastUtilTest {


    @Test
    public void init() {
        ToastUtil.init(null);
    }

    @Test
    public void errorShortToastI() {
        ToastUtil.errorShortToast(R.string.err_not_support);
    }

    @Test
    public void errorShortToastS() {
        ToastUtil.errorShortToast(R.string.err_not_support);
    }


    @Test
    public void errorLongToast() {
        ToastUtil.errorLongToast(R.string.err_not_support);
    }


    @Test
    public void successShortToast1() {
        ToastUtil.successShortToast(R.string.err_not_support);
    }


    @Test
    public void successShortToast2() {
        ToastUtil.successShortToast("dlkjf");
    }

    @Test
    public void infoShortToast() {
        ToastUtil.infoShortToast("3424");
    }

    @Test
    public void warningShortToast() {
        ToastUtil.warningShortToast("ef");
    }

    @Test
    public void normalShortToast() {
        ToastUtil.normalShortToast("244");
    }

    @Test
    public void customShowToast() {
        ToastUtil.customShowToast("3", 1, 1);
    }
}