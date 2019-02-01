package kotlin_demo.function



open class ExampleView  {

    open fun click(){
        println("成员函数ExampleView click")
    }
}

open class MyButton : ExampleView() {

    override fun click() {
        println("成员函数MyButton click")
    }
}
