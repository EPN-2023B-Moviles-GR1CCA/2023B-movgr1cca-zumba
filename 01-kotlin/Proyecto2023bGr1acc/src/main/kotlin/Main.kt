import java.util.*

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

    //CONSRTRUCTOR PRIMARIO
    abstract class Numeros(   protected  val numeroUno: Int, protected  val numeroDos: Int, ){
        init {//Bloque  codigo del constructor primario
            this.numeroUno; this.numeroDos //this es opcionl
            numeroUno; numeroDos; //sin el this es lo mismo
            println("Inicializando")
        }

    }

    class Suma( // Constructor Primario Suma
        uno: Int, // Parametro
        dos: Int // Parametro
    ): Numeros(uno, dos) { // <- Constructor del Padre
        init { // Bloque constructor primario
            this.numeroUno; numeroUno;
            this.numeroDos; numeroDos;
        }

        constructor(//  Segundo constructor
            uno: Int?, // parametros
            dos: Int // parametros
        ) : this(  // llamada constructor primario
            if (uno == null) 0 else uno,
            dos
        ) { // si necesitamos bloque de codigo lo usamos
            numeroUno;
        }

        constructor(//  tercer constructor
            uno: Int, // parametros
            dos: Int? // parametros
        ) : this(  // llamada constructor primario
            uno,
            if (dos == null) 0 else uno
        )

    }

    //Instacias
    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
}
