package tsp.temp

import tsp.Test2
import java.util.concurrent.Executors

object Test {

    const val KILL_NUM_LEVEL1 = 500
    const val KILL_NUM_LEVEL2 = 1000
    const val KILL_NUM_LEVEL3 = 5000
    const val KILL_NUM_LEVEL4 = 10000
    const val KILL_NUM_LEVEL5 = 100000

    @JvmStatic
    fun generalKillScore(number: Int, level: Int) {
        val list = mutableListOf<Int>()
        if (level == 1) {
            var star = number - 50
            var step = 10
            for (a in 1..15){
                list.add(star)
                if (a == 5){
                    step /= 10
                }
                star += step
            }
        } else if (level == 2 || level == 3) {
            var star = number - 1000
            var step = 100
            for (a in 1..29){
                list.add(star)
                if (a == 10 || a == 19){
                    step /= 10
                }
                star += step
            }
        }
        println(list.toString())


    }


}