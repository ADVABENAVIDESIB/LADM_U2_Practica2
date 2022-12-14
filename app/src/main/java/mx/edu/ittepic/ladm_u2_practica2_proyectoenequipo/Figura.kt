package mx.edu.ittepic.ladm_u2_practica2_proyectoenequipo


import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Figura(lienzo: Lienzo, imagen:Int, x:Float,y:Float) {
    val lienzo=lienzo
    val imagen=BitmapFactory.decodeResource(lienzo.resources, imagen)
    var x=x
    var y=y

    fun pintar(c:Canvas){
        val p= Paint()
        c.drawBitmap(imagen,x,y,p)
    }
    fun mover(xToque:Float,yToque:Float){
        x=xToque-imagen.width/2
        y=yToque-imagen.height/2
    }

    fun determinarArea(xToque:Float,yToque: Float):Boolean{
        var x2= x+imagen.width
        var y2=y+imagen.height
        if (xToque>=x&&xToque<=x2){
            if (yToque>=y&&yToque<=y2){
                return true
            }
        }
        return false
    }

    fun colisiono(figuraB:Figura):Boolean{
        var x2=x-imagen.width
        var y2=y-imagen.height
        var xIntermedia = x2/2
        var yIntermedia = y2/2


        var x3= figuraB.x-figuraB.imagen.width
        var y3=figuraB.y-figuraB.imagen.height
        var xIntermedia3 = x2/2
        var yIntermedia3 = y2/2

        if(determinarArea(x2,y2))return true//caso 1
        if(determinarArea(x3,y3))return true//caso 1

        if (determinarArea(x,y2))return true //caso 2
        if (determinarArea(x2,y))return true//caso 3
        if (determinarArea(x,y))return true //caso 4
        if (determinarArea(xIntermedia,y2)) return true //caso 5
        if (determinarArea(x2,yIntermedia))return true//caso 6
        if (determinarArea(xIntermedia,y))return true//caso 7
        if (determinarArea(x,yIntermedia))return true//caso 8
        return false
    }

    fun colisionoCon(figuraB:Figura):Boolean{
        var x2=x+imagen.width
        var y2=y+imagen.height
        var xIntermedia = x2/2
        var yIntermedia = y2/2


        var x3= figuraB.x+figuraB.imagen.width
        var y3=figuraB.y+figuraB.imagen.height
        var xIntermedia3 = x2/2
        var yIntermedia3 = y2/2
        if(determinarArea(x2,y2)&&determinarArea(x3,y3))return true//caso 1
        if (determinarArea(x,y2)&&determinarArea(figuraB.x,y3))return true //caso 2
        if (determinarArea(x2,y)&&determinarArea(x3,figuraB.y))return true//caso 3
        if (determinarArea(x,y)&&determinarArea(figuraB.x,figuraB.y))return true //caso 4
        if (determinarArea(xIntermedia,y2)&&determinarArea(xIntermedia3,y3)) return true //caso 5
        if (determinarArea(x2,yIntermedia)&&determinarArea(x3,yIntermedia3))return true//caso 6
        if (determinarArea(xIntermedia,y)&&determinarArea(xIntermedia3,figuraB.y))return true//caso 7
        if (determinarArea(x,yIntermedia)&&determinarArea(figuraB.x,yIntermedia3))return true//caso 8
        return false
    }

}