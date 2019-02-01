package kotlin_demo.function

/**
 * 拓展函数的作用在于用以解决，现有的java类是不做任何改动
 * 下述类子中
 * String 代表的拓展类型
 * 在Kotlin代码中通过匹配到接收者对象指定类型即可调用但是必须使用import将函数导入，而在java代码中则有所区别
 *
 * 在拓展函数中，接收者类型是可以调用其内部属性以及其内部函数，但需要谨记一点是不允许打破其封装性，
 * 也就是其私有以及其保护是无法在拓展这一方式中被调用
 * 本质上拓展函数就是静态函数，只不过Kotlin使其变为更为简易的语法
 *
 * 有关拓展函数是否可重写的问题
 * 拓展函数并不是类的一部分，它只是类拓展实现多态的一种思维，他是声明在类之外的，尽管它被分别定义为同名不同类型的函数，
 * 但实则上有改变量指向的静态类型所决定调用时哪一个拓展方法，而不是实则运行的类型。所以不存在重写这一个说法，
 * Kotlin只是把它当作一个静态方法
 *
 * 有关成员函数于拓展函数使用的冲突问题
 * 当一个类的成员函数与拓展函数重名的时候，优先使用成员函数，所以在拓展API类的时候要时常注意，成员属性是否与拓展函数重名，
 * 如果重名了可以使用有两种解决方案：
 * 拓展的成员函数如果没有真正运用可以为拓展成员函数进行重新命名这一个办法解决；
 * 使用as这一个关键字设置别名进行别名引用避免使用拓展函数的时候，函数指向不正确；别名这一办法运用于Kotlin的代码，
 * 而由于java代码是显式调用拓展函数所以可以很好的避免这一个问题
 *
 * */
fun String.lastChar(): Char = this.get(this.length - 1)

/**
 * 拓展函数的运用：
 * 输出集合元素
 * */
fun <T> Collection<T>.joinString(sperator: String = ",", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    forEach {
        result.append(it.toString())
        result.append(sperator)
    }
    result.append(postfix)
    return result.toString().replace("$sperator$postfix", postfix)
}

/**
 * 拓展函数的运用：
 * 输出指定类型的集合元素的
 * */
fun Collection<String>.joinToString(sperator: String = ",", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    forEach {
        result.append(it.toString())
        result.append(sperator)
    }
    result.append(postfix)
    return result.toString().replace("$sperator$postfix", postfix)
}

/**
 * 拓展函数不可重写示例
 */
fun ExampleView.show() {
    println("this's ExampleView show")
}

fun ExampleView.click() {
    println("extanded click")
}

fun MyButton.show() {
    println("this's MyButton show")
}
/**
 * 拓展属性的使用跟拓展函数使用类似
 * */
var StringBuilder.lastChar: Char
    set(value) {
        this.setCharAt(this.lastIndex - 1, value)
    }
    get() = this.get(this.lastIndex-1)

