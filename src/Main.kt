import kotlin.random.Random

fun main() {
    val DAUS: String = "⚀ ⚁ ⚂ ⚃ ⚄ ⚅"
    val CARES_DAU: Array<String> = arrayOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    var partides: Int?
    var tiradesPerPartida: Int?
    var numPartida: Int = 1

    //Variable contador
    var partidesGuanyades: Int = 0
    var partidesEmpatades: Int = 0

    var nomUsuari: String


    //Li pregunta el nom
    do {
        print("Digue’m el teu nom: ")
        nomUsuari = readln().trim() //Elimina els espais en blanc del nom
        if (nomUsuari.isEmpty()) {
            println("Error: has d’introduir un nom vàlid!")
        }
    } while (nomUsuari.isEmpty())

    println("\nBenvingut/da, $nomUsuari! 🎲")



    println(
        "\n|=============================================|\n" +
        "| Benvingut/da $nomUsuari al joc dels daus.      " +
        "\n|Per guanyar cada partida, la suma dels punts |" +
        "\n|de les teves tirades dels teus daus ha de ser|" +
        "\n|superior a la de la CPU                      |" +
        "\n|=============================================|"
    )
    println(
        """
            =========================
            🎲 Daus disponibles 🎲
            $DAUS
            =========================
            """.trimIndent()
    )


    // Llegim el número de partides que volem jugar
    do {
        println("Quantes partides vols jugar? (de 1 a 3)")
        partides = readLine()?.toIntOrNull()

        if (partides != null && (partides < 1 || partides > 3)) {
            partides = null
            println("ERROR: Valor no acceptat!")
        }
    } while (partides == null)

    // Llegim el número de quantes tirades volem fer per cada partida
    do {
        println("Quantes tirades vols fer per cada partida? (de 1 a 6)")
        tiradesPerPartida = readLine()?.toIntOrNull()
        println("--------------------------------------------------------")
        if (tiradesPerPartida != null && (tiradesPerPartida < 1 || tiradesPerPartida > 6)) {
            tiradesPerPartida = null
            println("ERROR: Valor no acceptat!")
        }
    } while (tiradesPerPartida == null)

    // Declarem la matriu
    var tiradesGuardades: Array<IntArray>
    var tiradesGuardadesCpu: Array<IntArray>

    // Inicialitzem la matriu de partides files i (tiradesPerPartida + 1) columnes
    tiradesGuardades = Array(partides) { IntArray((tiradesPerPartida + 1)) }
    tiradesGuardadesCpu = Array(partides) { IntArray((tiradesPerPartida + 1)) }




    // Repetim tantes vegades com partides
    for (partida in 0 until partides) {
        var acumuladorCPU: Int = 0
        var tiradaActual: Int = 0


        for (tirada in 1 until tiradesGuardades[partida].size) {
            /** Tirades persona **/
            println("Pressione enter per continuar!")
            readLine()
            println("Tira el dau $nomUsuari! (Intent $tirada)")
            tiradaActual = Random.nextInt(1, 6 + 1)
            println("Has tret un ${CARES_DAU[tiradaActual - 1]} !")

            // Guardem la tirada
            tiradesGuardades[partida][tirada] = tiradaActual

            // Acumulem el sumatori a l'última columna de la fila
            tiradesGuardades[partida][tiradesPerPartida] += tiradaActual

            /** Tirades CPU **/
            println("Tirada de la CPU. (Intent $tirada)")
            acumuladorCPU = Random.nextInt(1, 6 + 1)
            println("Ha tret un ${CARES_DAU[acumuladorCPU - 1]} !")

            //Guardem la tirada de la CPU
            tiradesGuardadesCpu[partida][tirada] = acumuladorCPU

            //Sumem els punts de la CPU
            tiradesGuardadesCpu[partida][tiradesPerPartida] += acumuladorCPU
            println("---------------------------")
        }
        //Ensenya els punts de cada jugador
        println("Partida $numPartida acabada!")
        println("$nomUsuari has aconseguit ${tiradesGuardades[partida][tiradesPerPartida]} punts")
        println("La CPU ha aconseguit ${tiradesGuardadesCpu[partida][tiradesPerPartida]} punts")

        //Missatge segons si guanya, perd o empata
        if (tiradesGuardades[partida][tiradesPerPartida] > tiradesGuardadesCpu[partida][tiradesPerPartida]) {
            println("Felicitats $nomUsuari has guanyat!")
            partidesGuanyades++
        } else if (tiradesGuardades[partida][tiradesPerPartida] < tiradesGuardadesCpu[partida][tiradesPerPartida]) {
            println("Has perdut $nomUsuari quina llastima!")
        } else {
            println("Heu empatat!")
            partidesEmpatades++
        }
        println("---------------------------")
        //Li suma 1 al contador de partides
        numPartida++

        val percentatgeGuanyades = (partidesGuanyades.toDouble() / partides.toDouble()) * 100
        println("Has guanyat $partidesGuanyades de $partides partides.")
        println("Percentatge de victòries: ${("%.2f").format(percentatgeGuanyades)}%")
        //println("Empats: $partidesEmpatades")
        //println("Perdutres: ${partides - partidesGuanyades - partidesEmpatades}")
    }
}