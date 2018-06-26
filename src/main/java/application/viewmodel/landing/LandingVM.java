package application.viewmodel.landing;

import application.viewmodel.common.LayoutHeaderVM;
import application.viewmodel.common.LogoVM;
import application.viewmodel.common.ProductVM;

import java.util.ArrayList;

public class LandingVM extends LayoutHeaderVM {
    private LogoVM logo;
    private ArrayList<MenuItemVM> listHrMenuItems;
    private ArrayList<MenuItemVM> listVtMenuItemsAside;
    private ArrayList<BannerVM> listBanners;
    private ArrayList<ProductVM> listProducts;
    private ArrayList<ProductVM> listHotProducts;
    private ArrayList<ProductVM> listTrendProducts;
    private ArrayList<ProductVM> listNewProducts;

    public LogoVM getLogo() {
        return logo;
    }

    public void setLogo(LogoVM logo) {
        this.logo = logo;
    }

    public ArrayList<MenuItemVM> getListHrMenuItems() {
        return listHrMenuItems;
    }

    public void setListHrMenuItems(ArrayList<MenuItemVM> listHrMenuItems) {
        this.listHrMenuItems = listHrMenuItems;
    }

    public ArrayList<MenuItemVM> getListVtMenuItemsAside() {
        return listVtMenuItemsAside;
    }

    public void setListVtMenuItemsAside(ArrayList<MenuItemVM> listVtMenuItemsAside) {
        this.listVtMenuItemsAside = listVtMenuItemsAside;
    }

    public ArrayList<BannerVM> getListBanners() {
        return listBanners;
    }

    public void setListBanners(ArrayList<BannerVM> listBanners) {
        this.listBanners = listBanners;
    }

    public ArrayList<ProductVM> getListProducts() {
        return listProducts;
    }

    public void setListProducts(ArrayList<ProductVM> listProducts) {
        this.listProducts = listProducts;
    }

    public ArrayList<ProductVM> getListHotProducts() {
        return listHotProducts;
    }

    public void setListHotProducts(ArrayList<ProductVM> listHotProducts) {
        this.listHotProducts = listHotProducts;
    }

    public ArrayList<ProductVM> getListTrendProducts() {
        return listTrendProducts;
    }

    public void setListTrendProducts(ArrayList<ProductVM> listTrendProducts) {
        this.listTrendProducts = listTrendProducts;
    }

    public ArrayList<ProductVM> getListNewProducts() {
        return listNewProducts;
    }

    public void setListNewProducts(ArrayList<ProductVM> listNewProducts) {
        this.listNewProducts = listNewProducts;
    }
}
