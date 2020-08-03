package com.example.layuitest.controller;

import com.example.layuitest.dao.TestMapper;
import com.example.layuitest.domain.test_table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/index")
    public String welcome(){
        return "index";
    }

    @RequestMapping("/main")
    public String goToMain(){
        return "main";
    }

    @RequestMapping("/second")
    public String goToSecond(){
        return "second";
    }

    @RequestMapping("/openPage")
    public String goToOpen(){
        return "openPage";
    }

    @RequestMapping("/mybatisPratice")
    public String mybatisPratice(){
        return "mybatisPratice";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<Map<String,String>> getData() {
        List<test_table> list = testMapper.selectAll();
        List<Map<String,String>> dataList = new ArrayList<>();
        for(int i = 0; i< list.size(); i++){
            Map<String,String> data = new HashMap<>();
            test_table current = list.get(i);
            if(i == 0){
                data.put("menuId",String.valueOf(current.getId()));
                data.put("parentId","0");
                data.put("name",current.getName());
                data.put("id",String.valueOf(current.getId()));
            }else{
                data.put("menuId",String.valueOf(current.getId()));
                data.put("parentId","1");
                data.put("name",current.getName());
                data.put("id",String.valueOf(current.getId()));
            }
            dataList.add(data);
        }
        return dataList;
    }

    @RequestMapping("/helloworld")
    @ResponseBody
    public String hello(){
        return "hello world";
    }
}
