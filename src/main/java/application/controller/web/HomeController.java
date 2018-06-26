package application.controller.web;

import application.constant.StatusRegisterUserEnum;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.model.Role;
import application.data.model.User;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.data.service.UserService;
import application.viewmodel.common.ProductVM;
import application.viewmodel.landing.BannerVM;
import application.viewmodel.landing.LandingVM;
import application.viewmodel.landing.MenuItemVM;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @PreAuthorize("hasAnyRole('ADMIN')")
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
        listVtMenuItems.add(new MenuItemVM("DANH MỤC SẢN PHẨM","/"));
        listVtMenuItems.add(new MenuItemVM("Máy chạy bộ điện","/"));
        listVtMenuItems.add(new MenuItemVM("Xe đạp tập thể dục","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ thể hình","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ yoga- thẩm mỹ","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ bơi lội","/"));
        listVtMenuItems.add(new MenuItemVM("Giày thể thao","/"));
        listVtMenuItems.add(new MenuItemVM("Dụng cụ võ thuật","/"));

        PaginableItemList<Product> paginableItemListHot = productService.getListProducts(3,0);
        ArrayList<ProductVM> listHotProductVMs = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Product product : paginableItemListHot.getListData()) {
            ProductVM productVM = modelMapper.map(product,ProductVM.class);
            listHotProductVMs.add(productVM);
        }

        PaginableItemList<Product> paginableItemListTrend = productService.getListProducts(2,1);
        ArrayList<ProductVM> listTrendProductVMs = new ArrayList<>();
        for(Product product : paginableItemListHot.getListData()) {
            ProductVM productVM = modelMapper.map(product,ProductVM.class);
            listTrendProductVMs.add(productVM);
        }

        PaginableItemList<Product> paginableItemListNew = productService.getListProducts(4,0);
        ArrayList<ProductVM> listNewProductVms = new ArrayList<>();
        for (Product product : paginableItemListNew.getListData()) {
            ProductVM productVM = modelMapper.map(product,ProductVM.class);
            listNewProductVms.add(productVM);
        }

        vm.setListBanners(listBanners);
        vm.setListVtMenuItemsAside(listVtMenuItems);
        vm.setListHotProducts(listHotProductVMs);
        vm.setListTrendProducts(listTrendProductVMs);
        vm.setListNewProducts(listNewProductVms);

        model.addAttribute("vm", vm);

        return "index";
    }



    @GetMapping(path = "/home")
    public String home(Model model) {
        List<Role> listRoles = userService.getListRole();
        model.addAttribute("listRoles", listRoles);
        return "/home";
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
        return "redirect:/home";
    }
}
