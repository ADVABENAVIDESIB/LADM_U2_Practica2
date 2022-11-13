package mx.edu.ittepic.ladm_u2_practica2_proyectoenequipo

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class Estrella(puntero: Lienzo) {
    //val radio=radio

    var estrella = BitmapFactory.decodeResource(puntero.resources, R.drawable.estrella)
    var vel = 0L


    var x=(Math.random() * 1080 + 10).toFloat()
    var y=(Math.random() * -500 -500).toFloat()
    //var velocidad=velocidad
    var puntero= puntero

    fun pintar(c: Canvas){
        val p= Paint()
        c.drawBitmap(estrella,x,y,p)
    }



    var escope= CoroutineScope(Job()+Dispatchers.Default)
    var animar= escope.launch(EmptyCoroutineContext,CoroutineStart.LAZY){
        puntero.oso = BitmapFactory.decodeResource(puntero.resources, R.drawable.celebra)
        while(y<2220){
          //  puntero.p.runOnUiThread {
            vel=(Math.random() * 60 + 50).toLong()
            //if(y>2220) y=0F

            //println(y)
            y=y+((Math.random()*20+3).toFloat()*2F)
            puntero.invalidate()
           // }
            delay(vel)
        }

    }
    fun animar1() = GlobalScope.launch{

        while(y<2220){
            vel=(Math.random() * 60 + 50).toLong()
            //if(y>2220) y=0F

            //println(y)
            y=y+((Math.random()*20+3).toFloat()*2F)
            puntero.invalidate()
            delay(vel)
        }
    }
}