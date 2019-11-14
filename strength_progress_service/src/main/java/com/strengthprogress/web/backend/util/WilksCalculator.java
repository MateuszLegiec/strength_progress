package com.strengthprogress.web.backend.util;

import com.strengthprogress.web.backend.model.Gender;

public class WilksCalculator {

    private final static double MALE_A = -216.0475144;
    private final static double MALE_B = 16.2606339;
    private final static double MALE_C = -0.002388645;
    private final static double MALE_D = -0.00113732;
    private final static double MALE_E = 7.01863E-06;
    private final static double MALE_F = -1.291E-08;

    private final static double FEMALE_A = 594.31747775582;
    private final static double FEMALE_B = -27.23842536447;
    private final static double FEMALE_C = 0.82112226871;
    private final static double FEMALE_D = -0.00930733913;
    private final static double FEMALE_E = 4.731582E-05;
    private final static double FEMALE_F = -9.054E-08;

    public static double calculate(double weight,double bodyWeight, Gender gender){
        return weight *coeff(gender,bodyWeight);
    }

    private static double coeff(Gender gender, double bw){
        switch (gender){
            case MALE:
                return 500 / (MALE_A + MALE_B * bw + MALE_C * Math.pow(bw,2) + MALE_D * Math.pow(bw,3) + MALE_E * Math.pow(bw,4) + MALE_F * Math.pow(bw,5));
            case FEMALE:
                return 500 / (FEMALE_A + FEMALE_B * bw + FEMALE_C * Math.pow(bw,2) + FEMALE_D * Math.pow(bw,3) + FEMALE_E * Math.pow(bw,4) + FEMALE_F * Math.pow(bw,5));
            default:
                return 0;
        }
    }
}
