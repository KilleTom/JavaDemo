@file:JvmName("TLFAADemo")

package kotlin_demo.function

/**
 * 所谓顶层函数：去除包裹静态函数类容器，使其存在包内，简化类的创建，实则在jvm运行编译的时候还是会创建一个类，
 * 如果没有使用注解为该文件命名的时候，该文件名字则为该类名。
 * 如下：
 * @file:JvmName("TLFAADemo")
 * 使用注解将该文件类名编译为TLFAADemo
 * */
fun topLevelFunction(): String = "this is a top level function"

/**
 * 所谓顶层属性，可以看成是一个类外部属性成员，作为一些单独的数据片使用。
 * val 修饰的作用说明是不可变的只能有get方法调出
 * var 修饰的作用说明是可变的拥有get和set方法
 * const 等价于 public static final的作用
 * */

val authorName = "易庞宙"

var canSetValue = "zero"

const val resulut = "易庞宙 is already set"

