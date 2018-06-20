package application.viewmodel.landing;

import application.viewmodel.common.LayoutHeaderVM;
import application.viewmodel.common.LogoVM;
import application.viewmodel.common.ProductVM;

import java.util.ArrayList;

public class LandingVM extends LayoutHeaderVM {
    private ArrayList<MenuItemVM> listVtMenuItemsAside;
    private ArrayList<BannerVM> listBanners;
    private ArrayList<ProductVM> listProducts;

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
}
