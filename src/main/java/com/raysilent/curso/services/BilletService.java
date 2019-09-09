package com.raysilent.curso.services;

import com.raysilent.curso.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService {

    public void fillBilletPayment(BilletPayment pmnt, Date requestInstant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(requestInstant);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pmnt.setExpirationDate(cal.getTime());
    }
}
