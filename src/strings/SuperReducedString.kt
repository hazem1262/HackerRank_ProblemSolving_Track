package strings

import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the superReducedString function below.
tailrec fun superReducedString(s: String): String {
    val outPutString = arrayListOf<Char>()
    s.forEachIndexed{
        index, char -> if (index != s.length - 1) {
            if (char != s[index + 1] ) {
                if (index == 0 ){
                    if (char != s[index+1]){
                        outPutString.add(char)
                    }
                }else{
                    var numberOfEqualsCharsBefore = 0
                    for (i in (index - 1) downTo 0) {
                        if (char == s[i]) numberOfEqualsCharsBefore++ else break
                    }
                    if (numberOfEqualsCharsBefore % 2 == 0) {
                        outPutString.add(char)
                    }
                }
            }
        }
    }
    // check the last char
    var numberOfEqualsCharsBefore = 0
    if (s.length > 1){
        for (i in (s.length - 2) downTo 0) {
            if (s[s.length-1] == s[i]) numberOfEqualsCharsBefore++ else break
        }
        if (numberOfEqualsCharsBefore % 2 == 0) {
            outPutString.add(s[s.length-1])
        }
    }else{
        outPutString.add(s[s.length-1])
    }

    return if (outPutString.isEmpty()){
        "Empty String"
    } else if (outPutString.size == s.length){
        outPutString.joinToString(separator = "") { it.toString() }
    } else{
      superReducedString(outPutString.joinToString(separator = "") { it.toString() })
    }
}

fun main(args: Array<String>) {
    val s = readLine()!!

    val result = superReducedString(s)

    println(result)
}
