package mx.edu.ittepic.ladm_u2_practica2_proyectoenequipo

import android.app.Activity
import android.graphics.*
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.delay
import java.lang.Thread.sleep


class Lienzo(p:MainActivity): View(p) {
    var pizarra= BitmapFactory.decodeResource(resources, R.drawable.pizarra1)
    var uno= BitmapFactory.decodeResource(resources, R.drawable.a1)

    var screen: Rect = Rect(0, 0, 0, 0)
    var displayMio: Display? = null
    val background = BitmapFactory.decodeResource(getResources(),R.drawable.fondo);
    var punteroFigura : Figura ?= null
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
    var num1=0
    var num2=0
    var res=0

//variables para posiciones globales
    var yB=200f
    var xB=250f

    var yA=200f
    var xA=250f

    var yC=200f
    var xC=250f
// declarar todas las figuras
/*
*
    var masA= Figura(this,R.drawable.amas,xini2,yini2)

    var unoA= Figura(this,R.drawable.a1,xini1,yini1)
    var dosA= Figura(this,R.drawable.a2,xini3,yini3)
    var tresA= Figura(this,R.drawable.a3,xini5,yini5)
    *     var igualA= Figura(this,R.drawable.aigual,xini4,yini4)

* */

    lateinit var numero1:Figura

    var masA= Figura(this,R.drawable.amas,xini2,yini2)
    var igualA= Figura(this,R.drawable.aigual,xini4,yini4)
   // lateinit var masA:Figura
    lateinit var menosA:Figura
   // lateinit var igualA:Figura
    lateinit var unoA:Figura
    lateinit var dosA:Figura
    lateinit var tresA:Figura
    lateinit var cuatroA:Figura
    lateinit var cincoA:Figura
    lateinit var seisA:Figura
    lateinit var sieteA:Figura
    lateinit var ochoA:Figura
    lateinit var nueveA:Figura
    lateinit var diezA:Figura



    //B's
    lateinit var masB:Figura
    lateinit var menosB:Figura
    lateinit var igualB:Figura
    lateinit var unoB:Figura
    lateinit var dosB:Figura
    lateinit var tresB:Figura
    lateinit var cuatroB:Figura
    lateinit var cincoB:Figura
    lateinit var seisB:Figura
    lateinit var sieteB:Figura
    lateinit var ochoB:Figura
    lateinit var nueveB:Figura
    lateinit var diezB:Figura
    //C's
    lateinit var masC:Figura
    lateinit var menosC:Figura
    lateinit var igualC:Figura
    lateinit var unoC:Figura
    lateinit var dosC:Figura
    lateinit var tresC:Figura
    lateinit var cuatroC:Figura
    lateinit var cincoC:Figura
    lateinit var seisC:Figura
    lateinit var sieteC:Figura
    lateinit var ochoC:Figura
    lateinit var nueveC:Figura
    lateinit var diezC:Figura
    //arreglo de figuras a pintar
    var arregloFiguras = arrayListOf<Figura>()

    var figura=""
    init {



    }
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


