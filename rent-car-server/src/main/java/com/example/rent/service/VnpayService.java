package com.example.rent.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.rent.DTO.PaymentDTO;

@Service
public class VnpayService {
	@Value("${vnpay.tmncode}")
	private String vnp_TmnCode;

	@Value("${vnpay.hashsecret}")
	private String vnp_HashSecret;

	@Value("${vnpay.returnurl}")
	private String vnp_ReturnUrl;

	public String createPaymentRequest(PaymentDTO paymentDTO) throws UnsupportedEncodingException {
		String vnp_Amount = String.valueOf(paymentDTO.getAmount() * 100);
		String vnp_Command = "pay";
		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		String vnp_CurrCode = "VND";
		String vnp_IpAddr = "127.0.0.1";
		String vnp_Locale = "vn";
		String vnp_OrderInfo = "Thanh toan don hang: " + paymentDTO.getOrderCode();
		String vnp_TxnRef = vnp_TmnCode + "-" + paymentDTO.getOrderCode() + "-" + vnp_CreateDate;
		String vnp_Version = "2.1.0";
		String bankCode = paymentDTO.getBankCode();

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Amount", vnp_Amount);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
		vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
		vnp_Params.put("vnp_Locale", vnp_Locale);
		vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
		if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		String Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = Url + "?" + queryUrl;
		return paymentUrl;
	}
	public boolean verifyPaymentResponse(Map<String, String> responseParams) throws Exception {
        String vnp_SecureHash = responseParams.get("vnp_SecureHash");
        if (vnp_SecureHash == null) {
            throw new Exception("Missing vnp_SecureHash parameter");
        }

        String vnp_TxnRef = responseParams.get("vnp_TxnRef");
        if (vnp_TxnRef == null) {
            throw new Exception("Missing vnp_TxnRef parameter");
        }

        String vnp_Amount = responseParams.get("vnp_Amount");
        if (vnp_Amount == null) {
            throw new Exception("Missing vnp_Amount parameter");
        }

        String vnp_ResponseCode = responseParams.get("vnp_ResponseCode");
        if (vnp_ResponseCode == null) {
            throw new Exception("Missing vnp_ResponseCode parameter");
        }

        String vnp_TransactionNo = responseParams.get("vnp_TransactionNo");
        if (vnp_TransactionNo == null) {
            throw new Exception("Missing vnp_TransactionNo parameter");
        }

        String vnp_TmnCode = responseParams.get("vnp_TmnCode");
        if (vnp_TmnCode == null) {
            throw new Exception("Missing vnp_TmnCode parameter");
        }

        String vnp_BankCode = responseParams.get("vnp_BankCode");
        if (vnp_BankCode == null) {
            throw new Exception("Missing vnp_BankCode parameter");
        }

        String vnp_PayDate = responseParams.get("vnp_PayDate");
        if (vnp_PayDate == null) {
            throw new Exception("Missing vnp_PayDate parameter");
        }

        String vnp_OrderInfo = responseParams.get("vnp_OrderInfo");
        if (vnp_OrderInfo == null) {
            throw new Exception("Missing vnp_OrderInfo parameter");
        }

        String vnp_TransactionStatus = responseParams.get("vnp_TransactionStatus");
        if (vnp_TransactionStatus == null) {
            throw new Exception("Missing vnp_TransactionStatus parameter");
        }

        String originalData = "vnp_Amount=" + vnp_Amount +
                "&vnp_BankCode=" + vnp_BankCode +
                "&vnp_OrderInfo=" + vnp_OrderInfo +
                "&vnp_PayDate=" + vnp_PayDate +
                "&vnp_ResponseCode=" + vnp_ResponseCode +
                "&vnp_TmnCode=" + vnp_TmnCode +
                "&vnp_TransactionNo=" + vnp_TransactionNo +
                "&vnp_TransactionStatus=" + vnp_TransactionStatus +
                "&vnp_TxnRef=" + vnp_TxnRef +
                "&" + vnp_HashSecret;

        String expectedSignature = DigestUtils.sha512Hex(originalData);

        return vnp_SecureHash.equals(expectedSignature);
    }
	public static String hmacSHA512(final String key, final String data) {
		try {

			if (key == null || data == null) {
				throw new NullPointerException();
			}
			final Mac hmac512 = Mac.getInstance("HmacSHA512");
			byte[] hmacKeyBytes = key.getBytes();
			final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
			hmac512.init(secretKey);
			byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
			byte[] result = hmac512.doFinal(dataBytes);
			StringBuilder sb = new StringBuilder(2 * result.length);
			for (byte b : result) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();

		} catch (Exception ex) {
			return "";
		}
	}
	public String generateOrderId() {
	    UUID uuid = UUID.randomUUID();
	    return uuid.toString();
	  }
}
