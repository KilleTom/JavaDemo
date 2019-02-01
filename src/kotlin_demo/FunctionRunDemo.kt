package kotlin_demo

import kotlin_demo.function.*
import kotlin_demo.function.lastChar as last
import kotlin_demo.function.click as extendClick


object FunctionRunDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * 拓展函数的输出
         * */
        println("拓展函数的输出")
        println(""""result".last() = ${"result".last()}""")
        println("""listOf<Int>(1,3,3).joinString() = ${listOf<Int>(1, 3, 3).joinString(sperator = "; ", postfix = " }", prefix = "{ ")}""")
        println("""listOf("1", "3", "5").joinToString() = ${listOf("1", "3", "5").joinToString(sperator = "; ", postfix = " }", prefix = "{ ")}""")
        println("拓展函数是否可重写示例")
        val view :ExampleView = MyButton()
        view.click()
        view.show()
        view.extendClick()
    }
}
