package com.microservice.forexexchange.services;

import java.util.List;

import com.microservice.forexexchange.models.ExchangeValue;
import com.microservice.forexexchange.repositories.ExchangeValueRepository;
import com.microservice.forexexchange.response.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ExchangeValueRepository eValueRepository;

    // create new exchanage value
    public ResponseEntity<?> createExchangeValue(ExchangeValue body){
        try{
            // validate if exchnage values currency from and too exist before
            ExchangeValue validateCurrencyFromAndTo = eValueRepository.findByCurrencyFromAndCurrencyTo(body.getCurrencyFrom(), body.getCurrencyTo()).orElse(null);


            if(validateCurrencyFromAndTo != null){
                return ResponseEntity.ok(new ResponseMessage(true, "You have already set an exchange value for the supplied currencies."));
            }

            // save new currency exchange value
            eValueRepository.save(body);

            return new ResponseEntity<>(new ResponseMessage(false, "Currency Exchange created successfully"), HttpStatus.CREATED);
                    

        }catch(Exception ex){
            return ResponseEntity.ok(new ResponseMessage(true, ex.getMessage()));
        }
    }


    // list currency
    public List<ExchangeValue> fetchAllExchangeValues(){
        return eValueRepository.findAll();
    }

    // edit exchange value
    public ResponseEntity<?> editExchangeValue(Long id){
        // validate if exchange value exist
        ExchangeValue validateRecord = eValueRepository.findById(id).orElse(null);

        if(validateRecord == null){
            return ResponseEntity.ok(new ResponseMessage(true, "No exchange record not found.")); 
        }

        return ResponseEntity.ok(validateRecord);

    }


    // update exchange value
    public ResponseEntity<?> updateExchangeValue(Long id, ExchangeValue body){
        try{

            // validate if exchange value exist
            ExchangeValue validateRecord = eValueRepository.findById(id).orElse(null);
    
            if(validateRecord == null){
                return ResponseEntity.ok(new ResponseMessage(true, "No exchange record not found.")); 
            }
    
            // validate if exchange values currency from and too exist before
            ExchangeValue validateCurrencyFromAndTo = eValueRepository.findByCurrencyFromAndCurrencyTo(body.getCurrencyFrom(), body.getCurrencyTo()).orElse(null);
    
    
            if(validateCurrencyFromAndTo != null && validateCurrencyFromAndTo.getId() != id){
                return ResponseEntity.ok(new ResponseMessage(true, "You have already set an exchange value for the supplied currencies."));
            }
    
            // update record
            validateRecord.setCurrencyFrom(body.getCurrencyFrom());
            validateRecord.setCurrencyTo(body.getCurrencyTo());
            validateRecord.setConversionMultiple(body.getConversionMultiple());
    
            eValueRepository.save(validateRecord);
    
            return ResponseEntity.ok(new ResponseMessage(false, "Exchange value updated successfully."));
        }catch(Exception ex){
            return ResponseEntity.ok(new ResponseMessage(true, ex.getMessage()));   
        }



    }


    // delete exchange value record
    public ResponseEntity<?> deleteExchangeValueRecord(Long id){
        try{
            // validate if exchange value exist
            ExchangeValue validateRecord = eValueRepository.findById(id).orElse(null);

            if(validateRecord == null){
                return ResponseEntity.ok(new ResponseMessage(true, "No exchange record not found.")); 
            }

            eValueRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseMessage(false, "Exchange value record deleted successfully.")); 
        }catch(Exception ex){
            return ResponseEntity.ok(new ResponseMessage(true, ex.getMessage())); 
        }
    }
}