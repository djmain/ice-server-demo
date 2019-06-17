package service;

import Ice.Current;
import com.ice.user._UserServiceDisp;
import org.springframework.stereotype.Service;

/**
 * created by Jay on 2019/6/17
 */
@Service("userService")
public class UserServiceImpl extends _UserServiceDisp
{
    public String getUserName(String id, Current current)
    {
        System.out.println("id:" + id);
        return "zhangming";
    }
}
