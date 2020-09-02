package crawler2.controller;

import crawler2.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    @Autowired
    AsyncService asyncService;

    @GetMapping("/async")
    public String  testSync(){
        for(int i=0;i<100;i++){
            asyncService.exe(i);
        }
        return "hello";
    }
}
