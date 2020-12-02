package se.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.web.store.dao.AdminUserDAO;
import se.web.store.entity.AdminUser;
import se.web.store.security.PasswordGenerator;
import se.web.store.service.MyConversionService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminController {

    @Autowired
    AdminUserDAO adminUserDAO;

    private PasswordGenerator generator = new PasswordGenerator();

    @GetMapping
    public String getAdmin() {
        return "admin/admin.html";
    }

    /** ADMIN-USER - ADMIN LIST (admin-user-list.html) **/
    @GetMapping("adminuserlist")
    public String getAllAdminUsers(Model model) {
        List<AdminUser> adminUserList = adminUserDAO.findAll();
        model.addAttribute("adminuserlist", adminUserList);

        return "admin/admin-user-list.html";
    }

    /** ADMIN-USER - ADD NEW USER FORM (admin-user-form.html)**/
    @GetMapping("addadminuser")
    public String addAdminUser(Model model) {
        AdminUser adminUser = new AdminUser();
        model.addAttribute("saveAdminUser", adminUser);

        return "admin/admin-user-form.html";
    }

    @PostMapping("addadminuser/save")
    public String saveAdminUser(@ModelAttribute("saveAdminUser") AdminUser adminUser ) {
        // Get raw password and crypt
        String cryptPassword = generator.passwordGenerator(adminUser.getPassword());

        AdminUser newAdminUser = new AdminUser(
                adminUser.getUsername(),
                cryptPassword,
                adminUser.getRole(),
                true
        );

        adminUserDAO.save(newAdminUser);

        return "redirect:/admin/adminuserlist";
    }

    /** ADMIN-USER - UPDATE ADMIN USER **/
    @PostMapping(value = "adminuser/update")
    public String updateAdminUser(@RequestParam("adminUserId") Integer id, Model model ) {
        AdminUser adminUser = getUserObject(id);
        model.addAttribute("adminUserUpdate", adminUser);

        return "admin/admin-user-update.html";
    }

    @PostMapping("adminuser/update/save")
    public String updateAdminUserSave(
            @ModelAttribute("adminUserUpdate") AdminUser adminUser,
            @RequestParam("adminId") Integer id ) {

        AdminUser updateAdminUser = getUserObject(id);
        updateAdminUser.setUsername(adminUser.getUsername());
        updateAdminUser.setPassword(generator.passwordGenerator(adminUser.getPassword()));
        updateAdminUser.setRole(adminUser.getRole());
        updateAdminUser.setEnabled(true);

        adminUserDAO.save(updateAdminUser);

        return "redirect:/admin/adminuserlist";
    }

    /** ADMIN-USER - DELETE USER **/
    @GetMapping("adminuser/delete")
    public String deleteAdminUser(@RequestParam("adminUserId") Integer id ) {
        adminUserDAO.deleteById(id);

        return "redirect:/admin/adminuserlist";
    }

    /** OTHER **/
    /** Conversion  **/

    /** Converter Optional to AdminUser Object **/

    public AdminUser getUserObject(Integer id) {
        AdminUser user = null;

        Optional<AdminUser> userList = adminUserDAO.findById(id);
        if ( userList.isPresent() ) {
            user = userList.get();
        }
        return user;
    }


}
