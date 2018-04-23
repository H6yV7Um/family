package org.yxyqcy.family.common.validate;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.yxyqcy.family.common.message.ResponseMessage;
import org.yxyqcy.family.common.message.ValidatedMessage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author  cy
 * Created by cy on 16/5/20.
 */
public class ValidatedResult {
    private Boolean hasErrors = Boolean.valueOf(false);
    private Map<String, String[]> errorsMap;
    private BindingResult br;

    public ValidatedResult() {
    }

    public Boolean hasErrors() {
        return this.hasErrors;
    }

    public Map<String, String[]> getErrorsMap() {
        return this.errorsMap;
    }

    public BindingResult getBr() {
        return this.br;
    }

    public Map<String, String[]> dealBindingResult(BindingResult br) {
        this.br = br;
        this.errorsMap = new HashMap();
        if(br.hasErrors()) {
            this.hasErrors = Boolean.valueOf(true);
            List errorlist = br.getFieldErrors();
            Iterator var3 = errorlist.iterator();

            while(var3.hasNext()) {
                FieldError fieldError = (FieldError)var3.next();
                this.errorsMap.put(fieldError.getField(), new String[]{fieldError.getDefaultMessage(), fieldError.getCode()});
            }
        }

        return this.errorsMap;
    }

    public ResponseMessage getValidatedMessage() {
        ValidatedMessage vm = new ValidatedMessage();
        vm.setStatus("2");
        vm.setValidmessage(this.errorsMap);
        return vm;
    }
}
