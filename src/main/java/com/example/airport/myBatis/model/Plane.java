package com.example.airport.myBatis.model;

public class Plane {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLANE.ID
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLANE.CAPACITY
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    private Integer capacity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLANE.MODEL
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    private String model;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.PLANE.AIRLINE_ID
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    private Long airlineId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLANE.ID
     *
     * @return the value of PUBLIC.PLANE.ID
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLANE.ID
     *
     * @param id the value for PUBLIC.PLANE.ID
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLANE.CAPACITY
     *
     * @return the value of PUBLIC.PLANE.CAPACITY
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLANE.CAPACITY
     *
     * @param capacity the value for PUBLIC.PLANE.CAPACITY
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLANE.MODEL
     *
     * @return the value of PUBLIC.PLANE.MODEL
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public String getModel() {
        return model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLANE.MODEL
     *
     * @param model the value for PUBLIC.PLANE.MODEL
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.PLANE.AIRLINE_ID
     *
     * @return the value of PUBLIC.PLANE.AIRLINE_ID
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public Long getAirlineId() {
        return airlineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.PLANE.AIRLINE_ID
     *
     * @param airlineId the value for PUBLIC.PLANE.AIRLINE_ID
     *
     * @mbg.generated Wed Mar 27 09:59:42 EET 2024
     */
    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }
}