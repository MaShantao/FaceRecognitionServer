package xin.marico.facerecogition.test;

import com.alibaba.fastjson.JSON;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(24);
        student.setEmail("1205006751@qq.com");
        student.setHomeAddress("山东省济南市");
        student.setName("马善涛");
        student.setSchool("哈尔滨理工大学");
        student.setWeight(135);
        student.setTellphone("110120114");
        String json  = JSON.toJSONString(student);
        System.out.println(json);
        // 数据在传输过程中是以二进制的方式传输
        byte[] bytes = json.getBytes();
        System.out.println(bytes.length);
    }
}
