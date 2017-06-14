package com.ldg.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersionService;

/**
 * Created by liudo on 2017/6/14.
 */
public class ThriftServer {
    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket=new TNonblockingServerSocket(8899);
        THsHaServer.Args arg=new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersionService.Processor<PersionServiceImpl> processor=new PersionService.Processor<PersionServiceImpl>(new PersionServiceImpl());
         arg.protocolFactory(new TCompactProtocol.Factory());
         arg.transportFactory(new TFastFramedTransport.Factory());
         arg.processorFactory(new TProcessorFactory(processor));

        TServer server=new THsHaServer(arg);
        System.out.println("Thrift Server Started!");
        server.serve();
    }
}
