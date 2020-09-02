package crawler2.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("test")
    public void exe(int i){
        System.out.println(Thread.currentThread().getName()+" hello lp "+i);
    }
}
