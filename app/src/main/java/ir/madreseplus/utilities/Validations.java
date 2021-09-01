package ir.madreseplus.utilities;

import android.text.Editable;
import android.text.TextUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    public boolean phoneNumber(String phoneNumber) {
        return phoneNumber.length() == 11 && phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '9';
    }

    public boolean confirmCode(String confirmCode) {
        return confirmCode.length() == 4;

    }

    public boolean userName(String userName) {
        return userName.length() > 1;

    }

    public boolean familyName(String familyName) {
        return familyName.length() > 1;

    }

    public boolean NationalCode(String input) {
        ArrayList<String> invalidCods = new ArrayList<String>(Arrays.asList("0000000000", "1111111111", "22222222222", "33333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"));
        if (!input.matches("^\\d{10}$"))
            return false;
        if (invalidCods.contains(input))
            return false;


        int sum = 0;
        for (int i = 1; i < 10; i++) {
            int index = 9 - i;
            sum += (i + 1) * Integer.parseInt(input.substring(index, index + 1));
        }

        int check = Integer.parseInt(input.substring(9, 10));
        int sume = sum % 11;

        return (sume < 2 && check == sume) || (sume >= 2 && check + sume == 11);

    }

    public boolean isRTLlanguage(String s) {
        Pattern RTL_CHARACTERS =
                Pattern.compile("[\u0600-\u06FF\u0750-\u077F\u0590-\u05FF\uFE70-\uFEFF]");
        Matcher matcher = RTL_CHARACTERS.matcher(s);
        // it's RTL
        return matcher.find();

    }

    public boolean Email(CharSequence target, boolean isOptional) {
        if (!TextUtils.isEmpty(target)) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
        return isOptional;

    }

    public boolean LockNumber(Editable text) {
        return text.length() == 9;
    }

    public boolean sheba(String sheba) {
        BigInteger aLong = null;
        try {
            aLong = new BigInteger(sheba.substring(2, 24));
            aLong = aLong.multiply(new BigInteger("1000000"));
            BigInteger aLong1 = new BigInteger(sheba.substring(0, 2));
            aLong1 = aLong1.add(BigInteger.valueOf(182700));
            aLong = aLong.add(aLong1);
            if (aLong.mod(BigInteger.valueOf(97)).equals(BigInteger.valueOf(1)))
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean isValidSheba(String sheba) {
        if (sheba != null && Pattern.matches("\\d{24}", sheba)) {
            try {
                BigInteger value = new BigInteger(sheba.substring(2,sheba.length()));
                value = value.multiply(BigInteger.valueOf(1000000L));
                BigInteger suffix = BigInteger.valueOf(182700L).add(new BigInteger(sheba.substring(0, 2)));
                value = value.add(suffix);
                return value.mod(BigInteger.valueOf(97)).equals(BigInteger.ONE);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
