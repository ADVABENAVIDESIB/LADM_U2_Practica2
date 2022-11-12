package mx.edu.ittepic.ladm_u2_practica2_proyectoenequipo

import android.app.Activity
import android.app.AlertDialog
import android.graphics.*
import android.view.Display
import android.view.MotionEvent
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


class Lienzo(p:MainActivity): View(p) {
var p=p

    var gano=false
    //Variables estrellas
    var estrella=Estrella(this)
    var arregloDeEstrellas= ArrayList<Estrella>()
    var terminoUnaEjecucion=true
    var numero = (Math.random() * 1080 + 10).toFloat()
    var opacidad=0
    //------

    var pizarra= BitmapFactory.decodeResource(resources, R.drawable.pizarra1)
    var uno= BitmapFactory.decodeResource(resources, R.drawable.a1)

    var screen: Rect = Rect(0, 0, 0, 0)
    var displayMio: Display? = null
    val background = BitmapFactory.decodeResource(getResources(),R.drawable.fondo);
    var punteroFigura : Figura ?= null
    //variables para las imagenes (estas posiciones siempre seran las cajas correctas)
    var xini1=250f
    var yini1=200f
    var xini2=270f
    var yini2=200f
    var xini3=490f
    var yini3=200f
    var xini4=510f
    var yini4=200f
    var xini5=730f
    var yini5=200f
    var num1=0
    var num2=0
    var res=0

//variables para posiciones globales
    var yB=0f
    var xB=0f

    var yA=0f
    var xA=0f

    var yC=0f
    var xC=0f
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
    var arregloFiguras = arrayListOf<Figura>(Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB),Figura(this,R.drawable.b1,xB,yB))
    var arregloFigurasAux = arrayListOf<Figura>()
    var figura=""

    //validaciones
    var unoSi = true;
    var dosSi = true;
    var tresSi = true;
    var masSi = true;
    var igualSi = true;
    var num = -1;
    init {
       // generaNumeros()

        creaEstrellas()
        generaNumeros()
        sleep(100)

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


//        masA.pintar(c)
//        igualA.pintar(c)
        //sleep(2000)
             pintarNumeros(c);
            for(star in arregloDeEstrellas){
                star.pintar(c)
            }


    }
