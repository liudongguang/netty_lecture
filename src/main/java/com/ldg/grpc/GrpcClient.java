package com.ldg.grpc;

import com.ldg.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Created by LiuDongguang on 2017/8/2.
 */
public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel= ManagedChannelBuilder.forAddress("localhost",8899).usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub=StudentServiceGrpc.newBlockingStub(channel);
        MyResponse response = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(response.getRealname());
        System.out.println("--------------------------");
        Iterator<StudentResponse> studentsByAge = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        studentsByAge.forEachRemaining(item-> System.out.println(item.getName()+"    "+item.getAge()+"    "+item.getCity()));
        System.out.println("----------------------------");
        StreamObserver<StudentResponseList> studentResponseListstreamObserver=new StreamObserver<StudentResponseList>(){
            @Override
            public void onNext(StudentResponseList value) {
                value.getStudentResponseList().forEach(studentResponse -> {
                    System.out.println(studentResponse.getName()+"   "+studentResponse.getAge()+"   "+studentResponse.getCity());
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("client comleted...");
            }
        };
        //客户端以流的方式访问服务端，则一定是异步调用的
        StudentServiceGrpc.StudentServiceStub stub=StudentServiceGrpc.newStub(channel);
        StreamObserver<StudentRequest> studentRequestStreamObserver=stub.getStudentsWrapperByAges(studentResponseListstreamObserver);
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(50).build());
        studentRequestStreamObserver.onCompleted();


        System.out.println("----------------------------");
        StreamObserver<StreamRequest> requestStreamObserver=stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println("服务器返回的:"+value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });
        for(int i=0;i<10;i++){
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            Thread.sleep(1000);
        }
    }
}
