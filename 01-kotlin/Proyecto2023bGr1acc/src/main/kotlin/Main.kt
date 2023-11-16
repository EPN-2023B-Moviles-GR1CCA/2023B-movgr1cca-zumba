import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    //IMUTABLE NO ES REASIGNADA
    val inmutable: String = "RAQUEL";

    //MUTABLE (PUEDE SER REASIGNADAS)
    val mutable: String = "RAQUEL";



    //Varaiables Primitiva
    val nombreProfesor: String = "Raquel"
    val sueldo: Double= 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //clases java
    val fecha: Date = Date()


    //SWITCH
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

    //FUNCIONES
   //si no ponemos nada ponemos UNIT
    fun calcularSueldo(
        sueldo: Double, //Requerido
        tasa: Double = 12.00, //Opcional(Defecto)
        bonoEspecial: Double? = null, //Opcion null-Nullable

    ): Double{

        if(bonoEspecial== null){
            return sueldo* (100/tasa)
        }else{
            bonoEspecial.dec()
            return sueldo*(100/tasa) + bonoEspecial
        }
    }
    //PODEMOS HACER NULO A CULQUIER VARIABLE
    //CUANDO LE PONEMOS ESTE SINGNO?

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00)
    calcularSueldo(10.00, 12.00, 20.00)

   //PARAMETROS NOMBRADOS

    calcularSueldo(sueldo = 10.00)
    calcularSueldo(sueldo = 10.00, tasa = 15.00)
    calcularSueldo(sueldo = 10.00, tasa = 12.00, bonoEspecial = 20.00)


    calcularSueldo(sueldo = 10.00 , bonoEspecial = 20.00)//Parametrso NOMBRADO
    calcularSueldo(10.00, bonoEspecial = 20.00)

    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00,  tasa = 14.00)

    //CLASES
    //VAR ES REASIGNAGLE
    //CONSRTRUCTOR PRIMARIO
    abstract class Numeros(   protected  val numeroUno: Int,
                              protected  val numeroDos: Int,
    ){


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


