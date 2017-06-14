package com.ldg.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Persion;
import thrift.generated.PersionService;

/**
 * Created by liudo on 2017/6/14.
 */
public class ThriftClient {
    public static void main(String[] args) {
        TTransport transport=new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol=new TCompactProtocol(transport);
        PersionService.Client client=new PersionService.Client(protocol);
        try {
            transport.open();
            Persion p=client.getPersionByUsername("张三");
            System.out.println(p.getUsername()+"    "+p.getAge()+"    "+p.isMarried());
            System.out.println("-----------------------");
            Persion p2=new Persion();
            p2.setUsername("李四");
            p2.setAge(66);
            p2.setMarried(true);
            client.savePersion(p2);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            transport.close();
        }
    }
}
