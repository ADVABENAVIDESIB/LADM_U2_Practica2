package mx.edu.ittepic.ladm_u2_practica2_proyectoenequipo

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Estrella(puntero: Lienzo) {
    //val radio=radio
    var estrella = BitmapFactory.decodeResource(puntero.resources, R.drawable.estrella)
    var vel = 0L


    var x=(Math.random() * 1080 + 10).toFloat()
    var y=0F
    //var velocidad=velocidad
    var puntero= puntero

    fun pintar(c: Canvas){
        val p= Paint()
        c.drawBitmap(estrella,x,y,p)
    }




    fun animar() = GlobalScope.launch{
        while(true){
            vel=(Math.random() * 60 + 50).toLong()
            if(y>2220) y=0F

            //println(y)
            y=y+((Math.random()*20+3).toFloat()*2F)
            puntero.invalidate()
            delay(vel)
        }
    }
}