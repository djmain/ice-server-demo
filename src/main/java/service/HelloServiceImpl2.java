package service;


import Ice.Current;
import com.ice.hello.HelloService;
import com.ice.hello._HelloServiceDisp;

/**
 * created by Jay on 2019/6/17
 */
@DJService(name = HelloService.class, version = "2.0", value = "helloService2")
public class HelloServiceImpl2 extends _HelloServiceDisp
{

    public String hello(String name, Current current)
    {
        System.out.println("version 2.0, name:" + name);
        return "2.0, hello, " + name;
    }
}
