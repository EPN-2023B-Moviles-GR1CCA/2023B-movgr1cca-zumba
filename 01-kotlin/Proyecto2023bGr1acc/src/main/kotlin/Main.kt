import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    println("Hello World!")


    //*********IMUTABLE NO ES REASIGNADA
    val inmutable: String = "RAQUEL";


    //********MUTABLE (PUEDE SER REASIGNADAS)
    //Reasignar es que se puede igualar en este caso
    // con la varaiable mutable  se esta asigando otro valor
    var mutable: String = "RAQUEL";
    mutable= "ESTHER"


    //********Duck Typing
    // En la mayoria de los casos necesitamos poner  el tipo de vraible
    //Debido a que duck typing ya nos ayuda con eso
    var ejemploVarible = "Raquel Zumba"
    val  edadEjemplo: Int = 12
    ejemploVarible.trim()



    //********Varaiables Primitiva
    val nombreProfesor: String = "Raquel"
    val sueldo: Double= 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //clases java
    val fecha: Date = Date()


    //******SWITCH*******
    //Declracion de la variable
    val estadoCivilWhen = "C"


    when (estadoCivilWhen){
        ("C")-> {
            println("Casado")
        }
        "S"->{
            println("Soltero")
    }
        else ->{
            println("No sabemos")
        }
    }

    //if else en una sola linea
    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"



    //*******FUNCIONES**********
    //si no vamos a devolver nada ponemos UNIT
    fun imprimirNombre(nombre: String): Unit {
        //Para concatenar varias variables utlizamos  la forma del signo de dolar y las llaves
        println("Nombre: ${nombre} ${nombre}")
    }




    //PODEMOS HACER NULO A CULQUIER VARIABLE
    //CUANDO LE PONEMOS ESTE SINGNO?
    fun calcularSueldo(
        //tres tipos de parametros
        sueldo: Double, //Requerido
        tasa: Double = 12.00, //Opcional(Defecto)
        //Puede guardar ya sea un duble o nulo
        //Se puede hacer nullable a cualquier variables poniendo al lado el signo de interogacion
        bonoEspecial: Double? = null, //Opcion null-Nullable
    ): Double{

        if(bonoEspecial== null){
            return sueldo* (100/tasa)
        }else{
            bonoEspecial.dec()
            return sueldo*(100/tasa) + bonoEspecial
        }
    }


    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00)
    calcularSueldo(10.00, 12.00, 20.00)

   //*******PARAMETROS NOMBRADOS
    //No es necsario poner en el nombre que hemos escrito

    calcularSueldo(sueldo = 10.00)
    calcularSueldo(sueldo = 10.00, tasa = 15.00)
    calcularSueldo(sueldo = 10.00, tasa = 12.00, bonoEspecial = 20.00)


    calcularSueldo(sueldo = 10.00 , bonoEspecial = 20.00)//Parametro NOMBRADO
    calcularSueldo(10.00, bonoEspecial = 20.00)
