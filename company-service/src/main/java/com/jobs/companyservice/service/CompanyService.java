package com.jobs.companyservice.service;

import com.jobs.companyservice.model.Company;
import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void saveCompany(Company company);
    Company getCompanyById(Long id);
    boolean updateCompany(Long id, Company updatedCompany);
    boolean deleteCompany(Long id);
}

