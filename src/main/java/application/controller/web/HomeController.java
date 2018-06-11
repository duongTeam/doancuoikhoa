package application.controller.web;

import application.constant.StatusRegisterUserEnum;
import application.data.model.User;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.data.service.UserService;
import application.viewmodel.landing.BannerVM;
import application.viewmodel.landing.LandingVM;
import application.viewmodel.landing.MenuItemVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/")
public class HomeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "admin", method = RequestMethod.GET)
    public String admin(Model model, @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

        return "admin";
    }

    @GetMapping(path = "/")
    public String index(Model model,  HttpServletResponse response,
                        @RequestHeader("User-Agent") String userAgent,
                        HttpServletRequest request) {
        response.addCookie(new Cookie("current-page", "Cookie from Java code - Home Landing"));
        System.out.println("===========");
        System.out.println(userAgent);
        System.out.println("IP: " + request.getRemoteAddr());

        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);

        ArrayList<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/08/BANER-1-min.jpg","Hah"));
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/08/BANER-1-min.jpg","Hah"));
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/08/BANER-3.jpg","Hah"));

        ArrayList<MenuItemVM> listVtMenuItems = new ArrayList<>();
        return "index";
    }

    @GetMapping(path = "/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping(path = "/memberProfile")
    public String memberProfile() {
        return "profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "/register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute("user")User user, BindingResult result) {
        StatusRegisterUserEnum statusRegisterUserEnum = userService.registerNewUser(user);
        logger.info(statusRegisterUserEnum.toString());
        return "redirect:/";
    }
}
