package br.com.kevinlucas.android.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        test()
    }

    fun test() {

        val listPersons = mutableListOf<PersonModel>()
        for (i in 0..9){
            listPersons.add(PersonModel("Person $i"))
        }

        Log.i(TAG, listPersons.toString())

        // observavel
        val observable = Observable.just(listPersons.toList())

        // observador
        val observer: Observer<List<PersonModel>> = object : Observer<List<PersonModel>> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe()")
            }

            override fun onNext(t: List<PersonModel>) {
                // chama para cada pessoa por vez
                t.forEach {
                    Log.i(TAG, "onNext(): " + it.name)
                }
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError()")
            }

            override fun onComplete() {
                // executa ao terminar de emitir o observavel
                Log.i(TAG, "onComplete()")
            }
        }

        //assinatura
        observable.subscribe(observer)
    }
}