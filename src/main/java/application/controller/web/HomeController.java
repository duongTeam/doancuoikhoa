package application.controller.web;

import application.constant.StatusRegisterUserEnum;
import application.data.model.*;
import application.data.service.CategoryService;
import application.data.service.NewService;
import application.data.service.ProductService;
import application.data.service.UserService;
import application.model.*;
import application.viewmodel.common.ProductVM;
import application.viewmodel.landing.BannerVM;
import application.viewmodel.landing.LandingVM;
import application.viewmodel.landing.MenuItemVM;
import application.viewmodel.user.UserVM;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(path = "/")
public class HomeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private NewService newService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(path = "admin", method = RequestMethod.GET)
    public String admin(Model model, @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

        return "admin";
    }

    @GetMapping(path="/list-products/{catId}")
    public String listProducts(Model model, @PathVariable int catId) {

        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);
        ArrayList<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/10/may-chay-bo.jpg","Hah"));
        listBanners.add(new BannerVM("https://sportonline.com.vn/wp-content/uploads/2017/10/may-chay-bo-phong-gym.jpg","Hah"));

        Category existedCategory = categoryService.findbyId(catId);

        try {
            ModelMapper modelMapper = new ModelMapper();

            CategoryProductModel categoryProductModel = modelMapper.map(existedCategory,CategoryProductModel.class);
            model.addAttribute("cat",categoryProductModel);
//                model.addAttribute("paginableItem",productService.getListProducts(pageSize,pageNumber));

        }catch (Exception e) {
            e.printStackTrace();
        }

        vm.setListBanners(listBanners);
        model.addAttribute("vm",vm);
        return "list-products";
    }

    @GetMapping(path = "/")
    public String index(Model model,  HttpServletResponse response,
                        @RequestHeader("User-Agent") String userAgent,
                        HttpServletRequest request,
                        final Principal principal) {
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
        listVtMenuItems.add(new MenuItemVM("DANH MỤC SẢN PHẨM","/"));
        listVtMenuItems.add(new MenuItemVM("Máy chạy bộ điện","/"));
        listVtMenuItems.add(new MenuItemVM("Xe đạp tập thể dục","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ thể hình","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ yoga- thẩm mỹ","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ bơi lội","/"));
        listVtMenuItems.add(new MenuItemVM("Giày thể thao","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ võ thuật","/"));

        List<Category> listCategories = categoryService.getListAllCategories();

        vm.setCategoryList(listCategories);

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();

        User listUsers = userService.findUserByUsername(username);
        vm.setUser(listUsers);

        model.addAttribute("vm", vm);
        return "index";
    }

    @GetMapping(path = "/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping(path = "/news")
    public String news(Model model){
        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);
        model.addAttribute("vm",vm);
        ArrayList<New> news = newService.getAll();
        model.addAttribute("news",news);
        return "news";
    }

    @GetMapping(path = "/user/{userId}")
    public String memberProfile(Model model, @PathVariable int userId) {
        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);

        User existedUser = userService.findUserById(userId);
        try {
            ModelMapper modelMapper = new ModelMapper();

            UserDetailModel userDetailModel = modelMapper.map(existedUser,UserDetailModel.class);
            model.addAttribute("user",userDetailModel);
//                model.addAttribute("paginableItem",productServicse.getListProducts(pageSize,pageNumber));

        }catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("vm",vm);
        return "profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        LandingVM vm = new LandingVM();
        this.setLayoutHeaderVM(vm);
        model.addAttribute("vm", vm);
        return "login";
    }

}
