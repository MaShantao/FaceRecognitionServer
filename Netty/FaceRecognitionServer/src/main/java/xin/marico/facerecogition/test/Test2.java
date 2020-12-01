package xin.marico.facerecogition.test;

import com.alibaba.fastjson.JSON;

public class Test2 {

    public static void main(String[] args) {
        StudentProto.Student student = StudentProto.Student.
                newBuilder().setAge(24)
                .setEmail("1205006751@qq.com")
                .setHomeAddress("山东省济南市")
                .setName("马善涛")
                .setSchool("哈尔滨理工大学")
                .setWeight(135)
                .setTellphone("120114110").build();
        System.out.println(student);
        // 数据在传输过程中是以二进制的方式传输
        byte[] bytes = student.toByteArray();
        System.out.println(bytes.length);
    }
}
