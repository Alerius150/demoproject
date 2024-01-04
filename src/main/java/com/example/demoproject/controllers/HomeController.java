package com.example.demoproject.controllers;


import com.example.demoproject.beans.FirstBean;
import com.example.demoproject.beans.TestBean;
import com.example.demoproject.beans.ThirdBean;
import com.example.demoproject.entities.Categories;
import com.example.demoproject.entities.Countries;
import com.example.demoproject.entities.ShopItems;
import com.example.demoproject.entities.Users;
import com.example.demoproject.services.ItemService;
import com.example.demoproject.services.TestService;
import com.example.demoproject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private TestBean testBean;

    @Autowired
    private FirstBean firstBeanBek;

    @Autowired
    private FirstBean secondBean;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ThirdBean thirdBean;

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    private String fullName = "Almein";

    @GetMapping(value="/index")
    public String index(Model model){
        model.addAttribute("currentUser", getUserData());


        List<ShopItems> items= itemService.getAllItems();

        model.addAttribute("tovary", items);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);
        return "index";
    }

    @GetMapping(value="/about")
    public String about(Model model){
        model.addAttribute("currentUser", getUserData());

        return "about";
    }

    @PostMapping(value="/additem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(@RequestParam(name="country_id", defaultValue = "0") Long countryId  ,
                          @RequestParam(name="item_name", defaultValue = "No Item!!!") String name,
                          @RequestParam(name="item_price", defaultValue = "0") int price,
                          @RequestParam(name="item_amount", defaultValue = "0") int amount) {

        Countries cnt = itemService.getCountry(countryId);

        if(cnt!=null) {
            ShopItems item = new ShopItems();
            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            item.setCountry(cnt);

            itemService.addItem(item);

        }
        return "redirect:/index";
    }

    @GetMapping(value="/details/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String details(Model model, @PathVariable(name="id") Long id){

        model.addAttribute("currentUser", getUserData());

        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "details";
    }

    @GetMapping(value="/edititem/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String editItem(Model model, @PathVariable(name="id") Long id){

        model.addAttribute("currentUser", getUserData());

        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "edititem/" + id;
    }


    @PostMapping(value="/saveitem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String saveItem(@RequestParam(name="id", defaultValue = "0") Long id,
                           @RequestParam(name="country_id", defaultValue = "0") Long country_id,
                           @RequestParam(name="item_name", defaultValue = "No item") String name,
                           @RequestParam(name="item_price", defaultValue = "0") int price,
                           @RequestParam(name="item_amount", defaultValue = "0") int amount){

        ShopItems item = itemService.getItem(id);
        if(item!=null){
            Countries cnt = itemService.getCountry(id);


            if(cnt!=null){
                item.setName(name);
                item.setAmount(amount);
                item.setPrice(price);
                itemService.saveItem(item);
            }
        }


        return "redirect:/edititem/" + id;




    }
    @PostMapping(value="/deleteitem")
    public String deleteItem(@RequestParam(name="id", defaultValue = "0") Long id){

        ShopItems item = itemService.getItem(id);
        if(item!=null){
            itemService.deleteItem(item);

        }

        return "redirect:/index";

    }

    @PostMapping(value = "/assigncategory")
    public String assignCategory(@RequestParam(name="id") Long itemId,
                                 @RequestParam(name="category_id") Long categoryId){
        Categories cat = itemService.getCategory(categoryId);
        if(cat!=null){
            ShopItems item = itemService.getItem(itemId);
            if(item!=null){
                List<Categories> categories = item.getCategories();
                if(categories==null){
                    categories = new ArrayList<>();


                }
                categories.add(cat);


                itemService.saveItem(item);

                return "redirect:/edititem/" + itemId+ "#categoriesDiv";

            }

        }
        return "redirect:/";
    }

    @GetMapping(value="/403")
    public String accessDenied(Model model){
        model.addAttribute("currentUser", getUserData());
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("currentUser", getUserData());
        return "login";
    }

    @GetMapping(value="/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    @GetMapping(value="/additem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(Model model){
        model.addAttribute("currentUser", getUserData());
        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        return "additem";
    }

    @GetMapping(value="/register")
    public String register(Model model){
        model.addAttribute("currentUser", getUserData());


        return "register";
    }
    @PostMapping(value="/register")
    public String toRegister(@RequestParam(name="user_email") String email,
                             @RequestParam(name="user_password") String password,
                             @RequestParam(name="re_user_password") String rePassword,
                             @RequestParam(name="user_full_name") String fullName){
        if(fullName==null){
            return "redirect:/register?success";
        }


        if(password.equals(rePassword)){
            Users newUser = new Users();
            newUser.setFullName(fullName);
            newUser.setPassword(password);
            newUser.setEmail(email);
            if(userService.createUser(newUser)!=null){
                return "redirect:/register?success";
            }

            return "redirect:/403";

        }
        return "redirect:/register";
    }

    private Users getUserData(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            User secUser = (User)auth.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;

    }






}
