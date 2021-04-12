package com.xuanyuan.rxjava3analysis

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        // 1. 创建被观察者
        val btnTestRxJava = findViewById<View>(R.id.btnTestRxJava)
        // 2. 创建被观察者并订阅
        btnTestRxJava.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        // 3. 被观察者变动
        if (v.id == R.id.btnTestRxJava) {
            Toast.makeText(this, "按钮点了", Toast.LENGTH_SHORT).show()
        }
    }

     var emitter: Emitter<String>? = null

    /**
     * 一个简单的 Rxjava使用模型
     */
    private fun simpleMode() {

        val mObservable: Observable<String> = Observable.create {
            emitter=it

            it?.let {
                it.onNext("Hello, world!")
                it.onComplete()
            }
        }

        val mObserver: Observer<String> = object : Observer<String> {
            override fun onSubscribe(d: @NonNull Disposable?) {
            }
            override fun onNext(s: @NonNull String) {
            }

            override fun onError(e: @NonNull Throwable?) {}
            override fun onComplete() {}
        }

        mObservable.subscribe(mObserver)

    }

    lateinit var mObservable: Observable<String>

    /**
     * Observable的创建
     */
    @SuppressLint("LogConditional")
    private fun create() {
//        val mObservable: Observable<String> = Observable.create {}
//        val mObservable = Observable.just("Hello, world!")

//        val mObservable: Observable<String> = Observable.from("Hello", " world!", "how", "are", "you")

    }

    /**
     *  转换
     */
    @SuppressLint("LogConditional")
    private fun transform() {
        Observable.just("Hello, world!").flatMap { s: String ->
            val data = "测试$s"
            Observable.just(data)
        }


        Observable.just("110")
                .map { s -> s.toInt() }
                .subscribe {
                    val data = it * 6
                    val i = Log.i("+++++++++", "" + data)
                }

        Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter { data -> data in 4..7 }
                .doOnNext { data ->
                    run {
                        //TODO  保存数据
                    }
                }
                .subscribe { data -> Log.i("+++++++++", "" + data) }


    }

    /**
     * 限流
     */
    private fun limiting(){
        query().sample(500,TimeUnit.MILLISECONDS)
        query().throttleFirst(500,TimeUnit.MILLISECONDS)
        query().throttleLast(500,TimeUnit.MILLISECONDS)

        query().debounce(500,TimeUnit.MILLISECONDS)
        query().throttleWithTimeout(500,TimeUnit.MILLISECONDS)


        query().buffer(500,TimeUnit.MILLISECONDS)
        query().window(500,TimeUnit.MILLISECONDS)
    }


    private fun query(): Observable<String> {
        return Observable.just("Hello, world!")
    }

    private fun queryInt(): Observable<Int> {
        return Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    }
}