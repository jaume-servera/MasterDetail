package com.dam2023tfinal.jservera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class gpu implements Serializable {

    // Instance variables
    private String model;
    private String manufacturer;
    private Date launchDate;
    private int memorySize;
    private String memoryType;
    private double coreClockSpeed;
    private double boostClockSpeed;
    private int processingUnits;
    private int TDP;
    private int hdmiNumber;
    private int displayPortNumber;
    private int vgaNumber;
    private int dviNumber;
    private boolean supportRayTracing;
    private int numberOfTransistors;
    private String dimensions;
    private int price;

    private final UUID id = UUID.randomUUID();

    UUID getId() {
        return id;
    }

    public byte[] getImageUrl() {
        return new byte[0];
    }

    // Singleton pattern
    private static class SingletonInstance {
        private static final gpu INSTANCE = new gpu();
    }

    public static gpu getInstance() {
        return SingletonInstance.INSTANCE;
    }

    // Private constructor for Singleton
    gpu() {
    }

    // Getters and Setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getManufacturerIndex() {
        if (manufacturer.equals("Nvidia")) {
            return 0;
        } else if (manufacturer.equals("AMD")) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public int getMemorySizeIndex() {
        if (memorySize == 1) {
            return 0;
        } else if (memorySize == 2) {
            return 1;
        } else if (memorySize == 4) {
            return 2;
        } else if (memorySize == 6) {
            return 3;
        } else if (memorySize == 8) {
            return 4;
        } else if (memorySize == 12) {
            return 5;
        } else if (memorySize == 16) {
            return 6;
        } else if (memorySize == 24) {
            return 7;
        } else if (memorySize == 32) {
            return 8;
        } else if (memorySize == 48) {
            return 9;
        } else if (memorySize == 64) {
            return 10;
        }
        return 0;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public int getMemoryTypeIndex() {
        if (memoryType == "GDDR5")
            return 0;
        else if (memoryType == "GDDR5X")
            return 1;
        else if (memoryType == "GDDR6")
            return 2;
        else if (memoryType == "GDDR6X")
            return 3;

        return 0;

    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public double getCoreClockSpeed() {
        return coreClockSpeed;
    }

    public void setCoreClockSpeed(double coreClockSpeed) {
        this.coreClockSpeed = coreClockSpeed;
    }

    public double getBoostClockSpeed() {
        return boostClockSpeed;
    }

    public void setBoostClockSpeed(double boostClockSpeed) {
        this.boostClockSpeed = boostClockSpeed;
    }

    public int getProcessingUnits() {
        return processingUnits;
    }

    public void setProcessingUnits(int processingUnits) {
        this.processingUnits = processingUnits;
    }

    public int getTDP() {
        return TDP;
    }

    public void setTDP(int TDP) {
        this.TDP = TDP;
    }

    public int getHdmiNumber() {
        return hdmiNumber;
    }

    public void setHdmiNumber(int hdmiNumber) {
        this.hdmiNumber = hdmiNumber;
    }

    public int getDisplayPortNumber() {
        return displayPortNumber;
    }

    public void setDisplayPortNumber(int displayPortNumber) {
        this.displayPortNumber = displayPortNumber;
    }

    public int getVgaNumber() {
        return vgaNumber;
    }

    public void setVgaNumber(int vgaNumber) {
        this.vgaNumber = vgaNumber;
    }

    public int getDviNumber() {
        return dviNumber;
    }

    public void setDviNumber(int dviNumber) {
        this.dviNumber = dviNumber;
    }

    public boolean isSupportRayTracing() {
        return supportRayTracing;
    }

    public void setSupportRayTracing(boolean supportRayTracing) {
        this.supportRayTracing = supportRayTracing;
    }

    public int getNumberOfTransistors() {
        return numberOfTransistors;
    }

    public void setNumberOfTransistors(int numberOfTransistors) {
        this.numberOfTransistors = numberOfTransistors;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // toString method
    @Override
    public String toString() {
        return "gpu{" +
                "model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", launchDate=" + launchDate +
                ", memorySize=" + memorySize +
                ", memoryType='" + memoryType + '\'' +
                ", coreClockSpeed=" + coreClockSpeed +
                ", boostClockSpeed=" + boostClockSpeed +
                ", processingUnits=" + processingUnits +
                ", TDP=" + TDP +
                ", hdmiNumber=" + hdmiNumber +
                ", displayPortNumber=" + displayPortNumber +
                ", vgaNumber=" + vgaNumber +
                ", dviNumber=" + dviNumber +
                ", supportRayTracing=" + supportRayTracing +
                ", numberOfTransistors=" + numberOfTransistors +
                ", dimensions='" + dimensions + '\'' +
                ", price=" + price +
                '}';
    }
}
