package com.ldg.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Persion;
import thrift.generated.PersionService;

/**
 * Created by liudo on 2017/6/14.
 */
public class PersionServiceImpl implements PersionService.Iface{
    @Override
    public Persion getPersionByUsername(String username) throws DataException, TException {
        System.out.println("got Client param:"+username);
        Persion p=new Persion();
        p.setUsername(username);
        p.setAge(22);
        p.setMarried(false);
        return p;
    }

    @Override
    public void savePersion(Persion persion) throws DataException, TException {
        System.out.println("Got Client param:"+persion.getUsername()+"    "+persion.getAge()+"     "+persion.isMarried());
    }
}
