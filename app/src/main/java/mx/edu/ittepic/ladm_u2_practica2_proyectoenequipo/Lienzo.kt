package mx.edu.ittepic.ladm_u2_practica2_proyectoenequipo

import android.app.Activity
import android.graphics.*
import android.view.Display
import android.view.View


class Lienzo(p:MainActivity): View(p) {
    var pizarra= BitmapFactory.decodeResource(resources, R.drawable.pizarra1)
    var uno= BitmapFactory.decodeResource(resources, R.drawable.a1)

    var screen: Rect = Rect(0, 0, 0, 0)
    var displayMio: Display? = null
    val background = BitmapFactory.decodeResource(getResources(),R.drawable.fondo);

    //variables para las imagenes (estas posiciones siempre seran las cajas correctas)
    var xini1=250f
    var yini1=200f
    var xini2=370f
    var yini2=200f
    var xini3=490f
    var yini3=200f
    var xini4=610f
    var yini4=200f
    var xini5=730f
    var yini5=200f
// declarar todas las figuras

    var unoA= Figura(this,R.drawable.a1,xini1,yini1)
    var dosA= Figura(this,R.drawable.a2,xini3,yini3)
    var masA= Figura(this,R.drawable.amas,xini2,yini2)
    var igualA= Figura(this,R.drawable.aigual,xini4,yini4)
    var tresA= Figura(this,R.drawable.a3,xini5,yini5)

    
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        var p = Paint()

        var ancho: Int
        var alto: Int
        val size = Point()
        displayMio = (context as Activity).windowManager.defaultDisplay
        display.getSize(size);
        ancho = size.x;
        alto = size.y;
        screen = Rect(0, 0, ancho, alto)

        p.color= Color.rgb(0,255,0)
        p.setTextSize(100f)
        c.drawBitmap(background, null, screen, null);
        c.drawBitmap(pizarra,40f,100f,p)
        unoA.pintar(c)
        masA.pintar(c)
        dosA.pintar(c)
        igualA.pintar(c)
        tresA.pintar(c)    }
}