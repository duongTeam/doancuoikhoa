package application.data.service;

import application.constant.RoleIdConstant;
import application.constant.StatusRegisterUserEnum;
import application.constant.StatusRoleConstant;
import application.data.model.Role;
import application.data.model.User;
import application.data.model.UserRole;
import application.data.repository.RoleRepository;
import application.data.repository.UserRepository;
import application.data.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StatusRegisterUserEnum registerNewUser(User user) {
        logger.info("Start RegisterNewUser");
        try {
            //kiem tra danh sach user da ton tai = username
            if(findUserByUsername(user.getUsername()) != null) {
                return StatusRegisterUserEnum.Existed_Username;
            }

            //kiem tra danh sach user da ton tai = email
            if(findUserByEmail(user.getEmail()) != null) {
                return StatusRegisterUserEnum.Existed_Email;
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedDate(new Date());

            //luu lai thong tin user
            userRepository.save(user);

            UserRole userRole = new UserRole();
            userRole.setRoleId(RoleIdConstant.Role_User);
            userRole.setUserId(user.getId());
            userRole.setStatus(StatusRoleConstant.ActiveStatus);

            userRoleRepository.save(userRole);

            return StatusRegisterUserEnum.Success;
        }catch (Exception e) {
            logger.info(e.getMessage());
            return StatusRegisterUserEnum.Error_OnSystem;
        }
    }

    //Lay ra danh sach cac Role
    public List<Role> getListRole() {
        return StreamSupport
                .stream(roleRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    //Tim kiem user bang username
    public User findUserByUsername(String username) {
        return StreamSupport
                .stream(userRepository.findByUsername(username).spliterator(),false)
                .findFirst().orElse(null);
    }

    //Tim kiem user bang email
    public User findUserByEmail(String email) {
        return StreamSupport
                .stream(userRepository.findByEmail(email).spliterator(),false)
                .findFirst().orElse(null);
    }

    //Lay ra List cac Role dang duoc su dung
    public List<Role> getActiveListRole(int userId) {
        List<UserRole> listUserRoles = StreamSupport
                .stream(userRoleRepository.findRolesOfUser(userId).spliterator(),false).filter(
                        userRole -> userRole.getStatus() == StatusRoleConstant.ActiveStatus
                ).collect(Collectors.toList());
        return getListRole().stream().filter(role -> {
            return (listUserRoles.stream().filter(userRole -> userRole.getRoleId() ==  role.getId()).findFirst().orElse(null) != null);
        }).collect(Collectors.toList());
    }

}

