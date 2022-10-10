package br.com.kevinlucas.android.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        test()
    }

    fun test() {

        val person: PersonModel = PersonModel("Soleimani")

        // observavel
        val observable: Observable<PersonModel> = Observable.just(person)

        // observador
        val observer: Observer<PersonModel> = object : Observer<PersonModel> {
            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe()")
            }

            override fun onNext(t: PersonModel) {
                Log.i(TAG, "onNext(): " + t.name)
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

        // emite um sinal para cada observador
        observable.subscribe(observer) // retorna uma assinatura
    }
}