        masA.pintar(c)
        igualA.pintar(c)
        generaNumeros()
        sleep(100)
        pintarNumeros(c);

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN->{

                //PRESIONASTE
                if (unoA.determinarArea(event.x,event.y)){
                    punteroFigura=unoA;
                    figura="UnoA"
                }
                if (masA.determinarArea(event.x,event.y)){
                    punteroFigura=masA;
                    figura="MasA"
                }
                if (dosA.determinarArea(event.x,event.y)){
                    punteroFigura=dosA;
                    figura="dosA"
                }
                if (igualA.determinarArea(event.x,event.y)){
                    punteroFigura=igualA;
                    figura="igualA"
                }
                if (tresA.determinarArea(event.x,event.y)){
                    punteroFigura=tresA;
                    figura="tresA"
                }
            }
            MotionEvent.ACTION_MOVE->{
                //ARRASTRASTE
                if (punteroFigura!=null){
                    punteroFigura!!.mover(event.x,event.y)
                    if (punteroFigura!!.colisionoCon(unoA)) {
                        //Toast.makeText(context, "colisionaron "+figura+" con UnoA",Toast.LENGTH_SHORT).show()
                       // println("colisionaron " + figura + " con UnoA")
                    }
                }
            }
            MotionEvent.ACTION_UP->{
                //SOLTASTE EL DEDO
                punteroFigura= null
            }
        }
        invalidate()
        return true
    }

    fun generaNumeros(){
        var resultado=11

        while (resultado>10){
           num1=(Math.random()*10+1).toInt();
           num2=(Math.random()*10+1).toInt();
           res=num1+num2
            resultado=res
        }

        println("NUM1 "+num1);
        println("NUM2 "+num2);

        println("RESULTADO "+resultado);
        for (i in 0..2){
            when(i){
                0->{
                    crearFiguras(num1,"num1");
                }
                1->{
                    crearFiguras(num2,"num2");
                }
                2->{
                    crearFiguras(res,"res")
                }
            }
         println("Longitud del arreglo: "+arregloFiguras.size)
        }

        masA=Figura(this,R.drawable.amas,xA,yA)
        menosA=Figura(this,R.drawable.amenos,xA,yA)
        igualA=Figura(this,R.drawable.aigual,xA,yA)

        masB=Figura(this,R.drawable.bmas,xB,yB)
        menosB=Figura(this,R.drawable.bmenos,xB,yB)
        igualB=Figura(this,R.drawable.bigual,xB,yB)

        masC=Figura(this,R.drawable.bigual,xC,yC)
        menosC=Figura(this,R.drawable.bigual,xC,yC)
        igualC=Figura(this,R.drawable.bigual,xC,yC)

//metemos primero las B porque van hasta arriba
        //a partir del indice 8 osea 9,10,11,12..17
        //se encuentran los signos siempre en las mismas posiciones para cuando  se quiera hacer referencia
        //solo hay que calcular en que indice se encuentra [9..17]
        arregloFiguras.add(masB)
        arregloFiguras.add(menosB)
        arregloFiguras.add(igualB)

        arregloFiguras.add(masA)
        arregloFiguras.add(menosA)
        arregloFiguras.add(igualA)

        arregloFiguras.add(masC)
        arregloFiguras.add(menosC)
        arregloFiguras.add(igualC)



    }
    fun crearFiguras(numero:Int,queEs:String){

        when(queEs){
            "num1"->{
                xB= 250f
                xA=250f
                yA= 900f
                xC=250f
                yC= 1200f
            }
            "num2"->{
                xB= 490f
                xA=490f
                yA= 900f
                xC=490f
                yC= 1200f
            }
            "res"->{
                xB= 730f
                xA=730f
                yA= 900f
                xC=730f
                yC= 1200f
            }
        }



        when(numero){
            1->{
                unoB= Figura(this,R.drawable.b1,xB,yB)
                unoA= Figura(this,R.drawable.a1,200F,yini1+500F)
                unoC= Figura(this,R.drawable.c1,xini1,yini1+800F)
                arregloFiguras.add(unoA)
                arregloFiguras.add(unoB)
                arregloFiguras.add(unoC)

            }
            2->{
                dosB= Figura(this,R.drawable.b2,xB,yB)
                dosA= Figura(this,R.drawable.a2,xA,yA)
                dosC= Figura(this,R.drawable.c2,xC,yC)
                arregloFiguras.add(dosA)
                arregloFiguras.add(dosB)
                arregloFiguras.add(dosC)
            }
            3->{
                tresB= Figura(this,R.drawable.b3,xB,yB)
                tresA= Figura(this,R.drawable.a3,xA,yA)
                tresC= Figura(this,R.drawable.c3,xC,yC)
                arregloFiguras.add(tresA)
                arregloFiguras.add(tresB)
                arregloFiguras.add(tresC)
            }
            4->{
                cuatroB= Figura(this,R.drawable.b4,xB,yB)
                cuatroA= Figura(this,R.drawable.a4,xA,yA)
                cuatroC= Figura(this,R.drawable.c4,xC,yC)
                arregloFiguras.add(cuatroA)
                arregloFiguras.add(cuatroB)
                arregloFiguras.add(cuatroC)
            }
            5->{
                cincoB= Figura(this,R.drawable.b5,xB,yB)
                cincoA= Figura(this,R.drawable.a5,xA,yA)
                cincoC= Figura(this,R.drawable.c5,xC,yC)
                arregloFiguras.add(cincoA)
                arregloFiguras.add(cincoB)
                arregloFiguras.add(cincoC)

            }
            6->{
                seisB= Figura(this,R.drawable.b6,xB,yB)
                seisA= Figura(this,R.drawable.a6,xA,yA)
                seisC= Figura(this,R.drawable.c6,xC,yC)
                arregloFiguras.add(seisA)
                arregloFiguras.add(seisB)
                arregloFiguras.add(seisC)

            }
            7->{
                sieteB= Figura(this,R.drawable.b7,xB,yB)
                sieteA= Figura(this,R.drawable.a7,xA,yA)
                sieteC= Figura(this,R.drawable.c7,xC,yC)
                arregloFiguras.add(sieteA)
                arregloFiguras.add(sieteB)
                arregloFiguras.add(sieteC)
            }
            8->{
                ochoB= Figura(this,R.drawable.b8,xB,yB)
                ochoA= Figura(this,R.drawable.a8,xA,yA)
                ochoC= Figura(this,R.drawable.c8,xC,yC)
                arregloFiguras.add(ochoA)
                arregloFiguras.add(ochoB)
                arregloFiguras.add(ochoC)
            }
            9->{
                nueveB= Figura(this,R.drawable.b9,xB,yB)
                nueveA= Figura(this,R.drawable.a9,xA,yA)
                nueveC= Figura(this,R.drawable.c9,xC,yC)
                arregloFiguras.add(nueveA)
                arregloFiguras.add(nueveB)
                arregloFiguras.add(nueveC)
            }
            10->{
                diezB= Figura(this,R.drawable.b10,xB,yB)
                diezA= Figura(this,R.drawable.a10,xA,yA)
                diezC= Figura(this,R.drawable.c10,xC,yC)
                arregloFiguras.add(diezA)
                arregloFiguras.add(diezB)
                arregloFiguras.add(diezC)
            }
        }
    }
    fun pintarNumeros(c:Canvas){
        println("AL MOMENTO DE PINTAR "+arregloFiguras.size)
        for(i in 0..arregloFiguras.size-1){
            println("dentro del for"+i)
            arregloFiguras[i].pintar(c)
        }
        numero1= arregloFiguras[1];
    }

}