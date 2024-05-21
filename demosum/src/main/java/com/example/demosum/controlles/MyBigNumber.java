package com.example.demosum.controlles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.logging.Logger;


@Controller
public class MyBigNumber {

    private static final Logger LOGGER = Logger.getLogger(MyBigNumber.class.getName());

    @GetMapping("/")
    public String showForm() {
        return "sum-form";
    }

    @PostMapping("/sum")
    public String sum(@RequestParam("stn1") String stn1,
                      @RequestParam("stn2") String stn2,
                      Model model) {
        String result = sumStrings(stn1, stn2);
        model.addAttribute("stn1", stn1);
        model.addAttribute("stn2", stn2);
        model.addAttribute("result", result);
        return "sum-result";
    }

    private String sumStrings(String stn1, String stn2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        int i = stn1.length() - 1;
        int j = stn2.length() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = (i >= 0) ? Character.getNumericValue(stn1.charAt(i--)) : 0;
            int digit2 = (j >= 0) ? Character.getNumericValue(stn2.charAt(j--)) : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.insert(0, sum % 10);

            LOGGER.info(String.format("Added digits: %d + %d = %d, Carry: %d", digit1, digit2, sum % 10, carry));
        }

        if (carry > 0) {
            result.insert(0, carry);
            LOGGER.info("Final carry: " + carry);
        }

        return result.toString();
    }
}
