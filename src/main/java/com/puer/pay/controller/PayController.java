package com.puer.pay.controller;

import com.puer.pay.dto.PayResultGetDto;
import com.puer.pay.dto.PayTransationConfirmDTO;
import com.puer.pay.dto.PreOrderDto;
import com.puer.pay.service.PayService;
import com.puer.pay.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author shenggongjie
 * @date 2021/7/2 14:36
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/pay")
@CrossOrigin
public class PayController {

    private final PayService payService;

    @PostMapping("/payConfirmMini")
    public PayInfoVo payConfirm(@RequestBody PayTransationConfirmDTO dto){
        return payService.payConfirm(dto);
    }

    @PostMapping("/preOrder")
    public PooulResponse<PreOrderVo> preOrder(@RequestBody PreOrderDto dto){
        return payService.preOrder(dto);
    }

    @PostMapping("/paidNotify")
    public String paidNotify(@RequestBody String data, HttpServletRequest request){
        PooulResponse<PayNotifyResponse> response = payService.paidNotify(data);
        int code = response.getCode();
        if (0 == code) {
            request.getServletContext().setAttribute(response.getData().getMch_trade_id(),0);
            return "success";
        }
        return "fail";
    }

    @PostMapping("/payResult")
    public Integer payResult(@RequestBody PayResultGetDto dto, HttpServletRequest request){
        String merchTradeId = dto.getMerchTradeId();
        ServletContext servletContext = request.getServletContext();
        Object result = servletContext.getAttribute(merchTradeId);
        if (Objects.nonNull(result)) {
            servletContext.removeAttribute(merchTradeId);
            return (Integer) result;
        }
        return 1;
    }
}
