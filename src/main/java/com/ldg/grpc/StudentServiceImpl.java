package com.ldg.grpc;

import com.ldg.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

/**
 * Created by LiuDongguang on 2017/8/2.
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase{
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息："+request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    /**
     * 服务器端的流形式，可以看做集合
     * @param request
     * @param responseObserver
     */
    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息："+request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(request.getAge()).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(request.getAge()).setCity("济南").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(request.getAge()).setCity("淄博").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(request.getAge()).setCity("青岛").build());
        responseObserver.onCompleted();
    }

    /**
     * 客户端流，服务端普通
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println("on next:"+value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted...");
                StudentResponse studentResponse1=StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build();
                StudentResponse studentResponse2=StudentResponse.newBuilder().setName("李四").setAge(30).setCity("深圳").build();
                StudentResponseList studentResponseList=StudentResponseList.newBuilder().addStudentResponse(studentResponse1).addStudentResponse(studentResponse2).build();
                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                  responseObserver.onCompleted();
            }
        };
    }
}