//de aqui hacia abajo es de Ana

    //de aqui hacia abajo es de Ana
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN->{
                //'+B'=9, '=B'=10, '+A'=11, '=A'=12, '+C'=13, '=C'=14
                //     0    1     2        3    4    5        6   7   8
                // [1Bnum,1Anum,1Cnum],[2Bnum,2Anum,2Cnum],[Bres,Ares,Cres]
                //PRESIONASTE
                if (arregloFiguras[1].determinarArea(event.x,event.y)){
                    punteroFigura=arregloFiguras[1];
                    figura="UnoA"
                    println("Estas clickeando el NUM1")
                }
                if (arregloFiguras[11].determinarArea(event.x,event.y)){
                    punteroFigura=arregloFiguras[11];
                    figura="MasA"
                    println("Estas clickeando el MAS")
                }
                if (arregloFiguras[4].determinarArea(event.x,event.y)){
                    punteroFigura=arregloFiguras[4];
                    figura="dosA"
                    println("Estas clickeando el NUM2")
                }
                if (arregloFiguras[12].determinarArea(event.x,event.y)){
                    punteroFigura=arregloFiguras[12];
                    figura="igualA"
                    println("Estas clickeando el =")
                }
                if (arregloFiguras[7].determinarArea(event.x,event.y)){
                    punteroFigura=arregloFiguras[7];
                    figura="tresA"
                    println("Estas clickeando el RESULTADO")
                }
            }

            MotionEvent.ACTION_MOVE->{
                //ARRASTRASTE
                if (punteroFigura!=null){
                    punteroFigura!!.mover(event.x,event.y)

                    if(punteroFigura!!.colisionoCon(arregloFiguras[0]) && punteroFigura==arregloFiguras[1]){

                        // Toast.makeText(context, "colisionaron "+figura+" con UnoA",Toast.LENGTH_SHORT).show()
                        //println("colisionaro "+figura+" con UnoB")
                        arregloFiguras[1].x = arregloFiguras[0].x
                        arregloFiguras[1].y = arregloFiguras[0].y
                        if(unoSi){num++; unoSi=false; print("uno:"+num); cambiar();}
                        // unoB.x=3000f; unoB.y=3000f;

                    } else if(punteroFigura!!.colisionoCon(arregloFiguras[0]) && punteroFigura!=arregloFiguras[1]){println("no en uno")}
                    if(punteroFigura!!.colisionoCon(arregloFiguras[3]) && punteroFigura==arregloFiguras[4]){

                        // Toast.makeText(context, "colisionaron "+figura+" con UnoA",Toast.LENGTH_SHORT).show()
                        //println("colisionaro "+figura+" con UnoB")

                        arregloFiguras[4].x = arregloFiguras[3].x
                        arregloFiguras[4].y = arregloFiguras[3].y
                        if(dosSi){num++; dosSi=false; print("dos:"+num); cambiar();}
                        // dosB.x=3000f; dosB.y=3000f;
                    } else if(punteroFigura!!.colisionoCon(arregloFiguras[3]) && punteroFigura!=arregloFiguras[4]) {println("no en ods")}
                    if(punteroFigura!!.colisionoCon(arregloFiguras[6]) && punteroFigura==arregloFiguras[7]){
                        // Toast.makeText(context, "colisionaron "+figura+" con UnoA",Toast.LENGTH_SHORT).show()
                        //println("colisionaro "+figura+" con UnoB")

                        arregloFiguras[7].x = arregloFiguras[6].x
                        arregloFiguras[7].y = arregloFiguras[6].y
                        if(tresSi){num++; tresSi=false; print("tres:"+num); cambiar();}
                        // tresB.x=3000f; tresB.y=3000f;
                    } else if(punteroFigura!!.colisionoCon(arregloFiguras[6]) && punteroFigura!=arregloFiguras[7]) {println("no en trees")}
                    if(punteroFigura!!.colisionoCon(arregloFiguras[9]) && punteroFigura==arregloFiguras[11]){

                        // Toast.makeText(context, "colisionaron "+figura+" con UnoA",Toast.LENGTH_SHORT).show()
                        //println("colisionaro "+figura+" con UnoB")
                        arregloFiguras[11].x = arregloFiguras[9].x
                        arregloFiguras[11].y = arregloFiguras[9].y
                        if(masSi){num++; masSi=false; print("mas:"+num); cambiar();}
                        // masB.x=3000f; masB.y=3000f;
                    } else  if(punteroFigura!!.colisionoCon(arregloFiguras[9]) && punteroFigura==arregloFiguras[11]) {println("no en ms")}
                    if(punteroFigura!!.colisionoCon(arregloFiguras[10]) && punteroFigura==arregloFiguras[12]){
                        // Toast.makeText(context, "colisionaron "+figura+" con UnoA",Toast.LENGTH_SHORT).show()
                        //println("colisionaro "+figura+" con UnoB")

                        arregloFiguras[12].x = arregloFiguras[10].x
                        arregloFiguras[12].y = arregloFiguras[10].y
                        if(igualSi==true){num++; igualSi=false; println("igual:"+num); cambiar();}
                        // igualB.x=3000f; igualB.y=3000f;
                    } else if(punteroFigura!!.colisionoCon(arregloFiguras[10]) && punteroFigura!=arregloFiguras[12]) {println("no en igual" )}



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




    //De aqui hacia arriba es de Ana
    fun generaNumeros(){
        arregloFiguras.clear()
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
        // println("Longitud del arreglo: "+arregloFiguras.size)
        }


        var xini2=270f
        var yini2=200f

        var xini4=610f

        var yini4=500f
        yB=200F
        masA=Figura(this,R.drawable.amas,xini2+240F,yini2+700f)// las x estan volteadas para desacomodarlas
       // menosA=Figura(this,R.drawable.amenos,xA+600f,yA)
        igualA=Figura(this,R.drawable.aigual,xini2,yini2+700f)//

        masB=Figura(this,R.drawable.bmas,xini2,yini2)
      //  menosB=Figura(this,R.drawable.bmenos,xB+200f,yB)
        igualB=Figura(this,R.drawable.bigual,xini2+240F,yini2)

        masC=Figura(this,R.drawable.cmas,xini2,yini2+1000F)
      //  menosC=Figura(this,R.drawable.cmenos,xC+200f,yC)
        igualC=Figura(this,R.drawable.cigual,xini2+240F,yini2+1000f)

//metemos primero las B porque van hasta arriba
        //a partir del indice 8 osea 9,10,11,12..17 cuando exista el menos en este caso solo es hasta el 14
        //se encuentran los signos siempre en las mismas posiciones para cuando  se quiera hacer referencia
        //solo hay que calcular en que indice se encuentra [9..17]
        //'+B'=9, '=B'=10, '+A'=11, '=A'=12, '+C'=13, '=C'=14

        arregloFiguras.add(masB)//'+B'=9
        //arregloFiguras.add(menosB)
        arregloFiguras.add(igualB)//'=B'=10

        arregloFiguras.add(masA)//'+A'=11
        //arregloFiguras.add(menosA)
        arregloFiguras.add(igualA)//'=A'=12

        arregloFiguras.add(masC)//'+C'=13
       // arregloFiguras.add(menosC)
        arregloFiguras.add(igualC)//'=C'=14


    }
    fun crearFiguras(numero:Int,queEs:String){
        yB=200F
        when(queEs){
            "num1"->{
                xB= 150f
                xA=630f
                yA= 900f
                xC=150f
                yC= 1200f
            }
            "num2"->{
                xB= 390f
                xA=390f
                yA= 900f
                xC=390f
                yC= 1200f
            }
            "res"->{
                xB= 630f

                xA=150f //las cordenadas de A estan cambiadas con el num1 para hacer "shufle"
                yA= 900f
                xC=630f
                yC= 1200f
            }
        }


        when(numero){
            1->{
                unoB= Figura(this,R.drawable.b1,xB,yB)
                unoA= Figura(this,R.drawable.a1,xA,yA)
                unoC= Figura(this,R.drawable.c1,xC,yC)
                arregloFiguras.add(unoB)
                arregloFiguras.add(unoA)
                arregloFiguras.add(unoC)

            }
            2->{
                dosB= Figura(this,R.drawable.b2,xB,yB)
                dosA= Figura(this,R.drawable.a2,xA,yA)
                dosC= Figura(this,R.drawable.c2,xC,yC)
                arregloFiguras.add(dosB)
                arregloFiguras.add(dosA)
                arregloFiguras.add(dosC)
            }
            3->{
                tresB= Figura(this,R.drawable.b3,xB,yB)
                tresA= Figura(this,R.drawable.a3,xA,yA)
                tresC= Figura(this,R.drawable.c3,xC,yC)
                arregloFiguras.add(tresB)
                arregloFiguras.add(tresA)
                arregloFiguras.add(tresC)
            }
            4->{
                cuatroB= Figura(this,R.drawable.b4,xB,yB)
                cuatroA= Figura(this,R.drawable.a4,xA,yA)
                cuatroC= Figura(this,R.drawable.c4,xC,yC)
                arregloFiguras.add(cuatroB)
                arregloFiguras.add(cuatroA)
                arregloFiguras.add(cuatroC)
            }
            5-> {
                cincoB = Figura(this, R.drawable.b5, xB, yB)
                cincoA = Figura(this, R.drawable.a5, xA, yA)
                cincoC = Figura(this, R.drawable.c5, xC, yC)
                arregloFiguras.add(cincoB)
                arregloFiguras.add(cincoA)
                arregloFiguras.add(cincoC)

            }
            6->{
                seisB= Figura(this,R.drawable.b6,xB,yB)
                seisA= Figura(this,R.drawable.a6,xA,yA)
                seisC= Figura(this,R.drawable.c6,xC,yC)
                arregloFiguras.add(seisB)
                arregloFiguras.add(seisA)
                arregloFiguras.add(seisC)

            }
            7->{
                sieteB= Figura(this,R.drawable.b7,xB,yB)
                sieteA= Figura(this,R.drawable.a7,xA,yA)
                sieteC= Figura(this,R.drawable.c7,xC,yC)
                arregloFiguras.add(sieteB)
                arregloFiguras.add(sieteA)
                arregloFiguras.add(sieteC)
            }
            8->{
                ochoB= Figura(this,R.drawable.b8,xB,yB)
                ochoA= Figura(this,R.drawable.a8,xA,yA)
                ochoC= Figura(this,R.drawable.c8,xC,yC)
                arregloFiguras.add(ochoB)
                arregloFiguras.add(ochoA)
                arregloFiguras.add(ochoC)
            }
            9->{
                nueveB= Figura(this,R.drawable.b9,xB,yB)
                nueveA= Figura(this,R.drawable.a9,xA,yA)
                nueveC= Figura(this,R.drawable.c9,xC,yC)
                arregloFiguras.add(nueveB)
                arregloFiguras.add(nueveA)
                arregloFiguras.add(nueveC)
            }
            10->{
                diezB= Figura(this,R.drawable.b10,xB,yB)
                diezA= Figura(this,R.drawable.a10,xA,yA)
                diezC= Figura(this,R.drawable.c10,xC,yC)
                arregloFiguras.add(diezB)
                arregloFiguras.add(diezA)
                arregloFiguras.add(diezC)
            }
        }
        invalidate()
    }
    fun pintarNumeros(c:Canvas){
      //  println("AL MOMENTO DE PINTAR "+arregloFiguras.size)
        for(i in 0..arregloFiguras.size-1){
           // println("dentro del for"+i)
            arregloFiguras[i].pintar(c)
        }
    }

    //Funciones movimiento estrellas
    fun creaEstrellas() {
        var i=0
        while (i<5){
            arregloDeEstrellas.add(Estrella(this))
            i++
        }
    }
    fun animaEstrellas() {
        var contador=0
        for(star in arregloDeEstrellas){
            star.animar.start()
            //println("copo"+contador++)
        }
    }
    fun cambiar(){
        //'+B'=9, '=B'=10, '+A'=11, '=A'=12, '+C'=13, '=C'=14
        //     0    1     2        3    4    5        6   7   8
        // [1Bnum,1Anum,1Cnum],[2Bnum,2Anum,2Cnum],[Bres,Ares,Cres]
        if(arregloFiguras[0].x==arregloFiguras[1].x&&
            arregloFiguras[3].x==arregloFiguras[4].x&&
            arregloFiguras[6].x==arregloFiguras[7].x&&
            arregloFiguras[9].x==arregloFiguras[11].x&&
            arregloFiguras[10].x==arregloFiguras[12].x){
            println("SI ENTRA                                                                       1100")
        }
        if(arregloFiguras[0].x==arregloFiguras[1].x&&
            arregloFiguras[3].x==arregloFiguras[4].x&&
            arregloFiguras[6].x==arregloFiguras[7].x&&
            arregloFiguras[9].x==arregloFiguras[11].x&&
            arregloFiguras[10].x==arregloFiguras[12].x){
            unoSi = true;
            dosSi = true;
            tresSi = true;
            masSi = true;
            igualSi = true;
            num=0
            arregloFiguras[2].x = arregloFiguras[1].x;
            arregloFiguras[2].y = arregloFiguras[1].y;
            arregloFiguras[5].x = arregloFiguras[4].x;
            arregloFiguras[5].y = arregloFiguras[4].y;
            arregloFiguras[8].x = arregloFiguras[7].x;
            arregloFiguras[8].y = arregloFiguras[7].y;
            arregloFiguras[13].x = arregloFiguras[11].x;
            arregloFiguras[13].y =  arregloFiguras[11].y;
            arregloFiguras[14].x =  arregloFiguras[12].x;
            arregloFiguras[14].y = arregloFiguras[12].y;

            creaEstrellas()
            animaEstrellas()
            corr()
            terminoUnaEjecucion=true
            println("terminoUnaEjecucion=true*******************")

        }
    }
        fun corr()= GlobalScope.launch{
                        delay(8000)
                        generaNumeros()
            }
}
