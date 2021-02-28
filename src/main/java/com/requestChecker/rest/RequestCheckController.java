package com.requestChecker.rest;

import com.requestChecker.beans.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/request")
public class RequestCheckController {
    @Autowired
    HttpServletRequest servletRequest;
    @Autowired
    Request request;

    ArrayList<Request> requests = new ArrayList<>();

    @RequestMapping("/call")
    public void callback(HttpServletRequest requestFromServer) throws IOException {
        request = new Request();
        request.setTimestamp(System.currentTimeMillis());
        request.setProtocol(requestFromServer.getProtocol());
        request.setRequestType(requestFromServer.getMethod());
        request.setRemoteAddress(getClientIpAddress(requestFromServer));
        request.setHash(requestFromServer.getHeader("hash"));
        request.setRequestBody(requestFromServer.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        requests.add(0, request);
    }

    public String getClientIpAddress(HttpServletRequest requestFromServer) {
        String xForwardedForHeader = servletRequest.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return requestFromServer.getRemoteAddr();
        } else {
            return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
        }
    }

    @GetMapping("/getresult")
    public ResponseEntity<?> getResult() {
        if (request.toString().isEmpty()) {
            return new ResponseEntity<String>("Nothing to show", HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(request, HttpStatus.OK);
        }
    }

    @GetMapping("/getallresults")
    public ResponseEntity<?> getAllResults() {
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/clear")
    public ResponseEntity<?> clear() {
        request = new Request();
        requests.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/gettoken")
    public ResponseEntity<?> generateToken(@RequestBody String data) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            result = bytesToHex(md.digest());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
