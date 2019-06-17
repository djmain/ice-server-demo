
import com.ice.hello.HelloService;
import com.ice.user.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.*;

import java.util.Map;

/**
 * created by Jay on 2019/6/17
 */

public class Server
{


    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Map<String, Object> map = context.getBeansWithAnnotation(DJService.class);

        HelloService helloService = (HelloService) context.getBean("helloService");
        HelloService helloService2 = (HelloService) context.getBean("helloService2");
        DJService helloDJService = helloService.getClass().getAnnotation(DJService.class);
        DJService helloDJService2 = helloService2.getClass().getAnnotation(DJService.class);

        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        int status = 0;
        Ice.Communicator communicator = null;
        try
        {
            //初使化连接，args可以传一些初使化参数，如连接超时时间，初使化客户连接池的数量等
            communicator = Ice.Util.initialize(args);
            //创建名为SimplePrinterAdapter的适配器，并要求适配器使用缺省的协议(TCP/IP侦听端口为10000的请求)
            Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("ServerAdapter", "default -p 10001");

            //将服务单元增加到适配器中，并给服务对象指定名称为SimplePrinter，该名称用于唯一确定一个服务单元
            adapter.add(helloService, Ice.Util.stringToIdentity(helloDJService.name().getName() + "-" + helloDJService.version()));
            adapter.add(helloService2, Ice.Util.stringToIdentity(helloDJService2.name().getName() + "-" + helloDJService2.version()));
            adapter.add(userService, Ice.Util.stringToIdentity(UserService.class.getName()));

            //激活适配器，这样做的好处是可以等到所有资源就位后再触发
            adapter.activate();
            //让服务在退出之前，一直持续对请求的监听
            communicator.waitForShutdown();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            status = 1;
        }

        if (communicator != null)
        {
            try
            {
                communicator.destroy();
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }

}
