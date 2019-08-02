package View;

import javax.swing.*;
import java.awt.*;

public class InfoBean {
    private String startCity;
    private String[] passCity;
    private int caseNumber;

    public int getCaseNumber() {
        return caseNumber;
    }

    public String getStartCity() {
        return startCity;
    }

    public String[] getPassCity() {
        return passCity;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public void setPassCity(String[] passCity) {
        this.passCity = passCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }
}
