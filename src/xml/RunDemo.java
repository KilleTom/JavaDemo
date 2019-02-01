package xml;


public class RunDemo {
    public static void main(String[] args) {
        /**
         * Dom：
         * 文件读取到内存中形成一个Dom树，文件过大负担大了则无法使用
         * 没有访问次数限制可以随意读取
         * 文件修改简易
         * 简易复杂度、开发
         * 模型由系统提供，开发者简历DOM树
         * SAX：
         * 顺序读取，不会一次性读取不受文件大小
         * 不支持任意读取
         * 不能修改文件
         * 略为复杂的开发
         * 模型由开发者自定义灵活性更高
         * */
        DOMDemo domDemo = new DOMDemo();
        domDemo.init();
        domDemo.createXml("person");
        domDemo.parserXml("person");
        SAXDemo saxDemo = new SAXDemo();
        saxDemo.createXml("book");
        saxDemo.parserXml("person");
    }
}
