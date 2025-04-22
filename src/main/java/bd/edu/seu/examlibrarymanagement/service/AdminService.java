package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.Admin;
import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin getByEmail(String email) {
        Optional<Admin> optional = adminRepository.findByEmail(email);
        return optional.orElse(null);

    }




}
