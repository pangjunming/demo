package com.example.client.domain;

public class Department {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.id
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.deptName
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    private String deptname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.address
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dept.ceo
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    private Integer ceo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.id
     *
     * @return the value of dept.id
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.id
     *
     * @param id the value for dept.id
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.deptName
     *
     * @return the value of dept.deptName
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.deptName
     *
     * @param deptname the value for dept.deptName
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.address
     *
     * @return the value of dept.address
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.address
     *
     * @param address the value for dept.address
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dept.ceo
     *
     * @return the value of dept.ceo
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public Integer getCeo() {
        return ceo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dept.ceo
     *
     * @param ceo the value for dept.ceo
     *
     * @mbggenerated Sun Jul 19 13:57:43 CST 2020
     */
    public void setCeo(Integer ceo) {
        this.ceo = ceo;
    }
}