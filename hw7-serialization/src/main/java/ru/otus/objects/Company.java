package ru.otus.objects;

import java.util.List;

public class Company {
    private String name;
    private String address;
    private int companyId;
    private List<String> employees;
    private Integer[] departmentIds;

    public Company(int companyId, String name, String address, List<String> employees, Integer[] departmentIds) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.departmentIds = departmentIds;
    }
}
