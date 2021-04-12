package com.xuanyuan.rxjava3analysis;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;


/**
 * @author 罗发新
 * 创建时间：2021/4/12  10:43
 * 邮件：424533553@qq.com
 * CSDN：https://blog.csdn.net/luo_boke
 * <p>
 * 更新时间：2021/4/12  10:43
 * <p>
 * 文件说明：
 */
public class TTTT {


//    Observable<String> myObservable = Observable.create();
//    io.reactivex.rxjava3.core.Observable<String> myObservable2 = io.reactivex.rxjava3.core.Observable.create();
//
//
//    private Observable<String> query() {
//        return Observable.just("Hello, world!");
//    }
//
//    @SuppressLint("CheckResult")
//    private void test() {
//
//        Observable.just("Hello, world!").flatMap(s -> {
//            s = "测试" + s;
//            return Observable.just(s);
//        }).subscribe(s -> {
//            Log.i("+++++++++",s);
//        });
//
//    }

    @SuppressLint({"CheckResult"})
    public static void main(String[] args) {
        queryInt().filter(integer -> {
            return integer > 3 && integer < 8;
        }).take(3).subscribe(System.out::print);

    }

    private static Observable<Integer> queryInt() {
        return Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    }

}
