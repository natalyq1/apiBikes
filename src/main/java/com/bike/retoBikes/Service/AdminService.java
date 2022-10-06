package com.bike.retoBikes.Service;

import com.bike.retoBikes.Model.Admin;
import com.bike.retoBikes.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;

    public List<Admin> getAll() {
        return adminRepo.getAll();
    }

    public Optional<Admin> getAdmin(int adminId) {
        return adminRepo.getAdmin(adminId);
    }

    public Admin save(Admin admin) {
        if (admin.getIdAdmin() == null) {
            return adminRepo.save(admin);
        } else {
            Optional<Admin> admin1 = adminRepo.getAdmin(admin.getIdAdmin());
            if (admin1.isPresent()) {
                return admin;
            } else {
                return adminRepo.save(admin);
            }
        }
    }

     public Admin update(Admin admin){
        if(admin.getIdAdmin()!=null){
            Optional<Admin> g= adminRepo.getAdmin(admin.getIdAdmin());
            if(g.isPresent()){
                if(admin.getName()!=null){
                    g.get().setName(admin.getName());
                }
                if(admin.getEmail()!=null){
                    g.get().setEmail(admin.getEmail());
                }
                if(admin.getPassword()!=null){
                    g.get().setPassword(admin.getPassword());
                }
                return adminRepo.save(g.get());
            }
        }
        return admin;
    }




    public boolean deleteAdmin (int id){
        boolean del = getAdmin(id).map(admin -> {
            adminRepo.delete(admin);
            return true;
        }).orElse(false);
        return del;
    }
}
