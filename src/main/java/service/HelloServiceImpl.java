package service;


import Ice.Current;
import com.ice.hello.HelloService;
import com.ice.hello._HelloServiceDisp;


/**
 * created by Jay on 2019/6/17
 */
@DJService(name = HelloService.class, version = "1.0", value = "helloService")
public class HelloServiceImpl extends _HelloServiceDisp
{

    public String hello(String name, Current current)
    {
        System.out.println("version 1.0, name:" + name);
        return "1.0, hello, " + name;
    }
}
