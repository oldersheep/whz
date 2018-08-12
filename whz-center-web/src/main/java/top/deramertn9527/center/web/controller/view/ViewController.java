package top.deramertn9527.center.web.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * 视图页面统一 controller
 *
 */
@Controller
@Slf4j
public class ViewController {

    @GetMapping("/")
    public String view() {
        return "redirect:/view/system/index";
    }

    /**
     * utl: /view/folder/page
     * <p>
     * 使用通配符，第一个路径为vm对应的文件夹名称，第二个路径为vm对应的模板名称
     *
     * @param folder 文件目录
     * @param page   页面名称
     * @return String
     */
    @GetMapping("/view/{folder}/{page}")
    public ModelAndView view(@PathVariable String folder, @PathVariable String page) {
        log.info("ViewController-view, folder: {}, page: {}", folder, page);
        return new ModelAndView("/" + folder + "/" + page);
    }
}