// Se puede utlizar los parametros nombrado en el porden que nosotros queramos
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00,  tasa = 14.00)


    //**********CLASES********

    //CONSRTRUCTOR PRIMARIO, viene justo despues del nombre de la clase
    //Las porpiedaes son publicas
    abstract class Numeros(protected  val numeroUno: Int, protected  val numeroDos: Int){
        init {//****Bloque  codigo del constructor primario
            this.numeroUno; this.numeroDos //this es opcionl
            numeroUno; numeroDos; //sin el this es lo mismo
            println("Inicializando")
        }
    }


    // ******Constructor Primario Suma
    class Suma(
        uno: Int,  //Parametro
        dos: Int //Parametro
    ): //Despues de los parentesis hay dos puntos, con eso hacemos que se desvie a nuestra clase lalamda Numeros
        Numeros(uno, dos) { // <- Constructor del Padre
        init { // Bloque constructor primario
            this.numeroUno; numeroUno;
            this.numeroDos; numeroDos;
        }


        //Constructores
        constructor(//  Segundo constructor
            uno: Int?, // parametros
            dos: Int // parametros

        ) : this(  // llamada constructor Primario
            if (uno == null) 0 else uno, dos
        ) { // si necesitamos bloque de codigo lo usamos
            numeroUno;
        }

        //En el tercer contructor no tiene bloque d ecoigo
        //En este caso se puede omitir
        constructor(//  Tercer constructor
            uno: Int, // parametros
            dos: Int? // parametros
        ) : this(  // llamada constructor primario
            uno,
            if (dos == null) 0 else uno
        )

        // cuarto constructor
        constructor(uno: Int?, dos: Int?) : this(
            if (uno == null) 0 else uno,
            if (dos == null) 0 else uno
        )

        //FUNCION SUMAR
        public fun sumar(): Int {//public po defecto o usar private
            val total = numeroUno + numeroDos
            return total
        }

        //atributos y metodos compartidos
   /* companion object { // Atributos y Metodos "Compartidos"
        // entre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }*/
    }



    //*******Instancias
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)
     sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    /*println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)*/

    //*******ARREGLOS
    //AREGLO ESTATICO
    val arregloEstatico: Array<Int> = arrayOf <Int> (1,2,3)
    println(arregloEstatico)
    val arregloDinamico: ArrayList<Int>  = arrayListOf <Int> (1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    // Agrega el valor 11 al final del ArrayList.
    arregloDinamico.add(11)
    // Agrega el valor 12 al final del ArrayList.
    arregloDinamico.add(12)
    // Imprime el contenido actualizado del ArrayList después de agregar los valores 11 y 12.
    println(arregloDinamico)

//****FOREACH****

    //Foreach no devuelve nada
    val respuestaForEach : Unit = arregloDinamico.forEach{valorActual : Int ->
        println("valor actual: ${valorActual}")
    }

    //it en ingles signofoca elemnatdo iteradfo
    // si hay mas de un parametro no podemos utlizar it
    arregloDinamico.forEach{ println(it) }

    //Indice actaul con el valor actual
    arregloEstatico.forEachIndexed { indice: Int, valorActual: Int ->
        println("Valor ${valorActual} Indice ${indice}")
    }
    println(respuestaForEach)

// MAP cambia el arreglo
//@map hace refrencia a lo que esta haciendo
    //Nos devuelve un nuevo aarreglo con los valores  modificados
    val respuestaMap : List<Double>  = arregloDinamico.map { valorActual: Int->
        //Valor actual  a tranformar a double
        return@map valorActual.toDouble() + 100.00
    }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it +15 }


    //******El operador Flitre nos ayuda a filtrar el arreglo
    val respuestaFilter: List<Int> = arregloDinamico.filter { valorActual : Int ->
        //Expresion Condicion
        val mayoresACinco: Boolean = valorActual > 5
        return@filter mayoresACinco
    }
    val respuestaFilterDos = arregloDinamico.filter{ it <= 5}
    println(respuestaFilter)
    println(respuestaFilterDos)




    //******OR AND devuelve valores valores Booleanos
    //Any=  Comprueba si al menos un elemento cumple con la condicion
    //All= Verifica que todos los elementos cumple con la condicion
    val respuestaAny: Boolean = arregloDinamico.any{valorActual:Int->
        return@any (valorActual > 5)
    }
    println(respuestaAny)///true

    val respuestaAll : Boolean = arregloDinamico.all{
            valorActual : Int ->
        return@all(valorActual >5)
    }
    println(respuestaAll)//FALSE

//Reduce: Acomula Valores
    val respuestReduce: Int = arregloDinamico.reduce{//acumulado= 0 siempre empieza en 0
            acumulado: Int, valorActual: Int ->
        return@reduce(acumulado + valorActual)//

    }
    println(respuestReduce)






}



