package hello.web;

import harbour.spring.dao.TodoDao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private TodoDao todoDao;
    
    @RequestMapping("/hello")
    public String hello(Model model) {
        Date now = new Date();
        logger.info("my call hello to get serverTime [{}]", now);
        model.addAttribute("serverTime", now);
        return "hello";
    }
    
}
