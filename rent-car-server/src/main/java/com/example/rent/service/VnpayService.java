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
		String vnp_OrderInfo = "Thanh toan don hang: " + paymentDTO.getPayment_id();
		String vnp_TxnRef = vnp_TmnCode + "-" + paymentDTO.getPayment_id() + "-" + vnp_CreateDate;
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
